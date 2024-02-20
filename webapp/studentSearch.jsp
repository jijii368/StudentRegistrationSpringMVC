 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="repositories.*,models.*,java.util.*"%>
<%@ include file="header.jsp"%>
    
        <title>Student Search</title>
</head>
<c:if test="${empty List }">
<%
StudentRepository dao = new StudentRepository();
List<StudentModel> srs = dao.allStudentUser();
request.setAttribute("srs", srs);

%>
</c:if>
<body>
<%@ include file="navbar.jsp"%>
      <div class="main_contents">
    <div id="sub_content">
      <form class="row g-3 mt-3 ms-2" action="StudentSearchServlet" method="get">
      <p style="color: red">${error }</p>
      
        <div class="col-auto">
          <label for="staticEmail2" class="visually-hidden">studentID</label>
          <input type="text"  class="form-control" id="staticEmail2" placeholder="Student ID" name="id">
        </div>
        <div class="col-auto">
          <label for="inputPassword2" class="visually-hidden">studentName</label>
          <input type="text" class="form-control" id="inputPassword2" placeholder="Student Name" name="name">
        </div>
        <div class="col-auto">
            <label for="inputPassword2" class="visually-hidden">Course</label>
            <input type="text" class="form-control" id="inputPassword2" placeholder="Course" name="attend">
          </div>
        <div class="col-auto">
          <button type="submit" class="btn btn-success mb-3" >Search</button>
        </div>
        
        <div class="col-auto">
          <button type="reset" class="btn btn-secondary mb-3">Reset</button>
        </div>
      </form>
<div>

      <table class="table table-striped" id="stduentTable">
        <thead>
          
          <tr>
            <th scope="col">#</th>
            <th scope="col">Student ID</th>
            <th scope="col">Name</th>
            <th scope="col">Course Name</th>
             <th scope="col">Photo</th>
            <th scope="col">Details</th>
          </tr>
          
        </thead>
        <tbody>
        <c:choose>
         <c:when test="${empty List}">
          <c:forEach  items="${srs}" var="u" varStatus="status">
	          <tr>
	           <td>${status.index+1}</td>
				<td>STU${u.getId()}</td>
				<td>${u.getName()}</td>
				<td>${u.getAttend()}</td>
				<%-- <td><img src="image/${u.getPhoto()}" alt="..." style="width: 80px;height: 60px"> </td> --%>
			
				<td><img src="data:image/**;base64,${Base64.getEncoder().encodeToString(u.getPhoto())}" alt="..." width="100" height="100"></td>
				<%-- <td>
	              <a href="StudentUpdateController?id=${u.getId()}" class="btn btn-secondary mb-2">Update</a> 
	            </td> --%>
	            <td>
	            <button type="button" class="btn btn-success  " onclick="location.href = 'studentUpdate.jsp?id=${u.id}';">
                        Update
                    </button>
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                    data-bs-target="#exampleModal" >Delete</button></td>
                   
	           </tr>
	               
    		 <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Student Deletion</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        
                         <h5 style="color: rgb(127, 209, 131);">Are you sure want to delete !</h5>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success col-md-2" data-bs-dismiss="modal" onclick="location.href = 'StudentDeleteController?id=${u.id}';">Ok</button>
    
                    </div>
                    
                </div>
            </div>
        </div>
	          
	         </c:forEach>
         </c:when>
         <c:otherwise>
          <c:forEach  items="${List}" var="u" varStatus="status">
	          <tr>
	           <td>${status.index+1}</td>
				<td>STU${u.getId()}</td>
				<td>${u.getName()}</td>
				<td>${u.getAttend()}</td>
				<%-- <td><img src="image/${u.getPhoto()}" alt="..." style="width: 80px;height: 60px"> </td> --%>
			
				<td><img src="data:image/**;base64,${Base64.getEncoder().encodeToString(u.getPhoto())}" alt="..." width="100" height="100"></td>
				<%-- <td>
	              <a href="StudentUpdateController?id=${u.getId()}" class="btn btn-secondary mb-2">Update</a> 
	            </td> --%>
	            <td>
	            <button type="button" class="btn btn-success  " onclick="location.href = 'studentUpdate.jsp?id=${u.id}';">
                        Update
                    </button>
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                    data-bs-target="#exampleModal" >Delete</button></td>
                   
	           </tr>
	               
    		 <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Student Deletion</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        
                         <h5 style="color: rgb(127, 209, 131);">Are you sure want to delete !</h5>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success col-md-2" data-bs-dismiss="modal" onclick="location.href = 'StudentDeleteController?id=${u.id}';">Ok</button>
    
                    </div>
                    
                </div>
            </div>
        </div>
	          
	         </c:forEach>
         </c:otherwise>
         
        </c:choose>
	       
         
          
        </tbody>
      </table>

    </div>
</div>
</div>
 <%@ include file="footer.jsp"%>      