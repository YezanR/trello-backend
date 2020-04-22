package com.yezan.trello.service;

import com.yezan.trello.entity.TaskGroup;
import com.yezan.trello.repository.TaskGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskGroupRepository taskGroupRepository;

    public TaskServiceImpl(TaskGroupRepository taskGroupRepository) {
        this.taskGroupRepository = taskGroupRepository;
    }

    @Override
    public List<TaskGroup> findAllGrouped(int boardId) {
        return this.taskGroupRepository.findAllByBoardId(boardId);
    }
}
