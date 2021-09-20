package com.Texashokies.DoubleClickr;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 * Logic for clicking in the visually displayed order
 * @author Texashokies
 *
 */
public class ClickAllButtonOrderLastFirst extends ClickAllButtonOrderFirstLast {

	/**
	 * Calls the static clickLogic method that click all points in given arraylist
	 */
	public void click(ArrayList<ClickPane> clicks) throws AWTException {
		if(clicks.isEmpty()) {
			displayEmptyAlert();
		}
		else {
			int a = displayConfirmWarning();
			if(a == JOptionPane.YES_OPTION) {
				clicks = reverseList(clicks);
				clickLogic(clicks, MouseInfo.getPointerInfo().getLocation());
			}
		}
	}

	/**
	 * Reverses a given arraylist of clickPanes
	 * @param originalList - The list to reverse
	 * @return
	 */
	private static ArrayList<ClickPane> reverseList(ArrayList<ClickPane> originalList){
		ArrayList<ClickPane> newList = new ArrayList<ClickPane>();
		for(int i = originalList.size() - 1; i >= 0;i--) {
			newList.add(originalList.get(i));
		}
		return newList;
	}
}
