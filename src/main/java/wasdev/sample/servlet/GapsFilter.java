package wasdev.sample.servlet;

public class GapsFilter {
	 public int[][] fillTheGapsOf(int[][] BinaryImage, int width, int height){
	        for(int x = 3; x < width-3; x++){
	            for(int y = 3; y < height-3;y++){
	                if( BinaryImage[x][y] == 0 ){ 
	                    if ((BinaryImage[x-2][y] == 1 && BinaryImage[x+2][y] == 1 && BinaryImage[x-1][y] == 1 && BinaryImage[x+1][y] == 1
	                            && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                            && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                            && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                            && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                            && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0) ||
	                            (BinaryImage[x-2][y] == 1 && BinaryImage[x+2][y] == 1 && BinaryImage[x-1][y] == 1 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0) ||
	                            (BinaryImage[x-2][y] == 1 && BinaryImage[x+2][y] == 1 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 1
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0) ||
	                            (BinaryImage[x-2][y] == 1 && BinaryImage[x+2][y] == 1 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0) ||
	                            (BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 1 && BinaryImage[x+1][y] == 1
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0)
	                            ){
	                        BinaryImage[x][y] = 1;
	                    }else if((BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                            && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 1 && BinaryImage[x+1][y-2] == 0
	                            && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 1
	                            && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                            && BinaryImage[x][y+1] == 1 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                            && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 1 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0) ||
	                            (BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 1 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 1
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 1 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0) ||
	                            (BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 1 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 1 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 1 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 01) ||
	                            (BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 1 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 1 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0)
	                            ){
	                        BinaryImage[x][y]=1;
	                    }else if((BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                            && BinaryImage[x-2][y-2] == 1 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                            && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 1 && BinaryImage[x][y-1] == 0
	                            && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                            && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 1 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                            && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 1) ||
	                            (BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 1 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 1 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 1) ||
	                            (BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 1 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 1 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 1) ||
	                            (BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 1 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 1)
	                            ){
	                        BinaryImage[x][y]=1;
	                    }else if((BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                            && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                            && BinaryImage[x+2][y-2] == 1 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                            && BinaryImage[x+1][y-1] == 1 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 1
	                            && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 1
	                            && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0) ||
	                            (BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 1 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 1 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 1
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0) ||
	                            (BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 1 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 1
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 1
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0) ||
	                            (BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 1 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 1
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0)
	                            ){
	                        BinaryImage[x][y]=1;
	                    }else if((BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 1 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 1
	                            && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                            && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                            && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 1
	                            && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 1
	                            && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0) ||
	                            (BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 1 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 1
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 1
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0) ||
	                            (BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 1 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 1
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 1
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0) ||
	                            (BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 1 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 1
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0) ||
	                            (BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 1 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 1
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0)
	                            ){
	                        BinaryImage[x][y]=1;
	                    }else if((BinaryImage[x-2][y] == 1 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 1 && BinaryImage[x+1][y] == 0
	                            && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                            && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                            && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                            && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 1 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                            && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 1) ||
	                            (BinaryImage[x-2][y] == 1 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 1 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 1) ||
	                            (BinaryImage[x-2][y] == 1 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 1 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 1) ||
	                            (BinaryImage[x-2][y] == 1 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 1)
	                            ){
	                        BinaryImage[x][y]=1;
	                    }else if((BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 1 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 1
	                            && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                            && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                            && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 1 && BinaryImage[x-1][y+1] == 0
	                            && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                            && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0) ||
	                            (BinaryImage[x-2][y] == 0 && BinaryImage[x+2][y] == 1 && BinaryImage[x-1][y] == 0 && BinaryImage[x+1][y] == 1
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 1 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0)
	                            ){
	                        BinaryImage[x][y]=1;
	                    }else if((BinaryImage[x-2][y] == 1 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 1 && BinaryImage[x+1][y] == 0
	                            && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                            && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                            && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 0 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                            && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 1 && BinaryImage[x-2][y+2] == 0
	                            && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0) ||
	                            (BinaryImage[x-2][y] == 1 && BinaryImage[x+2][y] == 0 && BinaryImage[x-1][y] == 1 && BinaryImage[x+1][y] == 0
	                                    && BinaryImage[x-2][y-2] == 0 && BinaryImage[x-1][y-2] == 0 && BinaryImage[x][y-2] == 0 && BinaryImage[x+1][y-2] == 0
	                                    && BinaryImage[x+2][y-2] == 0 && BinaryImage[x-2][y-1] == 0 && BinaryImage[x-1][y-1] == 0 && BinaryImage[x][y-1] == 0
	                                    && BinaryImage[x+1][y-1] == 0 && BinaryImage[x+2][y-1] == 1 && BinaryImage[x-2][y+1] == 0 && BinaryImage[x-1][y+1] == 0
	                                    && BinaryImage[x][y+1] == 0 && BinaryImage[x+1][y+1] == 0 && BinaryImage[x+2][y+1] == 0 && BinaryImage[x-2][y+2] == 0
	                                    && BinaryImage[x-1][y+2] == 0 && BinaryImage[x][y+2] == 0 && BinaryImage[x+1][y+2] == 0 && BinaryImage[x+2][y+2] == 0)
	                            ){
	                        BinaryImage[x][y]=1;
	                    }		    		  }
	            }

	        }
	        return BinaryImage;
	    }
}
