package com.yezan.trello.service;

import com.yezan.trello.entity.TaskGroup;
import com.yezan.trello.repository.TaskGroupRepository;
import com.yezan.trello.repository.TaskRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TaskGroupServiceImpl implements TaskGroupService {

    private final TaskGroupRepository taskGroupRepository;
    private final TaskRepository taskRepository;

    public TaskGroupServiceImpl(TaskGroupRepository repository, TaskRepository taskRepository) {
        this.taskGroupRepository = repository;
        this.taskRepository = taskRepository;
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
}
