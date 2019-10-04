/*
 * Created on Aug 22, 2005
 * 
 */
package AdaptiveSimulator.Simulation;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import AdaptiveSimulator.Utilities.AdaptiveSimulationUtilities;
import AdaptiveSimulator.Utilities.SchemeConstants;
import AdaptiveSimulator.Utilities.SimulationNodeState;
import Simulation.SimulationEnvironment;
import Simulation.SimulationModel;
import Utilities.ConfigurationReader;
import Utilities.NetworkDirector;
import Utilities.Node;
import Utilities.NodePosition;
import Utilities.SimulationUtilities;
import Utilities.SimulatorException;
import Utilities.SimulatorMessages;
/**
 * @author soumya
 *
 * This class provides the adaptive algorithmm specific extension of the controller
 */
public class AdaptiveSimulationEnvironment extends SimulationEnvironment {

	private Hashtable NodeStates;
	private String CoordinatorNodeName = "";
	
	public AdaptiveSimulationEnvironment() {
		this(null,false);
	}
	
	public AdaptiveSimulationEnvironment(String Filename) {
			this(Filename,false);
		}
		
	public AdaptiveSimulationEnvironment(String Filename,boolean LogNetworkSnap) {
				super(Filename,LogNetworkSnap);
				NodeStates = new Hashtable();
			}
	
	 protected double readBeaconInterval()
	{
		//return super.
		ConfigurationReader reader = AdaptiveSimulationUtilities.getConfigurationReader();
		double interval = reader.getBeaconInterval();
		return interval;
					
	}
	
	protected void updateModel(double interval)
	{
		SimulationModel.getInstance().updateNetwork(interval);
//		do the decision making here kill processing time also..
        controlMovement();
		
			
	}
	
	public void generateNetwork(int numberOfNodes)
		{
			ConfigurationReader reader = AdaptiveSimulationUtilities.getConfigurationReader();
			Hashtable Nodes  = SimulationUtilities.generateNodes(numberOfNodes,reader);
			SimulationUtilities.identifyNeighbours(Nodes,reader);
			
			while(!AdaptiveSimulationUtilities.isNetworkConnected(Nodes))
			{
				//System.out.println("Network Not Connected Trying Again ******");
				Nodes = SimulationUtilities.generateNodes(Nodes.size(),reader);
				SimulationUtilities.identifyNeighbours(Nodes,reader);
			}
			
			//CoordinatorNodeName = AdaptiveSimulationUtilities.setCoordinator(Nodes);
			SimulationModel.getInstance().setNetwork(Nodes);
			createInitialState(Nodes);		
		}
	
		protected NetworkDirector getNetworkDirector()
		{
			return this;
		}
		
