package wasdev.sample.servlet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class Segmentation {

	static BufferedImage im;
	static private int[][] _board;
	  static  private BufferedImage _input;
	  static private Graphics inputGD;
	  static private int _width;
	  static private int _height;
	  static private int backgroundColor;
	 public static Map<Integer, int[]> Process(BufferedImage input, int bgColor){
	    	backgroundColor = bgColor;
	        _input = input;
	        _width = input.getWidth();
	        _height = input.getHeight();
	        _board = new int[_width][];
	        for(int i = 0;i < _width;i++)
	        	_board[i] = new int[_height];
	        Map<Integer, List<Pixel>> patterns = Find();
	        Map<Integer, int[]> images = new HashMap<Integer, int[]>();
	        inputGD = _input.getGraphics();
	        inputGD.setColor(Color.BLUE);
	        for(Integer id : patterns.keySet()){
	            int[] bmp = CreateBitmap(patterns.get(id));
	            images.put(id, bmp);
	        }
	        inputGD.dispose();
	        return images;
	    }
	 private static int[] CreateBitmap(List<Pixel> pattern){
	        int minX = Min(pattern, true);
	        int maxX = Max(pattern, true);
	        int minY = Min(pattern, false);
	        int maxY = Max(pattern, false);
	        if(minX - 3 < 0) minX = 0; else minX = minX - 3;
	        if(minY - 3 < 0) minY = 0; else minY = minY - 3;
	        if(maxX + 3 > im.getWidth()) maxX = im.getWidth(); else maxX = maxX + 3;
	        if(maxY + 3 > im.getHeight()) maxY = im.getHeight(); else maxY = maxY + 3;
	        int width = maxX + 1 - minX;
	        int height = maxY + 1 - minY;
	        BufferedImage bmp = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
	        for (Pixel pix : pattern){
	            bmp.setRGB(pix.x - minX, pix.y - minY,Color.WHITE.getRGB()); //shift position by minX and minY
	        }
	        //inputGD.drawRect(minX, minY, maxX-minX, maxY-minY);
	        int[] xy=new int[4];
	        xy[0]=minX;
	        xy[1]=minY;
	        xy[2]=maxX;
	        xy[3]=maxY;
	        return xy;
	    }
	 private static int Min(List<Integer> neighboringLabels, Map<Integer, Label> allLabels) {
	    	if(neighboringLabels.isEmpty())
	    		return 0; // TODO: is 0 appropriate for empty list	
	    	int ret = allLabels.get(neighboringLabels.get(0)).GetRoot().name;
	    	for(Integer n : neighboringLabels) {
	    		int curVal = allLabels.get(n).GetRoot().name;
	    		ret = (ret < curVal ? ret : curVal);
	    	}
	    	return ret;
	    }
	    private static int Min(List<Pixel> pattern, boolean xOrY) {
	    	if(pattern.isEmpty())
	    		return 0; // TODO: is 0 appropriate for empty list
	    	
	    	int ret = (xOrY ? pattern.get(0).x : pattern.get(0).y);
	    	for(Pixel p : pattern) {
	    		int curVal = (xOrY ? p.x : p.y);
	    		ret = (ret < curVal ? ret : curVal);
	    	}
	    	return ret;
	    }
	    private static int Max(List<Pixel> pattern, boolean xOrY) {
	    	if(pattern.isEmpty())
	    		return 0; // TODO: is 0 appropriate for empty list
	    	
	    	int ret = (xOrY ? pattern.get(0).x : pattern.get(0).y);
	    	for(Pixel p : pattern) {
	    		int curVal = (xOrY ? p.x : p.y);
	    		ret = (ret > curVal ? ret : curVal);
	    	}
	    	return ret;
	    }
	    private static Map<Integer, List<Pixel>> Find(){
	        int labelCount = 1;
	        Map<Integer, Label> allLabels = new HashMap<Integer, Label>();
	        for (int i = 0; i < _height; i++){
	            for (int j = 0; j < _width; j++){
	                Pixel currentPixel = new Pixel(j, i, _input.getRGB(j, i)== Color.WHITE.getRGB()?Color.WHITE.getRGB():Color.BLACK.getRGB());
	                if (CheckIsBackGround(currentPixel)){
	                    continue;
	                }
	                List<Integer> neighboringLabels = GetNeighboringLabels(currentPixel);
	                int currentLabel;

	                if (neighboringLabels.isEmpty()){
	                    currentLabel = labelCount;
	                    allLabels.put(currentLabel, new Label(currentLabel));
	                    labelCount++;
	                }
	                else{
	                    currentLabel = Min(neighboringLabels, allLabels);
	                    Label root = allLabels.get(currentLabel).GetRoot();
	                    for (Integer neighbor : neighboringLabels){
	                        if (root.getName() != allLabels.get(neighbor).GetRoot().name){
	                            allLabels.get(neighbor).Join(allLabels.get(currentLabel));
	                        }
	                    }
	                }
	                _board[j][i] = currentLabel;
	            }
	        }
	        Map<Integer, List<Pixel>> patterns = AggregatePatterns(allLabels);
	        return patterns;
	    }
	    private static List<Integer> GetNeighboringLabels(Pixel pix){
	        List<Integer> neighboringLabels = new ArrayList<Integer>();
	        for (int i = pix.y - 1; i <= pix.y + 2 && i < _height - 1; i++){
	            for (int j = pix.x - 1; j <= pix.x + 2 && j < _width - 1; j++){
	                if (i > -1 && j > -1 && _board[j][i] != 0){
	                    neighboringLabels.add(_board[j][i]);
	                }
	            }
	        }
	        return neighboringLabels;
	    }
	    private static Map<Integer, List<Pixel>> AggregatePatterns(Map<Integer, Label> allLabels){
	        Map<Integer, List<Pixel>> patterns = new HashMap<Integer, List<Pixel>>();
	        for (int i = 0; i < _height; i++){
	            for (int j = 0; j < _width; j++){
	                int patternNumber = _board[j][i];
	                if (patternNumber != 0){
	                    patternNumber = allLabels.get(patternNumber).GetRoot().name;
	                    if (!patterns.containsKey(patternNumber)){
	                        patterns.put(patternNumber, new ArrayList<Pixel>());
	                    }
	                    patterns.get(patternNumber).add(new Pixel(j, i, _input.getRGB(j, i)));
	                }
	            }
	        }
	        return patterns;
	    }
	    protected static boolean CheckIsBackGround(Pixel currentPixel){
	    	// check if pixel color is backgroundColor (white).
	        //return currentPixel.color.getAlpha() == 255 && currentPixel.color.getRed() == 255 && currentPixel.color.getGreen() == 255 && currentPixel.color.getBlue() == 255;
	    	return currentPixel.color == backgroundColor;
	    }
	    	     
	public static ArrayList<BufferedImage> LineSeg(BufferedImage image){
		
		ArrayList<BufferedImage> images=new ArrayList<BufferedImage>();
		
		int[] count=new int[image.getHeight()];
		Arrays.fill(count, 0);
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				if (image.getRGB(x, y)<=Color.GRAY.getRGB()){
					count[y]++;
				}
			}
		}
		
		int index=0;
		int value=count[0];
		int c=0;
		for(int i=0;i<count.length;i++){
			if(value==0 && count[i]==0){
				index=i;
			}
			else if(value!=0 && count[i]==0){
				if(c>15){
					BufferedImage x=new BufferedImage(image.getWidth(), i-index, BufferedImage.TYPE_BYTE_BINARY);
					for(int a=0;a<image.getWidth();a++){
						for(int b=0;b<i-index;b++){
							x.setRGB(a, b, image.getRGB(a, b+index));
						}
					}
					images.add(x);
				}
				value=0;
				c=0;
				
			}
			else if(count[i]!=0){
				value=count[i];
				c++;
			}
		}		
		return images;
		
	}
	public static ArrayList<BufferedImage> WordSeg(BufferedImage image) throws IOException{
		ArrayList<BufferedImage> images=new ArrayList<BufferedImage>();
	
		ArrayList<int[]> range=new ArrayList<int[]>();
		int bgColor = 0xFFFFFFFF; // white default background color
		
		Map<Integer, int[]> components = Process(image, bgColor);
		 for(Integer c : components.keySet()) {
			 int[] xy = components.get(c);
			 if(xy[2]-xy[0]>20 || xy[3]-xy[1]>20){
				 range.add(xy);
			 }
		 }
		 int[] swap=new int[4];
		 int[][] word=new int[range.size()][4];
		 for(int i=0;i<range.size();i++){
			 int[] a=range.get(i);
			 word[i][0]=a[0];
			 word[i][1]=a[1];
			 word[i][2]=a[2];
			 word[i][3]=a[3];
		 }
		 for (int i = 0; i < ( word.length - 1 ); i++) {
			 for (int j = 0; j < word.length - i - 1; j++) {
				 if (word[j][0] > word[j+1][0]) /* For descending order use < */ {
					 swap[0] = word[j][0];
					 swap[1] = word[j][1];
					 swap[2] = word[j][2];
					 swap[3] = word[j][3];
		            
					 word[j][0]=word[j+1][0];
					 word[j][1]=word[j+1][1];
					 word[j][2]=word[j+1][2];
					 word[j][3]=word[j+1][3];

					 word[j+1][0]=swap[0];
					 word[j+1][1]=swap[1];
					 word[j+1][2]=swap[2];
					 word[j+1][3]=swap[3];
				 }
			 }
		 }
		 int[] w=new int[4];
		 w[0]=word[0][0];
		 w[1]=word[0][1];
		 w[2]=word[0][2];
		 w[3]=word[0][3];
		 for(int i=1;i<word.length;i++){
			 if(word[i][0]-w[2]<40){
				 w[0]=Math.min(w[0],word[i][0]);
				 w[1]=Math.min(w[1],word[i][1]);
				 w[2]=Math.max(w[2],word[i][2]);
				 w[3]=Math.max(w[3],word[i][3]);
			 }
			 else if(word[i][0]-w[2]>40){
				 BufferedImage x=new BufferedImage(w[2]-w[0], w[3]-w[1], image.getType());
				 for(int a=0;a<w[2]-w[0];a++){
					 for(int b=0;b<w[3]-w[1];b++){
						 x.setRGB(a, b, image.getRGB(a+w[0], b+w[1]));
					 }
				 }
				 images.add(x);
				 w[0]=word[i][0];
				 w[1]=word[i][1];
				 w[2]=word[i][2];
				 w[3]=word[i][3];
			 }
		 }
		 BufferedImage x=new BufferedImage(w[2]-w[0], w[3]-w[1], image.getType());
		 for(int a=0;a<w[2]-w[0];a++){
			 for(int b=0;b<w[3]-w[1];b++){				
				 x.setRGB(a, b, image.getRGB(a+w[0], b+w[1]));
			 }
		 }
		 images.add(x);		
		return images;
	}
	public ArrayList<BufferedImage> Start(BufferedImage im) throws IOException {
		// TODO Auto-generated method stub
		
		ArrayList<BufferedImage> images=LineSeg(im);
		ArrayList<BufferedImage> word = new ArrayList<>();
	//	for(int j=0;j<images.size();j++)
		//	ImageIO.write(images.get(j), "bmp", new File(j+".bmp"));
		for(int i=0;i<images.size();i++){
			Segmentation.im=images.get(i);
			ArrayList<BufferedImage> image=WordSeg(images.get(i));
			
			for(int j=image.size() - 1; j >= 0; j--)
				word.add(image.get(j));
				//ImageIO.write(image.get(j), "bmp", new File(i+"-"+j+".bmp"));
		}
		return word;
	}
}
