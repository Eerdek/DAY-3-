package kass_java.repository;

import java.util.*;

import kass_java.Model.Bill;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BillRepository {

    private static final Logger logger = LogManager.getLogger(BillRepository.class);

    public List<Bill> getBills(Date startDate, Date endDate) {
        List<Bill> bills = new ArrayList<>();

        // Mock data
        bills.add(new Bill(1, "Table 1", "Narantuya", "Dine In", "Complete", 12000));
        bills.add(new Bill(2, "Table 2", "Tuvshuu", "Take Away", "Paid", 8000));
        bills.add(new Bill(3, "Table 3", "Munkhbayar", "Delivery", "Pending", 9500));

        if (startDate != null && endDate != null) {
            logger.info(" Filtering bills between {} and {}", startDate, endDate);
        } else {
            logger.info(" Fetching today's bills (no date filter)");
        }

        logger.info(" Total bills retrieved: {}", bills.size());
        return bills;
    }
}
