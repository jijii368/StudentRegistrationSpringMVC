<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="repositories.*,models.*,java.util.*"%>
<%@ include file="header.jsp"%>
    
        <title>Student Delete and Update</title>


<%@ include file="navbar.jsp"%>
<%
CourseRepository cdao = new CourseRepository();
	List<CourseModel> srs=cdao.allCourse();
	System.out.print(srs);
	request.setAttribute("list", srs);
%>

<%
int id = Integer.valueOf(request.getParameter("id"));
StudentRepository dao = new StudentRepository();

List<StudentModel> students = dao.studentById(id);
for(StudentModel stu : students){
	request.setAttribute("u", stu);
}



%>

		<div class="main_contents">
    		<div id="sub_content">
    		            <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student Update</h2>
            <h4 class="col-md-6 offset-md-2 mb-2 mt-2" style="color: red;">${error }</h4>
            <h4 class="col-md-6 offset-md-2 mb-2 mt-2" style="color: green;">${success }</h4>
         <form action="StudentUpdateController" method="post" enctype="multipart/form-data"  onsubmit="return validateForm()">

         
            <div class="row mb-4">
                <div class="col-md-2"></div>
              
                <div class="col-md-4">
                    <input type="hidden" class="form-control"  name="id" id="id" value="${u.id }" readonly="readonly">
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">Name</label>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="name" name="name" value="${u.name }">
                </div>
                 <div id="nameError" class="col-md-4" style="color: red;"></div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="dob" class="col-md-2 col-form-label">DOB</label>
                <div class="col-md-4">
                    <input type="date" class="form-control" id="dob" name="date" value="${u.date}">
                </div>
                 <div id="dateError" class="col-md-4" style="color: red;"></div>
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Gender</legend>
                <div class="col-md-4">
                    <div class="form-check-inline">
                        <input <c:if test="${u.gender=='male' }">checked</c:if> class="form-check-input" type="radio" name="gender" id="gridRadios1" value="male"
                            >
                        <label class="form-check-label" for="gridRadios1">
                            Male
                        </label>
                    </div>
                    <div class="form-check-inline">
                        <input <c:if test="${u.gender=='female' }">checked</c:if> class="form-check-input" type="radio" name="gender" id="attend" value="female">
                        <label class="form-check-label" for="gridRadios2">
                            Female
                        </label>
                    </div>
				</div>
				 <div id="genderError" class="col-md-4" style="color: red;"></div>
            </fieldset>
    
            <div class="row mb-4" >
                <div class="col-md-2"></div>
                <label for="phone" class="col-md-2 col-form-label">Phone</label>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="phone" name="phone" value="${u.phone }">
                </div>
                 <div id="phoneError" class="col-md-4" style="color: red;"></div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="education" class="col-md-2 col-form-label">Education</label>
                <div class="col-md-4">
                    <select class="form-select" aria-label="Education" id="education" name="education">
                        <option <c:if test="${u.education=='Bachelor of Information Technology' }">selected</c:if> value="Bachelor of Information Technology">Bachelor of Information Technology</option>
                        <option <c:if test="${u.education=='Diploma in IT' }">selected</c:if> value="Diploma in IT">Diploma in IT</option>
                        <option <c:if test="${u.education=='Bachelor of Computer Science' }">selected</c:if> value="Bachelor of Computer Science">Bachelor of Computer Science</option>
    
                    </select>
                </div>
            </div>
             <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Attend</legend>
    
                <div class="col-md-4">
				    
				    <% 
				    
				    for(CourseModel c : srs ){ 
				    	String name = c.getCourse_name();
				     
				    %>
				    <div>
				    <input type="checkbox" name="attend"  
				    
				  <% if(students.get(0).getAttend().contains(c.getCourse_name())) {%> checked="checked" <%}else{ %> <%} %>  value=<%=name%>>
				    <%=name %> 
				    </div>
				    <%} %>
                </div>
                
                <%  
                	String encode = Base64.getEncoder().encodeToString(students.get(0).getPhoto());
                %>
                
             <div id="attendError" class="col-md-4" style="color: red;"></div>
            </fieldset>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">Photo</label>
                <div class="col-md-4">
                <%-- <img src="data:image/**;base64,${Base64.getEncoder().encodeToString(students.get(0).getPhoto())}" alt="Student Photo" width="100" height="100"> --%>
				 <img  src="data:image/**;base64,<%=encode %> "alt="Student Photo" width="100" height="100"/>
	            
                    <input type="file" class="form-control" id="part" name="part" value="${u.photo }">
                </div>
                 <div id="partError" class="col-md-4" style="color: red;"></div>
            </div>

            <div class="row mb-4">
                <div class="col-md-4"></div>
    
                <div class="col-md-4">
                    <button type="submit" class="btn btn-success"  >
                        Update
                    </button>
               
               
               
 
                </div>
    
            </div>
    
    
            <!--Modal-->
    
            </form>
    </div>
</div>
<script>
    function validateForm() {
      /*   var studentId = document.getElementById("studentId").value; */
        var name = document.getElementById("name").value;
        var date = document.getElementById("date").value;
        var gender = document.querySelector('input[name="gender"]:checked');
        var phone = document.getElementById("phone").value;
        var education = document.getElementById("education").value;
        var attend = document.querySelectorAll('input[name="attend"]:checked');
        var part = document.getElementById("part").value;
  
            // Validate studentId
            /* if (studentId== "") {

            	document.getElementById("studentIdError").innerHTML = "Please enter Student ID";
                return false;
            } */
            
           

	/// Validate name
		if (name== "") {
			document.getElementById("nameError").innerHTML = "Please enter Name";
			return false;
		}

		// Validate date
		if (date== "") {
			document.getElementById("dateError").innerText = "Please enter Date ";
			return false;
		}

		// Validate gender
		if (!gender) {
			document.getElementById("genderError").innerText = "Please select Gender";
			return false;
		}

		// Validate phone
		if (phone== "") {
			document.getElementById("phoneError").innerText = "Please enter Phone";
			return false;
		}else if (!isValidPhone(phone)) {
			document.getElementById("phoneError").innerHTML = "Phone No is invalid !!!";
            isValid = false;
          }

		// Validate education
		if (education== "") {
			document.getElementById("educationError").innerText = "Please select Education";
			return false;
		}
		if (part== "") {
			document.getElementById("partError").innerText = "Select photo";
			return false;
		}
		 function isValidPhone(phone){
	        	var phoneRegex=/[0]{1}[1,2,9]{1}[0-9]{9}/;
	        		return phoneRegex.test(phone);
	        }
		// Validate attend
		if (attend.length === 0) {
			document.getElementById("attendError").innerText = "Select at least one";
			return false;
		}

		document.getElementById("attendError").innerText = "";
		return true;
	}
    
    
</script>
<%@ include file="footer.jsp"%>