package com.sergio.tienda.coches.interceptores;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class InterceptorAdmin implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String passAdmin = "";

        //para comprobar si ya ha metido algo anteriormente en el login
        if (request.getParameter("pass-login-admin") != null){
            passAdmin = request.getParameter("pass-login-admin");
            //para comprobar que la pass es correcta
            if (passAdmin.equals("123")){
                request.getSession().setAttribute("admin", "ok");
            }
        }

        //para controlar y redirigir si la ruta contiene admin
        if (request.getRequestURI().contains("/admin/")){
            if (!
                    (request.getSession().getAttribute("admin")!=null
                    &&
                    request.getSession().getAttribute("admin").equals("ok"))
            ){
                response.sendRedirect("../loginAdmin");
                return false;
            }
        }
        return true;
    } // END PREHANDLE





}// END CLASS INTERCEPTOR_ADMIN
