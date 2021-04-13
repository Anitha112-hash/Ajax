package net.codejava.spring.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.codejava.spring.dao.ContactDAO;
import net.codejava.spring.model.Contact;
import net.codejava.spring.model.Contactdup;
//import net.codejava.spring.model.ValidPassword;
import net.codejava.spring.utilities.SendEmail;
import com.fasterxml.jackson.annotation.JsonView;
import net.codejava.spring.model.Views;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.JsonObject;

import ajaxrespose.AjaxResponseBody;



@Controller
@EnableWebMvc
public class HomeController {

	@Autowired
	private ContactDAO contactDAO;
	
	@RequestMapping(value = "/")
	public ModelAndView newContact(ModelAndView model) {
		Contact newContact = new Contact();
		model.addObject("contact", newContact);
		return new ModelAndView("Landing");
		
}

	@RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	public ModelAndView adminlogin(@ModelAttribute Contact contact) {
		return new ModelAndView("AdminLogin");
	}
	
	@RequestMapping(value = "/userSearch", method = RequestMethod.GET)
	public ModelAndView userSearch(@ModelAttribute Contact contact) throws Exception {
		return new ModelAndView("usersearch");
		
	}	
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/listContact", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	
	@ResponseBody
		public AjaxResponseBody listContact(ModelAndView model) throws IOException {
		    List<Contact> listContact = contactDAO.list();
		    System.out.println("listContact");
		    List<Contactdup> listContdup = new ArrayList<Contactdup>();
		    Contactdup contactdup = null ;
		    AjaxResponseBody result = new AjaxResponseBody();
		  
		    for(Contact contact:listContact) {
		    	contactdup = new Contactdup();
		    	contactdup.setFname(contact.getFname());
		    	System.out.println("inside for");
		    	System.out.println(contact.getFname());
		    	listContdup.add(contactdup);	
		    }
		    result.setResult(listContdup);
		    result.setCode("200");
            result.setMsg("");
		return result;
		
	}
	
	
//	@RequestMapping(value = "/listContact", method = RequestMethod.GET)
//	@ResponseBody
//		public ModelAndView listContact(ModelAndView model) throws IOException {
//		    List<Contact> listContact = contactDAO.list();
//		    System.out.println("listContact");
////		    model.addObject("listContact", listContact);
////		    model.setViewName("list");
//		    JsonObject js=new JsonObject();
//		    for(Contact contact:listContact) {
//		    js.addProperty("fname", contact.getFname());	
//		    }
////		    model.addObject("listContact", "listContact");
////		return model;
//		return js.toString();
		
//	}
//	@RequestMapping(value = "/listContact", method = RequestMethod.GET)
//		public ModelAndView listContact(ModelAndView model) throws IOException {
//			List<Contact> listContact = contactDAO.list();
//    		model.addObject("listContact", listContact);
//    		model.setViewName("list");
// 
//    return model;
//	}
//	
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public ModelAndView signin(@ModelAttribute Contact contact) {
		return new ModelAndView("login");
		
	}
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signup(@ModelAttribute Contact contact) {
		return new ModelAndView("ContactForm");
		
	}
	@RequestMapping(value = "/saveAdminContact", method = RequestMethod.POST)
	public ModelAndView saveAdminContact(@ModelAttribute Contact contact) throws Exception {
		long c1=contactDAO.saveOrUpdate(contact);	
		SendEmail.sendEmail(contact.getEmail(),c1);
	//	return new ModelAndView("redirect:/Register.html");
		return new ModelAndView("Register");

	}
//	@RequestMapping(value="/adminlogin")
//	public ModelAndView listContact(ModelAndView model) throws IOException{
//	    List<Contact> listContact = contactDAO.list();
//	    model.addObject("listContact", listContact);
//	    model.setViewName("home");
//	 
//	    return model;
//	}
	
//	@RequestMapping(value="/list",method = RequestMethod.GET)
//	public String list()
//	public ModelAndView newContact(ModelAndView model) {
//		Contact newContact = new Contact();
//		model.addObject("contact", newContact);
//		model.setViewName("ContactForm");
//		return model;
//	}
	//RequestMapping(value="/save", method=RequestMethod.POST, params="save")
	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Contact contact) throws Exception {
		long c1=contactDAO.saveOrUpdate(contact);	
		SendEmail.sendEmail(contact.getEmail(),c1);
	//	return new ModelAndView("redirect:/Register.html");
		return new ModelAndView("Register");

	}
	 
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public ModelAndView updatePassword(@ModelAttribute Contact contact) {
		return new ModelAndView("ChangePass");
		

	}
	@RequestMapping(value = "/updatepassword", method = RequestMethod.GET)
	public ModelAndView updatePass(@ModelAttribute Contact contact) throws Exception {
	contactDAO.updatePassword(contact.getContact_id(),contact.getPassword());	
		return new ModelAndView("Success");

	}

	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public ModelAndView checkLogin(@ModelAttribute Contact contact) throws Exception {
		return new ModelAndView("login");
		
		
	}
	
	@RequestMapping(value = "/loginVerification", method = RequestMethod.POST)
		public ModelAndView loginVerification(@ModelAttribute Contact contact) throws Exception {
			String result = contactDAO.verifyLogin(contact.getEmail(),contact.getPassword());	
			Contact c1= contactDAO.getbyemailId(contact.getEmail());
			int numberofattempts=c1.getNumberOfAttempts();
			if (result.equals("success"))
			{
				contactDAO.updatenumberOfAttempts(contact.getEmail(),3);
				return new ModelAndView("Loginsuccess");
			}
			else if(result.equals("Fail"))
			{
				numberofattempts=contactDAO.updatenumberOfAttempts(contact.getEmail(),--numberofattempts);
				System.out.println("no.of attempts"+numberofattempts);
				if(numberofattempts == 0) {
					System.out.println("your userid is locked");
					contactDAO.lockout(contact.getEmail());
					System.out.println("your userid is locked");
					System.out.println("no of attempts" + numberofattempts );
					return new ModelAndView("accountlock");
				}
				return new ModelAndView("failure");
			} else if(result.equals("datemodified")) {
				return new ModelAndView("passwordwarning");
			
			}
			
			else {
				return new ModelAndView("accountlock");
			}
	}

	@RequestMapping(value = "/forgetpassword", method = RequestMethod.GET)
	public ModelAndView forgetPassword(@RequestParam(value="emailId", required = false) String emailId) throws Exception {
		Contact c1=contactDAO.getbyemailId(emailId);
		SendEmail.sendEmail(c1.getEmail(),c1.getContact_id());
		return new ModelAndView("Emailsent");
		
	}

	}
