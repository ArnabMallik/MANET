/*
 * Created on Aug 13, 2005
 *
 * 
 */
package UI;

import java.util.ArrayList;

import org.jfree.chart.tooltips.CustomXYToolTipGenerator;

import Simulation.SimulationModel;

/**
 * @author soumya
 *
 * This class generates the tooltip for the nodes. It uses JFreeChart classes
 * 
 */
public class NodeToolTipGenerator extends CustomXYToolTipGenerator{
	public NodeToolTipGenerator(SimulationModel model)
		{
			super();
			
			ArrayList list = model.getToolTipList();
			
			for (int i = 0; i< list.size();i++)
			{
				addToolTipSeries((ArrayList)list.get(i));
				
			}
					
		
		 }

}
