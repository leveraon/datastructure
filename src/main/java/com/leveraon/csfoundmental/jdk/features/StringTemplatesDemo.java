package com.leveraon.csfoundmental.jdk.features;

public class StringTemplatesDemo {
	public static void main(String[] args) {
	        String name = "Alice";
	        String product = "Laptop";
	        double price = 1200.50;
	        int quantity = 2;
	        Instant now = Instant.now();

	        // Using the default STR template processor
	        String message = STR."Hello, \{name}! Your order for \{quantity} \{product}s totaling \{price * quantity} is confirmed at \{now}.";
	        System.out.println(message);

	        // Multi-line string templates (using text blocks)
	        String orderSummary = STR."""
	            Order Details:
	            Customer: \{name}
	            Item: \{product} (x\{quantity})
	            Unit Price: \{price}
	            Total: \{price * quantity}
	            Order Time: \{now}
	            """;
	        System.out.println(orderSummary);

	        // You can use any valid Java expression inside the template
	        int a = 10;
	        int b = 20;
	        String mathResult = STR."The sum of \{a} and \{b} is \{a + b}. The product is \{a * b}.";
	        System.out.println(mathResult);

	        // (Future use with other template processors, e.g., for SQL or JSON)
	        // SQL_TEMPLATE."SELECT * FROM users WHERE name = '\{userName}' AND age > \{userAge}";
	        // JSON_TEMPLATE."""{"name": "\{name}", "age": \{age}}""";
	    }
}
