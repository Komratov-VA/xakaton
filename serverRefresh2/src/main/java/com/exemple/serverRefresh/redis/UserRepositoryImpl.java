package com.exemple.serverRefresh.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Properties;

@Repository
@Component
public class UserRepositoryImpl implements UserRepository {


    private RedisTemplate<String, String> redisTemplate;
    private HashOperations hashOperations;

    public UserRepositoryImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(String string) {
        hashOperations.put("STR",string,string);
    }

    @Override
    public Map<String,String> findAll() {
        return hashOperations.entries("STR");
    }

    @Override
    public String findById(String id) {
        return (String) hashOperations.get("STR", id);
    }

    @Override
    public void update(String user) {
        save(user);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete("STR",id);
    }

    @Override
    public Boolean putif(String id) {
        return hashOperations.putIfAbsent("STR",id,id);

    }
}
//разные варианты тестирования.
//ширина канала и пропускная способность.
// реальное время.
//
