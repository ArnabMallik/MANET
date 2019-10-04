/*
 * Created on Aug 13, 2005
 *
 */
package Utilities;

/**
 * @author soumya
 *
 * This class encapsulates a node position, similar to a point class.
 */
public class NodePosition {
	
		private double xPosition;
		private double yPosition;

		
		public NodePosition() {
			super();
		
		}
	
		public NodePosition(double X,double Y)
		{
			xPosition = X;
			yPosition = Y;
		}

		
		public double getXPosition() {
			return xPosition;
		}

		
		public double getYPosition() {
			return yPosition;
		}

		
		public void setXPosition(double d) {
			xPosition = d;
		}

		
		public void setYPosition(double d) {
			yPosition = d;
		}
	
		public static double caluclateDistance(NodePosition pos1,NodePosition pos2)
		{
			double toReturn;
			double term1, term2;
	
			term1 = (pos1.xPosition-pos2.xPosition);
			term2 = (pos1.yPosition-pos2.yPosition);
		
			toReturn = Math.sqrt(term1*term1+term2*term2);
			//System.out.println("Distance "+toReturn);
			return toReturn;
		}
	
		public String toString()
		{
			return "X = "+ xPosition +" Y = "+yPosition;
		}


}
