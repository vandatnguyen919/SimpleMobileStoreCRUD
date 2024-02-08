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
@WebServlet(name = "Mobile", urlPatterns = {"/mobile"})
public class MobileController extends HttpServlet {

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
            if (action == null) {
                List<Mobile> mobileList = DAO.getMobiles(false);
                request.setAttribute("mobileList", mobileList);
                request.getRequestDispatcher("mobile.jsp").forward(request, response);
            } else {
                List<Mobile> mobileList = null;
                if ("searchMobile".equalsIgnoreCase(action)) {
                    String q = request.getParameter("q");
                    if (q != null && !q.isEmpty()) {
                        mobileList = DAO.getMobiles(q, q);
                    }
                } else if ("addMobile".equalsIgnoreCase(action) || "updateMobile".equalsIgnoreCase(action)) {
                    String mobileID = request.getParameter("mobileID");
                    String name = request.getParameter("name");
                    String price = request.getParameter("price");
                    String quantity = request.getParameter("quantity");
                    String yearOfProduction = request.getParameter("yearOfProduction");
                    String description = request.getParameter("description");
                    String notSale = request.getParameter("notSale");
                    String imagePath = request.getParameter("imagePath");

                    byte notSaleByte = 0;
                    if ("true".equals(notSale)) {
                        notSaleByte = 1;
                    }
                    Mobile mobile = new Mobile();
                    mobile.setMobileID(mobileID);
                    mobile.setName(name);
                    mobile.setPrice(Float.parseFloat(price));
                    mobile.setQuantity(Integer.parseInt(quantity));
                    mobile.setYearOfProduction(Integer.parseInt(yearOfProduction));
                    mobile.setDescription(description);
                    mobile.setNotSale(notSaleByte);
                    mobile.setImagePath(imagePath);

                    if ("addMobile".equalsIgnoreCase(action)) {
                        if (DAO.addMobile(mobile)) {
                            request.setAttribute("message", "Added successfully!");
                        } else {
                            request.setAttribute("message", "Failed to add!");
                        }
                    } else if ("updateMobile".equalsIgnoreCase(action)) {
                        if (DAO.updateMobile(mobile)) {
                            request.setAttribute("message", "Updated successfully!");
                        } else {
                            request.setAttribute("message", "Failed to update!");
                        }
                    }
                } else if ("deleteMobile".equalsIgnoreCase(action)) {
                    String mobileID = request.getParameter("mobileID");
                    if (DAO.deleteMobile(mobileID)) {
                        request.setAttribute("message", "Deleted successfully!");
                    } else {
                        request.setAttribute("message", "Failed to delete!");
                    }
                }
                if (!"searchMobile".equalsIgnoreCase(action)) {
                    mobileList = DAO.getMobiles(false);
                }
                request.setAttribute("mobileList", mobileList);
                request.getRequestDispatcher("mobile.jsp").forward(request, response);
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
