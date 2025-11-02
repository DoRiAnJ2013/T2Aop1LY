// 代码生成时间: 2025-11-03 02:23:41
package com.example.projectmanagement;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.exceptions.HttpStatusException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@Controller("/projects")
public class ProjectManagementTool {

    private final ProjectService projectService;

    public ProjectManagementTool(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * Retrieves a list of all projects.
     *
     * @return HttpResponse containing a list of projects.
     */
    @Get("/")
    public HttpResponse<List<Project>> getAllProjects() {
        List<Project> projects = projectService.findAll();
        return HttpResponse.ok(projects);
    }

    /**
     * Retrieves a specific project by ID.
     *
     * @param id The ID of the project to retrieve.
     * @return HttpResponse containing the project.
     */
    @Get("/{id}")
    public HttpResponse<Project> getProjectById(@PathVariable Long id) {
        Optional<Project> project = projectService.findById(id);
        if (project.isPresent()) {
            return HttpResponse.ok(project.get());
        } else {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
    }
}

/**
 * Project.java - A model representing a project.
 */
class Project {
    private Long id;
    private String name;
    private String description;

    // Standard getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

/**
 * ProjectService.java - A service class for managing project data.
 */
interface ProjectService {
    List<Project> findAll();
    Optional<Project> findById(Long id);
}
