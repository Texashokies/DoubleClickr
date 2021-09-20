package com.Texashokies.DoubleClickr;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Logic for clicking clickpoints in the order of first to last in given array list
 * @author Texashokies
 *
 */
public class ClickAllButtonOrderFirstLast implements IClickAllButtonLogic{
	
	/**
	 * Display that there are no click points
	 */
	protected static void displayEmptyAlert() {
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f, "You need to set click points first.","Alert",JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Confirm with user that click points are valid
	 * @return the answer using JOptionPane format
	 */
	protected static int displayConfirmWarning() {
		JFrame f = new JFrame();
		return JOptionPane.showConfirmDialog(
				f,
				"Are you sure all of your click points are valid and safe?",
				"Alert",
				JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Does the logic given an array list of clickpanes in order of first added
	 * @param clicks - an Arraylist of clickPanes
	 * @throws AWTException
	 */
	protected static void clickLogic(ArrayList<ClickPane> clicks,Point clickButton) throws AWTException {
		//We will return the mouse back here at the end
		//Our  clicker
		Robot bot = new Robot();

		//Click in order of the click panes given
		for(ClickPane c : clicks) {
			bot.mouseMove((int)c.getPoint().getX(), (int)c.getPoint().getY());    
			bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);   
		}
				
		bot.mouseMove((int)clickButton.getX(), (int)clickButton.getY());
	}

	/**
	 * Calls clickLogic a static method
	 */
	public void click(ArrayList<ClickPane> clicks) throws AWTException {
		if(clicks.isEmpty()) {
			displayEmptyAlert();
		}
		else {
			int a = displayConfirmWarning();
			if(a == JOptionPane.YES_OPTION) {
				clickLogic(clicks, MouseInfo.getPointerInfo().getLocation());
			}
		}
	
	}
}
