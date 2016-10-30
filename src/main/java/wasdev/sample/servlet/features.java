package wasdev.sample.servlet;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import ij.process.ColorProcessor;

public class features {
	
	private double extent;
	private double orrientation;
	private double eccentricity;
	private double holes;
	private double paws; 
	private double StartEnd;
	static BufferedImage image;
	int numberOfStartAndEndPoints;
	///
	public features(BufferedImage image, String path) throws IOException{
		
		//image = imageCrop(image);
		this.image=image;
		numberOfStartAndEndPoints = 0;
		double[] a=HolesPawsStartEnd(image,path);
		a[0] = E2ralyMobileApp.holesNumber;
		image = thinnedImage(image,path);
		a[2] = numberOfStartAndEndPoints;
		//Crop the image
		image = imageCrop(image);
		ImageIO.write(image, "jpg", new File(path+File.separator+"shehab_image.png"));
		//ImageIO.write(image, "png", new File(OutputFolder + "\\" + output));
		this.image=image;
		orrientation=Oreintation(image);
		double[] e=Eccentricity(image);
		eccentricity=e[0];
		extent=Extent(image);
		holes=a[0];
		paws=a[1];
		StartEnd=a[2];
	}
	
	public double getEccentricity() {
		return eccentricity;
	}
	
	public double getExtent() {
		return extent;
	}
	
	public double getOrientation() {
		return orrientation;
	}
	
	public double getStartEnd() {
		return StartEnd;
	}
	
	public double getHoles() {
		return holes;
	}
	
	public double getPaws() {
		return paws;
	}
	
	public double[] HolesPawsStartEnd(BufferedImage realImage, String Path) throws IOException {
		
    	//int holesNumber = 0;
    	int numberOfPaws = 0;
    	//int numberOfStartAndEndPoints = 0;
    	CCL ccl = new CCL();
    	int bgColor = 0xFFFFFFFF; // white default background color
    	//realImage = image;
    	Map<Integer, BufferedImage> components = ccl.Process(realImage, bgColor);
    	for(Integer c : components.keySet()) {
    		BufferedImage component = components.get(c);
    		
    		/*int[][] binaryImage = new int[component.getHeight()][component.getWidth()];
    		for (int x = 0; x < binaryImage.length; x++) {
    			for (int y = 0; y < binaryImage[0].length; y++) {
    				if (component.getRGB(y, x) >= Color.GRAY.getRGB())
    					binaryImage[x][y] = 0;
    				else
    					binaryImage[x][y] = 1;
    			}
    		}
    		ThinningService thinningService = new ThinningService();
	        thinningService.doZhangSuenThinning(binaryImage);
	        */
			if(component.getWidth() > 30 || component.getHeight() > 30){
				numberOfPaws++;
			}
			System.out.print("\nC Number is: "+ c);
        	//holesNumber = holesNumber + new GetNumberOfHoles().start(component);
        	//binaryImage = new GapsFilter().fillTheGapsOf(binaryImage,component.getHeight(),component.getWidth());
        	//numberOfStartAndEndPoints = numberOfStartAndEndPoints + new StartEndPoints().getNumber(binaryImage,component.getHeight(),component.getWidth());
        }
    	double[] a=new double[3];
    	a[0]=0;
    	a[1]=numberOfPaws;
    	a[2] = 0;
    	return a;
	}
	
