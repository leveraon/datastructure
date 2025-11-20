package com.leveraon.csfoundmental.algorithms.examples.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

//Represents a single location on the route
class Location {
	int distance; // Distance from the start city (in km)
	int attractionPoints; // Points gained if it's an attraction (0 otherwise)
	double gasPricePerLiter; // Price per liter if it's a gas station (or a sentinel value)

	public Location(int distance, int attractionPoints, double gasPricePerLiter) {
		this.distance = distance;
		this.attractionPoints = attractionPoints;
		this.gasPricePerLiter = gasPricePerLiter;
	}

	public boolean isAttraction() {
		return attractionPoints > 0;
	}

	public boolean isGasStation() {
		return gasPricePerLiter > 0;
	}
}

//Represents the car's properties and trip constraints
class Car {
	int fuelTankCapacity; // in liters
	double fuelEfficiency; // in km/liter
	int initialFuel; // in liters
	double totalBudget;

	public Car(int fuelTankCapacity, double fuelEfficiency, int initialFuel, double totalBudget) {
		this.fuelTankCapacity = fuelTankCapacity;
		this.fuelEfficiency = fuelEfficiency;
		this.initialFuel = initialFuel;
		this.totalBudget = totalBudget;
	}
}

public class TripPlanner {
	// Helper to update the DP tables.
	// We update a state (i, f) if we find a new path with:
	// 1. More points.
	// 2. The same points but a lower cost.
	private static void updateState(int[][] dp, double[][] cost, int i, int fuel, int points, double currentCost) {
		if (points > dp[i][fuel] || (points == dp[i][fuel] && currentCost < cost[i][fuel])) {
			dp[i][fuel] = points;
			cost[i][fuel] = currentCost;
		}
	}

