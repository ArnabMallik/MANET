/*
 * Created on Aug 21, 2005
 *
 */
package AdaptiveSimulator.Utilities;

import AdaptiveSimulator.Utilities.*;
import Utilities.BaseConfigurationReader;

/**
 * @author soumya
 *
 * This is the algorithm specific implementation for the 
 * ConfigurationReader interface . 
 */
public class AdaptiveConfigurationReader extends BaseConfigurationReader{
	
	public double getNeighbourhoodDistance()
	{
		return SchemeConstants.NEIGHBOURHOODDISTANCE;
	}
	//return in minutes...refer to paper for the formula
	public double getBeaconInterval()
	{
		double IntervalInMinutes = 0;
		IntervalInMinutes = (getNeighbourhoodDistance()/(4*getMaximumVelocity()))*60;
		
		IntervalInMinutes = IntervalInMinutes - 2*(SchemeConstants.MAXIMUMPROPAGATIONTIMEINMILLIS/(1000*60));
		
		return IntervalInMinutes;
		//return super.getBeaconInterval();
	}
	

}