	public double[] zone( int [][] image){
		double [] vresult= new double [4];//index 0 = v, index 1 = lf, index 2 = h, index 3 = rd
		double[] vlength= new double [4];//index 0 = v, index 1 = lf, index 2 = h, index 3 = rd
		double [] rv = new double [9];
		Arrays.fill(vresult, 0);
		Arrays.fill(vlength, 0);
		int[][] mat = new int [5][5];
		for(int i=0; i<image.length-4; i++) {
			for(int j=0; j<(image[0].length)-4; j++) {
				for(int ii=0;ii<5;ii++) {
					for(int jj=0;jj<5;jj++) {
						mat[ii][jj]=image[i+ii][j+jj];
					}
				}
				double[] r=feature(mat);
				vresult[(int) r[0]]++;	// array to get the number of lines and the old features.
				vlength[(int) r[0]]+=r[1];// array to get total pixels in the line 							
			}
		}
		double[] nl =normalizednumber(vresult);
		double[] nv=normalizedlength(vlength,image.length*image[0].length);
		
		double count=0;
		for ( int i = 0 ; i <image.length ;i++) {
			for ( int j = 0; j <image[0].length; j++) {
				if (image[i][j]==1)
					count++;
			}
		}
		rv[0]=count/(image.length*image[0].length);
		rv[1]=nl[0];
		rv[2]=nl[1];
		rv[3]=nl[2];
		rv[4]=nl[3];
		rv[5]=nv[0];
		rv[6]=nv[1];
		rv[7]=nv[2];
		rv[8]=nv[3];
		
		return rv;	
	}
	
	public double[] feature (int[][] matrix) {
		int [] vresult= new int [4];//index 0 = v, index 1 = lf, index 2 = h, index 3 = rd
		Arrays.fill(vresult, 0);
		for (int i =1 ; i<4; i++) {
			for (int j=1; j<4; j++) {
				if (matrix[i][j]==1)  {
					if( matrix[i-1][j]==1) 
						vresult[0]++;
					if( matrix[i+1][j]==1) 
						vresult[0]++;
					if( matrix[i-1][j-1]==1) 
						vresult[1]++;
					if(matrix[i+1][j+1]==1) 
						vresult[1]++;
					if( matrix[i][j-1]==1) 
						vresult[2]++;
					if( matrix[i][j+1]==1)
					vresult[2]++;
					if( matrix[i-1][j+1]==1) 
						vresult[3]++;
					if( matrix[i+1][j-1]==1)
						vresult[3]++;
				}
			}
		}
		for (int i = 1; i <5 ; i++) {
			if(matrix[0][i]==1 && matrix[0][i-1]==1)
				vresult[2]++;
		}
		for (int i = 1; i <5 ; i++) {
			if(matrix[4][i]==1 && matrix[4][i-1]==1)
				vresult[2]++;
		}
		for (int i = 1; i <5 ; i++) {
			if(matrix[i][0]==1 && matrix[i-1][0]==1)
				vresult[0]++;
		}
		for (int i = 1; i <5 ; i++) {
			if(matrix[i][4]==1 && matrix[i-1][4]==1)
				vresult[0]++;
		}
	
		int maxIndex = 0;
		for (int i = 0; i < vresult.length; i++) {
			if (vresult[i] > vresult[maxIndex]) {
				maxIndex = i;
			}
		}
		double[] r=new double[2];
		r[0]=maxIndex;
		r[1]=vresult[maxIndex];
		return r;
	}
	
	public double[]  normalizednumber(double[] vresult ) {
		double[] value = new double [vresult.length];
		for (int i = 0 ; i < vresult.length; i++ ) {
			value[i]= 1.0 - ((vresult[i] / 10.0 )*2.0);
		}
		return value;
	}
	
	public double[]  normalizedlength(double[] r , double size ) {
		double[] len = new double [r.length];
		for (int i = 0 ; i < r.length; i++ ) {
			len[i]= (r[i] / size );
		}
		return len;
	}

	public double Oreintation(BufferedImage input){
		
		//input = thinnedImage(input);
		//Crop the image
		//input = imageCrop(input);
		ColorProcessor imagec = new ColorProcessor(input);
		BoundingBox bb = new BoundingBox();
        bb.run(imagec);
        double[] boundingBox = bb.getBoundingBox();
        return boundingBox[4];
	}
	
	public  double Extent(BufferedImage input){
	
		//input = thinnedImage(input);
		//Crop the image
		//input = imageCrop(input);
		int area=0;
		int[][] imageData = new int[input.getHeight()][input.getWidth()];
        for (int y = 0; y < imageData.length; y++) {
            for (int x = 0; x < imageData[y].length; x++) {
                if (input.getRGB(x, y) != -1) {
                    area++;
                } 
            }
        }
        
        ColorProcessor imagec = new ColorProcessor(input);
		BoundingBox bb = new BoundingBox();
        bb.run(imagec);
        double[] boundingBox = bb.getBoundingBox();
        return area/(boundingBox[2]*boundingBox[3]);
	}
	
