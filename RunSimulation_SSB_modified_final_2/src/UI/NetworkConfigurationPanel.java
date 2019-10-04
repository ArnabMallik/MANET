/*
 * Created on Aug 28, 2005
 *
 * 
 */
package UI;

import java.awt.GridLayout;
import java.util.Enumeration;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Simulation.SimulationModel;
import UI.Components.DoubleTextField;
import UI.Components.IntegerTextField;
import static UI.SimulationConfigurationPanel.leader_node_changed;
import Utilities.NetworkDirector;
import Utilities.Node;
import Utilities.Hopcount_calculator;
import Utilities.SimulatorException;
import Utilities.NodePosition;
import Utilities.QueueManager;
import Utilities.SimulationConstants;
import Utilities.SimulationUtilities;
import java.awt.Event;
import java.io.FileWriter;
import java.util.Collections;
import javax.rmi.CORBA.Util;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;


/**
 * @author soumya
 *
 * This panel is responsible to paint the network configuration 
 * parameters for different nodes in the network
 */
public class NetworkConfigurationPanel extends JPanel { 
	
	JLabel[] nodeNames;
	DoubleTextField[] XPositions;
	DoubleTextField[] YPositions;
	IntegerTextField[] Velocities;
	DoubleTextField[] Directions;
        IntegerTextField[] Importance;  // added this parameter
        DoubleTextField[] Battery_Life;  // added this parameter
        DoubleTextField[] HopStrength;   // added this parameter
       IntegerTextField[] Hopcount;     // added this parameter
        DoubleTextField[] Weight;   // added this parameter
	
	NetworkDirector director;
        
      
        
        public static NodePosition np[]=new NodePosition[Utilities.SimulationUtilities.no_of_node];  //added this array for storing the X and Y co-ordinate value of nodes
        
        public static int row_to_add,count1=0,count2=0,run=0,add_queue=0,first_time=0,one_time=0,update_running=0,count=0,added=0,deleted=0,delete_id=0,no_more_add=0,delete_confirmed=0;
        
        public static String s="";
        public static ArrayList al=new ArrayList();
        
         public static ArrayList al1=new ArrayList();
         
           public static ArrayList store_battery=new ArrayList();
        
        public static Node delete_node=new Node();
        //count1 and count2 has been used to make distinction for the initialization at first time and updation after this
        
        //run has been used to retrieve and manipulate the parameter values when the run button is clicked
        
        //add_queue has been used to insert the node id according to decreasing weight
        
        public static double max_hop_strength=0;
        
       // max_hop_strength has been used to calculate the hop strength values
         
           public static double weight_store[]=new double[Utilities.SimulationUtilities.no_of_node];
           
            public static double hopstrength_store[];
	
