package com.sergio.controller;
import com.sergio.domain.PriceList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "WelcomeServlet", urlPatterns = "/")
public class WelcomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("customer") != null) {
            req.setAttribute("products", PriceList.getPRODUCTS());
            req.getRequestDispatcher("WEB-INF/jsp/products.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("WEB-INF/jsp/welcome.jsp").forward(req, resp);
        }
    }
}

