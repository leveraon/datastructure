Insertion Sort is a simple sorting algorithm that builds the final sorted array (or list) one item at a time. It is much less efficient on large lists than more advanced algorithms such as Quick Sort, Merge Sort, or Heap Sort.

Here's a breakdown of its complexity:

### Time Complexity

Insertion Sort's time complexity depends heavily on the initial state of the input array.

*   **Best-Case Time Complexity:** **O(n)**
    *   This occurs when the array is already sorted. In this scenario, Insertion Sort iterates through the array, comparing each element with its preceding element. Since all elements are in their correct place, no shifts are required. It performs approximately `n` comparisons.

*   **Average-Case Time Complexity:** **O(n^2)**
    *   On average, for each element, it might need to shift roughly half of the previously sorted elements to find its correct position. The number of operations grows quadratically with the input size `n`.

*   **Worst-Case Time Complexity:** **O(n^2)**
    *   This occurs when the array is sorted in reverse order. For each element, Insertion Sort has to compare it with all previously sorted elements and shift all of them to make space. For an array of size `n`, the `i`-th element might require up to `i-1` comparisons and `i-1` shifts. The total operations sum up to approximately `(n-1) + (n-2) + ... + 1 + 0`, which is `n(n-1)/2`, resulting in O(n^2).

### Space Complexity

*   **Space Complexity:** **O(1)** (Auxiliary Space)
    *   Insertion Sort is an **in-place** sorting algorithm. It only requires a constant amount of additional memory (a few temporary variables) regardless of the input size `n` to hold the element being inserted.

### Summary Table

| Complexity Type | Best-Case | Average-Case | Worst-Case |
| :-------------- | :-------- | :----------- | :--------- |
| **Time**        | O(n)      | O(n^2)       | O(n^2)     |
| **Space**       | O(1)      | O(1)         | O(1)       |
| (Auxiliary)     |           |              |            |

### Key Characteristics

*   **In-Place:** Yes, it sorts the array within the original memory space.
*   **Stable:** Yes, it preserves the relative order of equal elements.
*   **Adaptive:** Yes, its performance improves if the array is partially sorted. It can be very fast for small or nearly sorted data.
*   **Good for Small Datasets:** Due to its low overhead, it is often more efficient than more complex O(n log n) algorithms for very small input sizes.
*   **Used in Hybrid Sorts:** Many efficient sorting algorithms (like Timsort and Introsort) use Insertion Sort as a subroutine to sort small partitions of the array because of its excellent performance on small and nearly sorted data.