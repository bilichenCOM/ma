package hw2;

import java.io.Serializable;

public class Group implements Serializable {

	private static final long serialVersionUID = 7919364556740675991L;

	private Triangle triangle;
	private Circle circle;
	private Square square;
	private Group child;

	public Group(Triangle triangle, Circle circle, Square square) {
		this.triangle = triangle;
		this.circle = circle;
		this.square = square;
	}

	public Group() {}

//	instances declaration
	public static class Triangle implements Serializable {
		private static final long serialVersionUID = -91542801512443599L;

		private String name;

		public Triangle(String name) {
			this.name = name;
		}

		public Triangle() {

		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public static class Circle implements Serializable {
		private static final long serialVersionUID = 117689453927864550L;

		private String name;

		public Circle(String name) {
			super();
			this.name = name;
		}

		public Circle() {

		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public static class Square implements Serializable {
		private static final long serialVersionUID = 6223770289324331342L;

		private String name;

		public Square(String name) {
			this.name = name;
		}

		public Square() {

		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
//

	public Triangle getTriangle() {
		return triangle;
	}

	public void setTriangle(Triangle triangle) {
		this.triangle = triangle;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public Square getSquare() {
		return square;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

	public Group getChild() {
		return child;
	}

	public void addChild(Group child) {
		this.child = child;
	}

}
