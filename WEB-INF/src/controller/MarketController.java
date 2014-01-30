package src.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Controller;

import src.entity.Market;



public class MarketController extends Controller
{
    public void showAction(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        request.setAttribute(
            "market",
            getManager(request).getRepository("Market").findOneBy("id", request.getParameter("id"))
        );
        render("market:show", request, response);
    }



    public void createAction(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        if(request.getMethod().equals("POST")){

        } else {
            render("market:create", request, response);
        }
    }
}