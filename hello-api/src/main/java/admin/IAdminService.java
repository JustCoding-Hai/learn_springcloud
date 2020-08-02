package admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import top.javahai.model.Admin;

/**
 * @author Ethan Huang
 * @create 2020-07-31 16:44
 */
public interface IAdminService {

    @GetMapping("/admin")
    public Admin getAllAdmin();
    @PostMapping("/admin/insert")
    public String insertAdmin();


}
