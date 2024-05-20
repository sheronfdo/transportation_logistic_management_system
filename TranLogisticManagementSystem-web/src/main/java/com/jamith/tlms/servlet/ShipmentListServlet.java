package com.jamith.tlms.servlet;

import com.jamith.tlms.dto.request.shipmentService.GetShipmentRequest;
import com.jamith.tlms.dto.response.shipmentService.GetShipmentResponse;
import com.jamith.tlms.remote.ShipmentService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/shipments")
public class ShipmentListServlet extends HttpServlet {

    @EJB
    ShipmentService shipmentService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GetShipmentResponse getShipmentResponse = shipmentService.getShipment(new GetShipmentRequest());
        req.setAttribute("shipmentList", getShipmentResponse.getShipmentData());
        RequestDispatcher dispatcher = req.getRequestDispatcher("shipmentDetails.jsp");
        dispatcher.forward(req, resp);
    }
}
