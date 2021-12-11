package com.example.springboot;

import javax.swing.text.Position;
import java.awt.*;
import java.util.ArrayList;

import static com.example.springboot.drawingcompnents.instructions;

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

	static ShapeFactory Factory = new ShapeFactory();

	static void move(int id, Point position){
		Shape a = Factory.GetShape(id);
		Point first = a.getPosition();
		a.position = position;
		Factory.EditShape(a);
		StringBuffer instruction = new StringBuffer("{move,");
		instruction.append(id);
		instruction.append(',');
		instruction.append(position.x);
		instruction.append(',');
		instruction.append(position.y);
		instruction.append(',');
		instruction.append(first.x);
		instruction.append(',');
		instruction.append(first.y);
		instruction.append('}');
		instructions.add(instruction.toString());
	}

	void copy(int id, int new_id, Point position) {
		Shape a = Factory.GetShape(id);
		a.setId(new_id);
		a.setPosition(position);
		Factory.EditShape(a);
		StringBuffer instruction = new StringBuffer("{copy,");
		instruction.append(id);
		instruction.append(',');
		instruction.append(new_id);
		instruction.append(',');
		instruction.append(position);
		instruction.append(',');
		instructions.add(instruction.toString());
	}

}