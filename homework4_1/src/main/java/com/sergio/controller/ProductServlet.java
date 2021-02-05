package com.sergio.controller;

import com.sergio.domain.PriceList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setAttribute("customer", req.getParameter("customer"));



        req.setAttribute("products", PriceList.getPRODUCTS());
        req.getRequestDispatcher("WEB-INF/jsp/products.jsp").forward(req, resp);


    }
}