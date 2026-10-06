package com.sergio.tienda.coches.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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








}
