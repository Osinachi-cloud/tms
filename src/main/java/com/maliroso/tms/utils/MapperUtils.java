package com.maliroso.tms.utils;

import com.maliroso.tms.model.dto.TaskDto;
import com.maliroso.tms.model.entity.Task;
import com.maliroso.tms.model.enums.Status;

import java.util.List;

public class MapperUtils {

    public static TaskDto mapTaskToDto(Task task){
        TaskDto taskDto = new TaskDto();

        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatus(task.getStatus().toString());

        return taskDto;
    }

    public static Task mapDtoToTask(TaskDto taskDto){
        Task task = new Task();

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(Status.valueOf(taskDto.getStatus()));

        return task;
    }

    public static List<TaskDto> mapTaskListToDto(List<Task> taskList) {
        return taskList.stream()
                .map(MapperUtils::mapTaskToDto)
                .toList();
    }

}
