package wasdev.sample.servlet;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class E2ralyMobileApp {
	static int holesNumber;
	static BufferedImage image;
	public static BufferedImage noise(BufferedImage image) {
		int[][] imagedata = new int[image.getWidth()][image.getHeight()];
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				if (image.getRGB(x, y)>=Color.GRAY.getRGB()){
					imagedata[x][y] = 1;
				}
				else {
					imagedata[x][y] = 0;
				}
			}
		}
		int [][] filter = new int[3][3];
        filter[0][0] = 1;
        filter[0][1] = 1;
        filter[0][2] = 1;
        filter[1][0] = 1;
        filter[1][1] = 1;
        filter[1][2] = 1;
        filter[2][0] = 1;
        filter[2][1] = 1;
        filter[2][2] = 1;
        for(int z = 1 ; z < image.getWidth(); z++){
        	for(int p = 1; p < image.getHeight(); p++){
        		if(imagedata[z][p] == 1){
        			int f = imagedata[z - 1][p - 1] * filter[0][0] + imagedata[z][p - 1] * filter[0][1] +
	                        imagedata[z + 1][p - 1] * filter[0][2] + imagedata[z - 1][p] * filter[1][0] +
	                        imagedata[z][p] * filter[1][1] + imagedata[z + 1][p] * filter[1][2] +
	                        imagedata[z - 1][p + 1] * filter[2][0] + imagedata[z][p + 1] * filter[2][1] +
	                        imagedata[z + 1][p + 1] * filter[2][2];
        			if(f <3 ) {
        				imagedata[z][p]=0;
        				image.setRGB(z, p, Color.BLACK.getRGB());
        			}
        		}
        	}
        }
        for (int x = 0; x < image.getWidth(); x++) {
        	for (int y = 0; y < image.getHeight(); y++) {
        		if (imagedata[x][y] == 1) {
        			image.setRGB(x, y, Color.WHITE.getRGB());
        		} 
        		else {
        			image.setRGB(x, y, Color.BLACK.getRGB());
        		}
        	}
        }
        return image;
	}
	public BufferedImage ConvertToBlackAndWhite(BufferedImage image){
		int[][] imagedata = new int[image.getWidth()][image.getHeight()];
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				if (image.getRGB(x, y)>=Color.GRAY.getRGB()){
					imagedata[x][y] = 1;
				}
				else {
					imagedata[x][y] = 0;
				}
			}
		}
		for (int x = 0; x < image.getWidth(); x++) {
        	for (int y = 0; y < image.getHeight(); y++) {
        		if (imagedata[x][y] == 1) {
        			image.setRGB(x, y, Color.WHITE.getRGB());
        		} 
        		else {
        			image.setRGB(x, y, Color.BLACK.getRGB());
        		}
        	}
        }
		
		return image;
	}
	public String StartProcesses(String ImagePath, String FolderPath) throws IOException {
		// TODO Auto-generated method stub
		Segmentation seg = new Segmentation();
		ArrayList<BufferedImage> words = new ArrayList<>();
		String result = "";
		
		BufferedImage image=ImageIO.read(new File(ImagePath));
		image = ConvertToBlackAndWhite(image);
		words = seg.Start(image);
		for(int i = 0; i < words.size(); i++){
			this.image = words.get(i);
			holesNumber =  new GetNumberOfHoles().start(words.get(i));	
			features feature=new features(words.get(i),FolderPath);
			cluster cluster=new cluster(feature.getExtent(),feature.getOrientation(),feature.getEccentricity(),feature.getPaws(),feature.getHoles(),feature.getStartEnd());
			//System.out.print("features:\ngetPaws: "+feature.getPaws()+"\ngetHoles: "+feature.getHoles()+"\ngetStartEnd: "+feature.getStartEnd());
			NNMain KNN=new NNMain();
			//System.out.println(feature.getExtent()+" "+feature.getOrientation()+" "+feature.getEccentricity()+" "+feature.getPaws()+" "+feature.getHoles()+" "+feature.getStartEnd());
			//System.out.println(cluster.getCluster());
			KNN.load(cluster.getCluster(FolderPath),FolderPath);
			System.out.print("MAMA ZAMNHA GAYA");
			String vote=KNN.recognize(feature.getFeature());
			System.out.print("GAYA B3D SHOWYA");
			//System.out.println(feature.getFeature());
			//System.out.println(vote);
			//create another dir (in imagesFolder dir) to save the system files
			vote=vote.replace("? ", "");
			String[] trainD = {vote,FolderPath+File.separator+"trainingM.model" };
			svm_train.main(trainD,FolderPath);
			String[] testD = {feature.getFeature(),FolderPath+File.separator+"trainingM.model", FolderPath+File.separator+"41-final-12vote-javaNNN-svm1-Kn4-cl_mat.txt" };
			result += svm_predict.main(testD);
		}
		
		return result;
		//return "";
	}
	private void shehab(){}

}
