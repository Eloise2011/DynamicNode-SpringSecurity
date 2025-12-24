package com.example.security08loginapi.mapper;

import com.example.security08loginapi.entity.TPermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPermission record);

    int insertSelective(TPermission record);

    TPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPermission record);

    int updateByPrimaryKey(TPermission record);

    List<TPermission> selectByUserId(Integer userId);
}