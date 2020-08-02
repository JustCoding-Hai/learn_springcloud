package top.javahai.openfeign.service;

import admin.IAdminService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Ethan Huang
 * @create 2020-07-31 17:45
 */
@FeignClient("provider")
public interface AdminService extends IAdminService {
}
