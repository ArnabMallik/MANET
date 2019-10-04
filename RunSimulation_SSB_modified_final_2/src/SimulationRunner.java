
import Simulation.SimulationEnvironment;



/*
 * Created on Aug 21, 2005
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
public class SimulationRunner { 

	public static void main(String[] args) {
		String LogFileName = "SimulationLog"+System.currentTimeMillis()+".txt";
            SimulationEnvironment simulationEnvironment = new Simulation.SimulationEnvironment();
            //new Simulation.SimulationEnvironment(LogFileName);
            //new Simulation.SimulationEnvironment(LogFileName,true);	
	}
}
