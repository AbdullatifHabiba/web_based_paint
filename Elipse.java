package com.example.springboot;

import java.awt.*;
import java.util.ArrayList;

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

	void Resize(int id, int x, int y){
		Elipse a = (Elipse) Factory.GetShape(id);
		a.setRadiusX(x);
		a.setRadiusY(y);
		Factory.EditShape(a);
	}
}
