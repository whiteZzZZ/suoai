

package com.yiban.suoai.interceptor;

import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.util.ApiResult;
import com.yiban.suoai.util.ErrorCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
@WebFilter(urlPatterns = { "/*" }, filterName = "TokenFilter")
public class LoginFilter implements Filter {

	@Autowired
	RedisService redisService;

	@Override
	public void destroy() {
        // Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 如果没有登录
		String uri = req.getRequestURI();

		int flag = 0;//1就要拦截

		List<String> banPages = new ArrayList<>();
		banPages.add("/square");
		banPages.add("/user");
		banPages.add("/paper");
		banPages.add("/matching");
		banPages.add("/word");
		banPages.add("/inform");
		banPages.add("/chat");
		banPages.add("/file");
		banPages.add("/paper");
		banPages.add("/weekWord");


		//添加url
		for (String o : banPages) {
			if (uri.contains(o)) {
				flag = 1;
			}
		}




		if(uri.contains("druid")) flag = 0;

		if(uri.contains("swagger")||uri.contains("api")){
		    if(req.getHeader("cipher") != null){
                if(req.getHeader("cipher").equals(req.getHeader("cipher")))
                    flag = 0;
            }

        }

		if (flag == 0) {
			chain.doFilter(req, res);
		} else {
			String token = req.getHeader("token");
			if(token == null){
                ApiResult apiResult = new ApiResult(ErrorCode.TOKEN_FAILURE,"token_is_null");
                res.getWriter().println(apiResult);
            }
			else{
				if (redisService.tokenExists(token)) {
					chain.doFilter(req, res);
				}
				// 继续访问其他资源
				else {
                    ApiResult apiResult = new ApiResult(ErrorCode.TOKEN_FAILURE,"token_is_wrong");
                    res.getWriter().println(apiResult);
				}
			}


		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
        // Auto-generated method stub

	}

}
