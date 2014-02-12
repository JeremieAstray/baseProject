package baseProject.modules.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jeremie on 14-2-12.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
