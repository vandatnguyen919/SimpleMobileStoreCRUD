/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.workshop.controller;

import com.workshop.dao.DAO;
import com.workshop.dto.Mobile;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HIEU
 */
@WebServlet(name = "WelcomeController", urlPatterns = {"/welcome"})
public class WelcomeController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("action");
            // Show all mobiles by default
            List<Mobile> mobileList = DAO.getMobiles();
            if (action == null) {
                request.setAttribute("mobileList", mobileList);
                request.getRequestDispatcher("welcome.jsp").forward(request, response);
            } else {
                if ("filterMobile".equalsIgnoreCase(action)) {
                    String minPriceString = request.getParameter("minPrice");
                    String maxPriceString = request.getParameter("maxPrice");
                    float minPrice = -1, maxPrice = -1;
                    if (!minPriceString.isEmpty()) {
                        minPrice = Float.parseFloat(minPriceString);
                    }
                    if (!maxPriceString.isEmpty()) {
                        maxPrice = Float.parseFloat(maxPriceString);
                    }
                    mobileList = DAO.getMobiles(minPrice, maxPrice);
                    request.setAttribute("mobileList", mobileList);
                }
                request.getRequestDispatcher("welcome.jsp").forward(request, response);
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
