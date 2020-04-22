package com.yezan.trello.service;

import com.yezan.trello.repository.TaskGroupRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskGroupServiceImpl implements TaskGroupService {

    private final TaskGroupRepository repository;

    public TaskGroupServiceImpl(TaskGroupRepository repository) {
        this.repository = repository;
    }
}