		private void createInitialState(Hashtable Nodes)
		{
			Enumeration keys = Nodes.keys();
			String KeyName;
			SimulationNodeState state;
			
			while(keys.hasMoreElements())
			{
				KeyName = (String)keys.nextElement();
				state = new SimulationNodeState();
				state.setNodeName(KeyName);
				NodeStates.put(KeyName,state);
			}
			
		}
		//This is the implementation of movement control algorithm as stated in the paper
		private void controlMovement()
		{
			Hashtable CurrentNodePositions = SimulationModel.getInstance().getNetwork();
			Node CurrentNode;
			String NodeName;
			Enumeration NodeNames = CurrentNodePositions.keys();
			
			SimulationUtilities.logOutput("Movement Control Start ************");
//			set all velocities to preferredvelocity before stopping any node
			resetNodeVelocities(CurrentNodePositions);
			// handle deviations
			while(NodeNames.hasMoreElements())
			{
				NodeName = (String)NodeNames.nextElement();
				CurrentNode = (Node)CurrentNodePositions.get(NodeName);
				handleDeviations(CurrentNode);
				//handleNeighbourhoods(CurrentNode,CurrentNodePositions);
			}
			
			//handle distance increase
			NodeNames = CurrentNodePositions.keys();	
			while(NodeNames.hasMoreElements())
			{
				NodeName = (String)NodeNames.nextElement();
				CurrentNode = (Node)CurrentNodePositions.get(NodeName);
				//handleDeviations(CurrentNode);
				handleNeighbourhoods(CurrentNode,CurrentNodePositions);
			}
			
			SimulationUtilities.logOutput("Movement Control End ************");		
		}
		// handling case 2 and 3 of the algo.
		private void handleDeviations(Node node)
		{
			SimulationNodeState State = (SimulationNodeState)NodeStates.get(node.getNodeName());
			StringBuffer MessageBuffer = new StringBuffer();
			
			if(!node.getNodeName().equals(CoordinatorNodeName))
			{
				node.setEditable(true);
			}
			
			if (State.getStatus() == SchemeConstants.NODEDIRECTIONDEVIATED) //handle previously directed node
			{
				double angle = State.getDeviateAngle();
				node.setMovementDirection(node.getMovementDirection()+angle);
				State.setStatus(SchemeConstants.NODERESET); 
				node.setPreferredVelocity(node.getCurrentVelocity());
				node.setEditable(false);
				
				MessageBuffer.append("Node ");
				MessageBuffer.append(node.getNodeName());
				MessageBuffer.append(" has been deviated from the normal direction during the previous interval ");
				MessageBuffer.append("\n");
			}
			else if (State.getStatus() == SchemeConstants.NODERESET) //identify new deviations
			{
				double angle = node.getMovementDirection();
				if (angle != 0)
				{
					node.setMovementDirection(angle - 2*angle);
					State.setStatus(SchemeConstants.NODEDIRECTIONDEVIATED);
					State.setDeviateAngle(angle);
					node.setPreferredVelocity(node.getCurrentVelocity());
					node.setEditable(false);
					
					MessageBuffer.append("Node ");
					MessageBuffer.append(node.getNodeName());
					MessageBuffer.append(" has been deviated from the normal direction during the current interval ");
					MessageBuffer.append("\n");
				}
				
			}
			
			SimulationUtilities.logOutput(MessageBuffer.toString());
		}
		
		private void handleNeighbourhoods(Node node,Hashtable nodelist)
		{
			Iterator Neighbours = node.getNeighbourList();
			Node NeighbourNode;
			String NeighbourNodeName;
			NodePosition Position = node.getCurrentPosition();
			NodePosition NeighbourPosition;
			ConfigurationReader reader = AdaptiveSimulationUtilities.getConfigurationReader();
			
			SimulationNodeState State = (SimulationNodeState)NodeStates.get(node.getNodeName());
			SimulationNodeState NeighbourNodeState;
			StringBuffer MessageBuffer = new StringBuffer();
			
			while(Neighbours.hasNext())
			{
				NeighbourNodeName = (String)Neighbours.next();
				NeighbourNode = (Node)nodelist.get(NeighbourNodeName);
				
				NeighbourNodeState = (SimulationNodeState)NodeStates.get(NeighbourNodeName);
				
				NeighbourPosition = NeighbourNode.getCurrentPosition();
				
			
				// case.
				if (NodePosition.caluclateDistance(Position,NeighbourPosition) > reader.getNeighbourhoodDistance())
				{
					MessageBuffer = new StringBuffer();
					MessageBuffer.append(" The neighbourhood distance increased for Nodes ");
					MessageBuffer.append(node.getNodeName());
					MessageBuffer.append(" and ");
					MessageBuffer.append(NeighbourNodeName);
					MessageBuffer.append(" ");
					
					if (!(State.getStatus() == SchemeConstants.NODEDIRECTIONDEVIATED))
					{
												
						if (Position.getYPosition() > NeighbourPosition.getYPosition())
						{
							node.setCurrentVelocity(0);
							State.setStatus(SchemeConstants.NODESTOPPED);
							node.setEditable(false);
							
							MessageBuffer.append(node.getNodeName());
											
						}
						else if (Position.getYPosition() < NeighbourPosition.getYPosition())
						{
							NeighbourNode.setCurrentVelocity(0);
							NeighbourNodeState.setStatus(SchemeConstants.NODESTOPPED);
							NeighbourNode.setEditable(false);
							
							MessageBuffer.append(NeighbourNodeName);
							
						}
						MessageBuffer.append(" has gone ahead and hence being stopped");
					}
					MessageBuffer.append("\n");
					SimulationUtilities.logOutput(MessageBuffer.toString());
				}
				
			}
		}
	 
