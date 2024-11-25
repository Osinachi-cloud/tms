package com.maliroso.tms.service;

import com.maliroso.tms.model.dto.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    TaskDto createTask(TaskDto taskDto);
    TaskDto updateTask(UUID id, TaskDto taskDto);
    Page<TaskDto> getTasks(Pageable pageable);
    TaskDto getTaskById(UUID id);
    void deleteTaskById(UUID id);






}
