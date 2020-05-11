package com.restaurant.controller;

import com.restaurant.command.Command;
import com.restaurant.injector.ApplicationInjector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "FrontController", urlPatterns = {"/login", "/signUp", "/logout", "/index", "/menu",
        "/myOrders", "/dish", "/lunch", "/basket", "/placeOrder", "/creditCard", "/admin"})
public class FrontController extends HttpServlet {
    private final Map<String, Command> commands;

    public FrontController() {
        this.commands = ApplicationInjector.getCommands();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void showRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = getCommand(request);
        String page = command.show(request);
        processCommand(request, response, page);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = getCommand(request);
        String page = command.execute(request);
        processCommand(request, response, page);
    }

    private void processCommand(HttpServletRequest request, HttpServletResponse response, String answer) throws IOException, ServletException {
        if (request.getAttribute("responseType").equals("servlet")) {
            response.sendRedirect(request.getContextPath() + answer);
        }
        else if(request.getAttribute("responseType").equals("json")) {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(answer);
            out.flush();
        }
        else if(request.getAttribute("responseType").equals("jsp")) {
            request.getRequestDispatcher(answer).forward(request, response);
        }
        else {
            request.getRequestDispatcher(answer).forward(request, response);
        }
//        if (!answer.contains(".jsp")) {
//            response.sendRedirect(request.getContextPath() + answer);
//        } else {
//            request.getRequestDispatcher(answer).forward(request, response);
//        }
    }

    private Command getCommand(HttpServletRequest request) {
        String path = request.getRequestURI();
        if(!commands.containsKey(path)) {
            return commands.get("/login");
        }
        return commands.get(path);
    }
}
