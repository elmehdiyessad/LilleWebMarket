package src.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import framework.Controller;

import src.entity.Market;
import src.entity.MarketRepository;



public class MarketController extends Controller
{
    public void showAction(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Market m = getRepository(request).findOneById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("market", m);

        render(
            "market:show",
            request,
            response,
            "Marché : " + (request.getParameter("rev") != null ? m.getTitleRev() : m.getTitle())
        );
    }

    public void createAction(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        if(request.getMethod().equals("POST")){
            Market m = new Market();
            m.hydrate(request);
            int id = getRepository(request).create(m);
            redirect(response, request.getContextPath() + "/market/show?id=" + id);
        } else {
            render("market:create", request, response, "Créer un marché");
        }
    }

    

    private MarketRepository getRepository(HttpServletRequest request) throws Exception
    {
        return ((MarketRepository) getManager(request).getRepository("Market"));
    }
}