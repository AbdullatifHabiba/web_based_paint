package com.example.springboot.Converter;

import com.example.springboot.shapes.*;
import com.example.springboot.shapes.Polygon;
import com.example.springboot.shapes.Shape;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;

public class JSONtoShapeConv implements Converter<Shape, JSONObject>{

    public Shape create(JSONObject json) throws JSONException {

        Shape temp = null;
        temp.setType(json.getString("type"));
        Point point = new Point();
        point.x = json.getInt("position.x");
        point.y = json.getInt("position.y");
        temp.setPosition(point);
        temp.setId(json.getInt("id"));
        temp.setColor(json.getString("color"));

        switch (temp.getType())
        {
            case "circle":
                Circle circle = (Circle) temp;
                ((Circle) temp).setRadius(json.getInt("radius"));
                break;
            case "line":
                Line line = (Line) temp;
                ((Line) temp).setX1(json.getInt("x1"));
                ((Line) temp).setX2(json.getInt("x2"));
                ((Line) temp).setY2(json.getInt("y1"));
                ((Line) temp).setY2(json.getInt("y2"));
                break;
            case "ellipse":
                Elipse elipse = (Elipse) temp;
                ((Elipse) temp).setRadiusx(json.getInt("radiusx"));
                ((Elipse) temp).setRadiusy(json.getInt("radiusy"));
                break;
            case "polygon":
                Polygon poly = (Polygon) temp;
                ((Polygon) temp).setPoints(json.getString("points"));
                break;
        }

        return temp;
    }

}