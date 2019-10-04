/*
 * Created on Aug 28, 2005
 *
 *  */
package UI;



/**
 * @author soumya
 *
 * Custom Event defined to be used as a communication mechanism between multiple 
 * User Interface panels. The communication follows standard event delegation
 * model
 * 
 */
public class ChartEvent {
	private int EventId;
	private Object EventValue;
	
	public ChartEvent(int id,Object value)
	{
           
		EventId = id;
		EventValue = value;
	}

	
	public int getEventId() {
		return EventId;
	}

	
	public Object getEventValue() {
		return EventValue;
	}

}
