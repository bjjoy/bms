package cn.bjjoy.bms.setting.service;

import cn.bjjoy.bms.setting.dto.UserRoleDto;
import cn.bjjoy.bms.setting.entity.UserRole;

import java.util.List;
import java.util.Map;

/**
 * Created by guoxiaoming on 2017/9/22.
 */
public interface UserRoleService {

    /**
     * 新建用户角色关系
     */
    int insert(UserRole userRole);

    /**
     * 更新用户角色关系
     */
    int update(Map param);

    /**
     * 删除用户角色关系
     */
    int delete(Integer userId);

    /**
     * 根据用户uuid获取user_role信息
     */
    List<UserRoleDto> getUserRoleList(List<Integer> userIdList);
}
