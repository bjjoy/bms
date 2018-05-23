package cn.bjjoy.bms.setting.service;

import cn.bjjoy.bms.setting.entity.Menu;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by bjjoy on 2017/11/02.
 */
public interface MenuService {

//    List<Menu> queryMenuByUser(String userUuid);

    /**
     * 新建菜单
     * @param menu
     */
    int insert(Menu menu);

    PageInfo<Menu> getPage(Menu menu, Integer pageNumber, Integer pageSize);

    List<Menu> getList(Menu menu);

    /**
     * 获取目录和菜单（type=0,1）
     */
    List<Menu> getMenuList();

    /**
     * 菜单详情
     * @param id
     */
    Menu getMenu(Integer id);

    /**
     * 菜单更新
     */
    int update(Menu menu);

    /**
     * 菜单删除
     */
    int delete(Menu menu);

    /**
     * 根据角id获取menu
     * @param roleId
     */
    List<Menu> getListByRoleId(Integer roleId);

	/**
	 * 根据menuId获取role_menu数量
	 */
	int getRoleMenuCountByMenuId(Integer menuId);
}