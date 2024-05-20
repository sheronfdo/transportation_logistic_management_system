package com.jamith.tlms.interceptor;


import com.jamith.tlms.dto.request.shipmentService.RouteOptimizeRequest;
import com.jamith.tlms.dto.response.shipmentService.RouteOptimizeOrderResponse;
import com.jamith.tlms.dto.response.shipmentService.RouteOptimizeResponse;
import com.jamith.tlms.entity.Order;
import com.jamith.tlms.entity.ShipmentOrder;
import com.jamith.tlms.util.enums.Status;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class RouteOptimizationInterceptor {
    @PersistenceContext(unitName = "tranLogisticSys")
    private EntityManager em;


    // Haversine formula to calculate distance between two points in kilometers
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the Earth in kilometers
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in kilometers
    }

    @AroundInvoke
    public Object optimizeRoute(InvocationContext context) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        Object[] parameters = context.getParameters();
        if (parameters.length > 0 && parameters[0] instanceof RouteOptimizeRequest) {
            RouteOptimizeRequest request = (RouteOptimizeRequest) parameters[0];
            List<Order> orders = fetchOrdersByShipmentId(request.getShipmentId());
            double startLat = request.getStartLat();
            double startLon = request.getStartLon();

            List<Order> sortedOrders = new ArrayList<>();
            List<Order> remainingOrders = new ArrayList<>(orders);

            double currentLat = startLat;
            double currentLon = startLon;

            while (!remainingOrders.isEmpty()) {
                Order nearestOrder = null;
                double nearestDistance = Double.MAX_VALUE;

                for (Order order : remainingOrders) {
                    double distance = calculateDistance(currentLat, currentLon, order.getLatitude(), order.getLongitude());
                    if (distance < nearestDistance) {
                        nearestDistance = distance;
                        nearestOrder = order;
                    }
                }

                sortedOrders.add(nearestOrder);
                remainingOrders.remove(nearestOrder);

                currentLat = nearestOrder.getLatitude();
                currentLon = nearestOrder.getLongitude();
            }
            List<RouteOptimizeOrderResponse> routeOptimizeOrderResponses = sortedOrders.stream()
                    .map(e->modelMapper.map(e, RouteOptimizeOrderResponse.class))
                    .collect(Collectors.toList());
            // Create the response object and set the optimized orders
            RouteOptimizeResponse response = new RouteOptimizeResponse();
            response.setRouteOptimizeOrderResponses(routeOptimizeOrderResponses);
            System.out.print(response.toString());
            return response;
        }

        return context.proceed();
    }

    private List<Order> fetchOrdersByShipmentId(Integer shipmentId) {
        List<Order> shipmentOrders = em
                .createQuery("SELECT s from ShipmentOrder s where s.shipment.id=:shipmentId and s.status=:status", ShipmentOrder.class)
                .setParameter("shipmentId", shipmentId)
                .setParameter("status", Status.active.name())
                .getResultList().stream().map(e -> {
                    Order order = em.find(Order.class, e.getOrder().getId());
                    return order;
                }).collect(Collectors.toList());
        return shipmentOrders;
    }

}