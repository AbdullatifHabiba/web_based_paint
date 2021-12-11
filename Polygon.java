package com.example.springboot;

import java.util.ArrayList;

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

	void Resize(int id, String points){
		Polygon a = (Polygon) Factory.GetShape(id);
		a.setPoints(points);
		Factory.EditShape(a);
	}

}
