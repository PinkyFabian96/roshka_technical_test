package org.example.dto;

import jakarta.validation.constraints.NotBlank;

public class SubtaskRequestDTO {

    @NotBlank(message = "El título es obligatorio")
    private String title;

    private String description;
    private String status;

    public SubtaskRequestDTO() {}

    public String getTitle()               { return title; }
    public void setTitle(String title)     { this.title = title; }

    public String getDescription()                     { return description; }
    public void setDescription(String description)     { this.description = description; }

    public String getStatus()                  { return status; }
    public void setStatus(String status)       { this.status = status; }
}
