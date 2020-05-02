package com.yezan.trello.service;

import com.yezan.trello.entity.Task;
import com.yezan.trello.entity.TaskGroup;
import com.yezan.trello.repository.TaskGroupRepository;
import com.yezan.trello.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskGroupRepository taskGroupRepository;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(
            TaskGroupRepository taskGroupRepository,
            TaskRepository taskRepository
    ) {
        this.taskGroupRepository = taskGroupRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskGroup> findAllGrouped(int boardId) {
        return this.taskGroupRepository.findAllByBoardId(boardId);
    }

    @Override
    public Task create(Task task) {
        Task newTask = new Task();
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setGroup(task.getGroup());
        int nextRank = this.getNextAvailableRank(task.getGroupId());
        newTask.setRank(nextRank);
        return this.taskRepository.save(newTask);
    }

    public void delete(int id) {
        this.taskRepository.deleteById(id);
    }

    @Override
    public Task update(int id, Task task) {
        Task existingTask = this.taskRepository.getOneById(id);
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        return this.taskRepository.save(existingTask);
    }

    @Override
    public int getNextAvailableRank(int groupId) {
        Optional<Integer> result = this.taskRepository.findMaxRankByGroupId(groupId);
        int maxRank = result.orElse(0);
        return maxRank + 1;
    }
}
