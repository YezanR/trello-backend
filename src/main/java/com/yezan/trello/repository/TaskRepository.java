package com.yezan.trello.repository;

import com.yezan.trello.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    Task getOneById(int id);
}
