<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp"%>
<title> Student Registration LGN001 </title>

</head>
<style>
.input-group {
  position: relative;
}

#togglePasswordIcon {
  position: absolute;
  top: 50%;
  right: 10px;
  transform: translateY(-50%);
  cursor: pointer;
}
</style>
<body class="login-page-body"> 
  
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h1>Welcome!</h1>
          </div>
        </div>
        <form class="login-form" action="LoginServlet" method="post" name="confirm">
          <p style="color: red">${error }</p>
          <p style="color: blue">${success }</p>
          <input type="text" placeholder="User Name" name="name" />
          <div class="input-group">
							<input type="password" class="form-control" id="password"
								name="password" > <i
								class="fa fa-eye-slash" id="togglePasswordIcon"
								onclick="togglePasswordVisibility()"></i>
						</div>
          <button>Login</button>
          <p class="message">Not registered? <a href="userRegistration.jsp">Create an account</a></p>
        </form>
      </div>
    </div>
    <script type="text/javascript">
function togglePasswordVisibility() {
    var passwordInput = document.getElementById("password");
    var toggleIcon = document.getElementById("togglePasswordIcon");

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        toggleIcon.classList.remove("fa-eye-slash");
        toggleIcon.classList.add("fa-eye");
    } else {
        passwordInput.type = "password";
        toggleIcon.classList.remove("fa-eye");
        toggleIcon.classList.add("fa-eye-slash");
    }
}
    </script>
    <!-- <script>
    document.getElementById('loginForm').addEventListener('submit',function(event)){
    	event.preventDefault();
    	var emailInput=document.getElementById('emailInput').value;
    	var passwordInputo=document.getElementById('passwordInput').value;
    	if(validation(emailInput,passwordInput)){
    		this.submit();
    	}
    });
    const validation=(email)
    }
    </script> -->
</body>

</html>