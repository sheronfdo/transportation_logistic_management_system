package com.jamith.tlms.servlet;

import com.jamith.tlms.dto.request.inventoryService.AddNewItemRequest;
import com.jamith.tlms.dto.response.inventoryService.AddNewItemResponse;
import com.jamith.tlms.dto.response.inventoryService.GetItemsResponse;
import com.jamith.tlms.remote.InventoryService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
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


        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        System.out.println(action);

        try {
            switch (action) {
                case "/inventory/new":
                    addNewItem(req, resp);
                    break;
//                case "/insert":
//                    insertUser(req, resp);
//                    break;
//                case "/delete":
//                    deleteUser(req, resp);
//                    break;
//                case "/edit":
//                    showEditForm(req, resp);
//                    break;
//                case "/update":
//                    updateUser(req, resp);
//                    break;
                default:
                    listItem(req, resp);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    void addNewItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddNewItemRequest addNewItemRequest = new AddNewItemRequest();
        addNewItemRequest.setName(req.getParameter("itemName"));
        addNewItemRequest.setPrice(Double.parseDouble(req.getParameter("itemPrice")));
        addNewItemRequest.setStockQty(Integer.parseInt(req.getParameter("itemQty")));

        AddNewItemResponse response = inventoryService.addNewItem(addNewItemRequest);
        resp.getWriter().write(response.getDescription());
    }

    void listItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GetItemsResponse response = inventoryService.getItems(null);
        req.setAttribute("itemList", response.getInventoryList());
        RequestDispatcher dispatcher = req.getRequestDispatcher("inventoryItem.jsp");
        dispatcher.forward(req, resp);
    }
}
