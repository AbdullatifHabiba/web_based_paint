package com.example.springboot;

import java.util.ArrayList;

import static com.example.springboot.drawingcompnents.instructions;

public class Polygon extends Shape {

	protected String points;

	public Polygon() {
		super();
		points = null;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	static void Resize(int id, String points){
		Polygon a = (Polygon) Factory.GetShape(id);
		String oldpoints = a.getPoints();
		a.setPoints(points);
		Factory.EditShape(a);
		StringBuffer instruction = new StringBuffer("{resize,");
		instruction.append(id);
		instruction.append(',');
		instruction.append(oldpoints);
		instruction.append(',');
		instruction.append(points);
		instruction.append('}');
		instructions.add(instruction.toString());
	}

}
