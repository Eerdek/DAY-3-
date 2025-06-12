package kass_java.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service class for handling checkout and payment operations in the KASS Java POS System.
 * This class manages payment processing, change calculation, and payment record storage.
 * Payment records are stored in-memory using a HashMap with main IDs as keys.
 * 
 * @author KASS Java POS System
 * @version 1.0
 * @since 2025-06-12
 */
public class CheckoutService {

    /**
     * In-memory storage for payment records using HashMap.
     * Maps main ID (Integer) to PaymentRecord objects for quick retrieval.
     */
    private final Map<Integer, PaymentRecord> payments = new HashMap<>();

    /**
     * Completes a payment transaction and stores the payment record.
     * This method validates that the received amount is sufficient to cover the total,
     * calculates the change due, and creates a payment record with "Paid" status.
     * 
     * @param mainId the unique identifier for this payment transaction
     * @param total the total amount due for the transaction
     * @param received the amount of money received from the customer
     * @return true if the payment was completed successfully
     * @throws IllegalArgumentException if the received amount is less than the total amount due
     */
   public boolean completePayment(int mainId, double total, double received) {
    if (received < total) {
        throw new IllegalArgumentException("Received amount is less than total.");
    }
    double change = Math.abs(received - total);
    payments.put(mainId, new PaymentRecord(total, received, change, "Paid"));
    return true;
  }

    /**
     * Calculates the change amount to be returned to the customer.
     * This method computes the absolute difference between the received amount and total.
     * Note: Uses Math.abs() which may not handle negative changes correctly in all scenarios.
     * 
     * @param total the total amount due for the transaction
     * @param received the amount of money received from the customer
     * @return the absolute difference between received and total amounts
     */
    public double calculateChange(double total, double received) {
        return Math.abs(received - total);
    }

    /**
     * Retrieves a payment record by its main ID.
     * This method looks up and returns the payment record associated with the given main ID.
     * 
     * @param mainId the unique identifier of the payment transaction to retrieve
     * @return the PaymentRecord associated with the main ID, or null if no record exists
     */
    public PaymentRecord getPayment(int mainId) {
        return payments.get(mainId);
    }

    /**
     * Immutable record class representing a completed payment transaction.
     * This static nested class encapsulates all payment-related information including
     * the transaction amounts, calculated change, and payment status.
     * All fields are final to ensure immutability after creation.
     * 
     * @author KASS Java POS System
     * @version 1.0
     * @since 2025-06-12
     */
    public static class PaymentRecord {
        /**
         * The total amount due for the transaction.
         */
        private final double total;
        
        /**
         * The amount of money received from the customer.
         */
        private final double received;
        
        /**
         * The change amount to be returned to the customer.
         */
        private final double change;
        
        /**
         * The status of the payment (e.g., "Paid", "Pending", etc.).
         */
        private final String status;

        /**
         * Constructs a new PaymentRecord with the specified payment details.
         * All parameters are stored as immutable fields in this record.
         * 
         * @param total the total amount due for the transaction
         * @param received the amount of money received from the customer
         * @param change the change amount to be returned to the customer
         * @param status the status of the payment transaction
         */
        public PaymentRecord(double total, double received, double change, String status) {
            this.total = total;
            this.received = received;
            this.change = change;
            this.status = status;
        }

        /**
         * Gets the total amount due for the transaction.
         * 
         * @return the total amount due
         */
        public double getTotal() { return total; }
        
        /**
         * Gets the amount of money received from the customer.
         * 
         * @return the received amount
         */
        public double getReceived() { return received; }
        
        /**
         * Gets the change amount to be returned to the customer.
         * 
         * @return the change amount
         */
        public double getChange() { return change; }
        
        /**
         * Gets the status of the payment transaction.
         * 
         * @return the payment status (e.g., "Paid", "Pending")
         */
        public String getStatus() { return status; }
    }
}
