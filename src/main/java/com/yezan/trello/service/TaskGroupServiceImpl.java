package com.yezan.trello.service;

import com.yezan.trello.entity.Task;
import com.yezan.trello.entity.TaskGroup;
import com.yezan.trello.repository.TaskGroupRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskGroupServiceImpl implements TaskGroupService {

    private final TaskGroupRepository taskGroupRepository;
    private final TaskService taskService;

    public TaskGroupServiceImpl(TaskGroupRepository repository, TaskService taskService) {
        this.taskGroupRepository = repository;
        this.taskService = taskService;
    }

    @Override
    public TaskGroup create(TaskGroup taskGroup) {
        return this.taskGroupRepository.save(taskGroup);
    }

    @Override
    public TaskGroup update(int id, TaskGroup taskGroup) {
        TaskGroup toUpdateGroup = this.taskGroupRepository.getOneById(id);
        toUpdateGroup.setTitle(taskGroup.getTitle());
        return this.taskGroupRepository.save(toUpdateGroup);
    }

    @Override
    public void delete(int id) {
        this.taskGroupRepository.deleteById(id);
    }

    @Override
    public Task addTask(int groupId, Task task) {
        TaskGroup group = this.taskGroupRepository.getOneById(groupId);
        task.setGroup(group);
        return this.taskService.create(task);
    }
}
