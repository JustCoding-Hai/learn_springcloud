package top.javahai.spring.cloud.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hai
 * @program: learn_springcloud
 * @description:
 * @create 2022/10/4 - 0:34
 **/
@Component
public class PermissionFilter extends ZuulFilter {
    /**
     * 过滤器类型，它决定过滤器在请求的哪个生命周期中执行。
     * 权限判断一般是pre，pre代表在请求被路由之前执行
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序。当请求一个阶段存在多个过滤器时，需要根据该方法返回的值来依次执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断该过滤器是否需要执行。可以通过该方法来指定过滤器的有效范围
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 核心过滤逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        //获取当前请求
        HttpServletRequest request = ctx.getRequest();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //如果请求条件不满足的话，直接从这里给出响应
        if (!"admin".equals(username) || !"123".equals(password)) {
            //设置不进行路由
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.addZuulResponseHeader("content-type","text/html;charset=utf-8");
            ctx.setResponseBody("非法访问");
        }
        return null;
    }
}
