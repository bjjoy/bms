package cn.bjjoy.bms.setting.service.impl;

import cn.bjjoy.bms.setting.dao.UserMapper;
import cn.bjjoy.bms.setting.dao.UserRoleMapper;
import cn.bjjoy.bms.setting.dto.UserDto;
import cn.bjjoy.bms.setting.entity.User;
import cn.bjjoy.bms.setting.entity.UserRole;
import cn.bjjoy.bms.setting.service.UserService;
import cn.bjjoy.bms.util.DataUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by bjjoy on 2017/11/02.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public int insert(UserDto userDto){

        //新建用户
        User user = DataUtils.getData(userDto, User.class);
        userMapper.insertUser(user);
        if(userDto.getRoleIdList() != null && userDto.getRoleIdList().size() > 0) {

            //添加用户角色关系
            for(Integer roleId : userDto.getRoleIdList()){
                UserRole userRole = new UserRole(userDto.getId(), roleId);
                userRoleMapper.insert(userRole);
            }
        }
        return user.getId();
    }

    @Override
    public int update(User user){
        return userMapper.updateById(user);
    }

    @Override
    public List<Map> getList(Map param){
        return DataUtils.getDataArray(userMapper.getList(param), Map.class);
    }

    @Override
    public Integer getCount(Map param){
        return userMapper.getCount(param);
    }

    @Override
    public User getUserDetail(Integer id){
        return userMapper.getById(id);
    }

    @Override
    public int updateUser(UserDto userDto){

        //1.更新用户
        User user = DataUtils.getData(userDto, User.class);
        int count = userMapper.updateById(user);
        if(userDto.getRoleIdList() != null && userDto.getRoleIdList().size() > 0) {

            //2.删除用户角色关系
            count += userRoleMapper.deleteByUserId(user.getId());

            //3.添加用户角色关系
            for(Integer roleId : userDto.getRoleIdList()){
                UserRole userRole = new UserRole(userDto.getId(), roleId);
                count += userRoleMapper.insert(userRole);
            }
        }
        return count;
    }

    @Override
    public void deleteUser(User user){

        //更新del_flag = 1
        userMapper.updateById(user);
        //删除用户角色关系
        userRoleMapper.deleteByUserId(user.getId());
    }

    /**
     * 初始化角色id
     * @param param
     */
    private List<String> getRoleIdList(Map param){
        if(null == param.get("roleIds") || StringUtils.isBlank(param.get("roleIds").toString())) {
            return new ArrayList<>();
        }
        String roleIds = param.get("roleIds").toString();
        return Arrays.asList(roleIds.split("\\$\\$"));
    }

    @Override
    public User findUserByName(String loginName){
        return this.userMapper.findUserByName(loginName);
    }
}
