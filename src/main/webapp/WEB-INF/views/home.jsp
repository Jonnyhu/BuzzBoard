<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

  <body>  
        <c:choose>
            <c:when test="${sessionScope.email != null}">
                <div class="container well">
                    <h2>My Subscribed Topics</h2>
                    
                    
                    <c:set var="topics" value="${subscribedTopics}" scope="request"/>
                    <c:import url="/WEB-INF/views/topics/table.jsp" />
                 </div>
                 
                 <div class="container well">
                    <h2>My Subscribed Users</h2>
                    
                    <c:set var="topics" value="${subsribedUserTopics}" scope="request"/>
                    <c:import url="/WEB-INF/views/topics/table.jsp" />
                 </div>
                 
                 <div class="container well">
                    <h2>My Topics</h2>                    
                    
                    <c:set var="topics" value="${myTopics}" scope="request"/>
                    <c:import url="/WEB-INF/views/topics/table.jsp" />
                 </div>
                 
            </c:when>
        <c:otherwise>
            
                <div class="jumbotron">
                    <h1>Welcome to BuzzBoard!</h1>
                    <p><a class="btn btn-primary" href="<c:url value="/signup/" />">Signup today</a> | <a class="btn btn-success"  href="<c:url value="/login/" />">Login</a></p>
                </div>
         </c:otherwise>
        </c:choose>
        
        


 </body>
</html>