package kass_java.repository;

import kass_java.Model.Bill;

import java.util.*;

public class BillRepository {

    public List<Bill> getBills(Date startDate, Date endDate) {
        List<Bill> bills = new ArrayList<>();

        bills.add(new Bill(1, "Table 1", "Narantuya", "Dine In", "Complete", 12000));
        bills.add(new Bill(2, "Table 2", "Tuvshuu", "Take Away", "Paid", 8000));
        bills.add(new Bill(3, "Table 3", "Munkhbayar", "Delivery", "Pending", 9500));

        if (startDate != null && endDate != null) {
            System.out.println("DATE hoorond shuugdle: " + startDate + " ↔ " + endDate);
        } else {
            System.out.println("TODAY KASS BILL LIST");
        }

        return bills;
    }
}
