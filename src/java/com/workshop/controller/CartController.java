/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.workshop.controller;

import com.workshop.dao.DAO;
import com.workshop.dto.CartDetails;
import com.workshop.dto.Mobile;
import com.workshop.dto.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HIEU
 */
@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

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
            String action = (String) request.getParameter("action");
            HttpSession session = request.getSession();
            // get User
            User user = (User) session.getAttribute("user");
            if (user != null) {
                if (action == null) {
                    List<CartDetails> cartList = DAO.getCartDetails(user.getUserID());
                    request.setAttribute("cartList", cartList);
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                } else {
                    String mobileID = (String) request.getParameter("mobileID");
                    if ("addToCart".equalsIgnoreCase(action)) {
                        if (DAO.addToCart(user.getUserID(), mobileID)) {
//                        request.setAttribute("message", "Added");
                        } else {
//                        request.setAttribute("message", "Failed to add");
                        }
                        List<Mobile> mobileList = DAO.getMobiles(true);
                        request.setAttribute("mobileList", mobileList);
                        request.getRequestDispatcher("welcome.jsp").forward(request, response);
                        return;
                    } else if ("deleteFromCart".equalsIgnoreCase(action)) {
                        if (DAO.deleteFromCart(user.getUserID(), mobileID)) {
                            request.setAttribute("message", "Deleted");
                        } else {
                            request.setAttribute("message", "Failed to delete");
                        }

                    } else if ("increaseCartQuantity".equalsIgnoreCase(action)) {
                        if (DAO.increaseQuantityInCartDetailsByOne(user.getUserID(), mobileID)) {
                        } else {
                        }
                    } else if ("decreaseCartQuantity".equalsIgnoreCase(action)) {
                        if (DAO.decreaseQuantityInCartDetailsByOne(user.getUserID(), mobileID)) {
                        } else {
                        }
                    }
                    List<CartDetails> cartList = DAO.getCartDetails(user.getUserID());
                    request.setAttribute("cartList", cartList);
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                }
            } else {
                response.sendRedirect("login");
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
