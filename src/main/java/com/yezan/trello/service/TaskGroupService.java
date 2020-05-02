package com.yezan.trello.service;

import com.yezan.trello.entity.Task;
import com.yezan.trello.entity.TaskGroup;

public interface TaskGroupService {
    TaskGroup create(TaskGroup taskGroup);
    TaskGroup update(int id, TaskGroup taskGroup);
    void delete(int id);
    Task addTask(int groupId, Task task);
    Task moveTask(int taskId, int toGroupId);
}
