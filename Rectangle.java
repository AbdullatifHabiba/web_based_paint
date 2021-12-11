package com.example.springboot;

import java.util.ArrayList;

public class Rectangle extends Shape {
	protected int width, height;

	public Rectangle() {
		super();
		width = 0;
		height = 0;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	void resize(int id, int x, int y) {
		Rectangle a = (Rectangle) Factory.GetShape(id);
		a.setWidth(x);
		a.setHeight(y);
		Factory.EditShape(a);
	}
}
