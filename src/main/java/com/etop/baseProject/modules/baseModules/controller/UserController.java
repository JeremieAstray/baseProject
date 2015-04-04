package com.etop.baseProject.modules.baseModules.controller;

import com.etop.baseProject.basic.controller.BaseController;
import com.etop.baseProject.modules.baseModules.entity.Resource;
import com.etop.baseProject.modules.baseModules.entity.User;
import com.etop.baseProject.modules.baseModules.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 用户登陆控制器
 * <p>
 * Created by Jeremie on 2014/10/22.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

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
        model.addAttribute("user", new User());
        return "/main/user/login";
    }

    /**
     * 登陆操作，失败则返回到登陆页面
     *
     * @param user
     * @param bindingResult
     * @param model
     * @param attr
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String login(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes attr, HttpSession session) throws Exception {
        try {
            if (bindingResult.hasErrors()) {
                addMessage(attr, "用户名或密码错误");
                return "redirect:/user/login";
            }
            //使用shiro管理登录
            SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getUsername(), user.getPassword().toLowerCase()));
            user = userService.findByName(user.getUsername());
            session.setAttribute("user", user);
            session.setAttribute("menu", userService.getUserMenu(user.getId()));
            List<List<Resource>> menus = userService.getAdminMenu(user.getId());
            if (menus != null) {
                session.setAttribute("adminMenu", menus);
            }
            return "redirect:/user/success";
        } catch (AuthenticationException e) {
            addMessage(attr, "用户名或密码错误");
            return "redirect:/user/login";
        }
    }

    /**
     * 登陆操作，失败则返回到登陆页面
     *
     * @return
     */
    @RequiresPermissions("menu:success")
    @RequestMapping(value = "/success", produces = "text/html; charset=utf-8")
    public String success() throws Exception {
        return "/main/manage/user/success";
    }

    /**
     * 退出登录
     *
     * @param attr
     * @return
     */
    @RequestMapping(value = "/logout", produces = "text/html; charset=utf-8")
    public String logout(RedirectAttributes attr, HttpSession session) throws Exception {
        if (session.getAttribute("user") != null)
            session.removeAttribute("user");
        if (session.getAttribute("menu") != null)
            session.removeAttribute("menu");
        SecurityUtils.getSubject().logout();
        addMessage(attr, "你已经成功退出");
        return "redirect:/user/login";
    }

    /**
     * 进入修改密码页面
     *
     * @return
     */
    @RequiresRoles("user")
    @RequestMapping(value = "/editPassword", produces = "text/html; charset=utf-8", method = RequestMethod.GET)
    public String editPassword() {
        return "/main/manage/user/edit";
    }


    /**
     * 修改密码
     *
     * @return
     */
    @RequiresRoles("user")
    @RequestMapping(value = "/editPassword", produces = "text/html; charset=utf-8", method = RequestMethod.POST)
    public String editPassword(Integer id, String oldpassword, String newpassword, Model model) throws Exception {
        Map<String, String> result = userService.updatePassword(id, oldpassword, newpassword);
        this.addMessage(model, result.get("type"), result.get("message"));
        return "/main/manage/user/edit";
    }

    /**
     * 进入注册新用户页面
     *
     * @return
     */
    @RequiresRoles("admin")
    @RequestMapping(value = "/register", produces = "text/html; charset=utf-8", method = RequestMethod.GET)
    public String registerUser() {
        return "/main/admin/user/register";
    }

    /**
     * 注册新用户页面
     *
     * @return
     */
    @RequiresRoles("admin")
    @RequestMapping(value = "/register", produces = "text/html; charset=utf-8", method = RequestMethod.POST)
    public String registerUser(@Valid User user, boolean adminRole, boolean userRole, Model model) throws Exception {
        Map<String, String> result = userService.saveUser(user, adminRole, userRole);
        this.addMessage(model, result.get("type"), result.get("message"));
        return "/main/admin/user/register";
    }
}
