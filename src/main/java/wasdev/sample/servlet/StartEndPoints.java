package wasdev.sample.servlet;

public class StartEndPoints {
    public int getNumber(int[][] BinaryImage, int width, int height){
        int count = 0;
        for(int x = 2 ; x < width - 1; x++){
            for(int y = 2; y < height - 1; y++){
                if(BinaryImage[x][y] == 1){
                    int neigborhoodPixels = 0;
                    if(y == 0 && BinaryImage[x][y] == 1){
                        neigborhoodPixels = BinaryImage[x - 1][y]+
                                BinaryImage[x][y]+
                                BinaryImage[x + 1][y]+
                                BinaryImage[x - 1][y + 1]+
                                BinaryImage[x][y + 1]+
                                BinaryImage[x + 1][y + 1];
                    }else if(x == 0 && BinaryImage[x][y] == 1){
                        neigborhoodPixels = BinaryImage[x][y - 1]+
                                BinaryImage[x + 1][y - 1]+
                                BinaryImage[x][y]+
                                BinaryImage[x + 1][y]+
                                BinaryImage[x][y + 1]+
                                BinaryImage[x + 1][y + 1];
                    }else if(y == height - 1 && BinaryImage[x][y] == 1){
                        neigborhoodPixels = BinaryImage[x - 1][y - 1]+
                                BinaryImage[x][y - 1]+
                                BinaryImage[x + 1][y - 1]+
                                BinaryImage[x - 1][y]+
                                BinaryImage[x][y]+
                                BinaryImage[x + 1][y];
                    }else if(x == width - 1 && BinaryImage[x][y] == 1){
                        neigborhoodPixels = BinaryImage[x - 1][y - 1]+
                                BinaryImage[x][y - 1]+
                                BinaryImage[x - 1][y]+
                                BinaryImage[x][y] +
                                BinaryImage[x - 1][y + 1]+
                                BinaryImage[x][y + 1];
                    }else if(BinaryImage[x][y] == 1){
                        neigborhoodPixels = BinaryImage[x - 1][y - 1]+
                                BinaryImage[x][y - 1]+
                                BinaryImage[x + 1][y - 1]+
                                BinaryImage[x - 1][y]+
                                BinaryImage[x][y]+
                                BinaryImage[x + 1][y]+
                                BinaryImage[x - 1][y + 1]+
                                BinaryImage[x][y + 1]+
                                BinaryImage[x + 1][y + 1];
                    }
                    if(neigborhoodPixels == 2){
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
