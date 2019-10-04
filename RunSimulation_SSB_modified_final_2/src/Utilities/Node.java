/*
 * Created on Aug 13, 2005
 * 
 */
package Utilities;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author soumya
 *
 * This class defines the Node - the basic building block of the simulator
 * 
 */
public class Node {
	
	    private NodePosition CurrentPosition;
           private int id;
		private double PreferredVelocity;
		private double CurrentVelocity;
		private double MovementDirection;
		private String NodeName;
                private int Importance;
                private double battery_life;
                private double hop_strength;
               
		private boolean isPropertyEditable; // denotes whether the property can be editable by the user
		
		private ArrayList NeighbourNodes;
                
                public static int max;
                
           public Node()
                   
           {
               
               
           }

		
		public Node(String nodeName) {
			NodeName = nodeName;
			NeighbourNodes = new ArrayList();
			isPropertyEditable = true;
		}
           
                
                public void setImportance(int importance) {
                    
                    Importance=importance;
                }
                
                public int getImportance() {
                    return Importance;
                 
                }
                public void setNodeid(int id) {
                    
                    this.id=id;
                }
                
                public int getNodeid() {
                    
                    return id;
                }
                
               
		
		public NodePosition getCurrentPosition() {
			return CurrentPosition;
		}
                
                public NodePosition getCurrentPosition(int n) {
                    
			return CurrentPosition;
		}

		
		public double getCurrentVelocity() {
			return CurrentVelocity;
		}

		
		public double getMovementDirection() {
			return MovementDirection;
		}

		/**
		 * @return
		 */
		public double getPreferredVelocity() {
			return PreferredVelocity;
		}

		
		public void setCurrentPosition(NodePosition position) {
			CurrentPosition = position;
		}

		
		public void setCurrentVelocity(double d) {
			CurrentVelocity = d;
		}

		
		public void setMovementDirection(double d) {
			MovementDirection = d;
		}

		
		public void setPreferredVelocity(double d) {
			PreferredVelocity = d;
		}
                
                public void setBatterylife(double battery_life){
                    
                    this.battery_life=battery_life;
                    
                    
                    
                }
                public double getBatterylife(){
                    
                   return battery_life;
                    
                }

		public void setNodeName(String string) {
			NodeName = string;
		}
                
                public String getNodeName() {
			return NodeName;
		}
                
                public void sethop_strength(double hs) {
			hop_strength=hs;
		}
                
                public double gethop_strength() {
			return hop_strength;
		}

		public void addNeighbour(String NeighbourName) {
			NeighbourNodes.add(NeighbourName);
		}
	
		public void removeNeighbour(String NeighbourName) {
			NeighbourNodes.remove(NeighbourName);
		}
	
		public Iterator getNeighbourList()
		{
			return NeighbourNodes.iterator();
		}
	
		public boolean isNeighbour(String DestNodeName)
		{
			return NeighbourNodes.contains(DestNodeName);
		}
	
		public int getNumberOfNeighbours()
		{
			return NeighbourNodes.size();
		}
	
		public String toString()
		{
			StringBuffer buf = new StringBuffer();
			
			buf.append("Node Name Is: ");
			buf.append(NodeName);
			buf.append("\n");
			
			buf.append("Position Is: ");
			buf.append(CurrentPosition.getXPosition());
			buf.append(",");
			buf.append(CurrentPosition.getYPosition());
			buf.append("\n");
			
			buf.append("Velocity Is: ");
			buf.append(CurrentVelocity);
			buf.append(" Direction Is: ");
			buf.append(MovementDirection);
			buf.append("\n");
			
			buf.append("Neighbour Nodes Are: ");
			int count = NeighbourNodes.size();
			for(int i = 0;i< count;i++)
			{
				buf.append(NeighbourNodes.get(i));
				if(i < count -1)
					buf.append(",");
				else
					buf.append("\n");	
			}
			
			return buf.toString();
		}
		
				
		//Direction is assumed positive in clockwise from Y axis.
		public void move(double interval)
		{
			double X,Y;
			
			double directionInRadian = Math.toRadians(MovementDirection);
			double linearMovement = (double)((interval*CurrentVelocity)/60);
			double cosineValue = Math.cos(directionInRadian);
			double sineValue = Math.sin(directionInRadian);
			
			X = CurrentPosition.getXPosition();
			Y = CurrentPosition.getYPosition();
			
			if(MovementDirection > 0)
			{
				X =  X + linearMovement*sineValue;
				Y =  Y + linearMovement*cosineValue;
			}
			else if (MovementDirection == 0)
			{
				Y = Y+linearMovement;
			}
			else if (MovementDirection < 0)
			{
				X =  X - linearMovement*sineValue;
				Y =  Y + linearMovement*cosineValue;
			}
			
			
			CurrentPosition.setXPosition(X);
			CurrentPosition.setYPosition(Y);
		}
		
		public void resetNeighbours()
		{
			NeighbourNodes = new ArrayList();
		}
		
		public void setPosition(double X,double Y)
		{
			CurrentPosition.setXPosition(X);
			CurrentPosition.setYPosition(Y);
		}
		
		public boolean isNodePropertyEditable()
		{
			return isPropertyEditable;
		}
		
		public void setEditable(boolean b)
		{
			isPropertyEditable = b;
		}
                
                public static int runSelectionAlgo(double a[],int j) {
                    
                    double max=-1;
                    
                    int maxindex=-1;
                    
                    for(int i=0;i<j+1;i++)
                    {
                        if(a[i]>=max)
                            
                        {
                            
                            max=a[i];
                            
                            maxindex=i;
                            
                        }
                           
                            
                    }
                    
                   
                    
                    return maxindex;
                        
                }
                
                public static void setMaxNodeid(int id){
                    
                    max=id;
                    
                }
                  public static int getMaxNodeid(){
                    
                    return max;
                    
                }
}
