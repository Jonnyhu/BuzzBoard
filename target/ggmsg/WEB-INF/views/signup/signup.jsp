<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Signup</title>
    </head>
    <body>
        <div class="col-lg-3">
            <div class="well bs-component container"> 
            <form action="<c:url value='/signup/verify' />" th:object="${newuser}" method="post">
              <div class="form-group" >
                <label>Name:</label>            
                <p><input class="form-control" type="text" name="username" th:field="*{username}" /></p>
                <label>Email:</label>
                <p><input class="form-control" type="email" name="email" th:field="*{email}" /></p>
                <label>Phone number:</label>
                <p><input class="form-control" type="text" name="phonenumber" th:field="*{phonenumber}" /></p>
                <label>Password:</label>
                <p><input class="form-control" type="password" name="password" th:field="password" /></p>
              </div>
              <button type="submit" class="btn btn-success">Create Me!</button>
            </form>
              </div>
        </div>
    </body>
</html>
