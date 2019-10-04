/*
 * Created on Aug 13, 2005
 * 
 */
package Simulation;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import Utilities.Node;

/**
 * @author soumya
 *
 * This class holds the model of the simulator. It has got provisions 
 * to update the model also. It is a singleton class.
 * 
 */
public class SimulationModel {
	
	protected Hashtable Nodes;
	protected int numberOfNodes;
	protected ArrayList ToolTipList;
	protected static SimulationModel model = null;
	
	public static synchronized SimulationModel getInstance()
	{
		if (model == null) model = new SimulationModel();
		return model;
	}
	
	private SimulationModel()
	{
		Nodes = new Hashtable();
		ToolTipList = new ArrayList();
	}
	
	public void setNetwork(Hashtable NodeList)
	{
		numberOfNodes = NodeList.size();
		Nodes  = (Hashtable)NodeList.clone();
                
	}
	
	public Hashtable getNetwork()
	{
		return Nodes;
	}
	
	public int getNumberOfNodes()
	{
		return numberOfNodes;
	}
	
	public void addToolTip(ArrayList tipList)
	{
		ToolTipList.add(tipList);
	}
	
	public void resetToolTip()
		{
			ToolTipList = new ArrayList();
		}
	
	public ArrayList getToolTipList()
	{
		return ToolTipList;
	}
	
	
	
	public void updateNetwork(double interval)
	{
		Enumeration nodes = Nodes.elements();
		Node currentNode;
		while(nodes.hasMoreElements())
		{
			currentNode = (Node)nodes.nextElement();
			currentNode.move(interval);
		}
			
	}
	
	public void updateNodeProperties(String NodeName,double XPosition,double YPosition,double velocity,int importance,double battery_life)
	{
		Node node = (Node)model.getNetwork().get(NodeName);
		node.setPosition(XPosition,YPosition);
		node.setCurrentVelocity(velocity);                // updated this function for update button action
		//node.setMovementDirection(direction);       
                node.setImportance(importance);
                node.setBatterylife(battery_life);
               
               
        }

}
