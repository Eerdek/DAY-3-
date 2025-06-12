package kass_java.repository;

import java.util.*;

import kass_java.Model.Bill;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Repository class for managing Bill entities.
 * This class provides operations for retrieving bills with date filtering capabilities.
 * Currently uses mock data for demonstration purposes.
 * 
 * @author KASS Java POS System
 * @version 1.0
 * @since 2025-06-12
 */
public class BillRepository {

    private static final Logger logger = LogManager.getLogger(BillRepository.class);

    /**
     * Retrieves bills within a specified date range.
     * Currently returns mock data for demonstration purposes.
     * The method supports date filtering and logs the operation details.
     * 
     * @param startDate the start date for filtering bills (inclusive). Can be null to ignore start date filtering.
     * @param endDate the end date for filtering bills (inclusive). Can be null to ignore end date filtering.
     * @return a List of Bill objects. Returns mock bills regardless of date parameters in current implementation.
     *         The list contains sample bills with different statuses (Complete, Paid, Pending) and order types.
     */
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
