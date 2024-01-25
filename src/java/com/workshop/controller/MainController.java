package com.workshop.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HIEU
 */
@WebServlet(urlPatterns = {"/main"})
public class MainController extends HttpServlet {

    private static final String LOGIN = "login";
    private static final String SIGNUP = "signup";
    private static final String LOGOUT = "logout";
    private static final String ADD_MOBILE = "addMobile";
    private static final String DELETE_MOBILE = "deleteMobile";
    private static final String UPDATE_MOBILE = "updateMobile";
    private static final String SEARCH_MOBILE = "searchMobile";
    private static final String FILTER_MOBILE = "filterMobile";

    private static final String ADD_TO_CART = "addToCart";
    private static final String DELETE_FROM_CART = "deleteFromCart";
    private static final String INCREASE_CART_QUANTITY = "increaseCartQuantity";
    private static final String DECREASE_CART_QUANTITY = "decreaseCartQuantity";

    private static final String MOBILE_CONTROLLER = "mobile";
    private static final String WELCOME_CONTROLLER = "welcome";
    private static final String CART_CONTROLLER = "cart";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = LOGIN;
            /* TODO output your page here. You may use following sample code. */
            try {
                String action = request.getParameter("action");

                if (action == null || action.equalsIgnoreCase(LOGIN)) {
                    url = LOGIN;
                } else if (action.equalsIgnoreCase(SIGNUP)) {
                    url = SIGNUP;
                } else if (action.equalsIgnoreCase(LOGOUT)) {
                    url = LOGOUT;
                } else if (action.equalsIgnoreCase(ADD_MOBILE)
                        || action.equalsIgnoreCase(DELETE_MOBILE)
                        || action.equalsIgnoreCase(UPDATE_MOBILE)
                        || action.equalsIgnoreCase(SEARCH_MOBILE)) {
                    url = MOBILE_CONTROLLER;
                } else if (action.equalsIgnoreCase(FILTER_MOBILE)) {
                    url = WELCOME_CONTROLLER;
                } else if (action.equalsIgnoreCase(ADD_TO_CART)
                        || action.equalsIgnoreCase(DELETE_FROM_CART)
                        || action.equalsIgnoreCase(INCREASE_CART_QUANTITY)
                        || action.equalsIgnoreCase(DECREASE_CART_QUANTITY)) {
                    url = CART_CONTROLLER;
                }
            } catch (Exception e) {
                log("Error at MainController: " + e.toString());
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
