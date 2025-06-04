package kass_java.service;

import kass_java.Model.Order;
import kass_java.Model.Table;
import kass_java.repository.TableRepository;

import java.util.*;

/**
 * Service to manage orders by table
 */
public class TableOrderService {
    private final OrderService orderService;
    private final TableRepository tableRepository;
    
    
    private final Map<String, List<Integer>> tableOrdersMap = new HashMap<>();
    
    public TableOrderService(OrderService orderService, TableRepository tableRepository) {
        this.orderService = orderService;
        this.tableRepository = tableRepository;
    }
    
    /**
     * Associate an order with a table
     */
    public boolean assignOrderToTable(int orderId, String tableName) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return false;
        }
        
        Table table = tableRepository.getByName(tableName);
        if (table == null) {
            return false;
        }
        
        
        table.assignOrder(orderId);
        tableRepository.save(table);
        
        
        if (!tableOrdersMap.containsKey(tableName)) {
            tableOrdersMap.put(tableName, new ArrayList<>());
        }
        List<Integer> tableOrders = tableOrdersMap.get(tableName);
        if (!tableOrders.contains(orderId)) {
            tableOrders.add(orderId);
        }
        
        return true;
    }
    
    /**
     * Get all orders for a specific table
     */
    public List<Order> getOrdersByTable(String tableName) {
        List<Order> result = new ArrayList<>();
        
        if (tableOrdersMap.containsKey(tableName)) {
            for (Integer orderId : tableOrdersMap.get(tableName)) {
                Order order = orderService.getOrderById(orderId);
                if (order != null) {
                    result.add(order);
                }
            }
        }
        
        return result;
    }
    
    /**
     * Close an order and update table status if all orders are completed
     */
    public boolean completeOrder(int orderId, String tableName) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return false;
        }
        
        
        order.setStatus("Completed");
        
        
        if (tableOrdersMap.containsKey(tableName)) {
            tableOrdersMap.get(tableName).remove(Integer.valueOf(orderId));
            
            
            if (tableOrdersMap.get(tableName).isEmpty()) {
                Table table = tableRepository.getByName(tableName);
                if (table != null) {
                    table.releaseTable();
                    tableRepository.save(table);
                }
            }
        }
        
        return true;
    }
    
    /**
     * Get current active order for a table
     */
    public Order getCurrentTableOrder(String tableName) {
        Table table = tableRepository.getByName(tableName);
        if (table != null && table.getStatus().equals("Occupied")) {
            return orderService.getOrderById(table.getCurrentOrderId());
        }
        return null;
    }
    
    /**
     * Get all tables with their current orders
     */
    public Map<Table, Order> getAllTablesWithOrders() {
        Map<Table, Order> result = new HashMap<>();
        List<Table> allTables = tableRepository.getAllTables();
        
        for (Table table : allTables) {
            if (table.getStatus().equals("Occupied")) {
                Order currentOrder = orderService.getOrderById(table.getCurrentOrderId());
                result.put(table, currentOrder);
            } else {
                result.put(table, null);
            }
        }
        
        return result;
    }
}
