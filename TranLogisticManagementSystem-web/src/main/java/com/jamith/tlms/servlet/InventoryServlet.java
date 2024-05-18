package com.jamith.tlms.servlet;

import com.jamith.tlms.dto.request.inventoryService.AddNewItemRequest;
import com.jamith.tlms.dto.response.inventoryService.AddNewItemResponse;
import com.jamith.tlms.remote.InventoryService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {

    @EJB
    InventoryService inventoryService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddNewItemRequest addNewItemRequest = new AddNewItemRequest();
        addNewItemRequest.setName(req.getParameter("itemName"));
        addNewItemRequest.setPrice(Double.parseDouble(req.getParameter("itemPrice")));
        addNewItemRequest.setStockQty(Integer.parseInt(req.getParameter("itemQty")));

        AddNewItemResponse response = inventoryService.addNewItem(addNewItemRequest);
        resp.getWriter().write(response.getDescription());

//        super.doPost(req, resp);
    }
}
