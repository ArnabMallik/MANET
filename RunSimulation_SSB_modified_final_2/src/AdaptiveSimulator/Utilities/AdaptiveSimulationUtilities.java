/*
 * Created on Aug 22, 2005
 *  
 */
package AdaptiveSimulator.Utilities;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import AdaptiveSimulator.Utilities.*;
import Utilities.ConfigurationReader;
import Utilities.Node;
import Utilities.SimulationUtilities;

/**
 * @author soumya
 *
 * This class defines our algorithm specific utility functions used in the 
 * simulator. These functions are in addition to the ones defined in the
 * base simulator
 * 
 */
public class AdaptiveSimulationUtilities extends SimulationUtilities {
	
	
	public static ConfigurationReader getConfigurationReader()
			{
				return new AdaptiveConfigurationReader();
			}
			
	
	public static String setCoordinator(Hashtable Nodes)
		{
			String coordinator = getCoordinatorNodeName(Nodes);
			Node coordinatorNode = (Node)Nodes.get(coordinator);
		    coordinatorNode.setEditable(false);
		    return coordinatorNode.getNodeName();
		}
		
	// as the current algorithm assumes the network to be connected
	// it will try to regenerate the network
	private static ArrayList SearchList = null;// to avoid infinite looping in finding connectivity
	
	public static boolean isNetworkConnected(Hashtable Nodes)
	{	
		boolean toReturn = true;
		
		Enumeration NodeNames = Nodes.keys();
		String SourceNodeName, DestNodeName;
		
		SourceNodeName = (String)NodeNames.nextElement();
		//SearchList = new ArrayList();
		
		while(NodeNames.hasMoreElements())
		{
			SearchList = new ArrayList();
			DestNodeName = (String)NodeNames.nextElement();
			if(!isConnected(SourceNodeName,DestNodeName,Nodes))
			{
				toReturn = false;
			}
			if(!toReturn) break;
		}
		
		return toReturn;	 
	}
	
	private static boolean isConnected(String SourceNodeName,String DestNodeName,Hashtable Nodes)
	{
		boolean toReturn = false;
		Node SourceNode = (Node)Nodes.get(SourceNodeName);
		String TempSource;
		
		SearchList.add(SourceNodeName);
		
			
		if(SourceNode.getNumberOfNeighbours() == 0)
			toReturn = false;
		else if (SourceNode.isNeighbour(DestNodeName))
			toReturn = true;
		else	
		{
			Iterator Neighbours = SourceNode.getNeighbourList();
			
			while(Neighbours.hasNext())
			{
				TempSource = (String)Neighbours.next();
				
				if (SearchList.contains(TempSource)) continue;
				
				toReturn = isConnected(TempSource,DestNodeName,Nodes);
				if(toReturn) break;
			}
			
		}
		
		
		
		return toReturn;
	}
	
	// see the algorithm
	private static String getCoordinatorNodeName(Hashtable Nodes)
	{	 
		ArrayList elgibleList = null;
		int MaxNoOfOneHopNeighbours = 0;
		String CoordinatorName = null;
		
		Enumeration nodelist = Nodes.elements();
		Node currentNode;
		int tempNeighbourCount;
		
		while(nodelist.hasMoreElements())
		{
			currentNode = (Node)nodelist.nextElement();
			//System.out.println(" Node "+currentNode.getNodeName() + " has "+ currentNode.getNumberOfNeighbours() + " Neighbours");
			tempNeighbourCount = currentNode.getNumberOfNeighbours();
			if (tempNeighbourCount == 0) continue;
			if(tempNeighbourCount > MaxNoOfOneHopNeighbours)
			{
				MaxNoOfOneHopNeighbours = tempNeighbourCount;
				CoordinatorName = currentNode.getNodeName();
			}
			else if(tempNeighbourCount == MaxNoOfOneHopNeighbours)
			{
				if (elgibleList == null) 
				{
					elgibleList = new ArrayList();
					elgibleList.add(CoordinatorName);
				}
				elgibleList.add(currentNode.getNodeName());	
				CoordinatorName = null;
			}
			
		}
		if (CoordinatorName == null)
		{
			//System.out.println("Eligible Node Count "+elgibleList.size());
			CoordinatorName = resolveConflict(Nodes,elgibleList);// see the algo
		}
		//System.out.println("Coordinator is "+CoordinatorName);	
		return CoordinatorName;
	}
	
	private static String resolveConflict(Hashtable Nodes,ArrayList list)
	{
		int MaxNoOfTwoHopNeighbours = 0;
		String CoordinatorName = null;
		Node CurrentNode;
		String CurrentNodeName;
		int noOfTwoHopNeighbours;
		for(int i = 0; i < list.size(); i++)
		{
			CurrentNodeName = (String)list.get(i);
			CurrentNode = (Node)Nodes.get(CurrentNodeName);
			noOfTwoHopNeighbours = getNumberOfTwoHopNeighbours(CurrentNode,Nodes);
			if( noOfTwoHopNeighbours >= MaxNoOfTwoHopNeighbours)
			{
				CoordinatorName = CurrentNode.getNodeName();
				MaxNoOfTwoHopNeighbours = noOfTwoHopNeighbours;
			}
		}
		return CoordinatorName;
	}
	
	private static int getNumberOfTwoHopNeighbours(Node Node,Hashtable Nodes)
	{
		int toReturn = -1;
		
		ArrayList TwoHopNeighbours = new ArrayList();
		Iterator NeighbourEnum = Node.getNeighbourList();
		Iterator SecondLevelNeighbours;
		Node CurrentNeighbour;
		String CurrentNeighbourName , TwoHopNeighbourName;
		
		while(NeighbourEnum.hasNext())
		{
			CurrentNeighbourName = (String)NeighbourEnum.next();
			CurrentNeighbour = (Node)Nodes.get(CurrentNeighbourName);
			
			SecondLevelNeighbours = CurrentNeighbour.getNeighbourList();
			
			while(SecondLevelNeighbours.hasNext())
			{
				TwoHopNeighbourName = (String)SecondLevelNeighbours.next();
				if (Node.isNeighbour(TwoHopNeighbourName)) continue;
				if (!TwoHopNeighbours.contains(TwoHopNeighbourName))
					TwoHopNeighbours.add(TwoHopNeighbourName);
			}			
		}
		
		
		toReturn = TwoHopNeighbours.size();
		return toReturn;
	}
	
	
}
