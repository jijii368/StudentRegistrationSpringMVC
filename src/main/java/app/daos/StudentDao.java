package app.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import app.dto.StudentResponseDTO;
import app.helper.MyConnection;
import app.models.CourseModel;
import app.models.StudentModel;


public class StudentDao {
	public int createStudent(StudentModel srs) {
		MultipartFile multipartFile = srs.getFile();
		

		String fileName = multipartFile.getOriginalFilename();
			int status=0;
			String query="insert into student_registration(name,registration_date,gender,phone,education,photo) values(?,?,?,?,?,?)";
			Connection con=MyConnection.getInstance().getConnection();
			try {
				
				PreparedStatement ps=con.prepareStatement(query);
				
				ps.setString(1, srs.getName());
				ps.setString(2, srs.getRegistration_date());
				ps.setString(3, srs.getGender());
				System.out.println("2");
				ps.setString(4, srs.getPhone());
				ps.setString(5, srs.getEducation());
				System.out.println("1");
				
				ps.setString(6, fileName);
				status =ps.executeUpdate();
				System.out.println("status---->" + status);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} 
			return status;
		}
	public ArrayList<StudentResponseDTO> allStudentUser() {
		ArrayList<StudentResponseDTO> srs=new ArrayList<StudentResponseDTO>();
		String query="select * from student_registration";
		Connection con=MyConnection.getInstance().getConnection();
		try {
			PreparedStatement ps=con.prepareStatement(query);
			
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				StudentResponseDTO sr = new StudentResponseDTO();
					sr.setId(rs.getInt("id"));
					sr.setName(rs.getString("name"));
					sr.setCourse(getCourseByStudentId(sr.getId()));
					sr.setPhoto(rs.getString("photo"));
				srs.add(sr);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return srs;

	    }
	public List<StudentModel> studentCourse(String name,String phone){ 
		
		List<StudentModel> srs=new ArrayList<StudentModel>();
		String query="select * from student_registration where name=? and phone=?";
		Connection con=MyConnection.getInstance().getConnection();
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, name);
			 ps.setString(2, phone );
		     
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				StudentModel sr = new StudentModel();

				sr.setId(rs.getInt("id"));
				sr.setName(rs.getString("name"));
				sr.setRegistration_date(rs.getString("registration_date"));
				sr.setGender(rs.getString("gender"));
				sr.setPhone(rs.getString("phone"));
				sr.setEducation(rs.getString("education"));
				sr.setPhoto(rs.getString("photo"));
				srs.add(sr);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return srs;
	}
	public int stuCourseDelete(int student_id){
		int status=0;
		String query="delete from student_course where student_id=?";
		Connection con=MyConnection.getInstance().getConnection();
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, student_id);
			status=ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	public ArrayList<StudentResponseDTO> courseName(String course_name) {
		ArrayList<StudentResponseDTO> srs=new ArrayList<StudentResponseDTO>();
		String query="SELECT * FROM student_registration WHERE id IN (SELECT  student_id\r\n"
				+ "    FROM student_course WHERE course_id = (SELECT \r\n"
				+ "    id FROM course_registration WHERE course_name = ?))";
		Connection con=MyConnection.getInstance().getConnection();
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, course_name);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				StudentResponseDTO sr = new StudentResponseDTO();
				sr.setId(rs.getInt("id"));
				sr.setName(rs.getString("name"));
				sr.setPhoto(rs.getString("photo"));
				sr.setCourse(getCourseByStudentId(sr.getId()));
				srs.add(sr);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return srs;

	    }
	public ArrayList<StudentResponseDTO> studentById(int studentId){ 
		
		ArrayList<StudentResponseDTO> srs=new ArrayList<StudentResponseDTO>();
		String query="select * from student_registration where id=?";
		Connection con=MyConnection.getInstance().getConnection();
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, studentId);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				StudentResponseDTO sr = new StudentResponseDTO();
				sr.setId(rs.getInt("id"));
				sr.setName(rs.getString("name"));
				sr.setPhoto(rs.getString("photo"));
				sr.setCourse(getCourseByStudentId(sr.getId()));
				srs.add(sr);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return srs;
	}
	public List<StudentModel> studentId(int studentId){ 
		
		List<StudentModel> srs=new ArrayList<StudentModel>();
		String query="select * from student_registration where id=?";
		Connection con=MyConnection.getInstance().getConnection();
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, studentId);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				StudentModel sr = new StudentModel();
				sr.setId(rs.getInt("id"));
				sr.setName(rs.getString("name"));
				sr.setRegistration_date(rs.getString("registration_date"));
				sr.setGender(rs.getString("gender"));
				sr.setPhone(rs.getString("phone"));
				sr.setEducation(rs.getString("education"));
			
				sr.setPhoto(rs.getString("photo"));
				srs.add(sr);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return srs;
	}
	public ArrayList<StudentResponseDTO> studentName(String name){ 

		ArrayList<StudentResponseDTO> srs=new ArrayList<StudentResponseDTO>();
		String query="select * from student_registration where name like ?";
		Connection con=MyConnection.getInstance().getConnection();
		try {
			PreparedStatement ps=con.prepareStatement(query);
			
		     ps.setString(1,"%"+ name +"%");
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				StudentResponseDTO sr = new StudentResponseDTO();

				sr.setId(rs.getInt("id"));
				sr.setName(rs.getString("name"));
				sr.setPhoto(rs.getString("photo"));
				sr.setCourse(getCourseByStudentId(sr.getId()));
				srs.add(sr);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return srs;
		}
	public ArrayList<StudentResponseDTO> studentMore(int id,String name,String attend){ 
		
		ArrayList<StudentResponseDTO> srs=new ArrayList<StudentResponseDTO>();
		String query="SELECT * FROM student_registration\r\n"
				+ "    WHERE id=? or name like ? or id IN (SELECT \r\n"
				+ "   student_id FROM student_course WHERE\r\n"
				+ "   course_id = (SELECT id FROM course_registration \r\n"
				+ "   WHERE course_name=?))";
		Connection con=MyConnection.getInstance().getConnection();
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, id);
			 ps.setString(2,"%"+name+"%" );
		     ps.setString(3, attend);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				StudentResponseDTO sr = new StudentResponseDTO();

				sr.setId(rs.getInt("id"));
				sr.setName(rs.getString("name"));
				sr.setPhoto(rs.getString("photo"));
				sr.setCourse(getCourseByStudentId(sr.getId()));
				srs.add(sr);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return srs;
	}
	public int studentDelete(int id){
		int status=0;
		String query="delete from student_registration where id=?";
		Connection con=MyConnection.getInstance().getConnection();
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, id);
			status=ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
	public int updateStudentRegistration(StudentModel srs) {
		MultipartFile multipartFile = srs.getFile();
			

			String fileName = multipartFile.getOriginalFilename();
			int status=0;
			String query="update student_registration set name=?,registration_date=?,gender=?,phone=?,education=?,photo=? where id=?";
			Connection con=MyConnection.getInstance().getConnection();
			try {
				PreparedStatement ps=con.prepareStatement(query);
				
				ps.setString(1, srs.getName());
				ps.setString(2, srs.getRegistration_date());
				ps.setString(3, srs.getGender());
				ps.setString(4, srs.getPhone());
				ps.setString(5, srs.getEducation());
				ps.setString(6, fileName);
				ps.setInt(7, srs.getId());
				status=ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			return status;
		}
		
//		  public List<StudentModel> studentId(int studentId){
//		  
//		  List<StudentModel> srs=new ArrayList<StudentModel>(); 
//		  String query="select c.course_name from student_registration st inner join student_course sc on st.id = sc.student_id inner join course_registration c on sc.course_id = c.id where st.id=?;";
//		  Connection con=MyConnection.getInstance().getConnection();
//		  try { 
//			  PreparedStatement ps=con.prepareStatement(query);
//			  ps.setInt(1, studentId);
//			  ResultSet rs=ps.executeQuery();
//			  while (rs.next()) {
//				  StudentModel sr = new StudentModel();
//				  sr.setId(rs.getInt("student_id"));
//		          sr.setAttend(rs.getString("course_name"));
//		  
//		          srs.add(sr); 
//		  } 
//			  } catch (SQLException e) { 
//			 System.out.println(e.getMessage());
//		  e.printStackTrace(); } 
//		  return srs; }
	
		public ArrayList<CourseModel> getCourseByStudentId(int id){
			Connection con = MyConnection.getInstance().getConnection();
			ArrayList<CourseModel> list = new ArrayList<CourseModel>();
			String sql = "select c.id, c.course_name From(select * from student_course where student_id=?) st inner join course_registration c \r\n"
					+ "on st.course_id = c.id";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1	, id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					CourseModel courseModel=new CourseModel();
					courseModel.setId(rs.getInt("id"));
					courseModel.setCourse_name(rs.getString("course_name"));
					list.add(courseModel);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return list;
		}
		
		public ArrayList<StudentResponseDTO> getAllStudentInfo(){
			Connection con =MyConnection.getInstance().getConnection();
			ArrayList<StudentResponseDTO>  list = new ArrayList<StudentResponseDTO>();
			String sql ="select id,name,photo from student_registration";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while (rs.next()) {
					StudentResponseDTO res=new StudentResponseDTO();
					res.setId(rs.getInt("id"));
					res.setName(rs.getString("name"));
					res.setPhoto(rs.getString("photo"));
					res.setCourse(getCourseByStudentId(res.getId()));
					list.add(res);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
		 
		}


