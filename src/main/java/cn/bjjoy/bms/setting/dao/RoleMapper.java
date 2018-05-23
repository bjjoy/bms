package cn.bjjoy.bms.setting.dao;

import cn.bjjoy.bms.setting.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface RoleMapper {
    int deleteById(Integer id);

    int insertRole(Role record);

    Role getById(Integer id);

    int updateById(Role record);

    List<Role> getList();

    /**
     * 根据用户id获取角色列表
     */
    List<Role> getListByUserId(Integer userId);

    int getCount(Map param);
}