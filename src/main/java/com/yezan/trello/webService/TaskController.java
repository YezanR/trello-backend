package com.yezan.trello.webService;

import com.yezan.trello.entity.Task;
import com.yezan.trello.entity.TaskGroup;
import com.yezan.trello.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("api")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("boards/{boardId}/tasks")
    public List<TaskGroup> findAll(@PathVariable int boardId) {
        return this.taskService.findAllGrouped(boardId);
    }

    @DeleteMapping("tasks/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        try {
            this.taskService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("tasks/{id}")
    public ResponseEntity<Task> update(@PathVariable int id, @RequestBody Task task) {
        try {
            Task updatedTask = this.taskService.update(id, task);
            return ResponseEntity.ok(updatedTask);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
