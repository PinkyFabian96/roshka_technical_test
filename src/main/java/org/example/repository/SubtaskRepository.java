package org.example.repository;

import org.example.model.Subtask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
    Page<Subtask> findByTaskId(Long taskId, Pageable pageable);
    Optional<Subtask> findByIdAndTaskId(Long id, Long taskId);
}