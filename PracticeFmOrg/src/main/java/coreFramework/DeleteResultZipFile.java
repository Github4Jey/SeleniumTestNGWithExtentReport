package coreFramework;

import java.io.File;

import org.testng.annotations.Test;


public class DeleteResultZipFile{

@Test
	 public static void deleteResultZipFile()
	    {
	        //Delete all files from this directory
	        String targetDirectory = System.getProperty("user.dir");
	        File dir = new File(targetDirectory);
	 
	        //Filter out all zip files
	        String[] zipfiles = dir.list(new LogFilterFilter());
	 
	        //If no zip file found; no need to go further
	        if (zipfiles.length == 0)
	        
	            return;
	 
	        //This code will delete all zip files one by one
	        for (String log : zipfiles)
	        {
	            String tempLogFile = new StringBuffer(targetDirectory).append(File.separator).append(log).toString();
	            File fileDelete = new File(tempLogFile);
	            boolean isdeleted = fileDelete.delete();
	            System.out.println("file : " + tempLogFile + " is deleted : " + isdeleted);
	        }
	    }
 
}

