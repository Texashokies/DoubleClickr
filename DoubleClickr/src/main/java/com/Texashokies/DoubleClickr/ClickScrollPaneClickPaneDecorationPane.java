package com.Texashokies.DoubleClickr;

import javax.swing.JPanel;

/**
 * Pane that helps with the visual style of a clickpane
 * @author Texashokies
 *
 */
public class ClickScrollPaneClickPaneDecorationPane extends JPanel{
	private static final long serialVersionUID = 1L;
	private ClickPane myPane;
	
	/**
	 * Construct just like a JPanel
	 */
	public ClickScrollPaneClickPaneDecorationPane() {
		super();
	}
	
	/**
	 * Sets the clickPane that it owns
	 * @param newPane The clickPane that it owns
	 */
	public void setClickScrollPane(ClickPane newPane) {
		myPane = newPane;
		add(newPane);
	}
	
	/**
	 * get the clickPane it owns
	 * @return the ClickPane
	 */
	public ClickPane getClickPane() {
		return myPane;
	}
	
}
