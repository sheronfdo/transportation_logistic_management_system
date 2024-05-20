package com.jamith.tlms.servlet;

import com.jamith.tlms.dto.request.shipmentService.ShipmentStartRequest;
import com.jamith.tlms.dto.response.shipmentService.ShipmentStartResponse;
import com.jamith.tlms.remote.ShipmentService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/shipmentStart")
public class ShipmentStartServlet extends HttpServlet {

    @EJB
    ShipmentService shipmentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer shipmentId = Integer.parseInt(req.getParameter("shipmentId"));
        ShipmentStartRequest shipmentStartRequest = new ShipmentStartRequest();
        shipmentStartRequest.setShipmentId(shipmentId);

        ShipmentStartResponse shipmentStartResponse = shipmentService.shipmentStart(shipmentStartRequest);
        req.setAttribute("shipmentStartResponse", shipmentStartResponse);
        RequestDispatcher dispatcher = req.getRequestDispatcher("shipmentOrderDetails.jsp");
        dispatcher.forward(req, resp);


    }
}