	public  double[] Eccentricity(BufferedImage input){
	
		//Binary thinning algorithm
		//input = thinnedImage(input);
		//Crop the image
		//input = imageCrop(input);	
		ColorProcessor imagec = new ColorProcessor(input);
		Eccentricity descriptor = new Eccentricity();
	    descriptor.run(imagec);	        
	    List<double[]> features = descriptor.getFeatures();
		return features.get(0);
	}
	
	public  BufferedImage imageCrop(BufferedImage input){
		int height = input.getHeight();
		int width = input.getWidth();
		int hend = -1, hstart = Integer.MAX_VALUE, wend = -1, wstart = Integer.MAX_VALUE;
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				if (input.getRGB(j, i) == Color.BLACK.getRGB()) {
					if(hstart > i){
						hstart = i;
					}
					if(wstart > j){
						wstart = j;
					}
					if(hend < i){
						hend = i;
					}
					if(wend < j){
						wend = j;
					}
				} 
			}
		}
		int heightNew = hend - hstart; 
		int widthNew = wend - wstart; 
		BufferedImage biCrop = new BufferedImage( widthNew+1, heightNew+1, input.getType());
		for(int i = hstart; i<=hend; i++){
			for(int j=wstart; j<=wend; j++){
				biCrop.setRGB(j-wstart, i-hstart, input.getRGB(j, i));
			}
		}
		return biCrop;
	}
	
	public  BufferedImage thinnedImage(BufferedImage input,String path){
		int[][] imageData = new int[input.getHeight()][input.getWidth()];
		Noise removeNoise = new Noise();
        //Color c;
        for (int y = 0; y < imageData.length; y++) {
            for (int x = 0; x < imageData[y].length; x++) {
                if (input.getRGB(x, y) == Color.BLACK.getRGB()) {
                    imageData[y][x] = 1;
                } else {
                    imageData[y][x] = 0;
                }
            }
        }
//        User Zhang Suen Thinning Algorithm
        imageData = new GapsFilter().fillTheGapsOf(imageData,input.getHeight(),input.getWidth());
        for (int y = 0; y < imageData.length; y++) { 
            for (int x = 0; x < imageData[0].length; x++) {
                if (imageData[y][x] == 1) {
                    input.setRGB(x, y, Color.BLACK.getRGB());
                } else {
                    input.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
        //******************************************************************************
        try{
        	ImageIO.write(input, "png", new File(path+File.separator+"shehab.png"));
        }catch(IOException e){
        	
        }
        //******************************************************************************
        ThinningService thinningService = new ThinningService();
        imageData = removeNoise.medianFilter(imageData);
        thinningService.doZhangSuenThinning(imageData);
        imageData = removeNoise.start(imageData);
        imageData = new GapsFilter().fillTheGapsOf(imageData,input.getHeight(),input.getWidth());
        numberOfStartAndEndPoints = new StartEndPoints().getNumber(imageData,input.getHeight(),input.getWidth());
        for (int y = 0; y < imageData.length; y++) { 
            for (int x = 0; x < imageData[0].length; x++) {
                if (imageData[y][x] == 1) {
                    input.setRGB(x, y, Color.BLACK.getRGB());
 
                } else {
                    input.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
        try{
        	ImageIO.write(input, "png", new File(path+File.separator+"shehab_2.png"));
        }catch(IOException e){
        	
        }
        return input;
	}

	public String getFeature() {
		// TODO Auto-generated method stub

		int ind=1;
		
		String x="1 ";
		//Binary thinning algorithm
		//image = thinnedImage(image);
		//Crop the image
		//image = imageCrop(image);		
		
		
		int [][] mat = new int [image.getHeight()/3][image.getWidth()];
		for ( int i = 0 ; i <mat.length ;i++) {
			for ( int j = 0; j <mat[0].length; j++) {
				if (image.getRGB(j,i) == Color.BLACK.getRGB())
					mat[i][j] = 1;
				else 
					mat[i][j] = 0;
			}
		}				
		double[] feature=zone(mat);
		
		for (int i = 0 ; i < feature.length ; i++){
			x+=ind +":"+feature[i]+" ";
			ind++;
		}
		
		int [][] mat2 = new int [image.getHeight()/3][image.getWidth()];
		
		Arrays.fill(mat2[1], 0);
		Arrays.fill(mat2[0], 0);
		for ( int i = 0 ; i <mat2.length ;i++) {
				for ( int j =0 ; j <mat2[0].length; j++) {
				if (image.getRGB(j,i+image.getHeight()/3) == Color.BLACK.getRGB()) {
		            mat2[i][j] = 1;
		        } else {
		            mat2[i][j] = 0;
		        } 
			}
		}
		double[] feature2=zone(mat2);
		
	
		for (int i = 0 ; i < feature2.length ; i++){
			x+=ind +":"+feature2[i]+" ";
			ind++;
		}
		
		int [][] mat3 = new int [image.getHeight()/3][image.getWidth()];
		Arrays.fill(mat3[1], 0);
		Arrays.fill(mat3[0], 0);
		for ( int i = 0 ; i <mat3.length ;i++) {
			for ( int j = 0; j <mat3[0].length; j++) {
				if (image.getRGB(j,i+image.getHeight()*2/3) == Color.BLACK.getRGB()) {
		            mat3[i][j] = 1;
		        } else {
		            mat3[i][j] = 0;
		        } 
			}
		}
		double[] feature3=zone(mat3);
		

		for (int i = 0 ; i < feature3.length ; i++){
			x+=ind +":"+feature3[i]+" ";
			ind++;
		}
		
		int [][] mat1v = new int [image.getHeight()][image.getWidth()/3];
		Arrays.fill(mat1v[1], 0);
		Arrays.fill(mat1v[0], 0);
		for ( int i = 0 ; i <mat1v.length ;i++) {
			for ( int j = 0; j <mat1v[0].length; j++) {
				if (image.getRGB(j,i) == Color.BLACK.getRGB()) {
		            mat1v[i][j] = 1;
		        } 
				else {
		            mat1v[i][j] = 0;
		        } 
			}
		}
		double[] feature1v=zone(mat1v);
		
	
		for (int i = 0 ; i < feature1v.length ; i++){
			x+=ind +":"+feature1v[i]+" ";
			ind++;
		}
		
		int [][] mat2v = new int [image.getHeight()][image.getWidth()/3];
		Arrays.fill(mat2v[1], 0);
		Arrays.fill(mat2v[0], 0);
		for ( int i = 0 ; i <mat2v.length ;i++) {
			for ( int j = 0; j <mat2v[0].length; j++) {
				if (image.getRGB(j+image.getWidth()/3,i) == Color.BLACK.getRGB()) {
		            mat2v[i][j] = 1;
		        } else {
		            mat2v[i][j] = 0;
		        } 
			}
		}
		double[] feature2v=zone(mat2v);
		
	
		for (int i = 0 ; i < feature2v.length ; i++){
			x+=ind +":"+feature2v[i]+" ";
			ind++;
		}
		
		int [][] mat3v = new int [image.getHeight()][image.getWidth()/3];
		Arrays.fill(mat3v[1], 0);
		Arrays.fill(mat3v[0], 0);
		for ( int i = 0; i <mat3v.length ;i++) {
			for ( int j = 0; j <mat3v[0].length; j++) {
				if (image.getRGB(j+image.getWidth()*2/3,i) == Color.BLACK.getRGB()) {
		            mat3v[i][j] = 1;
		        } else {
		            mat3v[i][j] = 0;
		        } 
			}
		}
		double[] feature3v=zone(mat3v);
		
	
		for (int i = 0 ; i < feature3v.length ; i++){
			x+=ind +":"+feature3v[i]+" ";
			ind++;
		}

		x+="55:"+eccentricity+" 56:" + orrientation+" 57:"+extent;
		return x;	
	}
}

