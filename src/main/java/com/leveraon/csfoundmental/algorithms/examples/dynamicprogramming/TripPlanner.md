## Senior DP Interview Question: Optimal Road Trip Planner

### Problem Description

You are planning a road trip from a `start` city to a `destination` city. Along the way, there are several "points of interest" (POIs) that you can visit. Each POI can be either:

1.  An **Attraction**: Visiting it earns you a certain number of `points`.
2.  A **Gas Station**: You can refuel here. Each gas station has a specific `price_per_liter`.

You are given a fixed sequence of `N` locations along your route, starting with the `start` city (index 0) and ending with the `destination` city (index `N-1`). Some intermediate locations might be attractions, some might be gas stations, and some might be both or just a point on the route.

Your car has a maximum `fuel_tank_capacity` (in liters) and a `fuel_efficiency` (km/liter). You start the trip with `initial_fuel` in your tank and a `total_budget`.

**Your Goal:** Maximize the total `points` collected from visiting attractions, ensuring you arrive at the `destination` city with any amount of fuel, without exceeding your `total_budget`.

**Constraints:**

*   You must travel sequentially along the given route (no skipping intermediate locations to jump further down the route unless explicitly stated as an option).
*   You cannot run out of fuel between locations.
*   You can choose to visit an attraction or not. If you visit, you get the points.
*   At a gas station, you can choose to refuel any amount from your current fuel level up to `fuel_tank_capacity`. The cost is `liters_bought * price_per_liter`.
*   You cannot go over your `total_budget`.

### Input Format

*   `N`: Total number of locations (including start and destination).
*   `locations`: A list of `N` objects, where each object represents a location:
    *   `id`: Unique identifier for the location.
    *   `type`: "Start", "Attraction", "GasStation", "Destination". Can be "Attraction" AND "GasStation" (e.g., `["Attraction", "GasStation"]`).
    *   `points`: (Optional, if type includes "Attraction") Integer points for visiting.
    *   `price_per_liter`: (Optional, if type includes "GasStation") Double cost per liter.
*   `distances`: A list of `N-1` integers, `distances[i]` is the distance in km from `locations[i]` to `locations[i+1]`.
*   `fuel_tank_capacity`: Integer.
*   `fuel_efficiency`: Integer (km per liter).
*   `initial_fuel`: Integer (liters).
*   `total_budget`: Integer (currency units).

### Output Format

Return an integer: the maximum total `points` achievable. If it's impossible to reach the destination within the budget, return -1.

### Example

Let's simplify for an example.
`N = 4` locations: `L0 (Start) -> L1 (Attraction) -> L2 (Gas Station) -> L3 (Destination)`

`locations = [`
    `{id: 0, type: "Start"},`
    `{id: 1, type: "Attraction", points: 10},`
    `{id: 2, type: "GasStation", price_per_liter: 2.0},`
    `{id: 3, type: "Destination"}`
`]`

`distances = [100, 50, 75]` (L0->L1: 100km, L1->L2: 50km, L2->L3: 75km)
`fuel_tank_capacity = 100` liters
`fuel_efficiency = 10` km/liter
`initial_fuel = 50` liters
`total_budget = 100`

**Analysis for Example:**

*   Fuel needed L0->L1: 100km / 10 km/L = 10L
*   Fuel needed L1->L2: 50km / 10 km/L = 5L
*   Fuel needed L2->L3: 75km / 10 km/L = 7.5L (let's assume fuel amounts can be decimals or round appropriately, for simplicity here let's assume `fuel_efficiency` and `distances` always result in integer liters)

**Initial State:** At L0, fuel=50, budget=100, points=0.

**Path 1 (Visit L1, Refuel at L2):**
1.  **L0 -> L1:**
    *   Travel 100km. Fuel consumed: 10L.
    *   Arrive at L1 with fuel: 50 - 10 = 40L. Cost: 0.
    *   Visit L1: Points = 10. Total points: 10.
