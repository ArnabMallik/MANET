/*
 * Created on Aug 17, 2005
 *
 * 
 */
package Utilities;

/**
 * @author soumya
 *
 * This interface gives a provision to read configuration paramteres of the simulator
 * It is kept as an interface considering future extensibility.
 */
public interface ConfigurationReader {
	
	public double getCommunicationRange(); //in KM
	public double getNeighbourhoodDistance(); //in KM
	public int getMaximumVelocity();//in KM/Hr
	public double getDefaultAngle();//in degrees
	public double getBeaconInterval();//in minutes
	
}
