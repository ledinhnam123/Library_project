/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledinhnam.Filterr;

import com.ledinhnam.Entity.EmployeesEntity;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hp
 */
@WebFilter(urlPatterns = {"/faces/books.xhtml","/faces/book-list.xhtml"
        ,"/faces/customers.xhmtl","/faces/employee-list.xhtml","/faces/employees.xhtml",
        "/faces/chart.xhtml","/faces/customer-list.xhtml","/faces/chart.xhtml"})
public class UserFilter implements Filter {

    private HttpServletRequest httpRequest;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        EmployeesEntity employ = (EmployeesEntity) session.getAttribute("employee");
        if (employ != null) {

            if (!employ.getIdentityId().equals("ADMIN")) {
                String home = "/faces/index.xhtml";
                RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(home);
                dispatcher.forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        } else {
            String loginPage = "/faces/login.xhtml";
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginPage);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
