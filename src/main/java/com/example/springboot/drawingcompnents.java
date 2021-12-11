package com.example.springboot;
import com.example.springboot.Converter.JSONToShapeArray;
import com.example.springboot.Converter.JSONtoShapeConv;
import com.example.springboot.shapes.Shape;
import com.example.springboot.shapes.ShapesArray;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.XML;
import org.springframework.web.bind.annotation.*;
import org.json.simple.parser.JSONParser;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

//import org.springframework.boot.CommandLineRunner;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
@RequestMapping("/shape")

public class drawingcompnents{


	ShapesArray ShapesA = new ShapesArray();
	ArrayList<Shape> Undone;


	Stack<Pair> undo = new Stack<>();
	Stack<Pair> redo = new Stack<>();
	ShapeFactory factory = new ShapeFactory();
	FileBuilder builder ;
	JSONArray ShapesJson = new JSONArray();



	@GetMapping("/create")
	void createShape(@RequestBody String sentobj) throws JsonProcessingException, JSONException {

		JSONObject sentJ = new JSONObject(sentobj);
		JSONtoShapeConv map = new JSONtoShapeConv();
		Shape jsontoShape = map.create(sentJ);
		System.out.println(sentJ);
		ShapesA.AddShape(factory.create(jsontoShape));
		Pair a = new Pair("create", factory.create(jsontoShape));
		undo.push(a);

	}

	@GetMapping("/edit")
	void edit(@RequestBody String sentobj) throws JSONException {
		JSONObject sentJ = new JSONObject(sentobj);
		JSONtoShapeConv map = new JSONtoShapeConv();
		Shape jsontoShape = map.create(sentJ);
		switch (sentJ.getString("operation"))
		{
			case "copy":
				ShapesA.AddShape(jsontoShape);
				break;
			case "remove":
				ShapesA.removeShape(jsontoShape.getId());
				break;
			case "edit":

				Shape temp = ShapesA.GetShape(jsontoShape.getId());
				Pair a = new Pair("edit", temp);
				undo.push(a);
				ShapesA.EditShape(jsontoShape);
				break;
		}
	}

	@GetMapping("/Undo")
	JSONObject undo() throws JSONException {

		Pair temp = undo.pop();
		switch (temp.frist)
		{
			case "create":
				redo.push(temp);
				ShapesA.removeShape(temp.second.getId());
				break;
			case "edit":
				Shape temp1 = ShapesA.GetShape(temp.second.getId());
				redo.push(new Pair("edit",temp1));
				ShapesA.EditShape(temp.second);
				break;
		}

		return new FileBuilder().jsonBuilder(ShapesA.shapes);

	}

	@GetMapping("/Redo")
	JSONObject redo() throws JSONException {

		Pair temp = redo.pop();
		switch (temp.frist)
		{
			case "create":
				undo.push(temp);
				ShapesA.AddShape(temp.second);
				break;
			case "edit":
				Shape temp1 = ShapesA.GetShape(temp.second.getId());
				undo.push(new Pair("edit",temp1));
				ShapesA.EditShape(temp.second);
				break;
		}
		return new FileBuilder().jsonBuilder(ShapesA.shapes);
	}

	@GetMapping("/Save")
	void save(@RequestParam String path, @RequestParam String name, @RequestParam String type) throws JSONException {
		FileBuilder builder = new FileBuilder();

		JSONObject saveJ = builder.jsonBuilder(ShapesA.shapes);

		String saveX = builder.xmlBuilder(ShapesA.shapes);


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

		@GetMapping("/Load")
		ArrayList<Shape> load(@RequestParam String path, @RequestParam String name, @RequestParam String type) {

			JSONToShapeArray mapper = new JSONToShapeArray();
			JSONParser jsonParser = new JSONParser();
			JSONObject json;
			File file = new File(path + "\\" +  name +  "." + type);
			try (FileReader reader = new FileReader(file))
			{
				//Read JSON file

				if (type.equalsIgnoreCase("json"))
				{
					json = (JSONObject) jsonParser.parse(reader);
					ShapesA.shapes = mapper.create(json);
				}
				else if (type.equalsIgnoreCase("xml"))
				{
					DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
					Document document = documentBuilder.parse(file);
					json = XML.toJSONObject(document.toString());
					ShapesA.shapes = mapper.create(json);
				}




			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
			return ShapesA.shapes;
		}

	}



