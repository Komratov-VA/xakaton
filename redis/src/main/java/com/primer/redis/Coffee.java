package com.primer.redis;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Coffee {
    private String id;
    private String name;

    public Coffee() {
    }

    public Coffee(String toString, String name) {
        this.id=toString;
        this.name=name;
    }

    public String getId() {
        return id;
    }
}