package com.maliroso.tms.service.impl;

import com.maliroso.tms.exceptions.TmsException;
import com.maliroso.tms.model.dto.TaskDto;
import com.maliroso.tms.model.entity.Task;
import com.maliroso.tms.model.enums.Status;
import com.maliroso.tms.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository mockTaskRepository;

    private TaskServiceImpl taskServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        taskServiceImplUnderTest = new TaskServiceImpl(mockTaskRepository);
    }

    @Test
    void testCreateTask() {
        // Setup
        final TaskDto taskDto = new TaskDto();
        taskDto.setTitle("title");
        taskDto.setDescription("description");
        taskDto.setStatus("PENDING");

        final TaskDto expectedResult = new TaskDto();
        expectedResult.setTitle("title");
        expectedResult.setDescription("description");
        expectedResult.setStatus("PENDING");

        // Configure TaskRepository.save(...).
        final Task task = new Task();
        task.setTitle("title");
        task.setDescription("description");
        task.setStatus(Status.PENDING);
        when(mockTaskRepository.save(any(Task.class))).thenReturn(task);

        // Run the test
        final TaskDto result = taskServiceImplUnderTest.createTask(taskDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateTask() {
        // Setup
        final TaskDto taskDto = new TaskDto();
        taskDto.setTitle("new title");
        taskDto.setDescription("new description");
        taskDto.setStatus("COMPLETED");

        final TaskDto expectedResult = new TaskDto();
        expectedResult.setTitle("new title");
        expectedResult.setDescription("new description");
        expectedResult.setStatus("COMPLETED");

        final Task task1 = new Task();
        task1.setTitle("new title");
        task1.setDescription("new description");
        task1.setStatus(Status.COMPLETED);
        final Optional<Task> task = Optional.of(task1);
        when(mockTaskRepository.findById(UUID.fromString("f1817a13-ccbd-42dc-ad19-ec45c797d14a"))).thenReturn(task);

        // Configure TaskRepository.save(...).
        final Task task2 = new Task();
        task2.setTitle("new title");
        task2.setDescription("new description");
        task2.setStatus(Status.COMPLETED);
        when(mockTaskRepository.save(any(Task.class))).thenReturn(task2);

        // Run the test
        final TaskDto result = taskServiceImplUnderTest.updateTask(
                UUID.fromString("f1817a13-ccbd-42dc-ad19-ec45c797d14a"), taskDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateTask_TaskRepositoryFindByIdReturnsAbsent() {
        // Setup
        final TaskDto taskDto = new TaskDto();
        taskDto.setTitle("title");
        taskDto.setDescription("description");
        taskDto.setStatus("status");

        when(mockTaskRepository.findById(UUID.fromString("f1817a13-ccbd-42dc-ad19-ec45c797d14a")))
                .thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(
                () -> taskServiceImplUnderTest.updateTask(UUID.fromString("f1817a13-ccbd-42dc-ad19-ec45c797d14a"),
                        taskDto)).isInstanceOf(TmsException.class);
    }

    @Test
    void testGetTasks() {
        // Setup
        // Configure TaskRepository.findAll(...).
        final Task task = new Task();
        task.setTitle("title");
        task.setDescription("description");
        task.setStatus(Status.PENDING);
        final Page<Task> tasks = new PageImpl<>(List.of(task));
        when(mockTaskRepository.findAll(any(Pageable.class))).thenReturn(tasks);

        when(mockTaskRepository.count()).thenReturn(0L);

        // Run the test
        final Page<TaskDto> result = taskServiceImplUnderTest.getTasks(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    void testGetTasks_TaskRepositoryFindAllReturnsNoItems() {
        // Setup
        when(mockTaskRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
        when(mockTaskRepository.count()).thenReturn(0L);

        // Run the test
        final Page<TaskDto> result = taskServiceImplUnderTest.getTasks(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    void testGetTaskById() {
        // Setup
        final TaskDto expectedResult = new TaskDto();
        expectedResult.setTitle("title");
        expectedResult.setDescription("description");
        expectedResult.setStatus("PENDING");

        // Configure TaskRepository.findById(...).
        final Task task1 = new Task();
        task1.setTitle("title");
        task1.setDescription("description");
        task1.setStatus(Status.PENDING);
        final Optional<Task> task = Optional.of(task1);
        when(mockTaskRepository.findById(UUID.fromString("f1817a13-ccbd-42dc-ad19-ec45c797d14a"))).thenReturn(task);

        // Run the test
        final TaskDto result = taskServiceImplUnderTest.getTaskById(
                UUID.fromString("f1817a13-ccbd-42dc-ad19-ec45c797d14a"));

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTaskById_TaskRepositoryReturnsAbsent() {
        // Setup
        when(mockTaskRepository.findById(UUID.fromString("f1817a13-ccbd-42dc-ad19-ec45c797d14a")))
                .thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> taskServiceImplUnderTest.getTaskById(
                UUID.fromString("f1817a13-ccbd-42dc-ad19-ec45c797d14a"))).isInstanceOf(TmsException.class);
    }

    @Test
    void testDeleteTaskById() {
        // Setup
        when(mockTaskRepository.existsById(UUID.fromString("c6650e9a-8e54-4669-9a52-b1049649d6f3"))).thenReturn(true);

        // Run the test
        taskServiceImplUnderTest.deleteTaskById(UUID.fromString("c6650e9a-8e54-4669-9a52-b1049649d6f3"));

        // Verify the results
        verify(mockTaskRepository).deleteById(UUID.fromString("c6650e9a-8e54-4669-9a52-b1049649d6f3"));
    }

    @Test
    void testDeleteTaskById_TaskRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockTaskRepository.existsById(UUID.fromString("c6650e9a-8e54-4669-9a52-b1049649d6f3"))).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> taskServiceImplUnderTest.deleteTaskById(
                UUID.fromString("c6650e9a-8e54-4669-9a52-b1049649d6f3"))).isInstanceOf(TmsException.class);
    }
}
