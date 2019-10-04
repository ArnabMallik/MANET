/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

/**
 *
 * @author Abhishek
 */
public class Hopcount_calculator {
    
    public static int hop_count_store[]=new int[Utilities.SimulationUtilities.no_of_node+1];
    
    public static int store_importance[]=new int[Utilities.SimulationUtilities.no_of_node+1];
    
    public static int flag=0;
    
    
    

    
   
    public static void count_hop()
    {
        
         
        
        NodePosition np1;
        
        NodePosition np2;
        
        Node hold[]=SimulationUtilities.get_array(); // returns the node array
        
        double neighbourhood_range=SimulationConstants.COMMUNICATIONRANGE/2;
        
        for(int i=1;i<=SimulationUtilities.no_of_node;i++)
            
            hop_count_store[i]=0;
        
    
        for(int i=1;i<SimulationUtilities.no_of_node-3+UI.NetworkConfigurationPanel.added;i++)
        {
            
            for(int j=i+1;j<=SimulationUtilities.no_of_node-3+UI.NetworkConfigurationPanel.added;j++)
                
            {
                
                if(i!=j) {
                    
                    np1=hold[i].getCurrentPosition();
                    
                    np2=hold[j].getCurrentPosition();
                        
                    double xpos_diff=np1.getXPosition()-np2.getXPosition();
                    
                    double ypos_diff=np1.getYPosition()-np2.getYPosition();
                    
                    if(Math.sqrt(xpos_diff*xpos_diff+ypos_diff*ypos_diff)<=neighbourhood_range)
                        
                    {
                       // System.out.println(i+" "+j+" "+Math.sqrt(xpos_diff*xpos_diff+ypos_diff*ypos_diff));
                            hop_count_store[i]=hop_count_store[i]+1;
                        
                             hop_count_store[j]=hop_count_store[j]+1;
                            
                        }
                        
                        
                        
                    }
                    
                 
                    
            
             
                }
                
               
                
            
            
            
        }
        
       
            

        
        
      
        
      
    
}

    


public static int getMax(int n) 

{
     int max=-1,i;
                      
                    for(i=0;i<n;i++)
                    {
                        if(hop_count_store[i]>=max)
                          
                            max=hop_count_store[i];
                         
                    }
                    
                    
                    return max;

}


    
    
    
    
    
}


