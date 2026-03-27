package org.example.service;

import org.example.dto.PagedResponseDTO;
import org.example.dto.TaskRequestDTO;
import org.example.dto.TaskResponseDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.model.Task;
import org.example.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public PagedResponseDTO<TaskResponseDTO> getAll(int page, int limit,
                                                    String sortBy, String order,
                                                    String category) {
        Sort sort = order.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page - 1, limit, sort);

        Page<Task> result = (category != null && !category.isEmpty())
                ? taskRepository.findByCategory(category, pageable)
                : taskRepository.findAll(pageable);

        List<TaskResponseDTO> data = result.getContent()
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

    public TaskResponseDTO create(TaskRequestDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCategory(dto.getCategory());
        task.setStatus(dto.getStatus());
        return toDTO(taskRepository.save(task));
    }

    public TaskResponseDTO getById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
        return toDTO(task);
    }

    public TaskResponseDTO update(Long id, TaskRequestDTO dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCategory(dto.getCategory());
        task.setStatus(dto.getStatus());
        return toDTO(taskRepository.save(task));
    }

    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found: " + id);
        }
        taskRepository.deleteById(id);
    }

    private TaskResponseDTO toDTO(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCategory(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }
}