package wasdev.sample.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UploadSysFile
 */
@WebServlet("/UploadSysFile")
public class UploadSysFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadSysFile() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Get file name 
		String fileName = request.getParameter("fileName");
		//Get file content
		String fileContent = request.getParameter("fileContent");
		//get directory path
		String path = getServletContext().getRealPath("/");
		File f = new File (path+File.separator+"ImagesFolder");
		//if directory exists use the path if not make directory
		if(!f.exists())
			f.mkdir();
		//Making System Folder inside Images Folder
		String system_folder = "SysFolder";
		File sysFolder = new File (f+File.separator+system_folder);
		if(!sysFolder.exists())
			sysFolder.mkdir();
		//create text file
		File outputFile = new File(sysFolder.getAbsolutePath()+File.separator+fileName+".txt");
		FileOutputStream outputStreamFile = new FileOutputStream(outputFile);
		PrintStream output = new PrintStream(outputStreamFile);
		output.print(fileContent);
		outputStreamFile.flush();
		//close file
		outputStreamFile.close();
		//close print stream
		output.close();
	}

}
//delete this comment