2.  **L1 -> L2:**
    *   Travel 50km. Fuel consumed: 5L.
    *   Arrive at L2 with fuel: 40 - 5 = 35L. Cost: 0. Points: 10.
    *   At L2 (Gas Station, price 2.0): Need to reach L3 (75km -> 7.5L fuel). Have 35L.
        *   Option: Refuel 0L. Fuel: 35L. Cost: 0. Total cost: 0.
        *   Option: Refuel to 100L. Buy 65L (100-35). Cost: 65 * 2.0 = 130. Total cost: 130. (Exceeds budget)
        *   Option: Buy just enough to reach L3, plus a bit more for safety/flexibility. Need at least 7.5L. If we refuel, say, 10L.
            *   Buy 10L. Fuel becomes: 35 + 10 = 45L. Cost: 10 * 2.0 = 20. Total cost: 20. Points: 10.
3.  **L2 -> L3 (Destination):**
    *   Travel 75km. Fuel consumed: 7.5L.
    *   Arrive at L3 with fuel: 45 - 7.5 = 37.5L. Cost: 20. Points: 10.
    *   Total points: 10. Total cost: 20. (Within budget 100).

**Path 2 (Don't visit L1, Refuel at L2 optimally):**
... (Similar calculation, but L1 points not added)

**The challenge is to explore all valid refueling and attraction visiting options to find the *maximum* points.**

### Hint for Senior Engineers (if they get stuck on state definition)

Think about what information you *absolutely need* at each location to make future decisions. You'll likely need to know:
1.  Which location you are currently at.
2.  How much fuel is currently in your tank.
3.  How much money you have spent so far.

This often suggests a 3D DP state. However, the `budget_spent` dimension can be very large. A common technique for such problems is to *flip* the optimization: instead of `dp[i][fuel][budget_spent] = max_points`, consider `dp[i][fuel][points_collected] = min_cost`. Then, after filling the DP table, iterate through all possible `(fuel, points)` states at the destination to find the `max_points` within the `total_budget`.

### Expected Solution Approach (High-Level)

1.  **State Definition:**
    `dp[location_idx][current_fuel_liters]` would store the `(max_points, min_cost_for_those_points)`.
    `dp` table initialized with `(0, 0)` for `dp[0][initial_fuel]` and `(-1, infinity)` for all others (where -1 points means unreachable).

    *   `location_idx`: `0` to `N-1`.
    *   `current_fuel_liters`: `0` to `fuel_tank_capacity`.

2.  **Base Case:** `dp[0][initial_fuel] = (0, 0)`.

3.  **Transitions:** Iterate through `location_idx` from `0` to `N-2`. For each `location_idx`, and for each possible `current_fuel_liters`:

    *   If `dp[location_idx][current_fuel_liters]` is a valid state (reachable, not `(-1, infinity)`):
        *   Extract `(current_points, current_cost)`.
        *   **Option A: Consider next location `location_idx + 1`**
            *   Calculate fuel required for `dist = distances[location_idx]`.
            *   If `current_fuel_liters >= fuel_required`:
                *   `new_fuel = current_fuel_liters - fuel_required`.
                *   `new_points = current_points` (initially).
                *   `new_cost = current_cost`.
                *   If `locations[location_idx + 1]` is an Attraction: `new_points += locations[location_idx + 1].points`.
                *   **Update `dp[location_idx + 1][new_fuel]`:** If `(new_points, new_cost)` is better than the current value in `dp[location_idx + 1][new_fuel]` (i.e., more points, or same points for less cost), update it.
        *   **Option B: Refuel at `location_idx` (if it's a Gas Station)**
            *   Iterate `liters_to_buy` from `0` to `fuel_tank_capacity - current_fuel_liters`.
            *   `refuel_cost = liters_to_buy * locations[location_idx].price_per_liter`.
            *   `new_fuel = current_fuel_liters + liters_to_buy`.
            *   `new_cost = current_cost + refuel_cost`.
            *   `new_points = current_points`.
            *   **Crucial:** This refueled state needs to be propagated. This could be viewed as a "self-loop" or an internal transition. After refueling, we are still at `location_idx` but with more fuel and higher cost. The update needs to consider this. A common way to handle this is a nested loop (similar to the unbounded knapsack or state propagation within a single node in a graph). Or, effectively, when you calculate Option A, you consider *all possible fuel levels* you could arrive at `location_idx` with, *and* all possible refuelings *at* `location_idx` *before* moving to `location_idx + 1`.

        A more robust approach for Option B:
        When processing `dp[location_idx][current_fuel_liters]`:
        1.  Consider moving to `location_idx + 1` (as above).
        2.  If `location_idx` is a Gas Station:
            *   For `f_fill` from `current_fuel_liters` to `fuel_tank_capacity`:
                *   `cost_to_fill = (f_fill - current_fuel_liters) * price_per_liter`.
                *   `total_cost_after_fill = current_cost + cost_to_fill`.
                *   Now, from this `(current_points, total_cost_after_fill)` state at `location_idx` with `f_fill` fuel, *then* consider moving to `location_idx + 1`. This effectively means the transition loop needs to consider both refueling *and then* traveling.

        This suggests a structure where `dp[i][j]` is updated based on `dp[i-1][k]` *and* `dp[i][k']`. This is common in Bellman-Ford-like DPs on a DAG.

4.  **Final Result:** After filling the `dp` table up to `N-1`, iterate through all `current_fuel_liters` at `dp[N-1][current_fuel_liters]`. Find the maximum `points` among all states where `current_cost <= total_budget`.

### Time and Space Complexity

*   **Time:** `O(N * F * F_refuel)` where `N` is locations, `F` is `fuel_tank_capacity`, and `F_refuel` is the range of refueling options (can also be `F`). So, roughly `O(N * F^2)`.
*   **Space:** `O(N * F)` to store the `dp` table.

### Senior-level Discussion Points

*   **Trade-offs:** How would the problem change if we could visit attractions in any order? (Becomes a Traveling Salesperson-like problem, much harder).
*   **Optimization:** Can we optimize the `F_refuel` part? For a given `location_idx` and `current_fuel_liters`, to reach `location_idx+1`, we only need enough fuel. We should only refuel what's strictly necessary or what maximizes future optionality (which is typically just filling up to capacity, or just enough to reach the next gas station if there's a cheaper one). Can this greedy choice reduce `F_refuel` to `O(1)` or `O(log F)`? (Often, no, because prices change and you might want to buy extra now if it's cheap).
*   **Edge Cases:** What if `initial_fuel` is too low to even reach the first gas station? What if `total_budget` is 0? What if `fuel_efficiency` is 0?
*   **Real-world factors:** How would you handle variable fuel prices along the route that fluctuate over time? How about road closures or unexpected detours? Driver fatigue / time limits? Multiple drivers with different skills?
*   **Alternative DP state:** Could we use `dp[location_idx][budget_spent]` to store `(max_points, current_fuel)`? This is viable if `total_budget` is small, but often large for money. This is why flipping the problem to `min_cost` for `max_points` is better when budget is high but points/fuel are constrained.
*   **Data Structures:** What data structure would you use for `dp[location_idx][current_fuel_liters]` if you need to store multiple `(points, cost)` pairs for a given `(location, fuel)` state? (e.g., a `SortedSet` or `Map` to keep track of the non-dominated Pareto optimal solutions).

---

This problem challenges a senior engineer to correctly model a multi-objective optimization problem with sequential decision-making, manage state variables carefully, and consider performance and real-world implications.

---
Of course. This problem is a classic dynamic programming challenge. We need to keep track of the best possible state (maximum points for a minimum cost) at each location for each possible fuel level.

### 1. Problem Analysis and DP State Definition

The core of the problem is making a sequence of decisions at each location to maximize a final score (points), while respecting constraints on resources (fuel and budget). This structure is a perfect fit for Dynamic Programming.

Let's define our state. At any given location `i`, what information do we need to carry forward to make decisions for the next location `i+1`?
1.  **Current Location:** This will be the first dimension of our DP table, `i`.
2.  **Current Fuel:** The amount of fuel we have is crucial to determine if we can reach the next location and how much we can refuel. This will be the second dimension, `f`.

So, our DP state will be `dp[i][f]`. What should this state store? We are trying to maximize points, but we also have a budget constraint. A common technique in such problems is to have the DP table store the "cost" to achieve a certain state and then optimize for the primary goal.

However, a more direct approach is to store the primary goal (max points) and use a parallel table to track the secondary constraint (min cost).

*   `dp[i][f]`: The **maximum points** you can accumulate upon arriving at location `i` with exactly `f` liters of fuel.
*   `cost[i][f]`: The **minimum budget spent** to achieve the `dp[i][f]` points upon arriving at location `i` with `f` liters of fuel.

We initialize `dp` with a value indicating an unreachable state (e.g., -1) and `cost` with an "infinite" value.

### 2. DP Transitions

The process involves iterating from location `i` to `i+1`. For each reachable state `(i, f_arrival)` (i.e., `dp[i][f_arrival] != -1`), we consider all possible actions at location `i` and calculate the resulting states at location `i+1`.

The actions at location `i` are:
1.  **Visit Attraction (or not):** This gives us two potential point totals.
2.  **Refuel (or not):** If it's a gas station, we can add any amount of fuel from 0 up to our tank's capacity.

The transition from `i` to `i+1` involves:
1.  Calculate the fuel needed to travel from `i` to `i+1`.
2.  For every possible state at `i` *after* making decisions (visiting, refueling), subtract the travel fuel to find the arrival fuel at `i+1`.
3.  Update the `dp[i+1]` and `cost[i+1]` tables if we've found a better way to reach that state (more points, or same points for less cost).

### 3. Java Implementation

Here is a complete, well-commented Java solution that implements this logic.

#### Location and Car Data Structures

First, let's define simple classes to hold our input data, making the code cleaner.

```java
import java.util.Arrays;
import java.util.List;

// Represents a single location on the route
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

// Represents the car's properties and trip constraints
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

```

#### The Main Solver Class

This class contains the core dynamic programming logic.

```java
public class RoadTripPlanner {

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
                int[] pointOptions = (points_with_visit != -1) ?
                                     new int[]{points_no_visit, points_with_visit} :
                                     new int[]{points_no_visit};
                
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
    private static void travelToNext(List<Location> locations, Car car, int[][] dp, double[][] cost,
                                     int i, int f_depart, int points, double currentCost) {
        
        int distanceToTravel = locations.get(i + 1).distance - locations.get(i).distance;
        double fuelNeeded = distanceToTravel / car.fuelEfficiency;

        if (f_depart >= fuelNeeded) {
            // It's possible to use fractional fuel, but our DP state is integer.
            // A common approach is to be pessimistic and assume we use the ceiling,
            // but for accuracy, we can keep track of fractional fuel and floor it for the DP index.
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

        Car car = new Car(
            50,     // fuelTankCapacity (liters)
            10.0,   // fuelEfficiency (km/liter)
            25,     // initialFuel (liters)
            100.0   // totalBudget ($)
        );

        int maxPoints = solve(locations, car);

        if (maxPoints != -1) {
            System.out.println("Maximum points achievable: " + maxPoints);
        } else {
            System.out.println("Destination is unreachable with the given constraints.");
        }
        
        // Expected Logic Walkthrough for the example:
        // 1. Start at loc 0 with 25L fuel, 0 points, $100 budget.
        //    - Option: Visit attraction (+10 points). Points = 10.
        //    - Option: Refuel. To reach A (150km), we need 15L fuel. We have 25L, so it's possible.
        //    - Let's say we don't refuel. Depart with 25L.
        // 2. Arrive at A (150km) with 25 - 15 = 10L fuel. Points = 10.
        //    - Must visit attraction (+50 points). Total Points = 60.
        //    - No gas station. Depart with 10L.
        // 3. To reach B (150km away), we need 15L fuel. We only have 10L. This path fails.
        //
        // Let's try another path:
        // 1. Start at loc 0 with 25L.
        //    - Visit attraction (+10 points). Points = 10.
        //    - Refuel. To be safe, let's fill up to 50L. We need 25L. Cost = 25 * 1.50 = $37.5. Budget left = $62.5.
        // 2. Arrive at A with 50 - 15 = 35L fuel. Points = 10, Cost = $37.5.
        //    - Visit attraction (+50 points). Total Points = 60.
        //    - Depart with 35L.
        // 3. Arrive at B (150km away) with 35 - 15 = 20L fuel. Points = 60, Cost = $37.5.
        //    - No attraction. Gas station is cheaper ($1.35). Let's fill up again.
        //    - Add 30L to get to 50L. Cost = 30 * 1.35 = $40.5. Total cost = 37.5 + 40.5 = $78. Budget left = $22.
        // 4. Arrive at Destination (100km away) with 50 - 10 = 40L fuel. Points = 60, Cost = $78.
        //    - Visit final attraction (+20 points). Total = 80.
        // This is a valid path. The DP will explore all such paths to find the maximum.
        // Correct answer for this example: 10 (start) + 50 (A) + 20 (dest) = 80
    }
}

```