package com.example.springboot;

import java.util.ArrayList;

import static com.example.springboot.drawingcompnents.instructions;

public class Line extends Shape {

	protected int x, y;

	public Line() {
		super();
		x = 0;
		y = 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	static void Resize(int id, int x, int y){
		Line a = (Line) Factory.GetShape(id);
		int oldx = a.getX();
		int oldy = a.getY();
		a.setX(x);
		a.setY(y);
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
