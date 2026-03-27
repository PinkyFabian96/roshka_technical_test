package org.example.service;

import org.example.dto.PagedResponseDTO;
import org.example.dto.SubtaskRequestDTO;
import org.example.dto.SubtaskResponseDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.model.Subtask;
import org.example.model.Task;
import org.example.repository.SubtaskRepository;
import org.example.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubtaskService {

    private final SubtaskRepository subtaskRepository;
    private final TaskRepository taskRepository;

    public SubtaskService(SubtaskRepository subtaskRepository, TaskRepository taskRepository) {
        this.subtaskRepository = subtaskRepository;
        this.taskRepository = taskRepository;
    }

    public PagedResponseDTO<SubtaskResponseDTO> getAll(Long taskId, int page, int limit,
                                                       String sortBy, String order) {
        if (!taskRepository.existsById(taskId)) {
            throw new ResourceNotFoundException("Task not found: " + taskId);
        }

        Sort sort = order.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Subtask> result = subtaskRepository.findByTaskId(taskId, pageable);

        List<SubtaskResponseDTO> data = result.getContent()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        PagedResponseDTO.Meta meta = new PagedResponseDTO.Meta(
                result.getTotalElements(),
                page,
                result.getTotalPages()
        );

        return new PagedResponseDTO<>(data, meta);
    }

    public SubtaskResponseDTO create(Long taskId, SubtaskRequestDTO dto) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + taskId));

        Subtask subtask = new Subtask();
        subtask.setTitle(dto.getTitle());
        subtask.setDescription(dto.getDescription());
        subtask.setStatus(dto.getStatus());
        subtask.setTask(task);

        return toDTO(subtaskRepository.save(subtask));
    }

    public SubtaskResponseDTO getById(Long taskId, Long subtaskId) {
        Subtask subtask = subtaskRepository.findByIdAndTaskId(subtaskId, taskId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Subtask not found: " + subtaskId + " for task: " + taskId));
        return toDTO(subtask);
    }

    public SubtaskResponseDTO update(Long taskId, Long subtaskId, SubtaskRequestDTO dto) {
        Subtask subtask = subtaskRepository.findByIdAndTaskId(subtaskId, taskId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Subtask not found: " + subtaskId + " for task: " + taskId));

        subtask.setTitle(dto.getTitle());
        subtask.setDescription(dto.getDescription());
        subtask.setStatus(dto.getStatus());

        return toDTO(subtaskRepository.save(subtask));
    }

    public void delete(Long taskId, Long subtaskId) {
        Subtask subtask = subtaskRepository.findByIdAndTaskId(subtaskId, taskId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Subtask not found: " + subtaskId + " for task: " + taskId));
        subtaskRepository.delete(subtask);
    }

    private SubtaskResponseDTO toDTO(Subtask subtask) {
        return new SubtaskResponseDTO(
                subtask.getId(),
                subtask.getTitle(),
                subtask.getDescription(),
                subtask.getStatus(),
                subtask.getTask().getId(),
                subtask.getCreatedAt(),
                subtask.getUpdatedAt()
        );
    }
}