package com.yezan.trello.webService;

import com.yezan.trello.entity.TaskGroup;
import com.yezan.trello.service.TaskGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api/taskGroups")
public class TaskGroupController {

    @Autowired
    private TaskGroupService service;

    @PostMapping
    public ResponseEntity<TaskGroup> create(@RequestBody TaskGroup taskGroup) {
        TaskGroup newGroup = this.service.create(taskGroup);
        return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskGroup> update(@PathVariable int id, @RequestBody TaskGroup taskGroup) {
        try {
            TaskGroup updatedGroup = this.service.update(id, taskGroup);
            return ResponseEntity.ok(updatedGroup);
        }
        catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        try {
            this.service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
