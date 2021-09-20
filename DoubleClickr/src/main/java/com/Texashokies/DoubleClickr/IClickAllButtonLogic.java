package com.Texashokies.DoubleClickr;

import java.awt.AWTException;
import java.util.ArrayList;

/**
 * Logic for the click all button
 * @author Texashokies
 *
 */
public interface IClickAllButtonLogic{
	/**
	 * Calls a static method of the given logic
	 * @param clicks arraylist of ClickPanes to click through
	 * @throws AWTException
	 */
	void click(ArrayList<ClickPane> clicks) throws AWTException;
}
