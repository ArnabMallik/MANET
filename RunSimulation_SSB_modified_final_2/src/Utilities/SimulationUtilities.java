/*
 * Created on Aug 13, 2005
 * 
 */
package Utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;


/**
 * @author soumya
 *
 * This class defines some utility functions used in the simulator
 * 
 */
public class SimulationUtilities {
	
	private static final int INITIALCOORDINATEBOUNDARY = 100;
	private static File LogFile;
	private static FileOutputStream FileOut;
         public static ArrayList al=new ArrayList();
        
            public static int no_of_node;
            
            public static int count=1;
            
            public static Node store[];
            
            public static Node n;
            
            
            
            
  
    public static void set_total(int n)
    {
        
        no_of_node=n;
        
    }
    
    public static int get_total()
            {
                
                return no_of_node;
            }
    
    public static void insert_new(Node n)
    {
        store[count++]=n;
        
    }
    
    public static Node[] get_array()
            
    {
        
        return store;
    }
	
	
		
//	method to generate initial node details
	 public static Hashtable generateNodes(int noOfNodes, ConfigurationReader reader)
	 {
             SimulationUtilities.set_total(noOfNodes);
            store= new Node[Utilities.SimulationUtilities.no_of_node+1];
            
              QueueManager.initialize(); 
             
		 Hashtable toReturn = new Hashtable();
		 Node simNode;
                
             
		 for(int count=0; count < noOfNodes; count++)
		 {
			 simNode = createNode(count+1,reader);
			 toReturn.put(simNode.getNodeName(),simNode);
                         al.add(count,simNode);
                        
		 }
                 
                Collections.reverse(SimulationUtilities.al);
                
                n=(Node)al.get(0);
               
		 return toReturn;
	 }
	 
	 	 
//	though the attribute name is NodeNumber, it actually helps in naming the node
	 private static Node createNode(int NodeNumber, ConfigurationReader reader)
	 {
		 Node node = new Node("Node"+NodeNumber);
                 
                 node.setNodeid(NodeNumber);
                 
                 insert_new(node);
                 
                // System.out.println(NodeNumber);
		 // give random velocity
		 //node.setPreferredVelocity(reader.getMaximumVelocity());
		 node.setPreferredVelocity(((double)(reader.getMaximumVelocity()-1)*Math.random())+1);
		// node.setCurrentVelocity(((double)(reader.getMaximumVelocity()-1)*Math.random())+1);
		 node.setCurrentVelocity(node.getPreferredVelocity());
		 
		 // angle
		 node.setMovementDirection(reader.getDefaultAngle());
		
		 //set the position
		 NodePosition pos = new NodePosition();
		 pos.setXPosition(((double)(INITIALCOORDINATEBOUNDARY-1)*Math.random())+1);
		 pos.setYPosition(((double)(INITIALCOORDINATEBOUNDARY-1)*Math.random())+1);
		
		 node.setCurrentPosition(pos);
		 return node;		
	 }
	 
	 //just to check a hashtable is a valid nodelist..
	public static void checkNodeList(Hashtable Nodes)
		{
			if (Nodes == null) throw new IllegalArgumentException("Empty NodeList");
			if(!validateNodesList(Nodes)) throw new IllegalArgumentException("Please pass a proper NodeList");
	
		}
		
    //	method to check whether the node hashtable is as expected by the algorithm
	 private static boolean validateNodesList(Hashtable Nodes)
	 {
		 boolean toReturn = true;
		 if(Nodes != null)
		 {
			 Enumeration nodeEnum = Nodes.elements();
			 Object tempNode;
			 while(nodeEnum.hasMoreElements())
			 {
				 tempNode = nodeEnum.nextElement();
				 if (!(tempNode instanceof Node))
					 toReturn = false;
				 if(!toReturn) break;	
			 }
		 }
		 return toReturn;
	 }
	 
//	method to identify neighbours for nodes
	 public  static void identifyNeighbours(Hashtable Nodes,ConfigurationReader reader)
	 {
		
		 checkNodeList(Nodes);
		 Enumeration KeyEnum = Nodes.keys();
		 Enumeration NodeEnum;
		 Node WorkingNode;
		 Node TempNode;
		 String key;
		
		 while(KeyEnum.hasMoreElements())
		 {
			 key = (String)KeyEnum.nextElement();
			 WorkingNode =(Node)Nodes.get(key);
			 WorkingNode.resetNeighbours();
			
			 NodeEnum = Nodes.elements();
			 while (NodeEnum.hasMoreElements())
			 {
				 TempNode = (Node)NodeEnum.nextElement();
				 if (TempNode.getNodeName().equalsIgnoreCase(WorkingNode.getNodeName())) continue;
				 if(NodePosition.caluclateDistance(WorkingNode.getCurrentPosition(),TempNode.getCurrentPosition()) <= reader.getNeighbourhoodDistance())
				 {
					 WorkingNode.addNeighbour(TempNode.getNodeName());
				 }
			 }
		 }
	 }
	// utility to refresh the neighbourlist during movement 
	public  static void refreshNeighbours(Hashtable Nodes,ConfigurationReader reader)
		 {
			         checkNodeList(Nodes);
			         addNeighbours(Nodes,reader);
			         removeNeighbours(Nodes,reader);
		 }
		 
