package cn.bjjoy.bms.setting.dto;

/**
 * @author bjjoy
 * @date 2017/11/02.
 */
public class MenuDto {

    private Integer id;

    /**
     * 父级ID
     */
    private Integer parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 编码
     */
    private String code;

    /**
     * 图标
     */
    private String icon;

    /**
     * 类型：0-菜单 1-子菜单 2-按钮
     */
    private Integer type;

    /**
     * 权限
     */
    private String permission;

    /**
     * 菜单是否与角色关联
     */
    private Integer isSelect=0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Integer isSelect) {
        this.isSelect = isSelect;
    }
}
