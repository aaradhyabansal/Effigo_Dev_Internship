package com.mapper.practice.Client;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class StripeClient {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    public StripeClient() {
        Stripe.apiKey = stripeApiKey;
    }

    public Customer createCustomer(String token, String email) {
        try {
            Map<String, Object> customerParams = new HashMap<>();
            customerParams.put("email", email);
            customerParams.put("source", token);
            return Customer.create(customerParams);
        } catch (StripeException e) {
            throw new RuntimeException("Failed to create Stripe customer: " + e.getMessage(), e);
        }
    }

    private Customer getCustomer(String id) throws StripeException {
        return Customer.retrieve(id);
    }

    public Charge chargeNewCard(String token, double amount) {
        try {
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int) (amount * 100)); // Convert to cents
            chargeParams.put("currency", "USD");
            chargeParams.put("source", token);
            return Charge.create(chargeParams);
        } catch (StripeException e) {
            throw new RuntimeException("Failed to charge new card: " + e.getMessage(), e);
        }
    }

    public Charge chargeCustomerCard(String customerId, int amount) {
        try {
            Customer customer = getCustomer(customerId);
            String sourceCard = customer.getDefaultSource();
            if (sourceCard == null) {
                throw new RuntimeException("No default card found for customer: " + customerId);
            }

            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", amount);
            chargeParams.put("currency", "USD");
            chargeParams.put("customer", customerId);

            return Charge.create(chargeParams);
        } catch (StripeException e) {
            throw new RuntimeException("Failed to charge customer card: " + e.getMessage(), e);
        }
    }
}
