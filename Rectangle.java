package com.example.springboot;

import java.util.ArrayList;

import static com.example.springboot.drawingcompnents.instructions;

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

	static void Resize(int id, int x, int y) {
		Rectangle a = (Rectangle) Factory.GetShape(id);
		int oldx = a.getWidth();
		int oldy = a.getHeight();
		a.setWidth(x);
		a.setHeight(y);
		Factory.EditShape(a);
		StringBuffer instruction = new StringBuffer("{resize,");
		instruction.append(id);
		instruction.append(',');
		instruction.append(oldx);
		instruction.append(',');
		instruction.append(oldy);
		instruction.append(',');
		instruction.append(x);
		instruction.append(',');
		instruction.append(y);
		instruction.append('}');
		instructions.add(instruction.toString());
	}
}
