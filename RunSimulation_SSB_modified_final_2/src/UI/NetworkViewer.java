/*
 * Created on Aug 13, 2005
 *
 * 
 */
package UI;

import java.util.Hashtable;

import Simulation.SimulationModel;
import Utilities.NetworkDirector;

/**
 * @author soumya
 *
 * This is the boundary class of the UI package. External world 
 * access User Interface classes through this class
 * 
 */
public class NetworkViewer {
	
	SimulationChart chart;
	
	
	public NetworkViewer(Thread inputThread,NetworkDirector generator,String FrameName,boolean NetworkSnapNeeded)
	{
		chart = new SimulationChart(FrameName,SimulationModel.getInstance(),inputThread,NetworkSnapNeeded);
		chart.addNetworkOperator(generator);		
	}
	
	public void ShowNetwork()
	{
		//SimulationUtilities.checkNodeList(Nodes);
		chart.showInputForm();
		chart.setVisible(true);
	}
	
	public void updateChart(Hashtable Nodes)
	{
		chart.updateChart(Nodes);
	}
	
	

}
