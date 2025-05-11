package com.klef.fsad.backend.service;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class RazorpayServiceImpl implements RazorpayService {

    public Map<String, Object> createOrder(double amount, String currency) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient("rzp_test_RefqIEzM75megk", "X6H7dX5llcvz8StgqainRh8F");

        // Prepare the order request
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", (int)(amount * 100)); // amount in paise
        orderRequest.put("currency", currency);
        orderRequest.put("payment_capture", 1);

        // Create the order
        Order order = razorpay.orders.create(orderRequest);

        // Create response map and put the required values
        Map<String, Object> response = new HashMap<>();
        response.put("id", order.get("id"));           // Extract order id
        response.put("amount", order.get("amount"));   // Extract order amount
        response.put("currency", order.get("currency")); // Extract currency

        return response; // Return the response map
    }
}