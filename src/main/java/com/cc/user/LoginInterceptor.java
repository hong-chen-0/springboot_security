package com.cc.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 目标方法执行前
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
	
    Logger logger = LoggerFactory.getLogger(getClass());
    
    private void handleFalseResponse(HttpServletResponse response)
    		throws Exception {
    		  response.setStatus(200);
    		  response.setContentType("application/json");
    		  response.setCharacterEncoding("UTF-8");

    		  response.getWriter().write("未登录");
    		  response.getWriter().flush();
    		}
    
    //登录拦截器
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("users");
        if (loginUser == null) {
        	logger.info("目标方法执行前:preHandle执行完毕:拦截");
        	handleFalseResponse(response);
            return false;
        } else {
            //放行
        	logger.info("目标方法执行前:preHandle执行完毕:放行");
            return true;
        }
    }
    /**
     * 目标方法执行后
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	logger.info("目标方法执行后:postHandle执行完毕");
    }
    /**
     * 页面渲染后
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    	logger.info("页面渲染后:afterCompletion执行完毕");
    	logger.info("------------------------------------------------");
    }
}