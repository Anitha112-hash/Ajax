package net.codejava.spring.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import net.codejava.spring.model.Contact;
import net.codejava.spring.utilities.SendEmail;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.util.*;    


public class ContactDAOImpl implements ContactDAO {

	private JdbcTemplate jdbcTemplate;
	
	public ContactDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public long saveOrUpdate(Contact contact) throws Exception {
		long seq=0;
		String password=new SendEmail().encrypt(contact.getPassword());
		if (contact.getContact_id() > 0) {
			// update
			String sql = "UPDATE contact SET fname=?,lname=?, email=?, password=?, "
						+ " WHERE contact_id=?";
			jdbcTemplate.update(sql, contact.getFname(), contact.getLname(), contact.getEmail(),
					password, contact.getContact_id());						
			
		} else {
			String sql = "select contact_id.NEXTVAL from dual";
			 seq = jdbcTemplate.queryForObject(sql, new Object[] {}, Long.class);
			 sql = "INSERT INTO AM_Register (contact_id,fname,lname, email,password,accountstatus,numberofattempts,date_created)"
						+ " VALUES (?,?, ?, ?, ?,?,?, CURRENT_TIMESTAMP)";
			jdbcTemplate.update(sql,seq,contact.getFname(), contact.getLname(), contact.getEmail(),
					password,"N",3);
			
		}
		return seq;
	}

	@Override
	public Contact updatePassword(int contactId,String password) throws Exception {
		password=new SendEmail().encrypt( password);
		String sql = "UPDATE AM_register SET password=? ,date_modified=CURRENT_TIMESTAMP" 
				+ " WHERE contact_id=?";
	jdbcTemplate.update(sql,password,contactId);
		return null;
	}
	
	
	@Override
	public String verifyLogin(String email, final String password ) throws Exception {
		Contact c1 = getbyemailId(email);
		if("Y".equals(c1.getAccountstatus())) 
		{
			return "accountlocked";
		}else if(c1.getDateModified()==null) {
			return "datemodified";
		}
		else {
		String pwd = new SendEmail().encrypt(password);
		String sql = "SELECT password FROM AM_REGISTER WHERE email='"+ email+"' and password='"+ pwd+"'";
		
		System.out.println(sql);
	return jdbcTemplate.query(sql, new ResultSetExtractor<String>() {
		
		@Override
		public String extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			if (rs.next()) {
				String dbpass = rs.getString("password");
				System.out.println("inside if" + dbpass);
				String decrptpassword = null;
				
				try {
					decrptpassword = new SendEmail().decrypt(dbpass);
					System.out.println("decrypted password" + decrptpassword);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("successflly fetched database" + dbpass);
			//	if(password.equals(dbpass)) {
				if(password.equals(decrptpassword)) {
					System.out.println("succesfully verified password");
					return "success";
				}
			}
			System.out.println("failed password");
			return "Fail";
		}
		
	});
} 
		

	}
	

	
	@Override
	public Contact get(int contactId) {
		String sql = "SELECT CONTACT_ID AS \"contact_id\",FNAME AS \"fname\","
				+ " FROM AM_Register WHERE contact_id=" + contactId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Contact>() {

			@Override
			public Contact extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Contact contact = new Contact();
					contact.setContact_id(rs.getInt("contact_id"));
					contact.setFname(rs.getString("fname"));
					contact.setLname(rs.getString("lname"));
					contact.setEmail(rs.getString("email"));
					contact.setPassword(rs.getString("password"));
					//contact.setDateCreated(rs.getTimestamp("Date_created"));
					//contact.setDateModified(rs.getTimestamp("Date_modified"));
					return contact;
				}
				
				return null;
			}
			
		});
	}
	@Override
	public Contact getbyemailId(String emailId) {
		String sql = "SELECT contact_id,email,accountstatus,date_modified,numberofattempts"
				+ " FROM AM_Register WHERE email='" + emailId+"'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Contact>() {

			@Override
			public Contact extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Contact contact = new Contact();
					contact.setContact_id(rs.getInt("contact_id"));
					contact.setEmail(rs.getString("email"));
					contact.setAccountstatus(rs.getString("accountstatus"));
					contact.setDateModified(rs.getString("date_modified"));
					contact.setNumberOfAttempts(rs.getInt("numberofattempts"));
					return contact;
				}
				
				return null;
			}
			
		});

	}
	
	
	@Override
	public List<Contact> list() {
		System.out.println("inside list");
	    String sql = "SELECT * FROM AM_REGISTER";
	    
	    List<Contact> listContact = jdbcTemplate.query(sql, new RowMapper<Contact>() {
	    	
	        @Override
	        public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Contact aContact = new Contact();
	           // System.out.println("inside table list");
//	            System.out.println(sql);
	            aContact.setContact_id(rs.getInt("contact_id"));
	            aContact.setFname(rs.getString("fname"));
	            aContact.setLname(rs.getString("lname"));
	            aContact.setEmail(rs.getString("email"));
//	            System.out.println(aContact);
//	            System.out.println(rs.getInt("contact_id"));
//	            System.out.println(rs.getString("fname"));
//	            System.out.println(rs.getString("lname"));
	            return aContact;
	        }
	 
	    });
	    //System.out.println(listContact);
	    return listContact;
	}
	
	
//	@Override
//	public List<Contact> list() {
//	    String sql = "SELECT * FROM AM_REGISTER";
//	     
//	    return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Contact.class));  
//	}
	
	@Override
	public void lockout(String emailid) {
		System.out.println("entered lockout");
		
		String sql = "UPDATE AM_register SET accountstatus='Y' " 
				+ " WHERE email='"+emailid+"'" ;
		System.out.println(sql);
		System.out.println(emailid);
	jdbcTemplate.update(sql);
	System.out.println("completed lockout");
		
	}
	@Override
	public int updatenumberOfAttempts(String email,int numberofattempts) {
		
		String sql = "UPDATE AM_register SET numberofattempts=?"
				+ " WHERE email=?";
	jdbcTemplate.update(sql,numberofattempts,email);
		return numberofattempts;
	}
}



