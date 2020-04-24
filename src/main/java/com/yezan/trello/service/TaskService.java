package com.yezan.trello.service;

import com.yezan.trello.entity.Task;
import com.yezan.trello.entity.TaskGroup;

import java.util.List;

public interface TaskService {
    List<TaskGroup> findAllGrouped(int boardId);
    Task create(Task task);
}