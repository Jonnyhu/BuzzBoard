<%-- 
    Document   : forgotapprove
    Created on : 28-Oct-2014, 6:09:15 PM
    Author     : Alex
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <body>
        <h1>Forgot Password</h1>
        
        <div class="container">
            <h3 class="form-signin-heading-heading">Success!</h3>
            <p>Your password has been send to your phone.</p>
            <form action="<c:url value="/login" />"> 
                  <button class="btn btn-lg btn-primary btn-block" type="submit"> Continue</button>
            </form>
        </div>
    </body>
</html>
