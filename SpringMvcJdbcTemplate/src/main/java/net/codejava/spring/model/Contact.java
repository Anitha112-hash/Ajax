package net.codejava.spring.model;

public class Contact {
	private int contact_id;
	private String fname;
	private String lname;
	private String email;
	private String password;
	private String retpass;
	private String captcha;
	private String accountstatus;
	private String dateModified;
	private int numberOfAttempts;
    
	public Contact(int contact_id, String fname, String lname, String email, String password, String retpass, String accountstatus ) {
		this.contact_id = contact_id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.retpass = retpass;
		this.accountstatus = accountstatus ;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRetpass() {
		return retpass;
	}

	public void setRetpass(String retpass) {
		this.retpass = retpass;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public int getContact_id() {
		return contact_id;
	}

	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}


	public String getAccountstatus() {
		return accountstatus;
	}

	public void setAccountstatus(String accountstatus) {
		this.accountstatus = accountstatus;
	}

	public Contact() {
	}

	public String getDateModified() {
		return dateModified;
	}

	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	public int getNumberOfAttempts() {
		return numberOfAttempts;
	}

	public void setNumberOfAttempts(int numberOfAttempts) {
		this.numberOfAttempts = numberOfAttempts;
	}


}
