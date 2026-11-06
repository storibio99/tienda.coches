package com.sergio.tienda.coches.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ControladorAdmin {


    @RequestMapping("loginAdmin")
    public String loginAdmin(){
        return "admin/login_admin";
    }//END LOGIN_ADMIN

    @RequestMapping("admin/inicioAdmin")
    public String inicioAdmin(){
        return "admin/inicio_admin";
    }//END INICIO_ADMIN

    @RequestMapping("admin/cerrarSesion")
    public RedirectView cerrarSesion(HttpServletRequest request){
        request.getSession().invalidate();
        RedirectView rw = new RedirectView();
        rw.setUrl("http://localhost:8080/");
        return rw;
    }








}
