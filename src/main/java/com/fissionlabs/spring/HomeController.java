package com.fissionlabs.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fissionlabs.spring.dao.UserDao;
import com.fissionlabs.spring.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/*@Autowired
	private UserDao userDao;*/
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		
		return "home";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.GET)
	public String save(Model model
			,@RequestParam("id") int id,@RequestParam("name") String name)
	
	{
		String status="not registered";
		if(id==1 || name=="venkat")
		{
			status="registered";
			/*model.addAttribute("employee", new Employee());*/
			model.addAttribute("result", status);
			return "register";
		}
		model.addAttribute("result", status);
		return "register";
	}
	/*@RequestMapping(value="/person/add",method=RequestMethod.GET)
	public String add(@ModelAttribute("emp") Employee emp ,Model model)
	{
		return "home";
	}
		*/
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(@RequestParam("id") int id,@RequestParam("name") String name,@RequestParam("password") String password)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring.xml");
		 UserDao dao=(UserDao) ctx.getBean(UserDao.class);
		 User uw= new User();
		 uw.setId(id);
		 uw.setName(name);
		 uw.setPassword(password);
		 dao.addUser(uw);
		 
		 return "success";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("id") int id,Model model)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring.xml");
		 UserDao dao=(UserDao) ctx.getBean(UserDao.class);
		
		 User uq=dao.getById(id);
		 String name=uq.getName();
		 String email=uq.getPassword();
		 
		 model.addAttribute("Name", name);
		 model.addAttribute("Email",email );
		 
		 return "demo";
		 
		 
	}
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(@RequestParam("id") int id,@RequestParam("name") String name,@RequestParam("password") String password)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring.xml");
		 UserDao dao=(UserDao) ctx.getBean(UserDao.class);
		 /*User uw= new User();
		 uw.setId(id);
		 uw.setName(name);
		 uw.setPassword(password);*/
		 dao.updateUser(id, name, password);
		 
		 
		 return "updated";
	}
	
	
	@RequestMapping(value="/user",method=RequestMethod.DELETE)
	public String delete(@RequestParam("id") int id,Model model)
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring.xml");
		 UserDao dao=(UserDao) ctx.getBean(UserDao.class);
		 /*User uq=dao.getById(id);
		 String name=uq.getName();
		 String email=uq.getPassword();
		 
		 model.addAttribute("Name", name);
		 model.addAttribute("Email",email );*/
		 dao.deleteUser(id);
		 
		 return "deleted";
		 
		 
	}
	
	
}
