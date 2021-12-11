package com.example.springboot;

import java.awt.*;
import java.util.ArrayList;

public abstract class Shape {

	int id;
	protected String shapeType;
	protected String color;
	protected Point position;

	public Shape() {
		id = 0;
		shapeType = null;
		color = null;
		position.x = 0;
		position.y =0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return shapeType;
	}

	public void setType(String t) {
		this.shapeType = t;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	ShapeFactory Factory = new ShapeFactory();

	void move(int id, Point position){
		Shape a = Factory.GetShape(id);
		a.position = position;
		Factory.EditShape(a);
	}

	void copy(int id, int new_id, Point position) {
		Shape a = Factory.GetShape(id);
		a.id = new_id;
		a.position = position;
		Factory.EditShape(a);
	}

}