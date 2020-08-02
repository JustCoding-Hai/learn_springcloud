package top.javahai.openfeign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.javahai.model.Admin;
import top.javahai.openfeign.service.AdminService;

import javax.ws.rs.GET;

/**
 * @author Ethan Huang
 * @create 2020-07-31 17:45
 */
@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/testAdmin")
    public void test(){
        Admin allAdmin = adminService.getAllAdmin();
        System.out.println(allAdmin);
        String s = adminService.insertAdmin();
        System.out.println(s);
    }
}
