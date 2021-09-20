package com.Texashokies.DoubleClickr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.json.simple.*;

/**
 * Logic for exporting click points to a JSON file using the json-simple library.
 * @author Texashokies
 *
 */
public class ClickExporter {
	
	@SuppressWarnings("unchecked")
	public static void exportClicks(ArrayList<ClickPane> clicks) {
		File exportFile = new File("Exported Clicks\\Clicks.json");
		
		//Create new file with same name if one already exits
		if(exportFile.exists() && !exportFile.isDirectory()) {
			int fileNo = 0;
			while(exportFile.exists()) {
				fileNo++;
				exportFile = new File("Exported Clicks\\Clicks(" + fileNo + ").json");
			}
		}
		
		exportFile.getParentFile().mkdir();
		try {
			exportFile.createNewFile();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), "File could not be created!","Alert",JOptionPane.WARNING_MESSAGE);
		}
		
		
		JSONObject clicksObject = new JSONObject();
		//Each click is an array
		JSONArray jsonClick;
		//Array of clicks
		JSONArray jsonClicks = new JSONArray();
		for(ClickPane c : clicks) {
			jsonClick = new JSONArray();
			jsonClick.add(c.getName());
			jsonClick.add(c.getPoint().x);
			jsonClick.add(c.getPoint().y);
			jsonClick.add(c.getGroup());
			
			//Add
			jsonClicks.add(jsonClick);
		}
		clicksObject.put("clicks",jsonClicks);
		
		try {
			Files.write(exportFile.toPath(), clicksObject.toJSONString().getBytes(),StandardOpenOption.APPEND);
			JOptionPane.showMessageDialog(new JFrame(), "Created new file in Exported Clicks Folder");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Could not write to json file!","Alert",JOptionPane.WARNING_MESSAGE);
		}
	}
}
