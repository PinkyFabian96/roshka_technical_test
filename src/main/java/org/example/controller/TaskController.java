package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.PagedResponseDTO;
import org.example.dto.TaskRequestDTO;
import org.example.dto.TaskResponseDTO;
import org.example.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {


    // Spring detecta automáticamente el constructor y hace la inyección
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    private final TaskService taskService;
    @GetMapping
    public ResponseEntity<PagedResponseDTO<TaskResponseDTO>> getAll(
            @RequestParam(defaultValue = "1")     int page,
            @RequestParam(defaultValue = "10")    int limit,
            @RequestParam(defaultValue = "id")    String sortBy,
            @RequestParam(defaultValue = "asc")   String order,
            @RequestParam(required = false)       String category) {
        return ResponseEntity.ok(taskService.getAll(page, limit, sortBy, order, category));
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(@Valid @RequestBody TaskRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> update(@PathVariable Long id,
                                                  @Valid @RequestBody TaskRequestDTO dto) {
        return ResponseEntity.ok(taskService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
