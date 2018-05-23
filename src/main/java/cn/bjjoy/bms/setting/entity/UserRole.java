package cn.bjjoy.bms.setting.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRole {

    private Integer userId;

    private Integer roleId;

    public UserRole(Integer userId, Integer roleId){
        this.userId = userId;
        this.roleId = roleId;
    }
}
