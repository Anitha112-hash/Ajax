package net.codejava.spring.dao;
import java.util.ArrayList;
import java.util.List;
import net.codejava.spring.model.Contact;

public interface ContactDAO {
	
	public long saveOrUpdate(Contact contact)throws Exception;
	
	
	public Contact get(int contactId);
	public Contact updatePassword(int contactId,String password)throws Exception;
	public String verifyLogin(String email, String password ) throws Exception ;
	//public void updatePassword(String password);
	public Contact getbyemailId(String emailId);
	public void lockout(String emailid);
	public int updatenumberOfAttempts(String email,int numberofattempts);
    public List<Contact> list();
	
}
