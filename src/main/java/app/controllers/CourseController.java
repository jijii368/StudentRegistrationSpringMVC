package app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.daos.CourseDao;
import app.models.CourseModel;


@Controller
public class CourseController {
	@Autowired
	private CourseDao courseDao;
	
	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public String course() {

		return "courseRegistration";
	}
	
	@RequestMapping(value = "/insertCourse",method = RequestMethod.POST)
	public String insertCourse(@RequestParam("name") String name,ModelMap model) {
		CourseModel cm=new CourseModel(name);
		/*
		 * List<CourseModel> existingCourse = courseDao.getCourseByName(name);
		 *  if(existingCourse != null) {
		 *   model.addAttribute("error","Course Name is Duplicate!"); 
		 *   return "courseRegistration"; }
		 */
		int status=courseDao.createCourse(cm);
		if(status>0) {
			model.addAttribute("success","Insert Successfully<^^>");
		}else {
			model.addAttribute("error","Course Name is Dublicate!");
		}
		return "courseRegistration";
	}
	@RequestMapping(value = "/allCourse", method = RequestMethod.GET)
	public String allCourse() {

		return "courseManagement";
	}
	 @RequestMapping(value="/courseDelete/{id}",method = RequestMethod.GET)
	    public String deleteUser(@PathVariable("id") int id) {
		courseDao.getCourseDelete(id);
		
	       return "courseManagement";
	        
	    }
	
		/*
		 * @RequestMapping(value = "/updateCourse",method = RequestMethod.POST) public
		 * String updateCourse(@RequestParam("name") String name,ModelMap model) {
		 * CourseModel cm=new CourseModel(name); int status=courseDao.updateCourse(cm);
		 * if(status>0) { model.addAttribute("success","Insert Successfully<^^>"); }else
		 * { model.addAttribute("error","Course Name is Dublicate!"); } return
		 * "courseRegistration"; }
		 */
}



