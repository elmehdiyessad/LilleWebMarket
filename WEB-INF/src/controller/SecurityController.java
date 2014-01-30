package src.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Controller;



public class SecurityController extends Controller
{
    public void indexAction(HttpServletRequest request, HttpServletResponse response)
    {
    	if(request.getUserPrincipal() != null)
    		redirect(response, request.getContextPath());
        if(request.getDispatcherType() == javax.servlet.DispatcherType.FORWARD)
            redirect(response, request.getContextPath() + "/login");
        else
        	render("security:login", request, response);
    }

    public void logoutAction(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {
        request.logout();
        redirect(response, request.getContextPath());
    }
}