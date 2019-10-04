/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.util.*;

/**
 *
 * @author Abhishek
 */
public class QueueManager {
    
    public static ArrayList al=new ArrayList();
    
    public static int change=1,queue_holder=0,a=1,len;
    
    public static boolean queue_empty=true;
  
    public static int store_leader[];
    
    
    
    public static void initialize()
    {
        
      
        
       
         
            
           store_leader=new int[SimulationUtilities.no_of_node-1];
          //cstore_leader=new int[Math.round(SimulationUtilities.no_of_node)]; 
          len=SimulationUtilities.no_of_node-3;
            

        
        
         
        
    }
    
    public static void increase_length(){
        
        if(UI.NetworkConfigurationPanel.added<=2)
        
        len++;
        
    }
    
    
    
    public static void show_queue() {
        
        
        
        
        double large;
        
        if(change==0)
            
            return;
        
        else {
            int j;
            
            for(j=2;j>=0;j--) {
            
           if(j<3-UI.NetworkConfigurationPanel.added) {
                
                al.set(j,0.0);
                
           }
           
            }
            
            if(UI.NetworkConfigurationPanel.deleted==1)
                
                al.set(SimulationUtilities.no_of_node-UI.NetworkConfigurationPanel.delete_id,0.0);
            
        // System.out.println(al);
            
        for(int i=0;i<len;i++)
        {
            
                   large=(double)Collections.max(al);
        
                     store_leader[i]=SimulationUtilities.no_of_node-al.indexOf(large);
                     
   
                    
                    while(al.contains(large))
                    
                   al.set(al.indexOf(large),0.0);
                    
          
           
                  
     
        
                }
        
       
                    
        
        
    }
        change=0;
        
    }
    
    
    public static int update_queue(){
        
        
        
        
        for(int i=0;i<store_leader.length-1;i++)       
           store_leader[i]=store_leader[i+1];
           
            store_leader[store_leader.length-1]=-1;
            
        
           
    if((double)UI.NetworkConfigurationPanel.store_battery.get(SimulationUtilities.no_of_node-store_leader[0])>50)
    { 
       //System.out.println((double)UI.NetworkConfigurationPanel.store_battery.get(SimulationUtilities.no_of_node-store_leader[0]));
           return 1;
    }
    else  return 0;
       
        
       // for(int i=0;i<len-1;i++)       
         //  cstore_leader[i]=cstore_leader[i+1];
        
        
        
       
        //cstore_leader[len-1]=-1;
        
        
        
    } 
    
    public static boolean isQueueEmpty() {
        
       if(store_leader[0]==-1 )//|| cstore_leader[0]==-1)
           
           return true;
       
       return false;
          
        
    }
    
    public static int get_next_leader() {
        
        
        return store_leader[a++];
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
}
