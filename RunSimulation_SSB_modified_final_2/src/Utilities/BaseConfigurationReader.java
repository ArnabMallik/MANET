/*
 * Created on Aug 17, 2005
 *
 *  
 */
package Utilities;

/**
 * @author soumya
 *
 * This class gives a base implementation of ConfigurationReader Interface.
 * It provides the parameters for the base simulator.
 */
public class BaseConfigurationReader implements ConfigurationReader {

	
        @Override
	public double getCommunicationRange() {
		
		return SimulationConstants.COMMUNICATIONRANGE;
	}

    /**
     *
     * @return
     */
    @Override
	public double getNeighbourhoodDistance() {
		
		return getCommunicationRange()/2;
	}

	
        @Override
	public int getMaximumVelocity() {
		
		return SimulationConstants.MAXVELOCITY;
	}

	
        @Override
	public double getDefaultAngle() {
		
		return SimulationConstants.DEFAULTANGLE;
	}

	
        @Override
	public double getBeaconInterval() {
		
		return 12;
	}

}
