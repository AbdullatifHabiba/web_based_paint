package com.example.springboot;

import java.util.ArrayList;

import static com.example.springboot.drawingcompnents.instructions;

public class Circle extends Shape{
	protected int radius;

	public Circle() {
		super();
		radius = 0;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	static void Resize(int id, int radius){
		Circle a = (Circle) Factory.GetShape(id);
		int old = a.getRadius();
		a.setRadius(radius);
		Factory.EditShape(a);
		StringBuffer instruction = new StringBuffer("{resize,");
		instruction.append(id);
		instruction.append(',');
		instruction.append(old);
		instruction.append(',');
		instruction.append(radius);
		instruction.append('}');
		instructions.add(instruction.toString());
	}

}
