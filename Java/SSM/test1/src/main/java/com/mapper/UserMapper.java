package com.mapper;

import com.pojo.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;


public interface UserMapper {
    List<User> getAllUser();
    List<Map<String, Object>> getAllUserInMapUsingList();
    @MapKey("id")
    Map<String, Object> getAllUserInMapUsingMap();
}
