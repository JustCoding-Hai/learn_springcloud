package top.javahai.provider;

import admin.IAdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.javahai.model.Admin;

/**
 * @author Ethan Huang
 * @create 2020-07-31 17:01
 */
@RestController
public class AdminController implements IAdminService {
    @Override
    public Admin getAllAdmin() {
        return new Admin(1,"a","dui","321");
    }

    @Override
    public String insertAdmin() {
        return "success!";
    }
}
