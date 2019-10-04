/*
 * Created on Aug 22, 2005
 * 
 */
package Utilities;

import java.util.Hashtable;

/**
 * @author soumya
 *
 * This interface provides the definition of the basic network operations
 * 
 */
public interface NetworkDirector {
	
	//to generate a sample network with the number of nodes.
	public void generateNetwork(int noOfNodes);
   // to refresh the network re calculating neighbourhood.
	public void refreshNetwork(Hashtable Nodes);
	//to decide whether two nodes are well connected.
	public boolean isNodesWellConnected(NodePosition position1,NodePosition position2);
	//to decide whether a node position can be ediatable during movement.
	public boolean isPositionEditable();
	//whether a node needs special handling during movement.
	public boolean isSpecialNode(String NodeName);
	//to decide whether a network is valid for the movement.
	public boolean validateNetwork(Hashtable Nodes) throws SimulatorException;
    // to validate before setting a node properties
    public void validateNode(Hashtable Nodes,String NodeName,double Velocity) throws SimulatorException;	
	
}
