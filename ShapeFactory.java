package com.example.springboot;

public class ShapeFactory {
    public Shape create(Object sentobject)
    {

        Shape sentobj = (Shape) sentobject;

        if (sentobj == null)
        {
            return null;
        }
        else if (sentobj.shapeType.equalsIgnoreCase("circle"))
        {
            Circle circle = new Circle();
            circle.shapeType = sentobj.shapeType;
            circle.position = sentobj.position;
            circle.radius = ((Circle) sentobj).radius;
            circle.color = sentobj.color;
            circle.id = sentobj.id;

            return circle;
        }
        else if (sentobj.shapeType.equalsIgnoreCase("rectangle"))
        {
            Rectangle rect = new Rectangle();
            rect.shapeType = sentobj.shapeType;
            rect.width = ((Rectangle) sentobj).width;
            rect.height = ((Rectangle) sentobj).height;
            rect.color = sentobj.color;
            rect.position = sentobj.position;
            rect.id = sentobj.id;

            return rect;
        }
        else if (sentobj.shapeType.equalsIgnoreCase("polygon"))
        {
            Polygon poly = new Polygon();
            poly.shapeType = sentobj.shapeType;
            poly.points = ((Polygon) sentobj).points;
            poly.color = sentobj.color;
            poly.position = sentobj.position;
            poly.id = sentobj.id;

            return poly;
        }
        else if (sentobj.shapeType.equalsIgnoreCase("elipse"))
        {
            Elipse elipse = new Elipse();
            elipse.shapeType = sentobj.shapeType;
            elipse.radiusX = ((Elipse) sentobj).radiusX;
            elipse.radiusY = ((Elipse) sentobj).radiusY;
            elipse.color = sentobj.color;
            elipse.id = sentobj.id;
            elipse.position = sentobj.position;

            return elipse;
        }
        else if (sentobj.shapeType.equalsIgnoreCase("line"))
        {
            Line line = new Line();
            line.shapeType = sentobj.shapeType;
            line.x1 = ((Line) sentobj).x1;
            line.x2 = ((Line) sentobj).x2;
            line.y1 = ((Line) sentobj).y1;
            line.y2 = ((Line) sentobj).y2;
            line.color = sentobj.color;
            line.position = sentobj.position;
            line.id = sentobj.id;

            return line;
        }
        return null;
    }

    Shape GetShape(int id){
        for (int i = 0;i < drawingcompnents.Shapeslist.size();i++){
            if(id == drawingcompnents.Shapeslist.get(i).id)
                return drawingcompnents.Shapeslist.get(i);
        }
        return null;
    }

    void EditShape(Shape a){
        for (int i = 0;i < drawingcompnents.Shapeslist.size();i++){
            if(a.id == drawingcompnents.Shapeslist.get(i).id) {
                drawingcompnents.Shapeslist.set(i, a);
                return;
            }
        }
        drawingcompnents.Shapeslist.add(a);
    }

    void RremoveShape(int id){
        for (int i = 0;i < drawingcompnents.Shapeslist.size();i++){
            if(id == drawingcompnents.Shapeslist.get(i).id)
                drawingcompnents.Shapeslist.remove(i);
        }
    }
}
