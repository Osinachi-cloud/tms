package com.maliroso.tms.service.impl;

import com.maliroso.tms.exceptions.TmsException;
import com.maliroso.tms.model.dto.TaskDto;
import com.maliroso.tms.model.entity.Task;
import com.maliroso.tms.model.enums.Status;
import com.maliroso.tms.repository.TaskRepository;
import com.maliroso.tms.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.maliroso.tms.utils.MapperUtils.*;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // creates new task
    @Override
    public TaskDto createTask(TaskDto taskDto) {
            Task task = mapDtoToTask(taskDto);
            Task savedTask = taskRepository.save(task);
            return mapTaskToDto(savedTask);
    }

    // updates a task
    @Override
    public TaskDto updateTask(UUID id, TaskDto taskDto) {
        Task task = getATaskById(id);
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(Status.valueOf(taskDto.getStatus()));

        Task savedTask = taskRepository.save(task);
        return mapTaskToDto(savedTask);
    }

    // Get all Tasks
    @Override
    public Page<TaskDto> getTasks(Pageable pageable) {
        try{
            Page<Task> taskPage = taskRepository.findAll(pageable);
            List<TaskDto> taskDtoList = mapTaskListToDto(taskPage.getContent());
            return new PageImpl<>(taskDtoList, pageable, taskRepository.count());
        } catch (Exception e){
            throw new TmsException("Error in fetching task records");
        }
    }

    // finds a task by id
    @Override
    public TaskDto getTaskById(UUID id) {
        Task task = getATaskById(id);
           return mapTaskToDto(task);
    }

    private Task getATaskById(UUID id) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if(existingTask.isEmpty()){
            throw new TmsException("Task does not exist");
        } else {
            return existingTask.get();
        }
    }

    // deletes task by Id
    @Override
    public void deleteTaskById(UUID id) {
        boolean taskExists = taskRepository.existsById(id);
        if (taskExists){
            System.out.println("start" + id);
            taskRepository.deleteById(id);
            System.out.println("end" +id);

        } else {
            throw new TmsException("Task does not exist");
        }
    }
}
