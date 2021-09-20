package com.Texashokies.DoubleClickr;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

/**
 * Frame for getting the desired click point
 * @author Texashokies
 *
 */
public class ClickRegisterFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	/**
	 * Mouslistener listening for click
	 */
	MouseAdapter myMouseListener = new MouseAdapter(){

		@Override
		public void mouseClicked(MouseEvent e) {
			sendPointToCreator(e.getPoint());
			dispose();
		}	
	};
	
	ClickPane myCreator;
	
	/**
	 * Construct this frame with given clickPane as a creator
	 * @param creator The ClickPane that created this Frame.
	 */
	public ClickRegisterFrame(ClickPane creator){
		
		myCreator = creator;
		
		//we need to extend across all monitors
		Rectangle2D result = new Rectangle2D.Double();
		GraphicsEnvironment localGE = GraphicsEnvironment.getLocalGraphicsEnvironment();
		//For all screens get
		for (GraphicsDevice gd : localGE.getScreenDevices()) {
			for(GraphicsConfiguration graphicsConfiguration : gd.getConfigurations()) {
				Rectangle2D.union(result, graphicsConfiguration.getBounds(), result);
			}
		}
		
		//Go across monitors
		setSize((int)result.getWidth(),(int)result.getHeight());
		
		addMouseListener(myMouseListener);
		
		
		setUndecorated(true);
		//Make window practically transparent
		setOpacity(.01f);
		setVisible(true);
	}
	
	/**
	 * Send the point to the ClickPane who created us
	 * @param p - the point to send
	 */
	void sendPointToCreator(Point p) {
		myCreator.recievePoint(p);
	}
	
}
