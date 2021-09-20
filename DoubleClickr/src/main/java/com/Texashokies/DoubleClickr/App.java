package com.Texashokies.DoubleClickr;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class App {
		
	ClickButton click;
	JRadioButton option1;
	JRadioButton option2;
	JRadioButton option3;
	
	ActionListener optionButtonListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(option1.isSelected()) {
				setClickButtonLogic(new ClickAllButtonOrderFirstLast());
			}
			else if(option2.isSelected()) {
				setClickButtonLogic(new ClickAllButtonOrderLastFirst());
			}
			else {
				setClickButtonLogic(new ClickAllButtonOrderGroup());
			}
		}
		
	};
	
	ActionListener sortByGroupListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			sortClicksByGroup();
		}
		
	};
	
	ActionListener sortByNameListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			sortClicksByName();
		}
		
	};
	
	ActionListener importListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			importClicksFromFile();
		}
		
	};
	
	ActionListener exportListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			exportClicksToFile();
		}
		
	};
	
    public static void main(String[] args) {
        new App();
    }

	private JFrame frame;
	private ClickScrollPane clicks;
	
    public App() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }

                frame = new JFrame("Double Clickr");
                frame.setPreferredSize(new Dimension(950,300));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                frame.setLayout(new BorderLayout());
                clicks = new ClickScrollPane(); 
                frame.add(clicks,BorderLayout.WEST);
                
                //Menu
                JMenu fileMenu = new JMenu("File");
                JMenu clickScrollOptions = new JMenu("Click Options");
                JMenu sortSubMenu = new JMenu("Sort");
                JMenuItem sortByGroup = new JMenuItem("Sort by group");
                JMenuItem sortByName = new JMenuItem("Sort by name");
                sortByGroup.addActionListener(sortByGroupListener);
                sortByName.addActionListener(sortByNameListener);
                sortSubMenu.add(sortByGroup);
                sortSubMenu.add(sortByName);
                clickScrollOptions.add(sortSubMenu);
                JMenuItem importItem = new JMenuItem("Import");
                JMenuItem exportItem = new JMenuItem("Export");
                importItem.addActionListener(importListener);
                exportItem.addActionListener(exportListener);
                fileMenu.add(importItem);
                fileMenu.add(exportItem);
                JMenuBar mb = new JMenuBar();
                mb.add(fileMenu);
                mb.add(clickScrollOptions);
                frame.setJMenuBar(mb);
                
                //Options
                JPanel optionsPanel = new JPanel();
                optionsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
                optionsPanel.add(new JLabel("Click All Button Options:"));
                
                //Option Buttons
                ButtonGroup optionsButtons = new ButtonGroup();
                option1 = new JRadioButton("Click from bottom point to top point (In order of addition)");
                option1.setSelected(true);
                option1.addActionListener(optionButtonListener);
                option1.setToolTipText("Clicks from the last point visually in the list to the first.");
                option2 = new JRadioButton("Click from top point to bottom point (In visual order)");
                option2.addActionListener(optionButtonListener);
                option2.setToolTipText("Clicks from the first point visually in the list to the last.");
                option3 = new JRadioButton("Click in order of group");
                option3.addActionListener(optionButtonListener);
                option3.setToolTipText("Goes in order from Group 1 to Group 10. "
                		+ "Click points with in a group go in the order in which each click point was added");
                optionsPanel.add(option1);
                optionsPanel.add(option2);
                optionsPanel.add(option3);
                optionsButtons.add(option1);
                optionsButtons.add(option2);
                optionsButtons.add(option3);
                
                frame.add(optionsPanel,BorderLayout.EAST);
                
                //Click button
                
                //Default logic is first to last added
                IClickAllButtonLogic clickButtonLogicDefault = new ClickAllButtonOrderFirstLast();
                
                click = new ClickButton("Click All",clicks,clickButtonLogicDefault);
                frame.add(click,BorderLayout.SOUTH);
               	
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    
    private void setClickButtonLogic(IClickAllButtonLogic newLogic) {
    	click.setLogic(newLogic);
    }
    
    private void sortClicksByGroup() {
    	clicks.sortByGroup();
    }
    
    private void sortClicksByName() {
    	clicks.sortByName();
    }
    
    private void importClicksFromFile() {
    	JFileChooser fc = new JFileChooser();
    	FileNameExtensionFilter filter = new FileNameExtensionFilter("Json Files","json");
    	fc.setFileFilter(filter);
    	int i = fc.showOpenDialog(frame);
    	if(i == JFileChooser.APPROVE_OPTION) {
    		try {
				ClickImporter.importClicks(fc.getSelectedFile(),clicks);
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(frame, "File not found.","Alert",JOptionPane.WARNING_MESSAGE);
			}
    	}
    }
    
    private void exportClicksToFile() {
    	ClickExporter.exportClicks(clicks.getClicks());
    }
}