/*
 * Created on Aug 21, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package Utilities;

/**
 * @author soumya
 *
 * Custom exception class to be used in the simulator to propagate exceptions
 */
public class SimulatorException extends Exception {
 public SimulatorException(String message)
 {
 	super(message);
 }
}
