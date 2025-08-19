/**
 * 
 */
package com.leveraon.csfoundmental.algorithms.examples;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leveraon.csfoundmental.datastructure.queues.ArrayQueue;
import com.leveraon.csfoundmental.datastructure.queues.CircularQueue;
import com.leveraon.csfoundmental.datastructure.queues.Queue;
import com.leveraon.csfoundmental.datastructure.stacks.ArrayStack;
import com.leveraon.csfoundmental.datastructure.stacks.Stack;
import com.leveraon.csfoundmental.datastructure.tree.Node;

/**
 * 
 */
public class Practice {
	public List<Integer> leftRotation(List<Integer> array, int count) {

		if (count == 0) {
			return array;
		}

		int length = array.size();
		int effectiveRotation = count % length;

		if (effectiveRotation == 0) {
			return array;
		}

		List<Integer> result = new ArrayList<>();

		for (int i = effectiveRotation; i < length; i++) {
			result.add(array.get(i));
		}

		for (int i = 0; i < effectiveRotation; i++) {
			result.add(array.get(i));
		}

		return result;
	}

	// Set visitied = new HashSet();
	// List adjList = new ArrayList();
	//
	// public <V> Set bfs(Graph<V> root) {
	//
	// Queue<V> queue = new ArrayQueue();
	// Set visited = new HashSet();
	//
	// while (!queue.isEmpty()) {
	// V current = queue.dequeue();
	//
	// for (V neighbour : adjList.get(current)) {
	// if (!visited.contains(neighbour)) {
	// visited.add(neighbour);
	// queue.enqueue(neighbour);
	// }
	// }
	//
	// }
	//
	// return visitied;
	// }

	// public void dfs(Vertex vertex, Set visited) {
	//
	// visited.add(vertex);
	//
	// for (Vertex neighbour : adjList.get(vertex)) {
	// if (!visited.contains(neighbour)) {
	// dfs(neighbour, visited);
	// }
	// }
	// }

	public boolean binarySearch(int[] array, int target) {
		if (array == null || array.length == 0) {
			return false;
		}

		int low = 0, high = array.length;

		while (low < high) {
			int mid = (high + low) / 2;

			if (target == array[mid]) {
				return true;
			}

			if (target > array[mid]) {
				low = mid;
			} else {
				high = mid;
			}
		}

		return false;
	}

	public long fibonacci(int n) {
		if (n <= 0) {
			return 0;
		}

		if (n == 1)
			return 1;

		int current = 0, next = 1;
		int result = 0;
		for (int i = 2; i < n; i++) {
			result = current + next;
			current = next;
			next = result;
		}

		return result;
	}

	public int[] reverseArray(int[] array) {
		int low = 0, high = array.length - 1;

		if (array.length <= 1) {
			return array;
		}

		while (low < high) {
			int temp = array[high];
			array[high] = array[low];
			array[low] = temp;
			high--;
			low++;
		}

		return array;
	}

	public boolean cycleDetection(Node<Integer> head) {

		if (head == null) {
			return false;
		}

		Node<Integer> slow = head;
		Node<Integer> fast = head;

		while (fast != null && fast.getRight() != null) {
			slow = slow.getRight();
			fast = fast.getRight().getRight();
			// check
			if (slow == fast) {
				return true;
			}

		}

		return false;
	}

	public void insertToNodeList(Node<Integer> head, int data, int position) throws Exception {
		Node<Integer> newNode = new Node<>(data, null, null);

		if (head == null) {
			head = newNode;
		}

		// check if position > size, throw error;
		Node<Integer> current = head;
		int index = 0;
		while (current != null && index < position) {
			current = current.getRight();
			index++;
		}

		if (current == null) {
			throw new Exception("Index out of bound");
		} else {
			newNode.setRight(current.getRight());
			current.setRight(newNode);
		}

	}

	public Node<Integer> leftRotationNodeList(Node<Integer> head, int rotation) {
		if (head == null) {
			return head;
		}

		int length = 1;
		Node<Integer> current = head;
		while (current.getRight() != null) {
			current = current.getRight();
			length++;
		}

		Node<Integer> oldTail = current;

		int effectiveRotation = rotation % length;

		if (effectiveRotation == 0)
			return head;

		// connect current with head, make a cycle
		oldTail.setRight(head);

		int count = 0;
		Node<Integer> newTail = head;
		while (count < effectiveRotation - 1) {
			newTail = newTail.getRight();
			count++;
		}
		Node<Integer> newHead = newTail.getRight();
		newTail.setRight(null);
		return newHead;
	}

	public int[] iceCreamParlor(int[] array, int money) {
		int[] matchFlavors = new int[] {};
		Map<Integer, Integer> match = new HashMap<>();

		for (int i = 0; i < array.length; i++) {
			int flavor = array[i];
			int matchFlavor = money - flavor;

			if (!match.containsKey(matchFlavor)) {
				match.put(matchFlavor, i);
			}

			if (match.containsKey(flavor)) {
				matchFlavors = new int[] { match.get(flavor), i };
			}
		}

		return matchFlavors;
	}

	public int josephusProblem(CircularQueue<Integer> queue, int count) {

		if (queue.isEmpty()) {
			return -1;
		}

		while (queue.size() > 1) {
			for (int i = 0; i < count; i++) {
				queue.rotate();
			}
			int out = queue.dequeue();
			System.out.println(out + " is out.");
		}

		int winner = queue.dequeue();
		return winner;
	}

