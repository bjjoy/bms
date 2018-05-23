package cn.bjjoy.bms.setting.controller;

import cn.bjjoy.bms.base.Codes;
import cn.bjjoy.bms.setting.dto.UserDto;
import cn.bjjoy.bms.setting.dto.UserRoleDto;
import cn.bjjoy.bms.setting.entity.Role;
import cn.bjjoy.bms.setting.service.MenuService;
import cn.bjjoy.bms.setting.service.UserService;
import cn.bjjoy.bms.util.DataUtils;
import cn.bjjoy.bms.base.ResponseResult;
import cn.bjjoy.bms.setting.entity.User;
import cn.bjjoy.bms.setting.service.RoleService;
import cn.bjjoy.bms.setting.service.UserRoleService;
import cn.bjjoy.bms.util.EncryptUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;

/**
 * @author bjjoy
 * @date 2017/8/28
 */
@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

	/**
	 * 跳转添加用户页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		return "/user/add";
	}

    /**
     * 生成用户
     * @param userDto
     */
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseResult insertOrUpdate(UserDto userDto){

		if(StringUtils.isNotBlank(userDto.getPassword())){
			userDto.setPassword(EncryptUtils.encryptMD5(userDto.getPassword()));
		}else {
			return ResponseResult.error(Codes.PARAM_ERROR);
		}
		this.userService.insert(userDto);
		Integer userId = userDto.getId();
        return ResponseResult.ok(userId);
    }

    /**
     * 获取用户详情
     */
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseResult getUser(@RequestParam Integer id){
        User user = this.userService.getUserDetail(id);
        List<Role> roleList = this.roleService.getList(new HashMap());
        List<UserRoleDto> userRoleList = DataUtils.getDataArray(roleList, UserRoleDto.class);
        UserDto userDto = DataUtils.getData(user, UserDto.class);
        userDto.setRoleList(userRoleList);

        //获取角色信息
        List<Integer> userIdList = new ArrayList<>();
        userIdList.add(id);
        List<UserRoleDto> userRoleDtoList = this.userRoleService.getUserRoleList(userIdList);

        //用户对应角色列表是否被选中
        for (UserRoleDto userRoleDto : userDto.getRoleList()){
            for (UserRoleDto selectRole : userRoleDtoList){
                if (userRoleDto.getId().equals(selectRole.getRoleId())){
                    userRoleDto.setIsSelect(1);
                }
            }
        }

        return ResponseResult.ok(userDto);
    }

	/**
	 * 跳转到修改用户页面
	 * @param id
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String toEdit(@PathVariable Integer id, ModelMap map){
		User user = userService.getUserDetail(id);
		map.addAttribute("userDto", user);
		return "/user/edit";
	}

    /**
     * 更新用户信息
     * @param userDto
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
    public ResponseResult update(UserDto userDto){

        if(userDto.getId() == null){
            return ResponseResult.error(Codes.PARAM_ERROR);
        }
        int count = userService.updateUser(userDto);
        return ResponseResult.ok(count);
    }

    /**
     * 用户管理初始化页面
     * @return
     */
    @RequestMapping(value = "/index" )
    public String index() {
        return "/user/index";
    }

    /**
     * 获取用户列表
     * @param userDtoParam
     */
	@ResponseBody
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public ResponseResult getList(UserDto userDtoParam,
                                  @RequestParam(defaultValue = "1") Integer pageNumber,
                                  @RequestParam(defaultValue = "10") Integer pageSize){
        Map param = new HashMap();
        param.put("pageSize", pageSize);
        param.put("startRow", (pageNumber - 1) * pageSize);
        if (StringUtils.isNotBlank(userDtoParam.getLoginName())){
            param.put("loginName", userDtoParam.getLoginName());
        }
        List<Map> userMapList = this.userService.getList(param);
        //没有用户数据直接返回
        if(userMapList == null || userMapList.size() == 0){
            Map<String, Object> responseResult = new HashMap<>();
            responseResult.put("userList", new ArrayList<>());
            responseResult.put("count", 0);
            return ResponseResult.ok(responseResult);
        }

        List<UserDto> userList = DataUtils.getDataArray(userMapList, UserDto.class);

        List<Integer> userIdList = new ArrayList<>();
        for(UserDto userDto : userList){
            userIdList.add(userDto.getId());
        }
        //获取用户角色信息
        List<UserRoleDto> userRoleVOList = this.userRoleService.getUserRoleList(userIdList);
        for(UserDto userDto : userList){
            //添加角色list
            for(UserRoleDto userRoleDto : userRoleVOList){
                if(userDto.getId().equals(userRoleDto.getUserId())){
                    if(userDto.getRoleList() == null){
                        userDto.setRoleList(new ArrayList<>());
                        userDto.getRoleList().add(userRoleDto);
                    }else{
                        userDto.getRoleList().add(userRoleDto);
                    }
                }
            }
        }

        Integer count = this.userService.getCount(param);
        Map<String, Object> responseResult = new HashMap<>();
        responseResult.put("userList",userList);
        responseResult.put("count", count);
        return ResponseResult.ok(responseResult);
    }

    /**
     * 删除用户
     * @param userId
     */
    @ResponseBody
    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.POST)
    public ResponseResult delete(@PathVariable Integer userId){

        if(userId == null){
            return ResponseResult.error(Codes.PARAM_ERROR);
        }
        User user = new User();
        user.setId(userId);
        user.setDelFlag(1);
        userService.deleteUser(user);
        return ResponseResult.ok(user.getId());
    }

    @RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
    public String grant(@PathVariable Integer id, ModelMap map) {
        User user = userService.getUserDetail(id);
        map.put("user", user);

        List<Role> roleList = roleService.getListByUserId(id);
        List<Integer> roleIds = new ArrayList<Integer>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }
        map.put("roleIds", roleIds);

        List<Role> roles = roleService.getList(new HashMap());
        map.put("roles", roles);
        return "/user/grant";
    }

//    @RequestMapping(value = "/get", method = RequestMethod.GET)
//    public ResponseResult get(User user){
//
//        return ResponseResult.ok(userService.findUserByName("test2"));
//    }
}