	 private void resetNodeVelocities(Hashtable Nodes)//think about specifics
	 {
		Enumeration SimulationStates = NodeStates.elements();
		SimulationNodeState state;
		Node currentNode;
		
		while(SimulationStates.hasMoreElements())
		{
			state = (SimulationNodeState)SimulationStates.nextElement();
			if (state.getStatus() == SchemeConstants.NODESTOPPED)
			{
				currentNode = (Node)Nodes.get(state.getNodeName());
				currentNode.setCurrentVelocity(currentNode.getPreferredVelocity());
				if(!currentNode.getNodeName().equals(CoordinatorNodeName))
				{
					currentNode.setEditable(true);
				}
			}
			if (!(state.getStatus() == SchemeConstants.NODEDIRECTIONDEVIATED))
			{
				state.setStatus(SchemeConstants.NODERESET);
			}
			
					
			
		}
	 }
	 
	public boolean isNodesWellConnected(
			NodePosition position1,
			NodePosition position2) {
			boolean toReturn = false;
		
			ConfigurationReader reader = AdaptiveSimulationUtilities.getConfigurationReader();
			
			//System.out.println("First Position **** "+position1.getXPosition()+","+position1.getYPosition());
			//System.out.println("Second Position **** "+position2.getXPosition()+","+position2.getYPosition());
			
			
			double distance = NodePosition.caluclateDistance(position1,position2);
			
			//System.out.println("Calculated Distnce "+distance);
			if (distance <= reader.getNeighbourhoodDistance())
				toReturn = true;
		
			//System.out.println("Returning******* "+toReturn);
			return toReturn;
		}
		
	protected String getSimulatorName()
		{
			return new String("Adaptive Simulation");
		}
    
	public boolean isPositionEditable() {
			if (CoordinatorNodeName.trim().length() == 0)
				return true;
			else	
				return false;
		}
		
	public boolean isSpecialNode(String NodeName)
	{
		return CoordinatorNodeName.equals(NodeName);	
	}
	
	public void validateNode(Hashtable Nodes, String NodeName, double Velocity)
			throws SimulatorException {
			ConfigurationReader reader = AdaptiveSimulationUtilities.getConfigurationReader();
			double MaxVelocity= Double.valueOf(""+reader.getMaximumVelocity()).doubleValue();
			if (Velocity > MaxVelocity )
				throw new SimulatorException("" + NodeName + SimulatorMessages.VELOCITYEXCEEDTEXT);
            
            Node currentNode = (Node)Nodes.get(NodeName);
			//System.out.println("Editable state is "+currentNode.isNodePropertyEditable());
            if (Velocity == 0 && currentNode.isNodePropertyEditable())
            	throw new SimulatorException("" + NodeName + SchemeConstants.NODESTOPMESSAGE);
		}
		
	public boolean validateNetwork(Hashtable Nodes) throws SimulatorException {
			//want to run this validation only for the first time
			if(CoordinatorNodeName.trim().length() == 0)
			{
				ConfigurationReader reader = AdaptiveSimulationUtilities.getConfigurationReader();
				SimulationUtilities.identifyNeighbours(Nodes,reader);
				SimulationModel.getInstance().setNetwork(Nodes);
				
				if (!AdaptiveSimulationUtilities.isNetworkConnected(Nodes))	
					throw new SimulatorException(SchemeConstants.NETWORKDISCONNECTMESSAGE);
		

				CoordinatorNodeName = AdaptiveSimulationUtilities.setCoordinator(Nodes);
				createInitialState(Nodes);	
			}
			
			
			return true;
		}
		
	  public String getOutputFileName()
		{
			return "AdaptiveSimulationOutput";
		}
		
	  public void refreshNetwork(Hashtable Nodes) {
			AdaptiveSimulationUtilities.identifyNeighbours(Nodes,AdaptiveSimulationUtilities.getConfigurationReader());

		}
}
