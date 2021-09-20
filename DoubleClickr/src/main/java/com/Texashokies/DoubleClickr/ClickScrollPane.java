package com.Texashokies.DoubleClickr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

/**
 * The pane containing all of the clickPanes
 * @author Texashokies
 *
 */
public class ClickScrollPane extends JPanel{
	private static final long serialVersionUID = 1L;
	private JPanel mainList;
	private ArrayList<ClickPane> myClickPanes;
	private ArrayList<ClickScrollPaneClickPaneDecorationPane> myClickPanelPanelContainers;
	
	/**
	 * Listener for the add button
	 */
	ActionListener addButtonListener = new ActionListener() {
		@Override
        public void actionPerformed(ActionEvent e) {
			addNewPane();
		}
	};
	
	/**
	 * Constructs a clickscroll pane
	 */
    public ClickScrollPane() {
        setLayout(new BorderLayout());
        //Our list
        mainList = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        mainList.add(new JPanel(), gbc);

        //Our scroll bar
        add(new JScrollPane(mainList));

        
        //The add button
        JButton add = new JButton("Add");
        add.addActionListener(addButtonListener);
        add.setToolTipText("Adds a new Click Point above previously added click points.");

        add(add, BorderLayout.SOUTH);
        
        myClickPanes = new ArrayList<ClickPane>();
        myClickPanelPanelContainers = new ArrayList<ClickScrollPaneClickPaneDecorationPane>();
    }

    public Dimension getPreferredSize() {
        return new Dimension(600, 200);
    }
    
    /**
     * Get the clickPanes of this Scroll Pane
     * @return An ArrayList of ClickPanes of this Scroll Pane
     */
    public ArrayList<ClickPane> getClicks(){
    	return myClickPanes;
    }
    
    /**
     * Add a new clickPane
     */
    private void addNewPane() {
    	ClickScrollPaneClickPaneDecorationPane panel = new ClickScrollPaneClickPaneDecorationPane();
        ClickPane paneToAdd = new ClickPane(this);
        panel.setClickScrollPane(paneToAdd);
        myClickPanes.add(paneToAdd);
        myClickPanelPanelContainers.add(panel);
        panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainList.add(panel, gbc, 0);
        validate();
        repaint();
    }
    
    /**
     * add the given clickPane
     * @param paneToAdd - the pane to add
     */
    public void addNewPane(ClickPane paneToAdd) {
    	ClickScrollPaneClickPaneDecorationPane panel = new ClickScrollPaneClickPaneDecorationPane();
        panel.setClickScrollPane(paneToAdd);
        myClickPanes.add(paneToAdd);
        myClickPanelPanelContainers.add(panel);
        panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainList.add(panel, gbc, 0);
        validate();
        repaint();
    }
    
    /**
     * Remove the given clickPane
     * @param paneToRemove - the clickPane to remove
     */
    public void removePane(ClickPane paneToRemove) {
    	ClickScrollPaneClickPaneDecorationPane panelToRemove = null;
    	for(ClickScrollPaneClickPaneDecorationPane p : myClickPanelPanelContainers) {
    		if(p.getClickPane() == paneToRemove) {
    			myClickPanes.remove(paneToRemove);
    			panelToRemove = p;
    			mainList.remove(p);
    		}
    	}
    	myClickPanelPanelContainers.remove(panelToRemove);
    	validate();
		repaint();
    }
    
    /**
     * Move the pane up visually
     * @param paneToMove The pane to move up
     */
    public void movePaneUp(ClickPane paneToMove) {
    	//Create the new list of click panes
    	//Moving up visual is actually moving it further down the list because
    	
    	JFrame f = new JFrame();
    	if(myClickPanes.get(myClickPanes.size() - 1) == paneToMove) {
    		JOptionPane.showMessageDialog(f, "This is already at the top!","Alert",JOptionPane.WARNING_MESSAGE);
    	}
    	else {
    		ArrayList<ClickPane> newClickPaneList = new ArrayList<ClickPane>();
        	newClickPaneList.addAll(myClickPanes);
        	
        	boolean paneFound = false;
        	for(int i = 0;i<myClickPanes.size() && !paneFound;i++) {
        		if(myClickPanes.get(i) == paneToMove) {
        			Collections.swap(newClickPaneList, i, i+1);
        			paneFound = true;
        		}
        	}
        	
        	while(!myClickPanes.isEmpty()) {
        		removePane(myClickPanes.get(0));
        	}
        	
        	//Remove everything from the original stuff
        	rebuildListFromArrayList(newClickPaneList);
    	}
    }
    
    /**
     * Move the pane down visually
     * @param paneToMove The pane to move down
     */
    public void movePaneDown(ClickPane paneToMove) {
    	JFrame f = new JFrame();
    	if(myClickPanes.get(0) == paneToMove) {
    		JOptionPane.showMessageDialog(f, "This is already at the bottom!","Alert",JOptionPane.WARNING_MESSAGE);
    	}
    	else {
    		ArrayList<ClickPane> newClickPaneList = new ArrayList<ClickPane>();
        	newClickPaneList.addAll(myClickPanes);
        	
        	boolean paneFound = false;
        	for(int i = 0;i<myClickPanes.size() && !paneFound;i++) {
        		if(myClickPanes.get(i) == paneToMove) {
        			Collections.swap(newClickPaneList, i, i-1);
        			paneFound = true;
        		}
        	}
        	
        	//Remove everything from the original stuff
        	while(!myClickPanes.isEmpty()) {
        		removePane(myClickPanes.get(0));
        	}
        	rebuildListFromArrayList(newClickPaneList);
    	}
    }
    
    /**
     * Rebuilds the pane with the given clickPane array list
     * @param clickPanesList - The arraylist to build from
     */
    private void rebuildListFromArrayList(ArrayList<ClickPane> clickPanesList) {
    	for(ClickPane p : clickPanesList) {
    		addNewPane(p);
    	}
    }
    
    public void sortByGroup() {
    	ArrayList<ClickPane> newClickPaneList = new ArrayList<ClickPane>();
    	newClickPaneList.addAll(myClickPanes);
    	Collections.sort(newClickPaneList, new Comparator<ClickPane>() {

			@Override
			public int compare(ClickPane o1, ClickPane o2) {
				return o1.getGroup() - o2.getGroup();
			}
    		
    	});
    	
    	Collections.reverse(newClickPaneList);
    	//Remove everything from the original stuff
    	while(!myClickPanes.isEmpty()) {
    		removePane(myClickPanes.get(0));
    	}
    	rebuildListFromArrayList(newClickPaneList);
    }
    
    public void sortByName() {
    	ArrayList<ClickPane> newClickPaneList = new ArrayList<ClickPane>();
    	newClickPaneList.addAll(myClickPanes);
    	Collections.sort(newClickPaneList, new Comparator<ClickPane>() {

			@Override
			public int compare(ClickPane o1, ClickPane o2) {
				return o1.getName().compareTo(o2.getName());
			}
    		
    	});
    	
    	Collections.reverse(newClickPaneList);
    	
    	//Remove everything from the original stuff
    	while(!myClickPanes.isEmpty()) {
    		removePane(myClickPanes.get(0));
    	}
    	rebuildListFromArrayList(newClickPaneList);
    }
    
}
