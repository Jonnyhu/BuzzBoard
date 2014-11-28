<%-- 
    Document   : login
    Created on : 21-Oct-2014, 11:38:01 AM
    Author     : Alex
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
  <body>  
    <div class="well bs-component container"> 

      <form class="form-signin" role="form" method="POST" th:object="${login}" action="<c:url value="/login" />">
        <h2 class="form-signin-heading-heading">Please log in</h2>
        <p>
            ${loginError}
        </p>
        
        <input name="email" type="text" class="form-control" placeholder="Email address / Username" th:field="*{email}" required autofocus>
        <input name="password" type="password" class="form-control" placeholder="Password" th:field="*{password}" required>
        <label class="checkbox">
          <input type="checkbox" value="remember-me" th:field="*{remember}"> Remember me
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
       
      </form>
         
      <a href="<c:url value="/forgot/" />" class="btn btn-warning btn-large">Forgot Password</a>  
      <a href="<c:url value="/signup/" />" class="btn btn-success btn-large">Create a BuzzBoard account</a>

    </div> <!-- /container -->


 </body>
</html>
