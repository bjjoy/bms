package cn.bjjoy.bms.setting.service.impl;

import cn.bjjoy.bms.setting.dto.UserRoleDto;
import cn.bjjoy.bms.setting.dao.UserRoleMapper;
import cn.bjjoy.bms.setting.entity.UserRole;
import cn.bjjoy.bms.setting.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by bjjoy on 2017/11/02.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public int insert(UserRole userRole){
        return userRoleMapper.insert(userRole);
    }

    @Override
    public int update(Map param){
        return userRoleMapper.update(param);
    }

    @Override
    public int delete(Integer userId){
        return userRoleMapper.deleteByUserId(userId);
    }

    @Override
    public List<UserRoleDto> getUserRoleList(List<Integer> userIdList){
        return userRoleMapper.getUserRoleList(userIdList);
    }
}
