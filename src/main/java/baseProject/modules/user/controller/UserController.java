package baseProject.modules.user.controller;

import baseProject.basic.controller.BaseController;
import baseProject.basic.entity.User;
import baseProject.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jeremie on 14-2-12.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/loginpage.html")
    public String loginPage() throws Exception{
        return "user/login";
    }

    @RequestMapping(value = "/login.html")
    public String login(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(userService.checkUser(user))
            return "user/success";
        return "user/fail";
    }
}
