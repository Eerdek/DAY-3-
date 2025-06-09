package kass_java.service;

import java.util.HashMap;
import java.util.Map;

public class CheckoutService {

    private final Map<Integer, PaymentRecord> payments = new HashMap<>();

   public boolean completePayment(int mainId, double total, double received) {
    if (received < total) {
        throw new IllegalArgumentException("Received amount is less than total.");
    }
    double change = Math.abs(received - total);
    payments.put(mainId, new PaymentRecord(total, received, change, "Paid"));
    return true;
  }


    public double calculateChange(double total, double received) {
        return Math.abs(received - total);
    }

    public PaymentRecord getPayment(int mainId) {
        return payments.get(mainId);
    }

    public static class PaymentRecord {
        private final double total;
        private final double received;
        private final double change;
        private final String status;

        public PaymentRecord(double total, double received, double change, String status) {
            this.total = total;
            this.received = received;
            this.change = change;
            this.status = status;
        }

        public double getTotal() { return total; }
        public double getReceived() { return received; }
        public double getChange() { return change; }
        public String getStatus() { return status; }
    }
}
