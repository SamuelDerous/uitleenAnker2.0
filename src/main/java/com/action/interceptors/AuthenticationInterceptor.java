/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action.interceptors;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;

/**
 *
 * @author zenodotus
 */
public class AuthenticationInterceptor implements Interceptor {

    @Override
    public void destroy() {
        
    }

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        Map<String, Object> sessionattributes = ai.getInvocationContext().getSession();
        String gebruikersnaam = (String) sessionattributes.get("gebruikersnaam");
        if(gebruikersnaam == null || gebruikersnaam.equals("")) {
            return Action.LOGIN;
        } else {
            return ai.invoke();
        }
    }
    
}
