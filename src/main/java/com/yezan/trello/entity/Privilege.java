package com.yezan.trello.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "privileges")
public class Privilege extends BaseEntity {
    @Id
    private String name;

    public Privilege(String name) {
        this.setName(name);
    }

    protected Privilege() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
