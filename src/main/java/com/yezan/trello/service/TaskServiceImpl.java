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
        Task existingTask = this.getById(id);
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

    @Override
    public Task getById(int id) {
        return this.taskRepository.getOneById(id);
    }

    @Override
    public Task moveToGroup(int taskId, TaskGroup group) {
        Task task = this.getById(taskId);
        task.setGroup(group);
        return this.taskRepository.save(task);
    }

    @Override
    public Task updateRank(int taskId, int newRank) {
        Task task = this.getById(taskId);

        Optional<Task> possibleTaskWithRank = taskRepository.findByRankAndGroup(newRank, task.getGroup());
        possibleTaskWithRank.ifPresent((theTask) -> {
            theTask.setRank(task.getRank());
            this.taskRepository.save(theTask);
        });

        task.setRank(newRank);
        return this.taskRepository.save(task);
    }
}
