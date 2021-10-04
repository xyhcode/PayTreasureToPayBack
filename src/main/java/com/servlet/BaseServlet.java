package com.servlet;
//一个servlet做多件事
import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//不需要加注解访问路径
public class BaseServlet extends HttpServlet {

    //重写service
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String url=request.getRequestURI();
        int g=url.lastIndexOf("/");//取到最后一个/
        String method=url.substring(g+1);//取/后面的字符
        System.out.println("正在访问："+method);

        try {
            //得到当前类的指定方法
            Method m=this.getClass().getDeclaredMethod(method,HttpServletRequest.class,HttpServletResponse.class);
            m.invoke(this, request,response);//赋值
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(1);
        }
    }

}