	private  static void addNeighbours(Hashtable Nodes,ConfigurationReader reader)
		{
			 Enumeration KeyEnum = Nodes.keys();
			 Enumeration NodeEnum;
			 Node WorkingNode;
			 Node TempNode;
			 String key;
	
			 while(KeyEnum.hasMoreElements())
			 {
				 key = (String)KeyEnum.nextElement();
				 WorkingNode =(Node)Nodes.get(key);
				 	
				 NodeEnum = Nodes.elements();
				 while (NodeEnum.hasMoreElements())
				 {
					 TempNode = (Node)NodeEnum.nextElement();
					 if (TempNode.getNodeName().equalsIgnoreCase(WorkingNode.getNodeName())) continue;
					 if (WorkingNode.isNeighbour(TempNode.getNodeName())) continue;
					 
					 if(NodePosition.caluclateDistance(WorkingNode.getCurrentPosition(),TempNode.getCurrentPosition()) <= reader.getNeighbourhoodDistance())
					 {
						 WorkingNode.addNeighbour(TempNode.getNodeName());
					 }
				 }
			 }
		}
	
	private  static void removeNeighbours(Hashtable Nodes,ConfigurationReader reader)
			 {
					 Enumeration KeyEnum = Nodes.keys();
					 Enumeration NodeEnum;
					 Node WorkingNode;
					 String TempNodeName;
					 Node TempNode;
					 String key;
					 Iterator neighbourList;
					 ArrayList removedNeighbours;
		
					 while(KeyEnum.hasMoreElements())
					 {
						 key = (String)KeyEnum.nextElement();
						 WorkingNode =(Node)Nodes.get(key);
						 neighbourList = WorkingNode.getNeighbourList();
						 removedNeighbours = new ArrayList();
						 while (neighbourList.hasNext())
						 {
							TempNodeName = (String)neighbourList.next();
							TempNode = (Node)Nodes.get(TempNodeName);
							if(NodePosition.caluclateDistance(WorkingNode.getCurrentPosition(),TempNode.getCurrentPosition()) > reader.getCommunicationRange())
							{
								removedNeighbours.add(TempNodeName);
							}
						 }
						 
						 if(removedNeighbours.size() > 0)
						 {
						 	String toRemove;
						 	int size = removedNeighbours.size();
						 	for(int i = 0; i < size;i++ )
						 	{
						 		toRemove = (String)removedNeighbours.get(i);
								WorkingNode.removeNeighbour(toRemove);
						 	}
						 }
						 
						
						
					 }
		 }
	 
	public static ConfigurationReader getConfigurationReader()
		{
			return new BaseConfigurationReader();
		}
		
	
	public static void initialize(String Name)
	{	
		if(Name == null) return;
		
		String FileName = SimulationConstants.LOGFILEPATH+Name;
		try
		{
			LogFile = new File(FileName);
			FileOut = new FileOutputStream(LogFile);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	
	public static void logOutput(String outstr)
	{
		if(FileOut == null) return;
		try
		{
			String towrite = outstr+"\t\n";
			FileOut.write(towrite.getBytes());
			FileOut.flush();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void terminate()
	{
		
		if(FileOut == null) return;
		try
		{
			FileOut.close();
			FileOut = null;
			LogFile = null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void printNetwork(Hashtable nodes)
	{
		Enumeration nodelist = nodes.elements();
		Node currentNode;
		logOutput("***********************");
		while(nodelist.hasMoreElements())
		{
			currentNode = (Node)nodelist.nextElement();
			logOutput(""+currentNode);
		}
		logOutput("***********************");
	}
	
	
}
