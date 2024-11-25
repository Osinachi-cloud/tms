package com.maliroso.tms.service.impl;

import com.maliroso.tms.exceptions.TmsException;
import com.maliroso.tms.model.dto.TaskDto;
import com.maliroso.tms.model.entity.Task;
//import com.maliroso.tms.repository.TaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepository mockTaskRepository;

    private TaskServiceImpl taskServiceImplUnderTest;

    @Before
    public void setUp() {
        taskServiceImplUnderTest = new TaskServiceImpl(mockTaskRepository);
    }

    @Test
    public void testCreateTask() {
        // Setup
        final TaskDto taskDto = new TaskDto();

        final TaskDto expectedResult = new TaskDto();

        // Configure TaskRepository.save(...).
        final Task task = new Task();
        when(mockTaskRepository.save(any(Task.class))).thenReturn(task);

        // Run the test
        final TaskDto result = taskServiceImplUnderTest.createTask(taskDto);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testUpdateTask() {
        // Setup
        final TaskDto taskDto = new TaskDto();

        final TaskDto expectedResult = new TaskDto();

        // Configure TaskRepository.findById(...).
        final Task task1 = new Task();
        final Optional<Task> task = Optional.of(task1);
        when(mockTaskRepository.findById(UUID.fromString("6153d178-86d9-4420-a538-77c820714a7d"))).thenReturn(task);

        // Configure TaskRepository.save(...).
        final Task task2 = new Task();
        when(mockTaskRepository.save(any(Task.class))).thenReturn(task2);

        // Run the test
        final TaskDto result = taskServiceImplUnderTest.updateTask(
                UUID.fromString("6153d178-86d9-4420-a538-77c820714a7d"), taskDto);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test(expected = TmsException.class)
    public void testUpdateTask_TaskRepositoryFindByIdReturnsAbsent() {
        // Setup
        final TaskDto taskDto = new TaskDto();

        when(mockTaskRepository.findById(UUID.fromString("6153d178-86d9-4420-a538-77c820714a7d"))).thenReturn(
                Optional.empty());

        // Run the test
        taskServiceImplUnderTest.updateTask(UUID.fromString("6153d178-86d9-4420-a538-77c820714a7d"), taskDto);
    }

    @Test
    public void testGetTasks() {
        // Setup
        // Configure TaskRepository.findAll(...).
        final Task task = new Task();
        final Page<Task> tasks = new PageImpl<>(List.of(task));
        when(mockTaskRepository.findAll(any(Pageable.class))).thenReturn(tasks);

        when(mockTaskRepository.count()).thenReturn(0L);

        // Run the test
        final Page<TaskDto> result = taskServiceImplUnderTest.getTasks(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    public void testGetTasks_TaskRepositoryFindAllReturnsNoItems() {
        // Setup
        when(mockTaskRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
        when(mockTaskRepository.count()).thenReturn(0L);

        // Run the test
        final Page<TaskDto> result = taskServiceImplUnderTest.getTasks(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    public void testGetTaskById() {
        // Setup
        final TaskDto expectedResult = new TaskDto();

        // Configure TaskRepository.findById(...).
        final Task task1 = new Task();
        final Optional<Task> task = Optional.of(task1);
        when(mockTaskRepository.findById(UUID.fromString("6153d178-86d9-4420-a538-77c820714a7d"))).thenReturn(task);

        // Run the test
        final TaskDto result = taskServiceImplUnderTest.getTaskById(
                UUID.fromString("6153d178-86d9-4420-a538-77c820714a7d"));

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test(expected = TmsException.class)
    public void testGetTaskById_TaskRepositoryReturnsAbsent() {
        // Setup
        when(mockTaskRepository.findById(UUID.fromString("6153d178-86d9-4420-a538-77c820714a7d"))).thenReturn(
                Optional.empty());

        // Run the test
        taskServiceImplUnderTest.getTaskById(UUID.fromString("6153d178-86d9-4420-a538-77c820714a7d"));
    }

    @Test
    public void testDeleteTaskById() {
        // Setup
        when(mockTaskRepository.existsById(UUID.fromString("14ca5b8b-b7f8-4d7e-9192-9b02e491b39e"))).thenReturn(true);

        // Run the test
        taskServiceImplUnderTest.deleteTaskById(UUID.fromString("14ca5b8b-b7f8-4d7e-9192-9b02e491b39e"));

        // Verify the results
        verify(mockTaskRepository).deleteById(UUID.fromString("14ca5b8b-b7f8-4d7e-9192-9b02e491b39e"));
    }

    @Test(expected = TmsException.class)
    public void testDeleteTaskById_TaskRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockTaskRepository.existsById(UUID.fromString("14ca5b8b-b7f8-4d7e-9192-9b02e491b39e"))).thenReturn(false);

        // Run the test
        taskServiceImplUnderTest.deleteTaskById(UUID.fromString("14ca5b8b-b7f8-4d7e-9192-9b02e491b39e"));
    }
}
