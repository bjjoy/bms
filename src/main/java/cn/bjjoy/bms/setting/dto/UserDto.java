package cn.bjjoy.bms.setting.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 *
 * @author bjjoy
 * @date 2017/11/01
 */
@Setter
@Getter
@ToString
public class UserDto {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户状态
     */
    private Integer state;

    /**
     * 工号
     */
    private String no;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 用户头像
     */
    private String photo;

    /**
     * 最后登陆IP
     */
    private String loginIp;

    /**
     * 最后登陆时间
     */
    private Date loginDate;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private String updateDate;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 角色id列表
     */
    private List<Integer> roleIdList;

    /**
     * 用户对应的角色列表
     */
    private List<UserRoleDto> roleList;

}
