package cn.bjjoy.bms.setting.dao;

import cn.bjjoy.bms.setting.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {
    int deleteByUuid(String uuid);

    int insertUser(User user);

    User getById(Integer id);

    int updateById(User user);

    List<User> getList(Map param);

    Integer getCount(Map param);

    User findUserByName(String loginName);
}