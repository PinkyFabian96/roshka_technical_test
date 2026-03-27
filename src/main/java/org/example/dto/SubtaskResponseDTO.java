package org.example.dto;

import java.time.LocalDateTime;

public class SubtaskResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String status;
    private Long taskId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SubtaskResponseDTO() {}

    public SubtaskResponseDTO(Long id, String title, String description, String status,
                              Long taskId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.taskId = taskId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId()                    { return id; }
    public void setId(Long id)             { this.id = id; }

    public String getTitle()               { return title; }
    public void setTitle(String title)     { this.title = title; }

    public String getDescription()                     { return description; }
    public void setDescription(String description)     { this.description = description; }

    public String getStatus()                  { return status; }
    public void setStatus(String status)       { this.status = status; }

    public Long getTaskId()                    { return taskId; }
    public void setTaskId(Long taskId)         { this.taskId = taskId; }

    public LocalDateTime getCreatedAt()                        { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt)          { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt()                        { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt)          { this.updatedAt = updatedAt; }
}