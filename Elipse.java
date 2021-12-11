package com.example.springboot;

import java.awt.*;
import java.util.ArrayList;

import static com.example.springboot.drawingcompnents.instructions;

public class Elipse extends Shape {
	protected int radiusX, radiusY;
	

	public Elipse() {
		super();
		radiusX = 0;
		radiusY = 0;
	}

	public int getRadiusX() {
		return radiusX;
	}

	public void setRadiusX(int radiusX) {
		this.radiusX = radiusX;
	}

	public int getRadiusY() {
		return radiusY;
	}

	public void setRadiusY(int radiusY) {
		this.radiusY = radiusY;
	}

	static void Resize(int id, int x, int y){
		Elipse a = (Elipse) Factory.GetShape(id);
		int oldx = a.getRadiusX();
		int oldy = a.getRadiusY();
		a.setRadiusX(x);
		a.setRadiusY(y);
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
