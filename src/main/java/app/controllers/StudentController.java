package app.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.daos.CourseDao;
import app.daos.StudentDao;
import app.dto.StudentResponseDTO;
import app.models.CourseModel;
import app.models.StudentModel;

@Controller
public class StudentController {
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private CourseDao courseDao;

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public ModelAndView login() {

		return new ModelAndView("studentRegistration", "student", new StudentModel());
	}

	@RequestMapping(value = "/studentCreate", method = RequestMethod.POST)
	  public String insertCourse(@ModelAttribute("student") StudentModel student, ModelMap model) {
			
		try {
	          MultipartFile multipartFile = student.getFile();
	          String uploadPath = "D:\\JWD46\\cocop\\StudentRegistrationSpringMVC\\src\\main\\webapp\\assets\\image" + File.separator;
	          String fileName = multipartFile.getOriginalFilename();
	          Path path = Paths.get(uploadPath + fileName);
	          Files.write(path, multipartFile.getBytes());
	          model.addAttribute("fileName", fileName);
	         
	          var b = studentDao.createStudent(student);
	          if(b==1) {
	        	  List<StudentModel> students = studentDao.studentCourse(student.getName(), student.getPhone());
		            studentDao.stuCourseDelete(student.getId()); 
		            for (String courseId : student.getAttend().split(",")) {
		                List<CourseModel> courses = courseDao.course(courseId);
		                if (!courses.isEmpty()) {
		                    courseDao.studentCourse(students.get(0).getId(), courses.get(0).getId());
		                }
		            }
	        	  
	            model.addAttribute("success", "Successful Register<^^>");
	              return "studentRegistration";
	          } else {
	                model.addAttribute("error", "Error occurred while registering the student.");
	                return "studentRegistration";
	          }
	      } catch (IOException e) {
	        
	      }
	      return "studentRegistration";
	      }
	         

	  @RequestMapping(value = "/studentSearch", method = RequestMethod.GET)
	  public String studentSearch(ModelMap mm) {
	    mm.addAttribute("list",studentDao.getAllStudentInfo());
	    return "studentSearch";
	  }
	  
	@RequestMapping(value = "/studentSearch", method = RequestMethod.POST)
	public String processForm(@RequestParam(value = "id", defaultValue = "0") int id,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "attend", defaultValue = "") String attend, ModelMap model) {

		if (id == 0 && name.isEmpty() && attend.isEmpty()) {
			List<StudentResponseDTO> srs = studentDao.allStudentUser();

			model.addAttribute("list", srs);
		} else if (!attend.isEmpty() && id == 0 && name.isEmpty()) {
			List<StudentResponseDTO> srs = studentDao.courseName(attend);
			if (srs.isEmpty()) {
				
				model.addAttribute("error", "Student not found");
			} else {
				model.addAttribute("list", srs);
			}
		} else if (attend.isEmpty() && id != 0 && name.isEmpty()) {
			List<StudentResponseDTO> srs = studentDao.studentById(id);
			if (srs.isEmpty()) {
				model.addAttribute("error", "Student not found");
			} else {
				model.addAttribute("list", srs);
			}
		} else if (attend.isEmpty() && id == 0 && !name.isEmpty()) {
			ArrayList<StudentResponseDTO> srs = studentDao.studentName(name);
			if (srs.isEmpty()) {
				model.addAttribute("error", "Student not found");
			} else {
				model.addAttribute("list", srs);
			}
		} else if (id != 0 || !name.isEmpty() || !attend.isEmpty()) {
			List<StudentResponseDTO> srs = studentDao.studentMore(id, name, attend);
			if (srs.isEmpty()) {
				model.addAttribute("error", "Student not found");
			} else {
				model.addAttribute("list", srs);
			}
		}

		return "studentSearch";
	}

	@RequestMapping(value = "/studentDelete/{id}", method = RequestMethod.GET)
	public String deleteStudent(@PathVariable("id") Integer id) {
		studentDao.stuCourseDelete(id);
		int status = studentDao.studentDelete(id);
		return "redirect:/studentSearch";

	}

	@RequestMapping(value = "/stuUpdate/{id}", method = RequestMethod.GET)
	public ModelAndView studentUpdate(@PathVariable("id") int id, ModelMap model) {
		List<StudentModel> list = studentDao.studentId(id);
		ArrayList<CourseModel> attend=studentDao.getCourseByStudentId(id);
		model.addAttribute("stu", list);
		model.addAttribute("id", id);
		model.addAttribute("course_list",courseDao.allCourse());
		model.addAttribute("Attend",attend);
		
		System.out.println(courseDao.allCourse());
		return new ModelAndView("studentUpdate", "student", new StudentModel());
	}

	@RequestMapping(value = "/studentUpdate", method = RequestMethod.POST)
	public String studentsUpdate(@ModelAttribute("student") StudentModel student, ModelMap model,RedirectAttributes redirect) {
		try {
			MultipartFile multipartFile = student.getFile();
			String uploadPath = "D:\\JWD46\\cocop\\StudentRegistrationSpringMVC\\src\\main\\webapp\\assets\\image"
					+ File.separator;
			String fileName = multipartFile.getOriginalFilename();
			Path path = Paths.get(uploadPath + fileName);
			Files.write(path, multipartFile.getBytes());
			
			model.addAttribute("fileName", fileName);
			
			redirect.addAttribute("stuId", student.getId());

			int status = studentDao.updateStudentRegistration(student);
			if (status == 1) {

				List<StudentModel> students = studentDao.studentCourse(student.getName(), student.getPhone());
				studentDao.stuCourseDelete(student.getId());
				for (String courseId : student.getAttend().split(",")) {
					List<CourseModel> courses = courseDao.course(courseId);
					if (!courses.isEmpty()) {
						courseDao.studentCourse(students.get(0).getId(), courses.get(0).getId());
					}
				}
				
				redirect.addFlashAttribute("success","Updated successfully <^^>");
				return "redirect:/stuUpdate/{stuId}";
				
			} else {
				redirect.addFlashAttribute("error", "Update Failed!!");
				return "redirect:/stuUpdate/{stuId}";
			}
		} catch (IOException e) {
			redirect.addFlashAttribute("error", "Error occurred while uploading the file.");
			return "redirect:/stuUpdate/{stuId}";
		}
		
	}
	
}
	
	
	
	
	