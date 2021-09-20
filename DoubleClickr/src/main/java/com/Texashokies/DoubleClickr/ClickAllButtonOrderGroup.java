package com.Texashokies.DoubleClickr;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 * Logic for clicking buttons in order by group
 * @author Texashokies
 *
 */
public class ClickAllButtonOrderGroup extends ClickAllButtonOrderFirstLast {
	
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
				ArrayList<ArrayList<ClickPane> > groups = getGroups(clicks);
				for(ArrayList<ClickPane> g : groups) {
					clickLogic(g,MouseInfo.getPointerInfo().getLocation());
				}
			}
		}
	}
	
	/**
	 * Sorts the given array list by group into seperate arraylists and returns an arraylist of those arraylists
	 * @param clicks - the arraylist to sort into groups
	 * @return An arraylist containing the arraylist of clickpanes
	 */
	private static ArrayList<ArrayList<ClickPane> > getGroups(ArrayList<ClickPane> clicks) {
		ArrayList<ArrayList<ClickPane> > groups = new ArrayList<ArrayList<ClickPane> >();
		for(ClickPane c : clicks) {
			if(groupIsInList(groups,c.getGroup())) {
				groups.get(getGroupIndex(groups,c.getGroup())).add(c);
			}
			else {
				ArrayList<ClickPane> newGroup = new ArrayList<ClickPane>();
				newGroup.add(c);
				groups.add(newGroup);
			}
		}
		
		groups.removeAll(Collections.singleton(null));
		for(ArrayList<ClickPane> g : groups) {
			System.out.println(g.get(0).getGroup());
		}
		return groups;
	}
	
	/**
	 * gets if the given list of groups has a group with a given number
	 * @param groups - the list of groups
	 * @param groupToFind - the group number to search for
	 * @return - true if there is a group with the given number
	 */
	private static boolean groupIsInList(ArrayList<ArrayList<ClickPane> > groups,int groupToFind) {
		for(ArrayList<ClickPane> g : groups) {
			if(g.get(0).getGroup() == groupToFind)
				return true;
		}
		return false;
	}
	
	/**
	 * Gets the index of group with a given number in a given list of groups
	 * @param groups - the list of groups
	 * @param groupToFind - the group number
	 * @return the index, or -1 if there is no group with the given number
	 */
	private static int getGroupIndex(ArrayList<ArrayList<ClickPane> > groups, int groupToFind) {
		for(ArrayList<ClickPane> g : groups) {
			if(g.get(0).getGroup() == groupToFind)
				return groups.indexOf(g);
		}
		return -1;
	}
}
