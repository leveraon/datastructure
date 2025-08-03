Quick Sort is a highly efficient, comparison-based sorting algorithm, often considered one of the fastest in practice for large unsorted lists. However, its performance characteristics can vary significantly depending on the input and the pivot selection strategy.

### Time Complexity

*   **Best-Case Time Complexity: O(n log n)**
    *   This occurs when the pivot element always divides the array into two (roughly) equal halves. This ideal partitioning leads to a logarithmic number of levels in the recursion tree, with each level involving linear work (the partitioning step).

*   **Average-Case Time Complexity: O(n log n)**
    *   On average, even with non-ideal pivot selections, Quick Sort performs very well. The probability of consistently picking bad pivots is low, leading to performance close to the best-case. The constant factors involved in Quick Sort are generally smaller than those for Merge Sort, making it faster in practice on average.

*   **Worst-Case Time Complexity: O(n^2)**
    *   This happens when the pivot selection consistently results in highly unbalanced partitions. For example, if the smallest or largest element is always chosen as the pivot (e.g., if the array is already sorted or reverse-sorted and the first/last element is always picked as the pivot). In such a scenario, one partition will be empty (or nearly empty) and the other will contain `n-1` elements. This degenerates into a linear scan for each element, similar to Bubble Sort or Insertion Sort, leading to quadratic time complexity.

### Space Complexity

*   **Average-Case Space Complexity: O(log n)**
    *   This is due to the recursion stack. In the average case, the depth of the recursion tree is logarithmic (log n). Each recursive call adds a frame to the stack.

*   **Worst-Case Space Complexity: O(n)**
    *   If the worst-case time complexity scenario occurs (unbalanced partitions), the recursion depth can go up to `n` (for `n` elements), leading to O(n) space for the recursion stack.
    *   *Optimization Note:* This can often be optimized to O(log n) worst-case space by always recursing on the smaller partition first (tail call optimization or an iterative approach for the larger partition).

### Summary Table

| Complexity Type | Best-Case | Average-Case | Worst-Case |
| :-------------- | :-------- | :----------- | :--------- |
| **Time**        | O(n log n) | O(n log n)   | O(n^2)     |
| **Space**       | O(log n)  | O(log n)     | O(n) (can be O(log n) with optimization) |

### Key Characteristics

*   **In-Place:** Yes, Quick Sort is typically an in-place sorting algorithm, meaning it sorts the array within the original memory space (it only requires a small amount of auxiliary space for the recursion stack).
*   **Unstable:** No, Quick Sort is generally not a stable sorting algorithm, meaning the relative order of equal elements may not be preserved.
*   **Divide and Conquer:** It's a classic example of a divide-and-conquer algorithm.
*   **Pivot Selection is Crucial:** The choice of pivot significantly impacts performance. Strategies like "median-of-three" pivot selection are often used to mitigate the risk of worst-case scenarios.