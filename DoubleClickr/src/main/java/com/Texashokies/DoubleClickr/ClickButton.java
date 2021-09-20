package com.Texashokies.DoubleClickr;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * The click all button
 * @author Texashokies
 *
 */
public class ClickButton extends JButton{	
	private static final long serialVersionUID = 1L;

	/**
	 * The button's action listener
	 */
	ActionListener clickButtonListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				myStrategy.click(myScrollPane.getClicks());
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	};
	
	//What scrollPane do we belong to
	ClickScrollPane myScrollPane;
	//The logic of the click
	IClickAllButtonLogic myStrategy;
	/**
	 * Construct the button with given title ScrollPane and logic
	 * @param title Text of the button
	 * @param list The owning scrollPane
	 * @param logic The logic to use
	 */
	public ClickButton(String title,ClickScrollPane list,IClickAllButtonLogic logic){	
		super(title);
		myScrollPane = list;
		myStrategy = logic;
		addActionListener(clickButtonListener);
	}
	
	/**
	 * Set what logic the button should use
	 * @param newLogic an IClickAllButtonLogic to use
	 */
	public void setLogic(IClickAllButtonLogic newLogic) {
		myStrategy = newLogic;
	}
}
