package com.Texashokies.DoubleClickr;

import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel containing a click point and information
 * @author Texashokies
 *
 */
public class ClickPane extends JPanel{
	private static final long serialVersionUID = 1L;
	private int group = 1;
	private Point p;
	private JLabel coordinates;
	private JTextField nameField;
	
	/**
	 * The listener for the set coordinates button
	 */
	ActionListener setListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			getPointFromUser();
		}
		
	};
	
	/**
	 * Listener for the remove button
	 */
	ActionListener removeListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			removePane();
		}
		
	};
	
	/**
	 * Listener for the set group list
	 */
	ActionListener groupListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedGroup = (int)groupMenu.getSelectedItem();
			group = selectedGroup;
		}
		
	};
	
	/**
	 * Listener for move up button
	 */
	ActionListener moveUpListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			movePaneUp();
		}
		
	};
	
	/**
	 * Listener for move down button
	 */
	ActionListener moveDownListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			movePaneDown();
		}
		
	};
	
	/**
	 * Listener for duplicate button
	 */
	ActionListener duplicateListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			duplicatePane();
		}
		
	};
	
	ClickScrollPane scrollPaneContainer;
	JComboBox<Integer> groupMenu;
	
	/**
	 * Construct new clickpane with owning ClickScrollPane
	 * @param myPane -The owning ClickScrollPane
	 */
	public ClickPane(ClickScrollPane myPane) {
		scrollPaneContainer = myPane;
		p = new Point();
		
		setLayout(new FlowLayout());
		
		
		//10 possibel groups
		int[] groupNumbers = {1,2,3,4,5,6,7,8,9,10};
		groupMenu = new JComboBox<Integer>();
		for(int n : groupNumbers) {
			groupMenu.addItem(n);
		}
		
		groupMenu.setBounds(50, 50,90,20);   
		groupMenu.addActionListener(groupListener);
		
		//Click points name
		nameField = new JTextField(10);
		
		//Coordinates
		coordinates = new JLabel();
		coordinates.setText("X: " + p.getX() + " Y: " + p.getY());
		
		//Set button
		JButton set = new JButton("Set");
		set.addActionListener(setListener);
		
		//remove button
		JButton remove = new JButton("Remove");
		remove.addActionListener(removeListener);
		
		//Move up button
		JButton moveUp = new JButton("^");
		moveUp.addActionListener(moveUpListener);
		
		//Move down button
		JButton moveDown = new JButton("v");
		moveDown.addActionListener(moveDownListener);
		
		//Duplicate Button
		JButton duplicate = new JButton("Duplicate");
		duplicate.addActionListener(duplicateListener);
		
		add(new JLabel("Name: "));
		add(nameField);
		add(new JLabel("Group: "));
		add(groupMenu);
		add(coordinates);
		add(set);
		add(remove);
		add(moveUp);
		add(moveDown);
		add(duplicate);
	}
	
	public ClickPane(ClickScrollPane myPane, String name, Point startPoint, int group) {
		scrollPaneContainer = myPane;
		p = startPoint;
		
		setLayout(new FlowLayout());
		
		
		//10 possible groups
		int[] groupNumbers = {1,2,3,4,5,6,7,8,9,10};
		groupMenu = new JComboBox<Integer>();
		for(int n : groupNumbers) {
			groupMenu.addItem(n);
		}
		
		groupMenu.setBounds(50, 50,90,20);   
		groupMenu.setSelectedItem(groupNumbers[group-1]);
		this.group = group;
		groupMenu.addActionListener(groupListener);
		
		//Click points name
		nameField = new JTextField(name,10);
		
		//Coordinates
		coordinates = new JLabel();
		coordinates.setText("X: " + p.getX() + " Y: " + p.getY());
		
		//Set button
		JButton set = new JButton("Set");
		set.addActionListener(setListener);
		
		//remove button
		JButton remove = new JButton("Remove");
		remove.addActionListener(removeListener);
		
		//Move up button
		JButton moveUp = new JButton("^");
		moveUp.addActionListener(moveUpListener);
		
		//Move down button
		JButton moveDown = new JButton("v");
		moveDown.addActionListener(moveDownListener);
		
		//Duplicate Button
		JButton duplicate = new JButton("Duplicate");
		duplicate.addActionListener(duplicateListener);
		
		add(new JLabel("Name: "));
		add(nameField);
		add(new JLabel("Group: "));
		add(groupMenu);
		add(coordinates);
		add(set);
		add(remove);
		add(moveUp);
		add(moveDown);
		add(duplicate);
	}
	
	/**
	 * Prompt user for coordinates
	 */
	private void getPointFromUser() {
		new ClickRegisterFrame(this);
	}
	
	/**
	 * Change our point to the given point
	 * @param recievedPoint - the point this click pane is created for.
	 */
	public void recievePoint(Point recievedPoint) {
		p = recievedPoint;
		coordinates.setText("X: " + p.getX() + " Y: " + p.getY());
	}
	
	/**
	 * Get the point this click pane is for
	 * @return The point this pane is for.
	 */
	public Point getPoint() {
		return p;
	}
	
	/**
	 * Remove the pane from the scrollPane
	 */
	private void removePane() {
		scrollPaneContainer.removePane(this);
	}
	
	/**
	 * Set what group this pane belongs to
	 * @param newGroup - the new group
	 */
	public void setGroup(int newGroup) {
		group = newGroup;
	}
	
	/**
	 * Get the group this pane belongs to
	 * @return the group this pane is in
	 */
	public int getGroup() {
		return group;
	}
	
	public String getName() {
		return nameField.getText();
	}
	
	/**
	 * Move the pane up visually
	 */
	private void movePaneUp() {
		scrollPaneContainer.movePaneUp(this);
	}
	
	/**
	 * Move the pane down visually
	 */
	private void movePaneDown() {
		scrollPaneContainer.movePaneDown(this);
	}
	
	/**
	 * Duplicates the given pane
	 */
	private void duplicatePane() {
		ClickPane newPane = new ClickPane(this.scrollPaneContainer,this.nameField.getText(),this.p,this.group);
		scrollPaneContainer.addNewPane(newPane);
	}

}
