package pers.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.springboot.exception.UserNotExistException;
import pers.springboot.server.Impl.AsyncServiceImpl;

@Controller
public class HelloController {

    @Autowired
    AsyncServiceImpl asyncService;

    @RequestMapping({"/", "/login.html"})
    public String index(){
        return "login";
    }

    @ResponseBody
    @RequestMapping("hello")
    public String hello(@RequestParam("user") String user){
        if(user.equals("aaa")){
            throw new UserNotExistException();
        }
        return "hello world!!!";
    }

    @ResponseBody
    @RequestMapping("/user/asyncTest")
    public String asyncTest(){
        asyncService.hello();
        return "hello world!!!";
    }

    @RequestMapping("success")
    public String success(){
        return "success";
    }

}
