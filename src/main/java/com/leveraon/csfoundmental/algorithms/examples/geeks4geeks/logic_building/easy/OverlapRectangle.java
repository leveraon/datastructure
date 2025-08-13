package com.leveraon.csfoundmental.algorithms.examples.geeks4geeks.logic_building.easy;

class Point {
	int x, y = 0;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

/**
 * <h2>Given two rectangles, find if the given two rectangles overlap or not.
 * Note that a rectangle can be represented by two coordinates, top left and
 * bottom right. So mainly we are given following four coordinates.</h2><br>
 * l1: Top Left coordinate of first rectangle. <br>
 * r1: Bottom Right coordinate of first rectangle. <br>
 * l2: Top Left coordinate of second rectangle. <br>
 * r2: Bottom Right coordinate of second rectangle.<br>
 * 
 * Example:<br>
 * Input: l1 = { 0, 10 }, r1 = { 10, 0 }, l2 = { 5, 5 }, r2 = { 15, 0 }<br>
 * Output: Rectangles Overlap<br>
 * 
 * Input: l1 = { 0, 10 }, r1 = { 10, 0 }, l2 = { -10, 5 }, r2 = { -1, 0 }<br>
 * Output: Rectangles Don't Overlap<br>
 */
public class OverlapRectangle {

	static boolean hasOverlapRectangle(Point l1, Point r1, Point l2, Point r2) {

		if (l1.x > r2.x || l2.x > r1.x) {
			return false;
		}

		if (l1.y < r2.y || l2.y < r1.y) {
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		Point l1 = new Point(0, 10), r1 = new Point(10, 0), l2 = new Point(-10, 5), r2 = new Point(-1, 0);
//		Point l1 = new Point(0, 10), r1 = new Point(10, 0), l2 = new Point(5, 5), r2 = new Point(15, 0);

		System.out.println("Is overlapping rectangle: " + hasOverlapRectangle(l1, r1, l2, r2));
	}
}
