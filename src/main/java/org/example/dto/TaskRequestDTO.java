package org.example.dto;

import jakarta.validation.constraints.NotBlank;

public class TaskRequestDTO {

    @NotBlank(message = "El título es obligatorio")
    private String title;

    private String description;
    private String category;
    private String status;

    public TaskRequestDTO() {}

    public String getTitle()               { return title; }
    public void setTitle(String title)     { this.title = title; }

    public String getDescription()         { return description; }
    public void setDescription(String description)     { this.description = description; }

    public String getCategory()                    { return category; }
    public void setCategory(String category)       { this.category = category; }

    public String getStatus()                  { return status; }
    public void setStatus(String status)       { this.status = status; }
}