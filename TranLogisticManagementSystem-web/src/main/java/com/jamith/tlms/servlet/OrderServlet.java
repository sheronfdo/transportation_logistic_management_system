package com.jamith.tlms.servlet;

import com.jamith.tlms.dto.request.inventoryService.GetItemsRequest;
import com.jamith.tlms.dto.request.orderService.AddOrderRequest;
import com.jamith.tlms.dto.response.inventoryService.GetItemsResponse;
import com.jamith.tlms.dto.response.orderService.AddOrderResponse;
import com.jamith.tlms.remote.InventoryService;
import com.jamith.tlms.remote.OrderService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    @EJB
    InventoryService inventoryService;

    @EJB
    OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GetItemsRequest getItemsRequest = new GetItemsRequest();
        getItemsRequest.setItemId(Integer.parseInt(req.getParameter("itemId")));

        GetItemsResponse response = inventoryService.getSingleItem(getItemsRequest);
        req.setAttribute("item", response.getInventoryItem());
        RequestDispatcher dispatcher = req.getRequestDispatcher("orderForm.jsp");
        dispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddOrderRequest addOrderRequest = new AddOrderRequest();
        addOrderRequest.setInventoryItemId(Integer.parseInt(req.getParameter("itemId")));
        addOrderRequest.setItemQty(Integer.parseInt(req.getParameter("itemQty")));
        addOrderRequest.setAddress(req.getParameter("orderAddress"));
        addOrderRequest.setLatitude(Double.parseDouble(req.getParameter("orderLatitude")));
        addOrderRequest.setLongitude(Double.parseDouble(req.getParameter("orderLongitude")));

        AddOrderResponse addOrderResponse = orderService.makeOrder(addOrderRequest);

        req.setAttribute("orderId", addOrderResponse.getOrderId());
        req.setAttribute("orderDescription", addOrderResponse.getDescription());
        req.setAttribute("orderTotalAmount", addOrderResponse.getTotalAmount());

        RequestDispatcher dispatcher = req.getRequestDispatcher("orderSuccess.jsp");
        dispatcher.forward(req, resp);
    }
}
