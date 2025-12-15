package com.leveraon.csfoundmental.jdk.features;

public class RecordPatternsDemo {
	record Point(int x, int y) {
	}

	record Color(String name) {
	}

	record ColoredPoint(Point p, Color c) {
	}

	record Box(ColoredPoint topLeft, ColoredPoint bottomRight) {
	}

	public static void printInfo(Object obj) {
		// Using Record Pattern in 'instanceof'
		if (obj instanceof Point(int x, int y)) {
			System.out.println("It's a Point at (" + x + ", " + y + ")");
		} else if (obj instanceof ColoredPoint(Point(int x, int y), Color(String colorName))) {
			// Nested Record Pattern: access x, y, and colorName directly
			System.out.println("It's a ColoredPoint at (" + x + ", " + y + ") with color " + colorName);
		} else if (obj instanceof Box(ColoredPoint(Point tl, Color c1), ColoredPoint(Point br, Color c2))) {
			// Further nesting, using _ for unnamed patterns (see JEP 443) if you don't care
			// about the color
			System.out.println("It's a Box with top-left " + tl + " and bottom-right " + br);
		} else {
			System.out.println("Unknown object: " + obj);
		}
	}

	public static void main(String[] args) {
		printInfo(new Point(10, 20));
		printInfo(new ColoredPoint(new Point(1, 2), new Color("Red")));
		printInfo(new Box(new ColoredPoint(new Point(0, 0), new Color("Black")),
				new ColoredPoint(new Point(100, 100), new Color("White"))));
		printInfo("Hello Java 21");
	}
}
