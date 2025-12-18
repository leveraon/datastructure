package com.leveraon.csfoundmental.jdk.features;

public class UnnamedPatternsDemo {
	enum Status {
		SUCCESS, ERROR, WARNING
	}

	record Event(String type, Object payload) {
	}

	public static void main(String[] args) {
//	        // 1. Unnamed variable in a catch block (already allowed, but now officially a variable _name_)
//	        try {
//	            int num = Integer.parseInt("abc");
//	        } catch (NumberFormatException _) { // We only care that it's a NumberFormatException, not the object itself
//	            System.out.println("Caught a NumberFormatException, value ignored.");
//	        }
//
//	        // 2. Unnamed pattern in a switch statement
//	        Status currentStatus = Status.WARNING;
//	        switch (currentStatus) {
//	            case SUCCESS -> System.out.println("Operation was successful.");
//	            case ERROR   -> System.out.println("An error occurred.");
//	            case _       -> System.out.println("Unhandled or ignored status type: " + currentStatus); // Matches any other Status enum constant
//	        }
//
//	        // 3. Unnamed pattern in a record destructuring (similar to JEP 440, but ignoring parts)
//	        Object obj = new Event("Login", "user123");
//	        if (obj instanceof Event("Login", _)) { // We only care about the type "Login", not the payload
//	            System.out.println("Login event detected, payload ignored.");
//	        }
//	        if (obj instanceof Event(String type, String _)) { // Match on payload type, but ignore the value
//	            System.out.println("Event of type '" + type + "' with a String payload (value ignored).");
//	        }
	    }
}
