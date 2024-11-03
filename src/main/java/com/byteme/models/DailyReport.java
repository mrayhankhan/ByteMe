// DailyReport.java
package com.byteme.models;

import java.util.*;

public class DailyReport {
    private String reportId;
    private Date date;
    private double totalSales;
    private int totalOrders;
    private Map<MenuItem, Integer> itemsSold;
    private List<MenuItem> popularItems;

    public DailyReport(String reportId) {
        this.reportId = reportId;
        this.date = new Date();
        this.totalSales = 0.0;
        this.totalOrders = 0;
        this.itemsSold = new HashMap<>();
        this.popularItems = new ArrayList<>();
    }

    public void addOrder(Order order) {
        totalSales += order.getTotalAmount();
        totalOrders++;
        
        for (MenuItem item : order.getItems()) {
            itemsSold.merge(item, 1, Integer::sum);
        }
        
        updatePopularItems();
    }

    private void updatePopularItems() {
        popularItems = new ArrayList<>(itemsSold.keySet());
        popularItems.sort((a, b) -> itemsSold.get(b).compareTo(itemsSold.get(a)));
        if (popularItems.size() > 5) {
            popularItems = popularItems.subList(0, 5);
        }
    }

    // Getters
    public String getReportId() { return reportId; }
    public Date getDate() { return date; }
    public double getTotalSales() { return totalSales; }
    public int getTotalOrders() { return totalOrders; }
    public Map<MenuItem, Integer> getItemsSold() { return new HashMap<>(itemsSold); }
    public List<MenuItem> getPopularItems() { return new ArrayList<>(popularItems); }

    @Override
    public String toString() {
        StringBuilder report = new StringBuilder();
        report.append("=== Daily Report ===\n");
        report.append("Date: ").append(date).append("\n");
        report.append("Total Sales: $").append(String.format("%.2f", totalSales)).append("\n");
        report.append("Total Orders: ").append(totalOrders).append("\n");
        report.append("\nTop 5 Popular Items:\n");
        for (MenuItem item : popularItems) {
            report.append(item.getName())
                  .append(" - Sold: ")
                  .append(itemsSold.get(item))
                  .append("\n");
        }
        return report.toString();
    }
}