package com.yezan.trello.repository;

import com.yezan.trello.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    void deleteAllByGroupId(int groupId);
}
