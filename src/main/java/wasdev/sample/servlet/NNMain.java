package wasdev.sample.servlet;
import javax.swing.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
  * Java Neural Network Example
  * Handwriting Recognition
  * by Jeff Heaton (http://www.jeffheaton.com) 1-2002
  * -------------------------------------------------
  * This class presents the main application window.
  *
  * @author Jeff Heaton (http://www.jeffheaton.com)
  * @version 1.0
  */

public class NNMain {
	
	DefaultListModel<NNSampleData> letterListModel = new DefaultListModel<NNSampleData>();

	int inputSize=57;
	int size=20;
   /**
    * The neural network.
    */
	KohonenNetwork net;//aggregation
 
	public NNMain() throws FileNotFoundException{
		//load(split(classes));
		//train();
	}
	
	String[] split(String classes){
		return classes.split(" ");
	}
	
    void load(String clas,String path) throws FileNotFoundException 
    {
    	System.out.print("MAMA ZAMNHA GAYA 2");
    	String[] classes=clas.split(" ");
    	int i=0;
    	File file;
    	String Fname;
    	Scanner scan;
    	letterListModel.clear();
    	for(int j=0;j<classes.length;j++)
    	{
    		//if(!classes[j].equals("23") )//&&!classes[j].equals("30")&&!classes[j].equals("32")&&!classes[j].equals("33"))
    		//{
    			Fname=classes[j]+".txt";
    			file = new File(path+File.separator+Fname);
    			scan = new Scanner(file);
    			while(scan.hasNextLine())
    			{
    				String line=scan.nextLine();
    				//System.out.println(line);
    				if(line==null)break;
    				
    				line=line.replace("  ", " ");
    				String[] d=line.split(" ");
    				//NNSampleData ds =new NNSampleData(  String.valueOf(scan.nextInt()),inputSize); //assoc
    				NNSampleData ds =new NNSampleData(  d[0],inputSize);
    				letterListModel.add(i++,ds);
    				for ( int y=1;y<=inputSize;y++ ) 
    				{
    					String[] dd=d[y].split(":");
    					ds.setData(y-1,Double.valueOf(dd[1]));
    				}
    			}
    		
    			scan.close();
    		//}
	   }
    	//*/
    	train();
   } 
	  
   
   /**
    * Called when the train button is pressed.
    */

    void train()
    {
    	System.out.print("MAMA ZAMNHA GAYA 3");
    	int inputNeuron = inputSize;
    	int outputNeuron = letterListModel.size();
    	NNTrainingSet set = new NNTrainingSet(inputNeuron,outputNeuron);//assoc
    	set.setTrainingSetCount(letterListModel.size());

    	int idx;
    	for ( int t=0;t<letterListModel.size();t++ ) {
    		idx=0;
    		NNSampleData ds = (NNSampleData)letterListModel.getElementAt(t);
    		for ( int y=0;y<inputSize;y++ ) {				  
    			set.setInput(t,idx++,(double)ds.getData(y));
    		}
    	}
    	net = new KohonenNetwork(inputNeuron,outputNeuron,size);//,this);
    	net.setTrainingSet(set);
    	net.learn();	  
    }

   /**
    * Called when the recognize button is pressed.
 * @throws IOException 
    */
    String recognize(String nNdata) {
    	System.out.print("MAMA ZAMNHA GAYA 4");
    	String[] data=nNdata.split(" ");
    	double[] input=new double[data.length];
    	
     	for(int i=0;i<data.length;i++)
     		data[i].replaceAll(" ", "");
    	for(int i=1;i<data.length;i++)
    	{
    		String[] d=data[i].split(":");
    		input[i-1]=Double.parseDouble(d[1]);
    	}
    	double normfac[] = new double[1];
		double synth[] = new double[1];
		int[] best = net.mywinner ( input, normfac , synth ) ;
		String map[] = mapNeurons();
		String z="";
		//double[] bestoutput = net.output ( input, normfac , synth ) ;
		ArrayList<String> count=new ArrayList<String>();
		//ArrayList output=new ArrayList();
		for(int j=0;j<size&&j<letterListModel.size();j++)
		{
			if(!count.contains(map[best[j]]))
			//{
				count.add(map[best[j]]);
				//System.out.print(map[best[j]]+"="+best[j]+"   ");
				
			//}			
		}
		//for(int j=0;j<size&&j<letterListModel.size();j++)
			//z+=best[j]+" ";
		
		//for(int j=count.size()-1;j>=0;j--)
		for(int j=0;j<count.size();j++)
			z+=count.get(j)+" ";
		//for(int j=0;j<output.size();j++)
			//z+=bestoutput[j]+" ";
		return z;
    }

   /**
    * Used to map neurons to actual letters.
    *
    * @return The current mapping between neurons and letters as an array.
    */
    String []mapNeurons() {
    	System.out.print("MAMA ZAMNHA GAYA 5");
    	String map[] = new String[letterListModel.size()];
    	double normfac[] = new double[1];
    	double synth[] = new double[1];

    	for ( int i=0;i<map.length;i++ )
    		map[i]="?";
    	for ( int i=0;i<letterListModel.size();i++ ) {
    		double input[] = new double[inputSize];
    		int idx=0;
    		NNSampleData ds = (NNSampleData)letterListModel.getElementAt(i);
    		for ( int y=0;y<inputSize;y++ ) {
    			input[idx++] =(double) ds.getData(y);
    		}
    		int[] best = net.mywinner ( input , normfac , synth ) ;
    
    		map[best[0]] =  ds.getLetter();
    	}
    	return map;
    }
}