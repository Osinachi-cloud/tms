package com.maliroso.tms.controller;

import com.maliroso.tms.exceptions.TmsException;
import com.maliroso.tms.model.dto.HttpResponse;
import com.maliroso.tms.model.dto.TaskDto;
import com.maliroso.tms.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<HttpResponse> createTask(@RequestBody TaskDto taskDto){
        TaskDto savedTask = taskService.createTask(taskDto);
        Map<String, TaskDto> taskDtoMap = new HashMap<>();
        taskDtoMap.put("task", savedTask);
        HttpResponse httpResponse = HttpResponse.builder()
                .message("Task Created")
                .data(taskDtoMap)
                .status(HttpStatus.CREATED)
                .statusCode(201)
                .build();

        return ResponseEntity.ok(httpResponse);
    }

    @GetMapping("/tasks")
    public ResponseEntity<HttpResponse> getTasks(@RequestParam Optional<Integer> page,
                                                 @RequestParam Optional<Integer> size){
        Pageable pageable = PageRequest.of(page.orElse(0), size.orElse(10));
        Page<TaskDto> taskDto = taskService.getTasks(pageable);
        Map<String, Page<TaskDto>> taskDtoMap = new HashMap<>();
        taskDtoMap.put("task", taskDto);
        HttpResponse httpResponse = HttpResponse.builder()
                .message("task list retrieved")
                .data(taskDtoMap)
                .status(HttpStatus.OK)
                .statusCode(200)
                .build();

        return ResponseEntity.ok(httpResponse);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<HttpResponse> findTaskById(@PathVariable UUID id){

        TaskDto taskDto = taskService.getTaskById(id);
        Map<String, TaskDto> taskDtoMap = new HashMap<>();
        taskDtoMap.put("task", taskDto);
        HttpResponse httpResponse = HttpResponse.builder()
                .message("task retrieved")
                .data(taskDtoMap)
                .status(HttpStatus.OK)
                .statusCode(200)
                .build();

        return ResponseEntity.ok(httpResponse);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<HttpResponse> upDateTask(@RequestBody TaskDto taskDto, @PathVariable  UUID id){
        TaskDto savedTask = taskService.updateTask(id,taskDto);
        Map<String, TaskDto> taskDtoMap = new HashMap<>();
        taskDtoMap.put("task", savedTask);
        HttpResponse httpResponse = HttpResponse.builder()
                .message("Task Updated")
                .data(taskDtoMap)
                .status(HttpStatus.CREATED)
                .statusCode(201)
                .build();

        return ResponseEntity.ok(httpResponse);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable  UUID id){
        try{
            taskService.deleteTaskById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            throw new TmsException("An error occurred");
        }
    }
}
