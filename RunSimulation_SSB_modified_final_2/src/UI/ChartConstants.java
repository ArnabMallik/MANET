/*
 * Created on Aug 28, 2005
 *
 */
package UI;

import java.awt.Color;

/**
 * @author soumya
 *
 * Constant file describing the constants used in the User Interface components
 */
public class ChartConstants {
	
	        public static String RUNTEXT = "Run";
		public static String PROPERTYTEXT = "Pause";
                public static String QUEUETEXT="Show Queue";   // added this constant for the leader queue
		public static Color BACKGROUNDCOLOR = Color.GRAY;
		public static Color PREFEREDLINKCOLOR = Color.green;
		public static Color ALTERNATELINKCOLOR = Color.red;		
		public static String NODENAMEMARKER = "*"; 
		
		public static int CREATECHART = 1;
		public static int MOVECHART = 2;
		public static int STOPCHART = 3;
		public static int UPDATECHART = 4;

}
