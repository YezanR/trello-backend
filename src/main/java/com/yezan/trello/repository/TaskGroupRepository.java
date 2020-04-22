package com.yezan.trello.repository;

import com.yezan.trello.entity.TaskGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskGroupRepository extends CrudRepository<TaskGroup, Integer> {
    List<TaskGroup> findAllByBoardId(int boardId);
}
