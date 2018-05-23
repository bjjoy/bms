package cn.bjjoy.bms.setting.dao;

import cn.bjjoy.bms.setting.dto.UserRoleDto;
import cn.bjjoy.bms.setting.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by bjjoy on 2017/9/22.
 */
@Mapper
@Repository
public interface UserRoleMapper {

    int insert(UserRole userRole);

    int update(Map param);

    int deleteByUserId(Integer userId);

    /**
     * 根据用户id获取user_role信息
     */
    List<UserRoleDto> getUserRoleList(@Param("userIdList") List<Integer> userIdList);

    /**
     * 获取可用用户对应角色数量
     */
    int getUserRoleCountByRoleId(@Param("roleId") Integer roleId);

}
