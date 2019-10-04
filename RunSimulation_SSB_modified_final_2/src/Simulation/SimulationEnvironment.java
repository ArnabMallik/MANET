/*
 * Created on Aug 13, 2005
 *
 */
package Simulation;

import java.util.Hashtable;

import UI.NetworkViewer;
import Utilities.ConfigurationReader;
import Utilities.NetworkDirector;
import Utilities.NodePosition;
import Utilities.SimulationUtilities;
import Utilities.SimulatorException;
import Utilities.SimulatorMessages;

/**
 * @author soumya
 *
 * This class provides the controller, and is run to run the simulator
 * Simulator can run with three options, a) without saving 
 * b)with saving movement dtls and c) with saving movement dtls and topology diagram
 * 
 * This class also controls the threaded movement of the network
 * 
 */
public class SimulationEnvironment implements Runnable,NetworkDirector {
	
	protected NetworkViewer viewer;
	public SimulationEnvironment(String LogFileName,boolean NetworkSnapNeeded)
	{
		SimulationUtilities.initialize(LogFileName);
		Thread simulatorThread = new Thread(this);	
	    displayNodes(simulatorThread,NetworkSnapNeeded);	
	    	
	}
	
	public SimulationEnvironment(String LogFileName)
		{
			this(LogFileName,false);
		}
	
	public SimulationEnvironment()
		{
			this(null,false);	    	
		}
	
	private void displayNodes(Thread simulatorThread,boolean NetworkSnapNeeded)
	{			
		viewer = new NetworkViewer(simulatorThread,getNetworkDirector(),getSimulatorName(),NetworkSnapNeeded);
		viewer.ShowNetwork();
	}

	
	
	public void run() {
	
		while(true)
		{
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
			}
			double interval = readBeaconInterval();
			//System.out.println("The interval is "+interval);
			updateModel(interval);
			viewer.updateChart(SimulationModel.getInstance().getNetwork());
			
		}

	}
	
	protected double readBeaconInterval()
	{
		ConfigurationReader reader = SimulationUtilities.getConfigurationReader();
		double interval = reader.getBeaconInterval();
		return interval;
					
	}
	
	protected void updateModel(double interval)
	{
		SimulationModel.getInstance().updateNetwork(interval);
		//SimulationUtilities.identifyNeighbours(SimulationModel.getInstance().getNetwork(),SimulationUtilities.getConfigurationReader());
		SimulationUtilities.refreshNeighbours(SimulationModel.getInstance().getNetwork(),SimulationUtilities.getConfigurationReader());
			
	}
	
	public void generateNetwork(int numberOfNodes)
	{
		ConfigurationReader reader = SimulationUtilities.getConfigurationReader();
		Hashtable Nodes  = SimulationUtilities.generateNodes(numberOfNodes,reader);
		SimulationUtilities.identifyNeighbours(Nodes,reader);
		SimulationModel.getInstance().setNetwork(Nodes);		
	}
	
	protected NetworkDirector getNetworkDirector()
	{
		return this;
	}

	
	public boolean isNodesWellConnected(
		NodePosition position1,
		NodePosition position2) {
		boolean toReturn = false;
              
		ConfigurationReader reader = SimulationUtilities.getConfigurationReader();
		double distance = NodePosition.caluclateDistance(position1,position2);
		if (distance <= reader.getNeighbourhoodDistance())
               
			toReturn = true;
           
                
                
		return toReturn;
	}
	
	protected String getSimulatorName()
	{
		return new String("Non-Adaptive Simulation");
	}

	
	public boolean isPositionEditable() {
	
		return true;
	}

	
	public boolean isSpecialNode(String NodeName) {

		return false;
	}

	
	public boolean validateNetwork(Hashtable Nodes) throws SimulatorException {
		return true;
	}

	
	public void validateNode(Hashtable Nodes, String NodeName, double Velocity)
		throws SimulatorException {
		
		ConfigurationReader reader = SimulationUtilities.getConfigurationReader();
		double MaxVelocity= Double.valueOf(""+reader.getMaximumVelocity()).doubleValue();
		if (Velocity > MaxVelocity )
			throw new SimulatorException("" + NodeName + SimulatorMessages.VELOCITYEXCEEDTEXT);
       
	}
	
	
	public void refreshNetwork(Hashtable Nodes) {
	
		SimulationUtilities.identifyNeighbours(Nodes,SimulationUtilities.getConfigurationReader());

	}

}
