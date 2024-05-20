package com.jamith.tlms.servlet;

import com.jamith.tlms.dto.request.shipmentService.RouteOptimizeRequest;
import com.jamith.tlms.dto.response.shipmentService.RouteOptimizeResponse;
import com.jamith.tlms.remote.ShipmentService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/route")
public class RouteServlet extends HttpServlet {

    @EJB
    ShipmentService shipmentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer shipmentId = Integer.parseInt(req.getParameter("shipmentId"));
        Double lat = Double.parseDouble(req.getParameter("lat"));
        Double lon = Double.parseDouble(req.getParameter("lon"));

        RouteOptimizeRequest routeOptimizeRequest = new RouteOptimizeRequest();
        routeOptimizeRequest.setShipmentId(shipmentId);
        routeOptimizeRequest.setStartLat(lat);
        routeOptimizeRequest.setStartLon(lon);

        RouteOptimizeResponse routeOptimizeResponse = shipmentService.getOrdersWithOptimizedRoute(routeOptimizeRequest);
        System.out.println("route optimized orders = "+ routeOptimizeResponse.toString());
        req.setAttribute("routeOptimizedOrders", routeOptimizeResponse.getRouteOptimizeOrderResponses());
        RequestDispatcher dispatcher = req.getRequestDispatcher("routeOptimizedOrders.jsp");
        dispatcher.forward(req, resp);
    }
}
