package cn.bjjoy.bms.setting.controller;

import cn.bjjoy.bms.base.Codes;
import cn.bjjoy.bms.setting.entity.Role;
import cn.bjjoy.bms.base.ResponseResult;
import cn.bjjoy.bms.setting.dto.RoleDto;
import cn.bjjoy.bms.setting.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.management.modelmbean.ModelMBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bjjoy
 * @date 2017/11/05
 */
@Controller
@CrossOrigin
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

	/**
	 * 角色管理初始化页面
	 * @return
	 */
	@RequestMapping(value = "/index" )
	public String index() {
		return "/role/index";
	}

    /**
     * 获取角色列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public ResponseResult getList(@RequestParam(defaultValue = "1") Integer pageNumber,
                                  @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<Role> page = roleService.getPage(pageNumber, pageSize);
        return ResponseResult.ok(page);
    }

    /**
     * 到创建角色页面
     * @return
     */
    @RequestMapping(value = "/add" )
    public String toAdd() {
        return "/role/add";
    }

    /**
     * 创建角色
     */
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseResult create(RoleDto roleDto){
        int count = roleService.create(roleDto);
        return ResponseResult.ok(count);
    }

    /**
     * 角色详情
     */
    @RequestMapping(value = "/getRole", method = RequestMethod.GET)
    public ResponseResult getRole(@RequestParam Integer id, String traceID){
        Map resultMap = roleService.getRole(id);
        return ResponseResult.ok(resultMap);
    }

	/**
	 * 获取角色到编辑角色页面
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String toEdit(@PathVariable Integer id, ModelMap map){
		Role role = roleService.getRoleById(id);
		map.addAttribute("role", role);
		return "/role/edit";
	}

	/**
	 * 到分配资源页面
	 */
	@RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
	public String toGrant(@PathVariable Integer id, ModelMap map){
		Role role = roleService.getRoleById(id);
		map.addAttribute("role", role);
		return "/role/grant";
	}

	/**
	 * 角色分配资源
	 */
	@ResponseBody
	@RequestMapping(value = "/update/grant", method = RequestMethod.POST)
	public ResponseResult updateGrant(RoleDto roleDto){
		if (roleDto.getMenuIds() == null){
			return new ResponseResult(601, "请选择资源");
		}

		int count = roleService.addRoleMenu(roleDto);
		return ResponseResult.ok(count);
	}

    /**
     * 角色更新
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult update(RoleDto roleDto){
        if (roleDto.getId() == null){
            return new ResponseResult(Codes.PARAM_ERROR.getCode(), "id is null");
        }
        int count = roleService.update(roleDto);
        return ResponseResult.ok(count);
    }

    /**
     * 检查角色是否可删除，有可用用户关联，禁止删除
     */
    @RequestMapping(value = "/deleteCheck", method = RequestMethod.GET)
    public ResponseResult deleteCheck(@RequestParam Integer roleId, String traceID){
        int count = roleService.getUserRoleCountByRoleId(roleId);
        if (count > 0){
            return ResponseResult.error(Codes.ROLE_IS_USED);
        }else {
            return ResponseResult.ok(count);
        }
    }

    /**
     * 角色删除
     */
    @ResponseBody
    @RequestMapping(value = "/delete/{roleId}", method = RequestMethod.POST)
    public ResponseResult delete(@PathVariable Integer roleId){
        if (roleId == null){
            return new ResponseResult(Codes.PARAM_ERROR.getCode(), "id is null");
        }
		int count = roleService.getUserRoleCountByRoleId(roleId);
		if (count > 0) {
			return ResponseResult.error(Codes.ROLE_IS_USED);
		}
        Role role = new Role();
        role.setId(roleId);
        role.setDelFlag("1");
        roleService.delete(role);
        return ResponseResult.ok(count);
    }
}