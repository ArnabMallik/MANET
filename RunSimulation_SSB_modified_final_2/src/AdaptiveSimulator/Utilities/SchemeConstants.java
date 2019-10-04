/*
 * Created on Aug 22, 2005
 *
 */
package AdaptiveSimulator.Utilities;

import Utilities.SimulationConstants;

/**
 * @author soumya
 *
 * This file defines some constants used in our adaptive algorithm
 */
public class SchemeConstants {
	
	public static final double NEIGHBOURHOODDISTANCE = SimulationConstants.COMMUNICATIONRANGE/2;
	public static final double MAXIMUMPROPAGATIONTIMEINMILLIS = 1000;//is it realistic?
	
	public static final int NODEDIRECTIONDEVIATED = 1;
	public static final int NODESTOPPED = 2;
	public static final int NODERESET = 3;
	public static final int UNDEFINED = -1;
	
	public static final String NODESTOPMESSAGE = " can not be manually stopped, it can unstabilize the network";
	public static final String NETWORKDISCONNECTMESSAGE = " The network is not connected, algorithm can not start";

}
