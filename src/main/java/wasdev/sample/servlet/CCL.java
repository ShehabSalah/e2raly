package wasdev.sample.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
//import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import javax.imageio.ImageIO;

public class CCL 
{
    private int[][] _board;
    private BufferedImage _input;
    private Graphics inputGD;
    private int _width;
    private int _height;
    private int backgroundColor;
   // private BufferedImage realImage;
    public Map<Integer, BufferedImage> Process(BufferedImage input, int bgColor){
    	backgroundColor = bgColor;
        _input = input;
        _width = input.getWidth();
        _height = input.getHeight();
        _board = new int[_width][];
        for(int i = 0;i < _width;i++)
        	_board[i] = new int[_height];
        Map<Integer, List<Pixel>> patterns = Find();
        Map<Integer, BufferedImage> images = new HashMap<Integer, BufferedImage>();
        inputGD = _input.getGraphics();
        inputGD.setColor(Color.BLUE);
        for(Integer id : patterns.keySet()){
            BufferedImage bmp = CreateBitmap(patterns.get(id));
            images.put(id, bmp);
        }
        inputGD.dispose();
        return images;
    }
    protected boolean CheckIsBackGround(Pixel currentPixel){
    	// check if pixel color is backgroundColor (white).
        //return currentPixel.color.getAlpha() == 255 && currentPixel.color.getRed() == 255 && currentPixel.color.getGreen() == 255 && currentPixel.color.getBlue() == 255;
    	return currentPixel.color == backgroundColor;
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
    private Map<Integer, List<Pixel>> Find(){
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
    private List<Integer> GetNeighboringLabels(Pixel pix){
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
    private Map<Integer, List<Pixel>> AggregatePatterns(Map<Integer, Label> allLabels){
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
    private BufferedImage CreateBitmap(List<Pixel> pattern){
        int minX = Min(pattern, true);
        int maxX = Max(pattern, true);
        int minY = Min(pattern, false);
        int maxY = Max(pattern, false);
        if(minX - 3 < 0) minX = 0; else minX = minX - 3;
        if(minY - 3 < 0) minY = 0; else minY = minY - 3;
        if(maxX + 3 > features.image.getWidth()) maxX = features.image.getWidth(); else maxX = maxX + 3;
        if(maxY + 3 > features.image.getHeight()) maxY = features.image.getHeight(); else maxY = maxY + 3;
        int width = maxX + 1 - minX;
        int height = maxY + 1 - minY;
        BufferedImage bmp = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (Pixel pix : pattern){
            bmp.setRGB(pix.x - minX, pix.y - minY,Color.WHITE.getRGB()); //shift position by minX and minY
        }
        //inputGD.drawRect(minX, minY, maxX-minX, maxY-minY);
        return bmp;
    }
    public static String getBaseFileName(String fileName) {
    	return fileName.substring(0, fileName.indexOf('.'));
    }
    public static String getFileNameExtension(String fileName) {
    	return fileName.substring(fileName.indexOf('.') + 1);
    }
    public BufferedImage getProcessedImage() {
    	return _input;
    }
}