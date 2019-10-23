package com.exemple.serverRefresh.redis;

import java.util.Map;

public interface UserRepository  {
    void save(String string);

    Map<String,String> findAll();
    String findById(String id);
    void update(String string);
    void delete(String id);
}
