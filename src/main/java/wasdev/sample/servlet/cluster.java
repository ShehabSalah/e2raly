package wasdev.sample.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class cluster {
		
	double extent;
	double orrientation;
	double eccentricity;
	double holes;
	double paws;
	double StartEnd;
	BufferedImage image;
	
	public cluster(double extent,double orr,double ecc,double paws,double holes,double se){
		this.extent=extent;
		this.orrientation=orr;
		this.eccentricity=ecc;
		this.holes=holes;
		this.paws=paws;
		this.StartEnd=se;
	}
	//public static BufferedImage img;
	
	public String getCluster(String path) throws IOException {
		File Pfile = new File(path+File.separator+"Paws.txt");
		File Hfile = new File(path+File.separator+"Holes.txt");
		File Ofile = new File(path+File.separator+"Orientation.txt");
		File Exfile = new File(path+File.separator+"Extent.txt");
		File Ecfile = new File(path+File.separator+"Eccentricity.txt");
		File SEfile = new File(path+File.separator+"StartEnd.txt");
		Scanner Pscan = new Scanner(Pfile);
		Scanner Hscan = new Scanner(Hfile);
		Scanner Oscan = new Scanner(Ofile);
		Scanner Exscan = new Scanner(Exfile);
		Scanner Ecscan = new Scanner(Ecfile);
		Scanner SEscan = new Scanner(SEfile);
		ArrayList<String> Paws=new ArrayList<String>();
		ArrayList<String> Holes=new ArrayList<String>();
		ArrayList<String> Orientation=new ArrayList<String>();
		ArrayList<String> Extent=new ArrayList<String>();
		ArrayList<String> Eccentricity=new ArrayList<String>();
		ArrayList<String> StartEnd=new ArrayList<String>();
		
		while(Pscan.hasNextLine())
    	{
    		Paws.add(Pscan.nextLine());
    	}
		Pscan.close();
		while(Hscan.hasNextLine())
    	{
    		Holes.add(Hscan.nextLine());
    	}
		Hscan.close();
		while(Oscan.hasNextLine())
    	{
    		Orientation.add(Oscan.nextLine());
    	}
		Oscan.close();
		while(Exscan.hasNextLine())
    	{
    		Extent.add(Exscan.nextLine());
    	}
		Exscan.close();
		while(Ecscan.hasNextLine())
    	{
    		Eccentricity.add(Ecscan.nextLine());
    	}
		Ecscan.close();
		while(SEscan.hasNextLine())
    	{
    		StartEnd.add(SEscan.nextLine());
    	}
		SEscan.close();
    	String x="";
    	for(int i=0;i<Paws.size();i++) {
    		String[] h=Holes.get(i).split(" ");
    		String[] p=Paws.get(i).split(" ");
    		String[] o=Orientation.get(i).split(" ");
    		String[] ex=Extent.get(i).split(" ");
    		String[] ec=Eccentricity.get(i).split(" ");
    		String[] se=StartEnd.get(i).split(" ");
    		
    		if(holes>=Double.valueOf(h[0]) && holes<=Double.valueOf(h[1]) && 
    		   paws>=Double.valueOf(p[0]) && paws<=Double.valueOf(p[1]) && 
    		   eccentricity >= Double.valueOf(ec[0]) && eccentricity<=Double.valueOf(ec[1]) &&
    		   orrientation>=Double.valueOf(o[0]) && orrientation<=Double.valueOf(o[1])&&
    		extent>=Double.valueOf(ex[0])&&extent<=Double.valueOf(ex[1]) &&
    		this.StartEnd>=Double.valueOf(se[0])&&this.StartEnd<=Double.valueOf(se[1])){
    			x+=(i+1)+" ";
    		}
    	}
    	if(x.equals("")) {
    		System.out.println("mal2tho0o0osh");
    		for(int i=0;i<Paws.size();i++) {
    			x+=(i+1)+" ";
    		}
    	}
    	return x;
	}
}
