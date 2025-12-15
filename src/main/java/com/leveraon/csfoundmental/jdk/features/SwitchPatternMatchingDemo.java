package com.leveraon.csfoundmental.jdk.features;

public class SwitchPatternMatchingDemo {
	sealed interface Shape permits Circle, Rectangle {
	}

	record Circle(double radius) implements Shape {
	}

	record Rectangle(double length, double width) implements Shape {
	}

	record Point(int x, int y) {
	} // Not a Shape

	public static double getArea(Shape shape) {
		return switch (shape) {
		case Circle c -> Math.PI * c.radius() * c.radius();
		case Rectangle r -> r.length() * r.width();
		case null -> throw new IllegalArgumentException("Shape cannot be null");
		};
	}

	public static String describeObject(Object obj) {
		return switch (obj) {
		case null -> "It's null!";
		case String s -> "A String of length " + s.length();
		case Integer i -> "An Integer with value " + i;
		case Point(int x, int y) -> "A Point at (" + x + ", " + y + ")"; // Record pattern
		case Circle(double radius) -> "A Circle with radius " + radius; // Record pattern
		default -> "An unknown object: " + obj.getClass().getSimpleName();
		};
	}

	public static void main(String[] args) {
		System.out.println("Area of circle: " + getArea(new Circle(5.0)));
		System.out.println("Area of rectangle: " + getArea(new Rectangle(4.0, 6.0)));

		System.out.println(describeObject("Hello Java 21"));
		System.out.println(describeObject(123));
		System.out.println(describeObject(new Point(10, 30)));
		System.out.println(describeObject(new Circle(2.5)));
		System.out.println(describeObject(new Object()));
		System.out.println(describeObject(null));
	}
}
