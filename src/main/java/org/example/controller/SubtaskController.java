package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.PagedResponseDTO;
import org.example.dto.SubtaskRequestDTO;
import org.example.dto.SubtaskResponseDTO;
import org.example.service.SubtaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks/{taskId}/subtasks")
public class SubtaskController {

    private final SubtaskService subtaskService;

    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }

    @GetMapping
    public ResponseEntity<PagedResponseDTO<SubtaskResponseDTO>> getAll(
            @PathVariable Long taskId,
            @RequestParam(defaultValue = "1")   int page,
            @RequestParam(defaultValue = "10")  int limit,
            @RequestParam(defaultValue = "id")  String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        return ResponseEntity.ok(subtaskService.getAll(taskId, page, limit, sortBy, order));
    }

    @PostMapping
    public ResponseEntity<SubtaskResponseDTO> create(
            @PathVariable Long taskId,
            @Valid @RequestBody SubtaskRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subtaskService.create(taskId, dto));
    }

    @GetMapping("/{subtaskId}")
    public ResponseEntity<SubtaskResponseDTO> getById(
            @PathVariable Long taskId,
            @PathVariable Long subtaskId) {
        return ResponseEntity.ok(subtaskService.getById(taskId, subtaskId));
    }

    @PutMapping("/{subtaskId}")
    public ResponseEntity<SubtaskResponseDTO> update(
            @PathVariable Long taskId,
            @PathVariable Long subtaskId,
            @Valid @RequestBody SubtaskRequestDTO dto) {
        return ResponseEntity.ok(subtaskService.update(taskId, subtaskId, dto));
    }

    @DeleteMapping("/{subtaskId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long taskId,
            @PathVariable Long subtaskId) {
        subtaskService.delete(taskId, subtaskId);
        return ResponseEntity.noContent().build();
    }
}