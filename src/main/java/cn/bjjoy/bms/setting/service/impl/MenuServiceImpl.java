package cn.bjjoy.bms.setting.service.impl;

import cn.bjjoy.bms.setting.dao.MenuMapper;
import cn.bjjoy.bms.setting.dao.RoleMenuMapper;
import cn.bjjoy.bms.setting.service.MenuService;
import cn.bjjoy.bms.setting.entity.Menu;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by bjjoy on 2017/11/02.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
	private RoleMenuMapper roleMenuMapper;

//    @Override
//    public List<Menu> queryMenuByUser(String userUuid){
//        return menuMapper.queryMenuByUser(userUuid);
//    }

    @Override
    public int insert(Menu menu){
        return menuMapper.insertMenu(menu);
    }

    @Override
    public PageInfo<Menu> getPage(Menu menu, Integer pageNumber, Integer pageSize){
        PageHelper.startPage(pageNumber,pageSize);
        List<Menu> menuList= menuMapper.getList(menu);
        return new PageInfo<>(menuList);
    }

    @Override
    public List<Menu> getList(Menu menu){
        return menuMapper.getList(menu);
    }

    @Override
    public List<Menu> getMenuList(){
        return menuMapper.getMenuList();
    }

    @Override
    public Menu getMenu(Integer id){
        return menuMapper.getById(id);
    }

    @Override
    public int update(Menu menu){
        return menuMapper.updateById(menu);
    }

    @Override
    public int delete(Menu menu){
        //更新del_flag=1
        int count = menuMapper.updateById(menu);

        //更新子菜单del_flag=1
        count += menuMapper.updateByParentId(menu);

        return count;
    }

    public List<Menu> getListByRoleId(Integer roleId){
        return this.menuMapper.getListByRoleId(roleId);
    }

    @Override
	public int getRoleMenuCountByMenuId(Integer menuId){
    	return roleMenuMapper.getRoleMenuCountByMenuId(menuId);
	}
}