	public int linearSum(int[] array, int n) {
		if (n == 0) {
			return array[n];
		}

		return linearSum(array, n - 1) + array[n - 1];
	}

	public int binarySum(int[] array, int low, int high) {
		if (low > high) {
			return 0;
		}
		if (low == high) {
			return array[low];
		}

		int mid = (low + high) / 2;
		return binarySum(array, low, mid) + binarySum(array, mid + 1, high);
	}

	public boolean binarySearch(int[] array, int target, int low, int high) {

		if (low > high) {
			return false;
		}

		int mid = (low + high) / 2;
		if (target == array[mid]) {
			return true;
		}

		if (target > array[mid]) {
			binarySearch(array, target, mid + 1, high);
		} else {
			binarySearch(array, target, low, mid - 1);
		}

		return false;
	}

	public int factorialFunction(int n) throws Exception {
		if (n < 0) {
			throw new Exception("Invlaid number");
		}

		if (n == 0)
			return 0;

		if (n > 20) {
			throw new Exception("Invlaid number");
		}

		return n * factorialFunction(n - 1);
	}

	public int factorialIterative(int n) throws Exception {
		if (n < 0) {
			throw new Exception("Invlaid number");
		}

		if (n == 0)
			return 0;

		if (n > 20) {
			throw new Exception("Invlaid number");
		}

		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}

		return result;
	}

	public int power(int base, int n) {
		if (n == 0)
			return 1;

		return base * power(base, n - 1);
	}

	public int powerEffcient(int base, int n) {
		if (n == 0)
			return 1;

		int partial = power(base, n / 2);
		int result = 1;
		if (n % 2 == 0) {
			result = partial * partial;
			return result;
		}

		return base * result;

	}

	public void reverseArrayRecursive(int[] array, int low, int high) {
		if (low < high) {
			int temp = array[low];
			array[low] = array[high];
			array[high] = temp;
			reverseArrayRecursive(array, low + 1, high - 1);
		}
	}

//	Wrong solution
//	public void insertSort(int[] array) {
//		if (array == null || array.length <= 1)
//			return;
//
//		int n = array.length;
//
//		for (int i = 1; i < n; i++) {
//			int pivot = array[i];
//			int j = n - i; // last element index
//			while (j >= 0 && array[j] > pivot) {
//				array[j + 1] = array[j];
//				j--;
//			}
//			array[j + 1] = pivot;
//		}
//
//	}

	public void merge(Queue<Integer> s1, Queue<Integer> s2, Queue<Integer> queue, Comparator<Integer> comp) {

		while (!s1.isEmpty() && !s2.isEmpty()) {
			if (comp.compare(s1.first(), s2.first()) < 0) {
				queue.enqueue(s1.dequeue());
			} else {
				queue.enqueue(s2.dequeue());
			}
		}

		while (!s1.isEmpty()) {
			queue.enqueue(s1.dequeue());
		}
		while (!s2.isEmpty()) {
			queue.enqueue(s2.dequeue());
		}
	}

	public void mergeSort(Queue<Integer> queue, Comparator<Integer> comp) {

		int size = queue.size();
		if (size < 2)
			return;

		Queue<Integer> firstHalf = new ArrayQueue<>();
		Queue<Integer> secondHalf = new ArrayQueue<>();

		while (firstHalf.size() < size / 2) {
			firstHalf.enqueue(queue.dequeue());
		}

		while (!queue.isEmpty()) {
			secondHalf.enqueue(queue.dequeue());
		}

		mergeSort(firstHalf, comp);
		mergeSort(secondHalf, comp);

		merge(firstHalf, secondHalf, queue, comp);

	}

	public boolean balancedBrackets(String[] array) {
		Map<String, String> rules = new HashMap<>();
		rules.put("}", "{");
		rules.put("]", "[");
		rules.put(")", "(");

		Stack<String> stack = new ArrayStack<>();

		for (String element : array) {
			if (element.equals("{") || element.equals("[") || element.equals("(")) {
				stack.push(element);
			} else

			if (element.equals("}") || element.equals("]") || element.equals(")")) {

				if (stack.isEmpty()) {
					return false;
				}

				String match = rules.get(element);
				String top = stack.pop();
				if (!match.equals(top)) {
					return false;
				}
			}
		}

		return stack.isEmpty();
	}

	public int height(Node<Integer> tree) {
		if (tree == null) {
			return -1;
		}

		int leftHeight = height(tree.getLeft());
		int rightHeight = height(tree.getRight());

		return 1 + Math.max(leftHeight, rightHeight);
	}

	List<Integer> traversal = new ArrayList<>();

	public void inorderTraversal(Node<Integer> node, List<Integer> traversal) {
		if (node == null)
			return;

		traversal.add(node.getElement());

		inorderTraversal(node.getLeft(), traversal);

		inorderTraversal(node.getRight(), traversal);
	}

	public Node<Integer> insertNodeInBinaryTree(Node<Integer> root, int data) {
		Node<Integer> newNode = new Node<>(data, null, null);

		if (root == null) {
			root = newNode;
			return root;
		}

		Queue<Node<Integer>> queue = new ArrayQueue<Node<Integer>>();
		queue.enqueue(newNode);

		while (!queue.isEmpty()) {
			Node<Integer> current = queue.dequeue();
			if (current.getLeft() == null) {
				current.setLeft(newNode);
				return root;
			} else {
				queue.enqueue(current.getLeft());
			}

			if (current.getRight() == null) {
				current.setRight(newNode);
				return root;
			} else {
				queue.enqueue(current.getRight());
			}
		}

		return root;

	}

}
