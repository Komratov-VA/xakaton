package com.primer.redis;

import java.io.Serializable;

public class User implements Serializable
{
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public User(String id, String name, Long salary) {
    this.id = id;
    this.name = name;
    this.salary = salary;
}
//    public User() {
//    }

    private String id;
    private String name;
    private Long salary;


}
