/*
 * Created on Aug 24, 2005
 *
 */
package AdaptiveSimulator.Utilities;

/**
 * @author soumya
 *
 * This is a pure helper file. This helps the adaptive simulator to 
 * maintain intermediate node state during movement
 */
public class SimulationNodeState {
	private String NodeName;
	private int Status;
	private double DeviateAngle;
	
	public SimulationNodeState()
	{
		NodeName = null;
		Status = SchemeConstants.UNDEFINED;
		DeviateAngle = 0;
	}

	
	public double getDeviateAngle() {
		return DeviateAngle;
	}

	
	public String getNodeName() {
		return NodeName;
	}

	
	public int getStatus() {
		return Status;
	}

	
	public void setDeviateAngle(double d) {
		DeviateAngle = d;
	}

	
	public void setNodeName(String string) {
		NodeName = string;
	}

	
	public void setStatus(int i) {
		Status = i;
	}

}
