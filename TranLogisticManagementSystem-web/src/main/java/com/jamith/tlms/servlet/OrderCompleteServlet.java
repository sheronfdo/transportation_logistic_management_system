package com.jamith.tlms.servlet;

import com.jamith.tlms.dto.request.orderService.CompleteOrderRequest;
import com.jamith.tlms.dto.response.orderService.CompleteOrderResponse;
import com.jamith.tlms.remote.OrderService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/orderComplete")
public class OrderCompleteServlet extends HttpServlet {

    @EJB
    OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer orderId = Integer.parseInt(req.getParameter("orderId"));
        CompleteOrderRequest completeOrderRequest = new CompleteOrderRequest();
        completeOrderRequest.setOrderId(orderId);

        CompleteOrderResponse response = orderService.completeOrder(completeOrderRequest);
        resp.getWriter().write(response.getDescription());
    }
}
