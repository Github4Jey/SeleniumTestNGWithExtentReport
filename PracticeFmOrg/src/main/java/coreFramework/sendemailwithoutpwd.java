package coreFramework;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.Test;

public class sendemailwithoutpwd {
	@Test
    public void sendemailwithoutpwd() throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", "157.227.4.36");
        props.put("mail.smtp.port", "25");
        props.put("mail.debug", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        
        
        
        message.setFrom(new InternetAddress("ForPerformanceTesting@bpo.net"));
       
        
        String to = "test.c@gmail.com , test.manish@gmail.com, test.c@gmail.com";
        InternetAddress[] parse = InternetAddress.parse(to , true);
        message.setRecipients(javax.mail.Message.RecipientType.TO,  parse);
        
        
        message.setSubject("Performance Testing Execution Result");
     // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        messageBodyPart.setText("Hi Team,"
        		+ "\r\n"
        		+ "Plese find the attached Performance Testing Execution Result."
        		+ "\r\n"
        		+ "\r\n"
        		+ "Thanks,"
        		+ "\r\n"
        		+ "Assurance - Performance Testing Team");
        //
        
        // Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        messageBodyPart = new MimeBodyPart();
        String filename = System.getProperty("user.dir")+"\\Aggregate_Output.zip";
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName("Aggregate_Output.zip");
        multipart.addBodyPart(messageBodyPart);

        // Send the complete message parts
        message.setContent(multipart);
        
        //
        
        Transport.send(message);
    }


}
