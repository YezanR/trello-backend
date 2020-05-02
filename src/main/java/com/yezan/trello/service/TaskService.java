package com.yezan.trello.service;

import com.yezan.trello.entity.Task;
import com.yezan.trello.entity.TaskGroup;

import java.util.List;

public interface TaskService {
    List<TaskGroup> findAllGrouped(int boardId);
    Task getById(int id);
    Task create(Task task);
    void delete(int id);
    Task update(int id, Task task);
    int getNextAvailableRank(int groupId);
    Task moveToGroup(int taskId, TaskGroup group);
    Task updateRank(int taskId, int newRank);
}