	public NetworkConfigurationPanel(SimulationModel model,NetworkDirector operator) throws IOException
	{
             Utilities.Hopcount_calculator.count_hop(); // calculates the number of direct neighbours of a node
              
            // creates an array to store the leaders
                   
        
                
   
           
            
		int noOfRows = model.getNumberOfNodes();
		director = operator;
                setLayout(new GridLayout(noOfRows-2, 10));
		String labelText1 = "Node Name";
		String labelText2 = "X Position";
		String labelText3 = "Y Position";
		String labelText4 = "Velocity (In Km/Hr)";
		String labelText5 = "Direction (In Degrees)";
                String labelText6 = "Importance";
                String labelText7 = "Battery Life(in %)";
                String labelText9 = "Weight";
                String labelText10= "HopStrength";
                String labelText11="hopcount";
                
               // System.out.println(noOfRows);

		add(new JLabel(labelText1));
		add(new JLabel(labelText2));
		add(new JLabel(labelText3));
		add(new JLabel(labelText4));
		add(new JLabel(labelText5));
                add(new JLabel(labelText6));
                add(new JLabel(labelText7));
                add(new JLabel(labelText9));
                add(new JLabel(labelText10));
                 add(new JLabel(labelText11));
                 
                 row_to_add=noOfRows-2;
                 
                // if(deleted==0 && added==1) 
                     
                  
                       //   setLayout(new GridLayout(noOfRows+1, 9));
                 
                // else if(deleted==1 && added==0)
                     
                    // setLayout(new GridLayout(noOfRows, 9));
                     
               
                
               
                 
                 for(int i=0;i<=3;i++) {
		          
                 if(added==i) {
                     //System.out.println(added);
                row_to_add+=i;
                 
                       setLayout(new GridLayout(row_to_add, 10));
                       
                     
                       
                 }
                 
                 }
                 
                
                  row_to_add=noOfRows-2;
                  
                   if(deleted==1 && added==3)
                 
              setLayout(new GridLayout(noOfRows, 10));
                     
                  /*   if(SimulationConfigurationPanel.node_added==1){
                         
                         
                   
          
                                add(new JLabel("Node"+(SimulationUtilities.no_of_node+1)));
                                DoubleTextField xpos=new DoubleTextField();
                                xpos.setText(String.format("%.2f",SimulationUtilities.n.getCurrentPosition().getXPosition()));
				add(xpos);
              
				 DoubleTextField ypos=new DoubleTextField();
                                ypos.setText(String.format("%.2f",SimulationUtilities.n.getCurrentPosition().getYPosition()));
                                add(ypos);
				IntegerTextField vel=new IntegerTextField();
                                vel.setText(""+Math.round(SimulationUtilities.n.getCurrentVelocity()));
                                add(vel);
				//add(Directions[rowCount]);
                               IntegerTextField imp=new IntegerTextField();
                                imp.setText(""+Math.round(SimulationUtilities.n.getImportance()));
                                add(imp);
                                DoubleTextField battery=new DoubleTextField();
                                battery.setText(String.format("%.2f",SimulationUtilities.n.getBatterylife()));
                                add(battery);
                               DoubleTextField weight=new DoubleTextField();
                               // weight.setText(String.format("%.2f",weight_store[0]));
                                add(weight);
                                DoubleTextField hs=new DoubleTextField();
                                //hs.setText(String.format("%.2f",hopstrength_store[0]));
                                add(hs);
                                
                                IntegerTextField hc=new IntegerTextField();
                                //hc.setText(""+Hopcount_calculator.hop_count_store[SimulationUtilities.no_of_node]);
                                add(hc);
                                
                               
                     
                                //  System.out.println(hopstrength_store[0]);
                                  
                                
                                  
                                  SimulationConfigurationPanel.node_added=0;
                       
                     
                 }*/
                     
                 
                 
            
                 
                 
              
                 

		nodeNames = new JLabel[noOfRows];
		XPositions = new DoubleTextField[noOfRows];
		YPositions = new DoubleTextField[noOfRows];
		Velocities = new IntegerTextField[noOfRows];
		Directions = new DoubleTextField[noOfRows];
                Importance = new IntegerTextField[noOfRows];
                Battery_Life = new DoubleTextField[noOfRows];
                HopStrength =new DoubleTextField[noOfRows];
                Hopcount = new IntegerTextField[noOfRows];
                Weight = new DoubleTextField[noOfRows];
                
            

		ArrayList Nodes = SimulationUtilities.al;
		Enumeration nodeList = Collections.enumeration(Nodes);
		Node tempNode;
		int rowCount = 0;
    
                int max_hop_count=Utilities.Hopcount_calculator.getMax(Utilities.SimulationUtilities.no_of_node);
                
                
                  //int importance_div=30/ Utilities.SimulationUtilities.get_total();
                  Double result[]=new Double[Utilities.SimulationUtilities.no_of_node];
                 
                 
                  int a[]=new int[noOfRows],temp=0;
                  Imp.random(a);
                  
                  
                  
                  int b[]=new int[noOfRows];
                  for(int i=0;i<noOfRows;i++){
                      
                  Random r=new Random();
                  b[i]=100;
                     // b[i]=r.nextInt(100-50+1)+50;
                  
                    
                  }
		while (nodeList.hasMoreElements()) {
				tempNode = (Node) nodeList.nextElement();
                                
                                    
                                   
                                
                               
                                
                                np[rowCount]=tempNode.getCurrentPosition();
                            
                         
				if(operator.isSpecialNode(tempNode.getNodeName()))
					nodeNames[rowCount] = new JLabel(tempNode.getNodeName()+ChartConstants.NODENAMEMARKER);
				else
					nodeNames[rowCount] = new JLabel(tempNode.getNodeName());
                                

	

				XPositions[rowCount] = new DoubleTextField();
				YPositions[rowCount] = new DoubleTextField();

				XPositions[rowCount].setText(
					"" + round(tempNode.getCurrentPosition().getXPosition(),2));
				YPositions[rowCount].setText(
					"" + round(tempNode.getCurrentPosition().getYPosition(),2));

				Velocities[rowCount] = new IntegerTextField();
				Directions[rowCount] = new DoubleTextField();

				Velocities[rowCount].setText(
					"" + Math.round(tempNode.getCurrentVelocity()));
			        Directions[rowCount].setText("" + tempNode.getMovementDirection());
                                
                                Importance[rowCount]=new IntegerTextField();
                                Battery_Life[rowCount]=new DoubleTextField();
                                Hopcount[rowCount]=new IntegerTextField();
                                HopStrength[rowCount]=new DoubleTextField();
                                
                                HopStrength[rowCount].setText("0");
                                
                             //   fw.write(XPositions[rowCount].getText()+System.lineSeparator()+YPositions[rowCount].getText()+System.lineSeparator()+Velocities[rowCount].getText()+System.lineSeparator());
                               
                                Weight[rowCount]=new DoubleTextField();
                                
                                if(count1==rowCount)
                                {
                                
                                    
                                 Importance[rowCount].setText(""+a[rowCount]);                        
                                 tempNode.setImportance(a[rowCount]);
                               
                                 
                                 Battery_Life[rowCount].setText(""+b[rowCount]);   //this section runs only once during initialization
                                 
                                 tempNode.setBatterylife(b[rowCount]);
                                 
                               al.add(Battery_Life[rowCount].getText());
                               
                               al1.add(tempNode);
                                
                                count1++;
                                
                                
                                
                                }
                                
                                
                                
                                else {
                                    
                                   
                                     
                                  Importance[rowCount].setText(""+tempNode.getImportance());
                                    
                                     
                                  Hopcount[rowCount].setText(""+Hopcount_calculator.hop_count_store[Utilities.SimulationUtilities.no_of_node-rowCount]);
                      
                                        
                                        
                                  Battery_Life[rowCount].setText(String.format("%.4f",tempNode.getBatterylife()));
                                    
                                            
                                        
                                        
                                    
                                  
                               //  Battery_Life[rowCount].setText(String.format("%.4f",tempNode.getBatterylife()));
                                     
                                 //if(SimulationUtilities.no_of_node>11)
                                      
                                      
                                       // nodeNames[rowCount]=new JLabel("Node"+Integer.toString(SimulationUtilities.no_of_node-rowCount-1));
                            
                                }
                                
                                 
                               
                                if(run>0)   //this section runs when the run button is clicked
                                {
                                     
                                    
                                     if(rowCount==(Utilities.SimulationUtilities.no_of_node-Node.getMaxNodeid())) //find leader node
                                         
                                     {
                                          
                                       if(Double.parseDouble(Battery_Life[rowCount].getText())<95) {
                                            
                                         if(QueueManager.store_leader[1]==-1 )//|| QueueManager.cstore_leader[1]==-1) 
                                          {
                                              
                                              JOptionPane.showMessageDialog(this,"queue empty");
                                              
                                             System.exit(0);
                                              
                                              
                                              
                                              
                                          }
                                           
                                          SimulationConfigurationPanel.leader_node_changed=1;
                                           
                                           
                                         Node.setMaxNodeid(QueueManager.store_leader[1]);
                                           
                                          
                                           
                                           //do {
                                         
                                        QueueManager.update_queue();
                                        
                                           //}while(i==0);                  
                                          
                                          
                                              Battery_Life[rowCount].setText(String.format("%.4f",tempNode.getBatterylife()-tempNode.getBatterylife()*0.00025*Math.random()));
                                    
                                               tempNode.setBatterylife(tempNode.getBatterylife()-tempNode.getBatterylife()*0.00025*Math.random());
                                          
                                           
                                       }  
                                        
                                    
                                    Battery_Life[rowCount].setText(String.format("%.4f",tempNode.getBatterylife()-tempNode.getBatterylife()*0.005));
                                     tempNode.setBatterylife(tempNode.getBatterylife()-tempNode.getBatterylife()*0.005);
                                    
                                     }
                                     else
                                     {
                                         
                                        
                                              Battery_Life[rowCount].setText(String.format("%.4f",tempNode.getBatterylife()-tempNode.getBatterylife()*0.00025*Math.random()));
                                    
                                               tempNode.setBatterylife(tempNode.getBatterylife()-tempNode.getBatterylife()*0.00025*Math.random());
                                          
                              
                                     }
                                    //System.out.println("running");
                                     
                                     Hopcount[rowCount].setText(""+Hopcount_calculator.hop_count_store[Utilities.SimulationUtilities.no_of_node-rowCount]);
                                     
                                    //hopcount is dynamic it is thus calculated in each refresh
                                }
                                
                                //Hopcount_calculator.count_hop();
                                    
                                    
                                   
                                
                                if(run==Utilities.SimulationUtilities.no_of_node)
                                    
                                    run=0;  // all the parameters have been initialized for single refresh thus making run=0
                               
                               
                               if(count2==rowCount)
                               {
                                   double hc=30*Hopcount_calculator.hop_count_store[Utilities.SimulationUtilities.no_of_node-rowCount]/max_hop_count;
                                   
                                     
              
                                   double imp=30*(noOfRows-tempNode.getImportance()+1)/Utilities.SimulationUtilities.no_of_node;
                                   
                                //  System.out.println(imp);
                                   
                                   double bl=Double.parseDouble(Battery_Life[rowCount].getText())/100;  
                                   

                                   //double hs=Double.parseDouble(HopStrength[rowCount].getText());
                                   
                                   
                                   Hopcount[rowCount].setText(""+Hopcount_calculator.hop_count_store[Utilities.SimulationUtilities.no_of_node-rowCount]);
                                   
                                   
                                      result[rowCount]=hc+imp+20*bl;
                                      if(result[rowCount]>100)
                                      result[rowCount]=100.00;
                                       
                                      
                                      //System.out.println(result[rowCount]);
                                 
                                  //Weight[rowCount].setText(String.format("%.2f",result));
                                 count2++;
                               }
                               else
                               {
                                   
                                       
                                    double hc=30*Hopcount_calculator.hop_count_store[Utilities.SimulationUtilities.no_of_node-rowCount]/max_hop_count;
                                   
                                   double imp=30*(noOfRows-tempNode.getImportance()+1)/Utilities.SimulationUtilities.no_of_node;
                                   
                                   double bl=Double.parseDouble(Battery_Life[rowCount].getText())/100;
                                   
                                   //double hs=Double.parseDouble(HopStrength[rowCount].getText());
                                   
                                  result[rowCount]=hc+imp+20*bl;
                                  if(result[rowCount]>100)
                                      result[rowCount]=100.00;
                                
                                  
                               }
                               
                              
                               
                             /*  if(deleted==1) {
                                   
                                  // System.out.println(noOfRows);
                                   if(added==0) {
                                   if(rowCount!=noOfRows-delete_id) {
                                       
                                       add(nodeNames[rowCount]);
				add(XPositions[rowCount]);
				add(YPositions[rowCount]);
				add(Velocities[rowCount]);
				//add(Directions[rowCount]);
                                add(Importance[rowCount]);
                                add(Battery_Life[rowCount]);
                                add(Weight[rowCount]);
                                add(HopStrength[rowCount]);
                                
                                add(Hopcount[rowCount]);
                                       
                                       
                                  
                                   }
                                   
                                   }
                                   
                                   else if(added==1) 
                                       if(rowCount!=noOfRows-delete_id) {
                                          // System.out.println(noOfRows);
                                       add(nodeNames[rowCount]);
				add(XPositions[rowCount]);
				add(YPositions[rowCount]);
				add(Velocities[rowCount]);
				//add(Directions[rowCount]);
                                add(Importance[rowCount]);
                                add(Battery_Life[rowCount]);
                                add(Weight[rowCount]);
                                add(HopStrength[rowCount]);
                                
                                add(Hopcount[rowCount]);
                                       
                                       
                                  
                                   }
                                   
                                   
                                   
                                   else {
                                       
                                       
                                   }
                                   
                                   
                               }*/
                               
                              if(one_time==0)
                               store_battery.add(Double.parseDouble(Battery_Life[rowCount].getText()));
                               else
                               store_battery.set(rowCount,Double.parseDouble(Battery_Life[rowCount].getText()));
                               //System.out.println(rowCount);
                              
                                if(deleted==1) {
                                       if(rowCount!=noOfRows-delete_id) {
                                          // System.out.println(noOfRows);
                                       add(nodeNames[rowCount]);
				add(XPositions[rowCount]);
				add(YPositions[rowCount]);
				add(Velocities[rowCount]);
				add(Directions[rowCount]);
                                add(Importance[rowCount]);
                                add(Battery_Life[rowCount]);
                                add(Weight[rowCount]);
                                add(HopStrength[rowCount]);
                                
                                add(Hopcount[rowCount]);
                                       
                                     
                                       }
                                   }
                    
                                       else  if(count>(2-added)) {
                               
				add(nodeNames[rowCount]);
				add(XPositions[rowCount]);
				add(YPositions[rowCount]);
				add(Velocities[rowCount]);
				add(Directions[rowCount]);
                                add(Importance[rowCount]);
                                add(Battery_Life[rowCount]);
                                add(Weight[rowCount]);
                                add(HopStrength[rowCount]);
                                
                                add(Hopcount[rowCount]);
                                
                           
                       }
                  
                                count++;
                         
                                
                                
                                   
                              
                              
                                 
			
				if(!operator.isPositionEditable())
				{
					XPositions[rowCount].setEnabled(false);
					XPositions[rowCount].setEditable(false);
				
					YPositions[rowCount].setEnabled(false);
					YPositions[rowCount].setEditable(false);
                                        
                                        Velocities[rowCount].setEnabled(false);
					Velocities[rowCount].setEditable(false);
                                        
                                        Directions[rowCount].setEnabled(false);
					Directions[rowCount].setEditable(false);
                                        
                                        Importance[rowCount].setEnabled(false);
					Importance[rowCount].setEditable(false);
                                        
                                        Battery_Life[rowCount].setEnabled(false);
					Battery_Life[rowCount].setEditable(false);
                                        
                                        Weight[rowCount].setEnabled(false);
					Weight[rowCount].setEditable(false);
                                        
                                        HopStrength[rowCount].setEnabled(false);
					HopStrength[rowCount].setEditable(false);
                                        
                                        Hopcount[rowCount].setEnabled(false);
					Hopcount[rowCount].setEditable(false);
				}
				else
				{
					XPositions[rowCount].setEnabled(true);
					XPositions[rowCount].setEditable(true);
				
					YPositions[rowCount].setEnabled(true);
					YPositions[rowCount].setEditable(true);
                                        
                                        Velocities[rowCount].setEnabled(true);
					Velocities[rowCount].setEditable(true);
                                        
                                      //  Directions[rowCount].setEnabled(true);
					//Directions[rowCount].setEditable(true);
                                        
                                        Importance[rowCount].setEnabled(true);
					Importance[rowCount].setEditable(true);
                                        
                                        Battery_Life[rowCount].setEnabled(true);
					Battery_Life[rowCount].setEditable(true);
                                        
                                        Weight[rowCount].setEnabled(true);
					Weight[rowCount].setEditable(true);
                                        
                                        HopStrength[rowCount].setEnabled(true);
					HopStrength[rowCount].setEditable(true);
                                        
                                        Hopcount[rowCount].setEnabled(true);
					Hopcount[rowCount].setEditable(true);
				}
                                
                              
			  al.set(rowCount,Battery_Life[rowCount].getText());
                          
                          al1.set(rowCount,tempNode);
			
				if(!tempNode.isNodePropertyEditable())
				{				
					//Velocities[rowCount].setEnabled(false);
					//Velocities[rowCount].setEditable(false);
								
					//Directions[rowCount].setEnabled(false);
					//Directions[rowCount].setEditable(false);
                                        
                                        
				}
				else
				{				
					//Velocities[rowCount].setEnabled(true);
					//Velocities[rowCount].setEditable(true);
					
					//Directions[rowCount].setEnabled(true);
					//Directions[rowCount].setEditable(true);
				}

					rowCount++;
                                        
           
                                         
                                        
			}

                //System.out.println(Weight.length);
	  setBackground(ChartConstants.BACKGROUNDCOLOR);
       
          
           hopstrength_store=new double[Utilities.SimulationUtilities.no_of_node];
          
          if(count1==Utilities.SimulationUtilities.no_of_node)  //calculation of hop strength
          
          {
              
              for(int i=SimulationUtilities.no_of_node-1;i>3-added;i--)
                  
              {
                  for(int j=i-1;j>3-added-1;j--)
                      
                  {
                      
                      if(i!=j)
                          
                      {
                          double distance=NodePosition.caluclateDistance(np[i],np[j]);
                         
                         double h1=Double.parseDouble(HopStrength[i].getText());
                         
                             double h2=Double.parseDouble(HopStrength[j].getText());
                          
                          if(distance<=0.25*SimulationConstants.COMMUNICATIONRANGE) {
                              if(delete_confirmed==1){
                                  
                                  
                               if(j==noOfRows-delete_id)  { 
                                  
                                  HopStrength[i].setText(""+(h1-1));
                            
                            hopstrength_store[i]-=1;
                          
                               }
                              }
                              else {
                              
                              
                            HopStrength[i].setText(""+(h1+1));
                            
                            hopstrength_store[i]+=1;
                            
                            HopStrength[j].setText(""+(h2+1));
                            
                            hopstrength_store[j]+=1;
                              }
                          }
                              
                          else if(distance>0.25*SimulationConstants.COMMUNICATIONRANGE && distance<=0.5*SimulationConstants.COMMUNICATIONRANGE) {
                              
                                if(delete_confirmed==1){
                                  
                                  
                               if(j==noOfRows-delete_id)  { 
                                  
                                  HopStrength[i].setText(""+(h1-0.75));
                            
                            hopstrength_store[i]-=0.75;
                          
                               }
                              }
                              
                              
                              
                              
                              
                              
                              
                               HopStrength[i].setText(""+(h1+0.75));
                               
                               hopstrength_store[i]+=0.75;
                               
                               HopStrength[j].setText(""+(h2+0.75));
                               
                               hopstrength_store[j]+=0.75;
                               
                          }
                              
                          else if(distance>0.5*SimulationConstants.COMMUNICATIONRANGE && distance<=0.75*SimulationConstants.COMMUNICATIONRANGE) {
                              
                                if(delete_confirmed==1){
                                  
                                  
                               if(j==noOfRows-delete_id)  { 
                                  
                                  HopStrength[i].setText(""+(h1-0.5));
                            
                            hopstrength_store[i]-=0.5;
                          
                               }
                              }
                              
                              
                              
                              
                              
                              
                               HopStrength[i].setText(""+(h1+0.5));
                               
                               hopstrength_store[i]+=0.5;
                               
                               HopStrength[j].setText(""+(h2+0.5));
                               
                               hopstrength_store[j]+=0.5;
                          }
                          
                          else {
                              
                                if(delete_confirmed==1){
                                  
                                  
                               if(j==noOfRows-delete_id)  { 
                                  
                                  HopStrength[i].setText(""+(h1-0.25));
                            
                            hopstrength_store[i]-=0.25;
                          
                               }
                              }
                              
                               HopStrength[i].setText(""+(h1+0.25));
                               
                               hopstrength_store[i]+=0.25;
                               
                               HopStrength[j].setText(""+(h2+0.25));
                               
                               hopstrength_store[j]+=0.25;
                               
                          }
                              
                               //result[i]=result[i]+20*hs;
                     
                        //  Weight[i].setText(String.format("%.2f",result[i]));

                          
                      }
                      
                      
                  }
                  
                  
              }
              
              for (int i=0;i<SimulationUtilities.no_of_node-1;i++) {
                  
                  
                               double hs=Double.parseDouble(HopStrength[i].getText());
                               
                               if(hs>max_hop_strength)
                                       
                                       max_hop_strength=hs;
                               
                              
                               
                  
                  
              }
              
              // System.out.println(max_hop_strength);
              
              for(int i=0;i<rowCount;i++) // final calculation of weight including hop strength
              {
                  
                   double hs=Double.parseDouble(HopStrength[i].getText())/max_hop_strength;
                
                  
                    result[i]=result[i]+20*hs;
                    
                    if(first_time<=i || SimulationConfigurationPanel.updated==1 || added<3){
                    
                        if(result[i]>100.0)
                            result[i]=100.0;
                          weight_store[i]=result[i];
                          
                        
                          Weight[i].setText(String.format("%.2f",weight_store[i]));
                          
                          first_time++;
                         
                  }
                    
                  else 
                        
                   Weight[i].setText(String.format("%.2f",weight_store[i]));

               
                  
                 if(add_queue<=i)
                 {
                      add_queue++;
                  
                QueueManager.al.add(weight_store[i]); // storing the weights in an arraylist
                
                     //System.out.println("hello");
                 }
                 else 
                     
                     
                    QueueManager.al.set(i,weight_store[i]); // replacing the old value with new value
                 
                 
              
              }
             //System.out.println(Collections.min(QueueManager.al));
              
               
              
             SimulationConfigurationPanel.updated=0;
          }
          
            
          if(one_time==0 || SimulationConfigurationPanel.updated==1 || added<3){ 
         Node.setMaxNodeid(rowCount-Node.runSelectionAlgo(weight_store,rowCount-1));
         //if(one_time==0)
            // JOptionPane.showMessageDialog(this,"Please add nodes to the netowrk first and then show the leader queue");
          one_time++;
          
          }
            
          QueueManager.show_queue();
          // System.out.println(Node.getMaxNodeid());
        nodeNames[rowCount-Node.getMaxNodeid()].setText("Node"+Node.getMaxNodeid()+"*");
          
        SimulationConfigurationPanel.updated=0;
        
//        fw.close();
        
        if(count==noOfRows)
            
            count=0;
        
        if(delete_confirmed==1)
            
            delete_confirmed=0;
        
          //System.out.println(store_battery);
        
        if(Double.parseDouble((String)Collections.min(al))<94) {
        
        
            delete_id=rowCount-al.indexOf(Collections.min(al));

           // System.out.println(delete_id);
       }
	}
	// This method updates the model from the network configuration values
	public void updateModel(SimulationModel model) throws SimulatorException
	{
           
		int noOfNodes = model.getNumberOfNodes();
                
    
           
                // Utilities.Hopcount_calculator.sortImportance(noOfNodes);
               // double weight_store[]=new double[50];
                
                  
                
		String nodeName;
		double X, Y, velocity, direction,importance,battery_life;
                
             
                
		for (int i = 0; i < noOfNodes; i++) {
                    

   
                     String imp=Importance[i].getText();
                     
                    
                      // Utilities.Hopcount_calculator.insert_importance(Integer.parseInt(imp),i+1);
			nodeName = nodeNames[i].getText();
	
			if(nodeName.endsWith(ChartConstants.NODENAMEMARKER))
			{
				nodeName = nodeName.substring(0,nodeName.length()-1);
			}
	
			//System.out.println("Read X Position as "+XPositions[i].getText().trim());
			//System.out.println("Read Y Position as "+YPositions[i].getText());
			
			X = Double.parseDouble(XPositions[i].getText().trim());
			Y = Double.parseDouble(YPositions[i].getText().trim());
			
			
			velocity = Double.parseDouble(Velocities[i].getText().trim());
			//direction = Double.parseDouble(Directions[i].getText().trim());
                       importance= Double.parseDouble(Importance[i].getText().trim());
                       battery_life=Double.parseDouble(Battery_Life[i].getText().trim());
        
                   
       
                        //weight_store[count++]=weight;
                       
                          
                         
                          
                
			
			if (director != null)
			{ 
				director.validateNode(model.getNetwork(),nodeName,velocity);					
			}
		
			model.updateNodeProperties(nodeName, X, Y, velocity, (int)importance,battery_life);
                        
                          
		}
                
               
                
               //Node.setMaxNodeid(Math.abs(Node.runSelectionAlgo(weight_store,count-1)-noOfNodes));

	}
	
	private double round(double number,int digitAfterDecimal)
	{
		//handle negative
		String toReturn = Double.toString(number);
		StringBuffer buf = null;
		char char1,char2;
		int int1,int2;
		
		int length = toReturn.length();
		
		int dotPosition = toReturn.indexOf(".");
		
		if (dotPosition > 0 && length > dotPosition+3)
		{
			buf = new StringBuffer();
			buf.append(toReturn.substring(0,dotPosition+digitAfterDecimal-1));
			try // could have applied some logic involving length
			{
			
				char1 = toReturn.charAt(dotPosition+digitAfterDecimal);
				char2 = toReturn.charAt(dotPosition+digitAfterDecimal+1);
				
				int1 = new Integer(char1).intValue();
				int2 = new Integer(char2).intValue();
				
				if (int2 >= 5)
					int1++;
				buf.append(int1);	
			}
			catch (Exception e)
			{
			}
		}
		if (buf != null)
			toReturn = buf.toString();
		return (new Double(toReturn)).doubleValue();
	}
}
