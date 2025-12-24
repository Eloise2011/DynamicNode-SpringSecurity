package com.example.security07loginresourcepermission.mapper;

import com.example.security07loginresourcepermission.entity.TRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRole record);

    int insertSelective(TRole record);

    TRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TRole record);

    int updateByPrimaryKey(TRole record);

    List<TRole> selectByUserId(Integer userId);
}