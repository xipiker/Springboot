package pers.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pers.springboot.domain.tUser;
import pers.springboot.server.tUserService;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private tUserService tUserService;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/user/emps")
    public String list(Model model){
        List<tUser> list = tUserService.getAll();
        log.info("list-----{}", list);
        model.addAttribute("listResult", list);
        return "emp/list";
    }

    @GetMapping("/user/emp")
    public String toAddPage(){
        return "emp/add";
    }

    @PostMapping("/user/emp")
    public String addEmp(tUser tUser){
        //log.info("tUser-----{}", tUser.getAge());
        tUserService.insert(tUser);
        return "redirect:/user/emps";
    }

    @PutMapping("user/emp")
    public String updateEmployee(tUser tUser){
        tUserService.updateByPrimaryKeySelective(tUser);
        return "redirect:/user/emps";
    }

    @DeleteMapping("user/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        tUserService.deleteById(Long.valueOf(id));
        return "redirect:/user/emps";
    }

    @GetMapping("/user/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        tUser tUser = tUserService.findTUserById(id);
        model.addAttribute("empRs", tUser);
        return "emp/add";
    }
}