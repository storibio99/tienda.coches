package com.sergio.tienda.coches;

import com.sergio.tienda.coches.interceptores.InterceptorAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Component
public class Config implements WebMvcConfigurer {

    @Autowired
    private InterceptorAdmin interceptorAdmin;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorAdmin);
        registry.addInterceptor(localeChangeInterceptor());
    }


    //con @Bean indicamos que el siguiente metodo devuelve
    //una bean de spring
    @Bean
    public LocaleResolver localeResolver(){

        //al tener @Bean en este metodo, spring va a meter
        //lo que se devuelva en el return en el contenedor de spring

        //para que queremos un LocaleResolver?
        //sirve para resolver en que idioma mostrar la app web
        //vamos a decir que el cambio de idioma se mantenga en sesion
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.getDefault()); //esto coge el idioma por defecto

        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){

        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setIgnoreInvalidLocale(true);
        localeChangeInterceptor.setParamName("idioma");
        return localeChangeInterceptor;

    }



}
