package cn.bjjoy.bms.setting.service;

import cn.bjjoy.bms.setting.dto.UserDto;
import cn.bjjoy.bms.setting.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by bjjoy on 2017/11/02.
 */
public interface UserService {

    /**
     * 创建用户
     */
    int insert(UserDto userDto);

    /**
     * 更新用户
     */
    int update(User user);

    /**
     * 获取用户列表
     */
    List<Map> getList(Map param);

    /**
     * 获取用户数量
     */
    Integer getCount(Map param);

    /**
     * 获取用户详情
     */
    User getUserDetail(Integer id);

    /**
     * 更新用户相关信息
     */
    int updateUser(UserDto userDto);

    /**
     * 删除用户相关信息（del_flag更新为1）
     */
    void deleteUser(User user);

    /**
     * 根据用户名查找用户
     */
    User findUserByName(String loginName);
}
