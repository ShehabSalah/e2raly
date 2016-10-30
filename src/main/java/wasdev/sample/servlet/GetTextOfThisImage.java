package wasdev.sample.servlet;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetTextOfThisImage
 */
@WebServlet("/GetTextOfThisImage")
public class GetTextOfThisImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static BufferedImage img;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("restriction")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get Image from mobile application as string using base64
		String stringImage = request.getParameter("binaryImage");
		//convert base64 to byte
		byte[] btDataFile = new sun.misc.BASE64Decoder().decodeBuffer(stringImage);
		//get the current date and time
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		//output the byte as image in resource folder with name image+current date and time
		//get directory path
		String path = getServletContext().getRealPath("/");
		//get users folder
		String users_folder = "users";
	    File f = new File (path+File.separator+"ImagesFolder");
	    //if directory exists use the path if not make directory
	    if(!f.exists())
	    	f.mkdir();
	    // Create directory with the user_id if it not exist
	    File userf = new File (path+"/ImagesFolder"+File.separator+users_folder);
	    if(!userf.exists())
	    	userf.mkdir();
	    //create image .png
		File of = new File(userf.getAbsolutePath()+File.separator+"Rimage"+timeStamp+".bmp");
		//open stream between java project and image created 
		FileOutputStream osf = new FileOutputStream(of);
		//write date to the image
		osf.write(btDataFile);
		//close stream
		//ok sir
		osf.flush();
		//close file
		osf.close();
		//Get the image uploaded from image upload folder
		img = ImageIO.read(new File(userf.getAbsolutePath()+File.separator+"Rimage"+timeStamp+".bmp"));
		//Send The image path and current time to processes class and get the result
		String result = new E2ralyMobileApp().StartProcesses(userf.getAbsolutePath()+File.separator+"Rimage"+timeStamp+".bmp",path+"/images" );
	    System.out.print("Result: "+ result);
		// print the results as a JSONObject
		response.setContentType("text/html");
		response.getWriter().println("{'result':[{'result_classes':'"+result+"'}]}");
       
	}
}