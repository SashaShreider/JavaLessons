//package com.javalessons.simple_crud.log;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Enumeration;
//
//@Component
//public class LoggingInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("\n--- Request ---");
//        System.out.println("Method: " + request.getMethod());
//        System.out.println("URL: " + request.getRequestURL());
//        System.out.println("Headers:");
//
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            System.out.println(headerName + ": " + request.getHeader(headerName));
//        }
//
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("\n--- Response ---");
//        System.out.println("Status: " + response.getStatus());
//        System.out.println("Headers:");
//        response.getHeaderNames().forEach(headerName ->
//            System.out.println(headerName + ": " + response.getHeader(headerName))
//        );
//    }
//}