/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.GridLayout;
import java.util.Enumeration;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Simulation.SimulationModel;
import UI.Components.DoubleTextField;
import UI.Components.IntegerTextField;
import static UI.SimulationConfigurationPanel.leader_node_changed;
import Utilities.NetworkDirector;
import Utilities.Node;
import Utilities.Hopcount_calculator;
import Utilities.SimulatorException;
import Utilities.NodePosition;
import Utilities.QueueManager;
import Utilities.SimulationConstants;
import Utilities.SimulationUtilities;
import java.awt.Event;
import java.io.FileWriter;
import java.util.Collections;
import javax.rmi.CORBA.Util;
import javax.swing.JOptionPane;
import java.io.*;

/**
 *
 * @author Abhishek
 */
public class Addnode extends JPanel {
    
        JLabel nodeNames;
	DoubleTextField XPositions;
	DoubleTextField YPositions;
	IntegerTextField Velocities;
	//DoubleTextField[] Directions;
        IntegerTextField Importance;  // added this parameter
        DoubleTextField Battery_Life;  // added this parameter
        DoubleTextField HopStrength;   // added this parameter
       IntegerTextField Hopcount;     // added this parameter
        DoubleTextField Weight; 
        
        
        
  public Addnode() {
      
      nodeNames=new JLabel("Node");
      XPositions=new DoubleTextField();
      YPositions=new DoubleTextField();
      Velocities=new IntegerTextField();
      Importance=new IntegerTextField();
      Battery_Life=new DoubleTextField();
      HopStrength=new DoubleTextField();
      Hopcount=new IntegerTextField();
      Weight=new DoubleTextField();
      
      XPositions.setText("");
       YPositions.setText("");
       Velocities.setText("");
      
       Importance.setText("");
       Battery_Life.setText("");
       HopStrength.setText("");
       Hopcount.setText("");
       Weight.setText("");
       
                                add(nodeNames);
				add(XPositions);
				add(YPositions);
				add(Velocities);
				//add(Directions[rowCount]);
                                add(Importance);
                                add(Battery_Life);
                                add(Weight);
                                add(HopStrength);
                                
                                add(Hopcount);
      
      
      
     // setBackground(ChartConstants.BACKGROUNDCOLOR);
      
      
      
  }      
        
        
        
        
        
        
        
    
}
