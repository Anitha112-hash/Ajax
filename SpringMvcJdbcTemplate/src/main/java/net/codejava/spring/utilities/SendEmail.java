package net.codejava.spring.utilities;

import java.security.spec.KeySpec;

import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.mail.*;    
import javax.mail.internet.*;

import org.apache.commons.codec.binary.Base64;   

public class SendEmail {
	private static final String UNICODE_FORMAT = "UTF8";

    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

    private KeySpec ks;

    private SecretKeyFactory skf;

    private Cipher cipher;

    byte[] arrayBytes;

    private String myEncryptionKey;

    private String myEncryptionScheme;

    SecretKey key;
	public  SendEmail() throws Exception {
        myEncryptionKey = "ThisIsSpartaThisIsSparta";
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        ks = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }
	public static void sendEmail(String toemailadd,long contactid) {
		//Get properties object    
        Properties props = new Properties();    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class",    
                  "javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "465");    
        //get Session   
        Session session = Session.getDefaultInstance(props,    
         new javax.mail.Authenticator() {    
         protected PasswordAuthentication getPasswordAuthentication() {    
         return new PasswordAuthentication("omnitechnical.sample@gmail.com","omnitech123");  
         }    
        });    
        //compose message    
        try {    
         MimeMessage message = new MimeMessage(session);    
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(toemailadd));    
         message.setSubject("test");    
         //message.setText("test email register"); 
         message.setText("http://localhost:8080/SpringMvcJdbcTemplate/changePassword?contactid="+contactid); 
         //send message  
         Transport.send(message);    
         System.out.println("message sent successfully");    
        } catch (MessagingException e) {throw new RuntimeException(e);}    
           
  }
	public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }
	public String decrypt(String encryptedString) {
	    String decryptedText = null;
	    try {
	        cipher.init(Cipher.DECRYPT_MODE, key);
	        byte[] encryptedText = Base64.decodeBase64(encryptedString);
	        byte[] plainText = cipher.doFinal(encryptedText);
	        decryptedText = new String(plainText);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return decryptedText;
	}

	}


