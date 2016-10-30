package wasdev.sample.servlet;

import java.util.Arrays;

public class Noise {
	public int[][] start(int[][] imagedata)
	{
		//*********************noise *************************************************//
	   
	      int [][] filter = new int[3][3];
          
          filter[0][1] = 1;
          filter[0][0] = 1;
          filter[0][2] = 1;
          filter[1][0] = 1;
          filter[1][1] = 1;
          filter[1][2] = 1;
          filter[2][0] = 1;
          filter[2][1] = 1;
          filter[2][2] = 1;
          for(int z = 0 ; z < imagedata.length; z++){
        	  for(int p = 0; p < imagedata[0].length; p++){
        		  if(imagedata[z][p] == 1)
        			  imagedata[z][p] = 0;
        		  else
        			  imagedata[z][p] = 1;
        	  }
          }
          for(int z = 1 ; z < imagedata.length - 1; z++){
        	  for(int p = 1; p < imagedata[0].length - 1; p++){
        		  if(imagedata[z][p] == 1){
        			  int f = imagedata[z - 1][p - 1] * filter[0][0] +
        					 imagedata[z][p - 1] * filter[0][1] +
		                     imagedata[z + 1][p - 1] * filter[0][2] +
		                     imagedata[z - 1][p] * filter[1][0] +
		                     imagedata[z][p] * filter[1][1] +
		                     imagedata[z + 1][p] * filter[1][2] +
		                     imagedata[z - 1][p + 1] * filter[2][0] +
		                     imagedata[z][p + 1] * filter[2][1] +
		                     imagedata[z + 1][p + 1] * filter[2][2];
        			  if(f < 3 ){
        				  imagedata[z - 1][p - 1] = 0;
        				  imagedata[z][p - 1] = 0;
        				  imagedata[z + 1][p - 1] = 0;
        				  imagedata[z - 1][p] = 0;
        				  imagedata[z][p]=0;
        				  imagedata[z + 1][p] = 0;
        				  imagedata[z - 1][p + 1] = 0;
        				  imagedata[z][p + 1] = 0;
        				  imagedata[z + 1][p + 1] =0;
	                  }
	               }
	           }
	       }
          for(int z = 0 ; z < imagedata.length; z++){
        	  for(int p = 0; p < imagedata[0].length; p++){
        		if(z==0 || z == imagedata.length - 1 || p == 0 || p == imagedata[0].length - 1){
        			imagedata[z][p]=1;
        		}  
        	  }
          }
          for(int z = 0 ; z < imagedata.length; z++){
        	  for(int p = 0; p < imagedata[0].length; p++){
        		  if(imagedata[z][p] == 0)
        			  imagedata[z][p] = 1;
        		  else
        			  imagedata[z][p] = 0;
        	  }
          }
         return imagedata;
       //*********************End noise *************************************************//
	}
	public int[][] medianFilter(int[][] imagedata){
    	for(int loop = 0; loop < 4; loop++){
	        int[] list = new int[9];
	        for (int x = 1; x < imagedata.length - 1; x++){
	            for (int y = 1; y < imagedata[0].length - 1; y++)
	            {
	               	list[0] = imagedata[x-1][y-1];
	               	list[1] = imagedata[x][y-1];
	               	list[2] = imagedata[x+1][y-1];
	               	list[3] = imagedata[x-1][y];
	               	list[4] = imagedata[x][y];
	               	list[5] = imagedata[x+1][y];
	               	list[6] = imagedata[x-1][y+1];
	               	list[7] = imagedata[x][y+1];
	               	list[8] = imagedata[x+1][y+1];      	
	               	Arrays.sort(list);
	                imagedata[x][y] = list[4];
	             }
	         }
	         
    	}
    	return imagedata;
    }
}