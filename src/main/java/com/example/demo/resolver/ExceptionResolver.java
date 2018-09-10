package com.example.demo.resolver;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionResolver {

	public static final String DEFAULT_ERROR_VIEW = "500";
	
	public static final String NoHandler_ERROR_VIEW = "404";
	
	//当普通调用时，一般跳转到自定义的错误页面。
    @ExceptionHandler(value = Exception.class)
    public ModelAndView ExceptionHandler1(HttpServletRequest req, Exception e) throws Exception {
    	ModelAndView mav = new ModelAndView();
    	//spring.mvc.throw-exception-if-no-handler-found=false
    	//mav.addObject("message", e.getMessage());
        //mav.setViewName(DEFAULT_ERROR_VIEW);
    	//spring.mvc.throw-exception-if-no-handler-found=true
    	if(e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
    		 mav.setViewName(NoHandler_ERROR_VIEW);
        }else {
        	 mav.addObject("message", e.getMessage());
             mav.setViewName(DEFAULT_ERROR_VIEW);
        }
        return mav;
    }
    
    //当ajax调用时，可返回约定的数据对象，方便页面统一处理。
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Map<String, String> ExceptionHandler2(HttpServletRequest req, Exception e) {
        
        Map<String, String> re = new HashMap<String, String>();
        re.put("error", "1");
        re.put("msg", e.getMessage());
        return re;
    }

}
