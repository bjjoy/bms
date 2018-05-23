package cn.bjjoy.bms.setting.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author bjjoy
 * @date 2017/11/02
 */
@Setter
@Getter
@ToString
public class UserRoleDto {

    /**
     * Role表角色id
     */
    private Integer id;

    /**
     * UserRole表角色id
     */
    private Integer roleId;

    /**
     * UserRole表用户id
     */
    private Integer userId;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 英文名称
     */
    private String enname;

    /**
     * 对应用户是否选中该角色(0否，1是)
     */
    private Integer isSelect = 0;

    private String permission;

}
