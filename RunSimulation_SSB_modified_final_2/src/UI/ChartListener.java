/*
 * Created on Aug 28, 2005
 *
 * 
 */
package UI;

import Utilities.SimulatorException;

/**
 * @author soumya
 *
 * The listener interface to handle chart event
 */
public interface ChartListener {
	
	public void handleChartEvent(ChartEvent event) throws SimulatorException;

}
