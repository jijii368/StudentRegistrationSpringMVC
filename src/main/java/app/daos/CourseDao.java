package app.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.helper.MyConnection;

import app.models.CourseModel;





public class CourseDao {
public int createCourse(CourseModel cr) {
		
		int status=0;
		String query="insert into course_registration(course_name) values(?)";
		Connection con=MyConnection.getInstance().getConnection();
		try {
			PreparedStatement ps=con.prepareStatement(query);
		
			ps.setString(1, cr.getCourse_name());
			status =ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		return status;
	}
public int getCourseDelete(int id){
	int status=0;
	String query="delete from course_registration where id=?";
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
public List<CourseModel> allCourse() {
	List<CourseModel> srs=new ArrayList<CourseModel>();
	String query="select * from course_registration ";
	Connection con=MyConnection.getInstance().getConnection();
	try {
		PreparedStatement ps=con.prepareStatement(query);
		
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			CourseModel sr = new CourseModel();
				sr.setId(rs.getInt("id"));
				sr.setCourse_name(rs.getString("course_name"));
				
			srs.add(sr);
		}
	} catch (SQLException e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return srs;

    }
    

public List<CourseModel> course(String course_name) {
	List<CourseModel> srs=new ArrayList<CourseModel>();
	String query="select * from course_registration where course_name=?";
	Connection con=MyConnection.getInstance().getConnection();
	try {
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, course_name);
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			CourseModel sr = new CourseModel();
				sr.setId(rs.getInt("id"));
				sr.setCourse_name(rs.getString("course_name"));
				
			srs.add(sr);
		}
	} catch (SQLException e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return srs;

    }
public List<CourseModel> courseName(String course_name) {
	List<CourseModel> srs=new ArrayList<CourseModel>();
	String query="SELECT * FROM student_registration WHERE id IN (SELECT  student_id\r\n"
			+ "    FROM student_course WHERE course_id = (SELECT \r\n"
			+ "    id FROM course_registration WHERE course_name = ?))";
	Connection con=MyConnection.getInstance().getConnection();
	try {
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1, course_name);
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			CourseModel sr = new CourseModel();
				sr.setId(rs.getInt("id"));
				sr.setCourse_name(rs.getString("course_name"));
				
			srs.add(sr);
		}
	} catch (SQLException e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	return srs;

    }
public int studentCourse(int student_id,int course_id) {
	
	int status=0;
	String query="insert into student_course(student_id,course_id) values(?,?)";
	Connection con=MyConnection.getInstance().getConnection();
	try {
		PreparedStatement ps=con.prepareStatement(query);
	
		ps.setInt(1, student_id);
		ps.setInt(2, course_id);
		status =ps.executeUpdate();
	} catch (SQLException e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	} 
	return status;
}
/*
 * public int updateCourse(CourseModel course) { int status=0; String
 * query="update into course_registration(course_name) values(?)"; Connection
 * con=MyConnection.getInstance().getConnection(); try { PreparedStatement
 * ps=con.prepareStatement(query);
 * 
 * ps.setString(1, course.getCourse_name()); status =ps.executeUpdate(); } catch
 * (SQLException e) { System.out.println(e.getMessage()); e.printStackTrace(); }
 * return status; }
 */
/*
 * public List<CourseModel> getCourseByName(String course_name) {
 * List<CourseModel> srs=new ArrayList<CourseModel>(); String query =
 * "SELECT * FROM course_registration WHERE course_name = ?";
 * 
 * Connection con=MyConnection.getInstance().getConnection();
 * 
 * try { PreparedStatement ps=con.prepareStatement(query); ps.setString(1,
 * course_name); ResultSet rs=ps.executeQuery(); while (rs.next()) { CourseModel
 * sr = new CourseModel(); sr.setId(rs.getInt("id"));
 * sr.setCourse_name(rs.getString("course_name")); } } catch (SQLException e) {
 * 
 * e.printStackTrace(); } return srs; }
 */

}
