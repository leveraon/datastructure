Merge Sort is a highly efficient, comparison-based sorting algorithm. Its complexity is very consistent across different input scenarios.

Here's a breakdown:

### Time Complexity

Merge Sort always divides the list into two halves, recursively sorts them, and then merges them. This leads to a very predictable performance:

*   **Best-Case Time Complexity:** **O(n log n)**
    *   Even if the array is already sorted, Merge Sort will still go through the entire process of dividing and merging, resulting in O(n log n) operations.
*   **Average-Case Time Complexity:** **O(n log n)**
    *   On average, the performance remains consistent.
*   **Worst-Case Time Complexity:** **O(n log n)**
    *   Unlike Quick Sort, Merge Sort's performance does not degrade to O(n^2) for specific input patterns (like already sorted or reverse-sorted arrays). This makes it very reliable.

**How O(n log n) is derived:**

1.  **Divide:** Splitting the array into two halves takes constant time, O(1).
2.  **Conquer (Recursion):** You recursively sort two subproblems, each of size `n/2`. This contributes `2 * T(n/2)`.
3.  **Combine (Merge):** Merging the two sorted halves takes linear time, O(n), because you have to examine every element once to place it correctly into the merged array.

This leads to the recurrence relation: `T(n) = 2T(n/2) + O(n)`
Solving this recurrence relation (e.g., using the Master Theorem or expansion) yields **O(n log n)**.

### Space Complexity

*   **Space Complexity:** **O(n)** (Auxiliary Space)
    *   Merge Sort requires an auxiliary (temporary) array of size `n` to perform the merging step. This is because merging two sorted lists generally requires a separate space to store the merged result before copying it back (or directly into) the original array.
    *   The recursion stack depth is O(log n), but the dominant factor for space complexity is the O(n) auxiliary array.

### Summary Table

| Complexity Type | Best-Case | Average-Case | Worst-Case |
| :-------------- | :-------- | :----------- | :--------- |
| **Time**        | O(n log n) | O(n log n)   | O(n log n) |
| **Space**       | O(n)      | O(n)         | O(n)       |
| (Auxiliary)     |           |              |            |

### Key Characteristics

*   **Stable:** Yes, Merge Sort is a stable sorting algorithm, meaning that the relative order of equal elements is preserved.
*   **Not In-Place:** Standard implementations require O(n) extra space, making it not an in-place sort. (Though in-place merge sort algorithms exist, they are significantly more complex and often have higher constant factors or worse practical performance).
*   **External Sorting:** It is well-suited for external sorting (sorting data that does not fit entirely into memory) due to its linear-time merging behavior.
*   **Parallelizable:** The "divide" step makes it relatively easy to parallelize.