	public static int solve(List<Location> locations, Car car) {
		int n = locations.size();
		int capacity = car.fuelTankCapacity;

		// dp[i][f] = max points to arrive at location i with f fuel
		int[][] dp = new int[n][capacity + 1];
		// cost[i][f] = min cost to achieve dp[i][f]
		double[][] cost = new double[n][capacity + 1];

		// Initialize DP tables
		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], -1); // -1 signifies an unreachable state
			Arrays.fill(cost[i], Double.POSITIVE_INFINITY);
		}

		// Base case: At the start city (location 0)
		dp[0][car.initialFuel] = 0; // 0 points collected initially
		cost[0][car.initialFuel] = 0.0; // 0 cost incurred initially

		// --- Main DP Loop ---
		for (int i = 0; i < n - 1; i++) {
			// Iterate through all possible fuel levels upon arrival at location i
			for (int f_arrival = 0; f_arrival <= capacity; f_arrival++) {
				// If this state is unreachable, skip it
				if (dp[i][f_arrival] == -1) {
					continue;
				}

				// --- Step 1: Decisions at Location i ---

				// Option A: Don't visit the attraction at location i (or it's not one)
				int points_no_visit = dp[i][f_arrival];

				// Option B: Visit the attraction at location i (if it is one)
				int points_with_visit = -1; // Sentinel
				if (locations.get(i).isAttraction()) {
					points_with_visit = dp[i][f_arrival] + locations.get(i).attractionPoints;
				}

				// --- Step 2: Refueling and Transition to Location i+1 ---

				// Iterate through both point scenarios (visiting and not visiting)
				int[] pointOptions = (points_with_visit != -1) ? new int[] { points_no_visit, points_with_visit }
						: new int[] { points_no_visit };

				for (int currentPoints : pointOptions) {
					// Now, for a given point total at i, consider refueling options
					if (locations.get(i).isGasStation()) {
						// Can refuel any amount up to full tank
						for (int f_depart = f_arrival; f_depart <= capacity; f_depart++) {
							double fuelAdded = f_depart - f_arrival;
							double refuelingCost = fuelAdded * locations.get(i).gasPricePerLiter;
							double newTotalCost = cost[i][f_arrival] + refuelingCost;

							if (newTotalCost <= car.totalBudget) {
								travelToNext(locations, car, dp, cost, i, f_depart, currentPoints, newTotalCost);
							}
						}
					} else {
						// Not a gas station, can't refuel. Departure fuel is arrival fuel.
						travelToNext(locations, car, dp, cost, i, f_arrival, currentPoints, cost[i][f_arrival]);
					}
				}
			}
		}

		// --- Step 3: Find the Final Answer ---
		// After the loop, find the max points at the destination city (n-1)
		// across all possible final fuel levels.
		// We also need to account for the decision at the final destination.
		int maxPoints = -1;
		Location destination = locations.get(n - 1);

		for (int f = 0; f <= capacity; f++) {
			if (dp[n - 1][f] != -1) {
				int finalPoints = dp[n - 1][f];
				if (destination.isAttraction()) {
					finalPoints += destination.attractionPoints; // Visit attraction at destination
				}
				maxPoints = Math.max(maxPoints, finalPoints);
			}
		}

		return maxPoints;
	}

	// Helper method to handle the travel logic from i to i+1
	private static void travelToNext(List<Location> locations, Car car, int[][] dp, double[][] cost, int i,
			int f_depart, int points, double currentCost) {

		int distanceToTravel = locations.get(i + 1).distance - locations.get(i).distance;
		double fuelNeeded = distanceToTravel / car.fuelEfficiency;

		if (f_depart >= fuelNeeded) {
			// It's possible to use fractional fuel, but our DP state is integer.
			// A common approach is to be pessimistic and assume we use the ceiling,
			// but for accuracy, we can keep track of fractional fuel and floor it for the
			// DP index.
			// Let's keep it simple and assume fuel is discrete for the DP table.
			// We'll cast down, assuming we only need floor(fuel) to be in the tank.
			// A more robust solution might scale fuel by 10 or 100 to handle fractions.
			int fuelAfterTravel = (int) Math.floor(f_depart - fuelNeeded);

			updateState(dp, cost, i + 1, fuelAfterTravel, points, currentCost);
		}
	}

	public static void main(String[] args) {
		// --- Example Usage ---
		// Route: Start -> A -> B -> Destination
		Location start = new Location(0, 10, 1.50); // Start is at 0km, has an attraction, and a gas station
		Location poiA = new Location(150, 50, 0); // 150km, attraction only
		Location poiB = new Location(300, 0, 1.35); // 300km, gas station only
		Location destination = new Location(400, 20, 0); // 400km, final attraction

		List<Location> locations = List.of(start, poiA, poiB, destination);

		Car car = new Car(50, // fuelTankCapacity (liters)
				10.0, // fuelEfficiency (km/liter)
				25, // initialFuel (liters)
				100.0 // totalBudget ($)
		);

		int maxPoints = solve(locations, car);

		if (maxPoints != -1) {
			System.out.println("Maximum points achievable: " + maxPoints);
		} else {
			System.out.println("Destination is unreachable with the given constraints.");
		}

		// Expected Logic Walkthrough for the example:
		// 1. Start at loc 0 with 25L fuel, 0 points, $100 budget.
		// - Option: Visit attraction (+10 points). Points = 10.
		// - Option: Refuel. To reach A (150km), we need 15L fuel. We have 25L, so it's
		// possible.
		// - Let's say we don't refuel. Depart with 25L.
		// 2. Arrive at A (150km) with 25 - 15 = 10L fuel. Points = 10.
		// - Must visit attraction (+50 points). Total Points = 60.
		// - No gas station. Depart with 10L.
		// 3. To reach B (150km away), we need 15L fuel. We only have 10L. This path
		// fails.
		//
		// Let's try another path:
		// 1. Start at loc 0 with 25L.
		// - Visit attraction (+10 points). Points = 10.
		// - Refuel. To be safe, let's fill up to 50L. We need 25L. Cost = 25 * 1.50 =
		// $37.5. Budget left = $62.5.
		// 2. Arrive at A with 50 - 15 = 35L fuel. Points = 10, Cost = $37.5.
		// - Visit attraction (+50 points). Total Points = 60.
		// - Depart with 35L.
		// 3. Arrive at B (150km away) with 35 - 15 = 20L fuel. Points = 60, Cost =
		// $37.5.
		// - No attraction. Gas station is cheaper ($1.35). Let's fill up again.
		// - Add 30L to get to 50L. Cost = 30 * 1.35 = $40.5. Total cost = 37.5 + 40.5 =
		// $78. Budget left = $22.
		// 4. Arrive at Destination (100km away) with 50 - 10 = 40L fuel. Points = 60,
		// Cost = $78.
		// - Visit final attraction (+20 points). Total = 80.
		// This is a valid path. The DP will explore all such paths to find the maximum.
		// Correct answer for this example: 10 (start) + 50 (A) + 20 (dest) = 80
	}
}
