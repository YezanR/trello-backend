package com.yezan.trello.repository;

import com.yezan.trello.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    Task getOneById(int id);
    @Query("select max(t.rank) from Task t where t.group.id = ?1")
    Optional<Integer> findMaxRankByGroupId(int groupId);
}
