package com.Texashokies.DoubleClickr;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The logic for importing JSON files created by ClickExporter using the json-simple library.
 * @author Texashokies
 *
 */
public class ClickImporter {

	public static void importClicks(File fileToImport,ClickScrollPane paneToAddTo) throws FileNotFoundException {
		FileReader reader = new FileReader(fileToImport);
		
		JSONParser jsonParser = new JSONParser();
		try {
			JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);
			
			
			JSONArray newArray = (JSONArray) jsonObject.get("clicks");
			
			for(int i =0; i < newArray.size();i++) {
				JSONArray click = (JSONArray)newArray.get(i);
				int x = Math.toIntExact((long)click.get(1));
				int y = Math.toIntExact((long)click.get(2));
				int group = Math.toIntExact((long)click.get(3));
				Point p = new Point(x,y);
				ClickPane newPane = new ClickPane(paneToAddTo,(String)click.get(0),p,group);
				paneToAddTo.addNewPane(newPane);
			}
			
			
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Encountered exception parsing file","Alert",JOptionPane.WARNING_MESSAGE);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Encountered exception parsing file","Alert",JOptionPane.WARNING_MESSAGE);
		}
	}
}
