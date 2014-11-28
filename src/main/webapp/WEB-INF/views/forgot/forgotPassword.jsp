<%-- 
    Document   : forgotPassword
    Created on : 28-Oct-2014, 5:38:35 PM
    Author     : Alex
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <body>
         <div class="well bs-component container"> 
        <h1>Forgot Password</h1>
            <p>
                ${forgotError}
            </p>
            <form class="form-signin" role="form" method="POST" th:object="${login}" action="<c:url value="/forgot/" />">
                <h3 class="form-signin-heading-heading">Please enter your email:</h3>
                <input name="email" type="email" class="form-control" placeholder="Email address" th:field="*{email}" required autofocus>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Retrieve Password</button>
            </form>
         </div>
    </body>
</html>
