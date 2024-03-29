<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="repositories.*,models.*,java.util.*"%>
<%@ include file="header.jsp"%>
    
        <title>Course Registration</title>
</head>

<body>
   <%@ include file="navbar.jsp"%>
    <form action="CourseUpdateController" method="post">

        <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Course Registration</h2>
        <p style="color: green" class="col-md-6 offset-md-2 mb-2 mt-2">${success }</p>
        <p style="color: red" class="col-md-6 offset-md-2 mb-2 mt-2">${error }</p>
        <div class="row mb-4">
            <div class="col-md-2"></div>
           <%String id=request.getParameter("id"); %>
            <div class="col-md-4">
                <input type="hidden" class="form-control" id="id" name="id" value="${ur.id }">
            </div>
        </div>

        <div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="name" class="col-md-2 col-form-label">Course Name</label>
            <div class="col-md-4">
                <input type="text" class="form-control" id="name" name="name" value="${ur.course_name }">
            </div>
        </div>
      
       
        <div class="row mb-4">
            <div class="col-md-4"></div>

            <div class="col-md-6">
               

                <button type="button" class="btn btn-secondary col-md-2" data-bs-toggle="modal" data-bs-target="#exampleModal">Add</button>
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                    aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Update Registration</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <h5 style="color: rgb(127, 209, 131);">Sure Update?!!!</h5>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-success col-md-2" data-bs-dismiss="modal">Ok</button>
                               </div>
                            </div>
                        </div>
                    </div>
            </div>

        </div>
        </form>
   <%@ include file="footer.jsp"%>