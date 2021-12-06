package com.example.springboot;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("/shape")

public class drawingcompnents  {
	ArrayList<Shape> Shapeslist;
@GetMapping("/circle")
Shape createcircle(Circle circle, @RequestParam String[] sentobj) {
	System.out.println("circle");

	circle.name="circle";
	circle.x=Integer.valueOf(sentobj[0]);
	circle.y=Integer.valueOf(sentobj[1]);
	circle.radius=Integer.valueOf(sentobj[2]);
	circle.color=sentobj[3];
	circle.isDraw=Boolean.valueOf(sentobj[4]);
	circle.isFilled=Boolean.valueOf(sentobj[4]);
	Shapeslist.add(circle);
	System.out.print(circle.color);
	return circle;
	
}
@GetMapping("/Elipse")
Shape createelipse(Elipse elipse, @RequestParam Object sentobj) {
	
	return elipse;
	
}@GetMapping("/Rectangle")
Shape createrectangle(Rectangle rect, @RequestParam Object sentobj) {
	
	return rect;
	
}
@GetMapping("/Triangle")
Shape createtriangle(Triangle triangle, @RequestParam Object sentobj) {
	
	return triangle;
	
}
@GetMapping("/Line")
Shape create(Line line, @RequestParam Object sentobj) {
	
	return line;
	
}
}
