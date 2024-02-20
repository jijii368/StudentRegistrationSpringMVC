<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="repositories.*,models.*,java.util.*"%>
<%@ include file="header.jsp"%>
    
        <title>Course Management</title>
</head>

<body>
<%@ include file="navbar.jsp"%>
	<%
CourseRepository cRepository = new CourseRepository();
	List<CourseModel> courses=cRepository.allCourse();
	request.setAttribute("list", courses);
%>
    <div class="main_contents">
    <div id="sub_content">
        <form class="row g-3 mt-3 ms-2" >
        	<h3 style="color: red">${error }</h3>
           
       
    	
        <table class="table table-striped" id="stduentTable">
            <thead>
                <c:if test="${list!=null }">
                <tr>
                    
                    <th scope="col">Course ID</th>
                    <th scope="col">Course Name</th>
                    <th scope="col">Details</th>
                    
                </tr>
                </c:if>
            </thead>
            <tbody>
            <c:forEach items="${list }" var="u">
                <tr>
                    <td>COU${u.id}</td>
                    <td>${u.course_name }</td>
                    
                <td>
                    <button type="button" class="btn btn-success  " onclick="location.href = 'CourseUpdateController?id=${u.id}&name=${u.course_name }';">
                        Update
                    </button>
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                    data-bs-target="#exampleModal" >Delete</button>
                </td>
                <!-- <td><button type="button" class="btn btn-secondary mb-3" data-bs-toggle="modal"
                    data-bs-target="#exampleModal" >Delete</button></td> -->
    
                </tr>
    		 <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Course Deletion</h5>
                        <button type="submit" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        
                         <h5 style="color: rgb(127, 209, 131);">Are you sure want to delete !</h5>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success col-md-2" data-bs-dismiss="modal" onclick="location.href = 'CourseDeleteController?id=${u.id}&name=${u.course_name }';">Ok</button>
    
                    </div>
                    
                </div>
            </div>
        </div>
                </c:forEach>
            </tbody>
        </table>
    
        </form>
       
    </div>
</div>
 <%@ include file="footer.jsp"%>

