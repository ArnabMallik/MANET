/*
 * Created on Aug 22, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author soumya
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AdaptiveSimulationRunner {
    
  
	public static void main(String[] args) {
		
		String LogFileName = "AdaptiveSimulationLog"+System.currentTimeMillis()+".txt";
		new AdaptiveSimulator.Simulation.AdaptiveSimulationEnvironment();
		//new AdaptiveSimulator.Simulation.AdaptiveSimulationEnvironment(LogFileName);
		//new AdaptiveSimulator.Simulation.AdaptiveSimulationEnvironment(LogFileName,true);	
	}
}
