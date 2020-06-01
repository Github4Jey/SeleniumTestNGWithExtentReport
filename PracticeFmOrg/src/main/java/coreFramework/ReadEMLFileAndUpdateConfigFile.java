package coreFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class ReadEMLFileAndUpdateConfigFile{


public static String  accessanddisplayemaildetails() throws Exception{
	//Thread.sleep(60000);
       String urltobeupd = display(new File("C:\\Applications\\Test\\Deployment Success - EON.eml"));
  System.out.println(urltobeupd);
return urltobeupd;
   }

   public static String display(File emlFile) throws Exception{
        Properties props = System.getProperties();
        props.put("mail.host", "smtp.dummydomain.com");
        props.put("mail.transport.protocol", "smtp");

        Session mailSession = Session.getDefaultInstance(props, null);
        InputStream source = new FileInputStream(emlFile);
        MimeMessage message = new MimeMessage(mailSession, source);
        String messagesubject = message.getSubject();
        System.out.println("Subject : " + message.getSubject());
        System.out.println("From : " + message.getFrom()[0]);
        System.out.println("--------------");
        System.out.println("Body : " +  message.getContent());
        System.out.println("Text: " + getTextFromMessage(message));
	      String bodymessage = getTextFromMessage(message);
	      String urlwithspace = bodymessage.substring(bodymessage.indexOf("http"),bodymessage.indexOf("Thanks")-1);
          //subject2.substring(beginIndex, endIndex)
          String appurl = urlwithspace.trim();

        
        return appurl;
 
    }
 public static String updateconfigfile() throws Exception {
   
   // File emlFile = null;
String urltobeupdated =  accessanddisplayemaildetails();
  System.out.println( urltobeupdated);
String filename = System.getProperty("user.dir")+"\\src\\main\\java\\coreframework\\configfile.properties";
   FileInputStream in = new FileInputStream(filename);
   Properties props = new Properties();
   props.load(in);
   in.close();

   FileOutputStream out = new FileOutputStream(filename);
   props.setProperty("url", urltobeupdated);
   props.store(out, null);
   out.close();
   Thread.sleep(3000);
return filename;    

    }
 
 private static String getTextFromMessage(Message message) throws MessagingException, IOException {
	    String result = "";
	    if (message.isMimeType("text/plain")) {
	        result = message.getContent().toString();
	    } else if (message.isMimeType("multipart/*")) {
	        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	        result = getTextFromMimeMultipart(mimeMultipart);
	    }
	    return result;
	}

	private static String getTextFromMimeMultipart(
	        MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break; // without break same text appears twice in my tests
	        } else if (bodyPart.isMimeType("text/html")) {
	            String html = (String) bodyPart.getContent();
	            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
	}
 
}

