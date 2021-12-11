package com.example.springboot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("/shape")

public class drawingcompnents{


	static ArrayList<Shape> Shapeslist;
	static ArrayList<String> instructions;
	ArrayList<String> Redo;
	ShapeFactory factory = new ShapeFactory();
	FileBuilder builder ;
	JSONArray ShapesJson = new JSONArray();


	@GetMapping("/create")
	void createShape(@RequestBody JSONObject sentobj) throws JsonProcessingException {
		ShapesJson.put(sentobj);
		ObjectMapper objectMapper = new ObjectMapper();
		Object jsontoObject = objectMapper.readValue(sentobj.toString(),Object.class);
		System.out.println(sentobj);
		Shape a = factory.create(jsontoObject);
		Shapeslist.add(a);
		StringBuffer instruction = new StringBuffer("{create,");
		instruction.append(a.id);
		instruction.append("}");
		String b = instruction.toString();
		instructions.add(b);
	}

	@GetMapping("/Undo")
	JSONObject undo() throws JSONException {
		String a = instructions.get(instructions.size() - 1);
		Redo.add(0, instructions.get(instructions.size() - 1));
		instructions.remove(instructions.size() - 1);
		String[] b = a.replaceAll("\\{|\\}", "").split(",");
		if (b[0].equalsIgnoreCase("create")){
			int id = Integer.parseInt(b[1]);
			factory.RremoveShape(id);
		}
		else if (b[0].equalsIgnoreCase("copy")){
			int id = Integer.parseInt(b[2]);
			factory.RremoveShape(id);
		}
		else if (b[0].equalsIgnoreCase("move")){
			int id = Integer.parseInt(b[1]);
			Point position = new Point();
			position.x = Integer.parseInt(b[4]);
			position.y = Integer.parseInt(b[5]);
			Shape.move(id, position);
			instructions.remove(instructions.size() - 1);
		}
		else if (b[0].equalsIgnoreCase("resize")){
			Shape a = factory.GetShape(Integer.parseInt(b[1]));
			switch (a.getType()){
				case "Circle":
					Circle.Resize(Integer.parseInt(b[1]), Integer.parseInt(b[2]));
					break;
				case "Elipse":
					Elipse.Resize(Integer.parseInt(b[1]), Integer.parseInt(b[2]), Integer.parseInt(b[3]));
					break;
				case "Line":
					Line.Resize(Integer.parseInt(b[1]), Integer.parseInt(b[2]), Integer.parseInt(b[3]));
					break;
				case "Polygon":
					Polygon.Resize(Integer.parseInt(b[1]), b[2]);
					break;
				case "Rectangle":
					Rectangle.Resize(Integer.parseInt(b[1]), Integer.parseInt(b[2]), Integer.parseInt(b[3]));
					break;
				default:
					break;
			}
		}
		return new FileBuilder(Shapeslist).jsonBuilder();
	}

	@GetMapping("/Redo")
	JSONObject redo() throws JSONException {
		Shapeslist.add(Undone.get(Undone.size() - 1));
		Undone.remove(Undone.size() - 1);
		return new FileBuilder(Shapeslist).jsonBuilder();
	}

	@GetMapping("/Save")
	void save(String path, String name, String type) throws JSONException {
		FileBuilder builder = new FileBuilder(Shapeslist);
		JSONObject saveJ = builder.jsonBuilder();
		String saveX = builder.xmlBuilder();
		FileWriter file = null;
		try {
			if (type == "xml") {
				file = new FileWriter(path + "\\" +  name +  ".xml");
				file.write(saveX.toString());
			}
			else{
				file = new FileWriter(path + "\\" +  name +  ".json");
				file.write(saveJ.toString());
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				file.flush();
				file.close();
			}catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

//	@GetMapping("/Load")
//	ArrayList<Shape> load(String path, String name) {
//		JSONParser jsonParser = new JSONParser();
//
//		try (FileReader reader = new FileReader("employees.json"))
//		{
//			//Read JSON file
//			Shapeslist = jsonParser.parse(reader);
//
//
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	}

//}


}
