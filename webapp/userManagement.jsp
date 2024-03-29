<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="repositories.*,models.*,java.util.*"%>
<%@ include file="header.jsp"%>
    
        <title>User Management</title>
</head>

<c:if test="${empty ur }">
<%
UserRepository dao = new UserRepository();
List<UserModel> urs = dao.getallUser();
request.setAttribute("urs", urs);

%>
</c:if>


<body>
<%@ include file="navbar.jsp"%>
	
    <div class="main_contents">
    <div id="sub_content">
        <form class="row g-3 mt-3 ms-2" action="UserSearchServlet" method="get">
        
        	<h3 style="color: red">${error }</h3>
            <div class="col-auto">
                <input type="text" class="form-control" id="staticEmail2" placeholder="User ID" name="id">
            </div>
            <div class="col-auto">
                <input type="text" class="form-control" id="inputPassword2" placeholder="User Name" name="name">
            </div>
    
            <div class="col-auto">
                <input type="submit" class="btn btn-primary mb-3" value="Search">
            </div>
           
            <div class="col-auto">
                <input type="button" class="btn btn-secondary  mb-3" onclick="location.href='userRegistration.jsp'" value="Add">
            </div>
           
            <div class="col-auto" class="row g-3 mt-3 ms-2">
                <!-- <button type="reset" class="btn btn-danger mb-3">Reset</button> -->
                <button type="reset" class="btn btn-danger mb-3"
                            onclick="location.href=''">Reset</button>
            </div>
        </form>
    	
        <table class="table table-striped" id="stduentTable">
            <thead>
            
                <tr>
                    
                    <th scope="col">User ID</th>
                    <th scope="col">User Name</th>
                    <th scope="col">Details</th>
                    
                </tr>
            
            </thead>
            <tbody>
            
           <c:choose>
         <c:when test="${empty ur }">
            <c:forEach items="${urs }" var="u">
                <tr>
                    <td>USR${u.id}</td> 
                    <td>${u.name }</td>
                    
                <td>
                    <button type="button" class="btn btn-success  " onclick="location.href = 'UserUpdateController?id=${u.id}&name=${u.name }';">
                        Update
                    </button>
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                    data-bs-target="#exampleModal" >Delete</button>
                </td>
              
    
                </tr>
    		 <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">User Deletion</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        
                         <h5 style="color: rgb(127, 209, 131);">Are you sure want to delete !</h5>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success col-md-2" data-bs-dismiss="modal" onclick="location.href = 'UserDeleteController?id=${u.id}&name=${u.name }';">Ok</button>
    
                    </div>
                    
                </div>
            </div>
        </div>
                </c:forEach>
           
           
         </c:when>
         <c:otherwise>
         
                   <c:forEach items="${ur }" var="u">
                <tr>
                    <td>USR${u.id}</td> 
                    <td>${u.name }</td>
                    
                <td>
                    <button type="button" class="btn btn-success  " onclick="location.href = 'UserUpdateController?id=${u.id}&name=${u.name }';">
                        Update
                    </button>
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                    data-bs-target="#exampleModal" >Delete</button>
                </td>
              
    
                </tr>
    		 <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">User Deletion</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        
                         <h5 style="color: rgb(127, 209, 131);">Are you sure want to delete !</h5>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success col-md-2" data-bs-dismiss="modal" onclick="location.href = 'UserDeleteController?id=${u.id}&name=${u.name }';">Ok</button>
    
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
 <%@ include file="footer.jsp"%>

