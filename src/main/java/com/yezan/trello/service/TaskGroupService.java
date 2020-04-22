package com.yezan.trello.service;

import com.yezan.trello.entity.TaskGroup;

public interface TaskGroupService {
    TaskGroup create(TaskGroup taskGroup);
    TaskGroup update(int id, TaskGroup taskGroup);
    void delete(int id);
}
