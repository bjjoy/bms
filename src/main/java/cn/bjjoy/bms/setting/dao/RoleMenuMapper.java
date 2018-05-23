package cn.bjjoy.bms.setting.dao;

import cn.bjjoy.bms.setting.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by eric on 2017/10/30.
 */
@Repository
@Mapper
public interface RoleMenuMapper {

    /**
     * 批量插入角色菜单关系
     * @param roleMenuList
     * @return
     */
    int insertBatch(@Param("roleMenuList") List<RoleMenu> roleMenuList);

    /**
     * 根据roleId删除角色菜单关系
     */
    int deleteByRoleId(@Param("roleId") Integer roleId);

    /**
     * 获取角色菜单列表
     */
    List<RoleMenu> getList(Map param);

    int getRoleMenuCountByMenuId(Integer menuId);
}
