package ASTWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiangpeng on 2017/12/18.
 */
@CrossOrigin(origins="http://127.0.0.1:8080",maxAge=3600)
@Controller
@RequestMapping("/AST")
public class IndexController{
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
