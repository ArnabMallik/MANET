/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

/**
 *
 * @author Arnab
 */
public class Imp {
    public static void random(int a[])
    {
        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

        int temp1,temp2,n=a.length;
        
        
        for(int i=0;i<a.length;i++)
            a[i]=i+1;
        if(n>2)
       for(int j=0;j<10;j++)
       { 
           
            
            temp1=(int)(Math.random()*100)%n;
            temp2=(int)(Math.random()*100)%n;
            if(temp1 >=n || temp2 >=n)
            {
                --temp1;
                --temp2;
            }
            
            
            
                //System.out.println(temp1+" "+temp2);
            
            int t=a[temp1];
            a[temp1]=a[temp2];
            a[temp2]=t;
            
        }
                                                      
            
                
               
               
        
        
        
        
    }
    
    
}

        
    