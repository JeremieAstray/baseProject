package com.etop.baseProject.modules.baseModules.controller;

import com.etop.baseProject.basic.controller.BaseController;
import com.etop.baseProject.modules.baseModules.entity.TUserEntity;
import com.etop.baseProject.modules.baseModules.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by jessy on 2015/3/5.
 */
@Controller
@RequestMapping("/base/user")
public class UserController extends BaseController {
    Logger logger = Logger.getLogger(String.valueOf(UserController.class));
    @Autowired
    private UserService userService;

    /**
     * 转向登陆界面
     *
     * @param model
     * @param message
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public String loginForm(Model model, String message) throws Exception {
        if (!StringUtils.isEmpty(message))
            model.addAttribute(message);
        logger.info("------userController------");
        model.addAttribute("user", new TUserEntity());
        return "/home/user/login";
    }

    @RequestMapping(value="/login",method=RequestMethod.POST,produces = "text/html; charset=utf-8")
    public String login(@Valid TUserEntity user,BindingResult bindingResult,Model model,RedirectAttributes attr) throws Exception{
        try {
            if (bindingResult.hasErrors()) {
                addMessage(attr, "用户名或密码错误！");
                return "redirect:/base/user/login";
            }
            //使用shiro管理登陆
            SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getName(), user.getPassword()));
            user = userService.findByName(user.getName());
            //List<List<TMenuEntity>> menu = userService.getUserMenu(user.getId());
//            model.addAttribute("user",user);
//            model.addAttribute("menus",menu);
            /*for(int i=0;i<menu.size();i++) {
                if (menu.get(i) != null) {
                    for(int j=0;j<menu.get(i).size();j++) {
                        if (menu.get(i).get(j) != null)
                            System.out.println(menu.get(i).get(j).getShowName());
                    }
                }
            }*/
            /*是否为管理员*/
            //List<List<TMenuEntity>> menus = userService.getAdminMenu(user.getId());
            /*if(menus != null){
                model.addAttribute("adminMenus",menus);
            }*/

            //用户登录成功------------获取所有用户信息，权限由前端shiro标签控制
            List<TUserEntity> userList = userService.getAllUser();
            logger.info("用户登录成功！");
            model.addAttribute("userList", userList);
            return "/home/user/user";
        } catch (AuthenticationException e) {
            addMessage(attr,"用户名或密码错误！");
            return "redirect:/login";
        }
    }
    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(RedirectAttributes attr){
        //使用权限管理工具进行管理用户的退出，注销登陆
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }
    @RequestMapping("/403")
    public String unauthorizedRole(RedirectAttributes attr){
        logger.info("用户没有此权限!");
        addMessage(attr,"用户没有此权限！");
        return "/errors/403";
    }

    @RequiresPermissions(value = "menu:create")
    @RequestMapping("/create")
    public String addUser(){
        return "/home/user/success";
    }

    @RequiresPermissions(value= "menu:update")
    @RequestMapping("/update")
    public String updateUser(int id){
        System.out.println("修改的id---------------"+id);
        return "/home/user/success";
    }

    @RequiresPermissions(value = "menu:del")
    @ResponseBody
    @RequestMapping(value = "/del",produces = "text/html; charset=utf-8",method= RequestMethod.GET)
    public String deleteUser(String id){
        System.out.println("=========================================>要删除的id为:" + id);
        return "/home/user/success";
    }

}
