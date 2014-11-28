<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    
    

  <body>
     
    <div class="container well bs-component container">  
        <c:choose>
            <c:when test="${sessionScope.username == User.username}">
                <h2>My Profile</h2>
            </c:when>
        </c:choose>
        <form class="form" role="form" method="GET" th:object="${UserProfile}"/>  
            <h2>Account Information</h2>
            <ul>
                <li>User Name: ${User.username}</li>
                <li>Email: ${User.email}</li>

                    <c:choose>
                        <c:when test="${ dontShowPhones != 1 }">
                            <c:choose>
                                <c:when test="${ InvalidPhoneNumber == 1 }">
                                    <li>Phone Number: ${User.phonenumber} <span class="label label-warning">Invalid Phone Number</span></li>                                    
                                </c:when>

                                <c:otherwise>
                                    <li>Phone Number: ${User.phonenumber}</li>            
                                </c:otherwise>                            
                            </c:choose>
                        </c:when>
                    </c:choose>

            </ul>

            <c:choose>
                <c:when test="${sessionScope.username == User.username}">
                    <a class="btn btn-success" href="<c:url value="/profile/edit/${User.username}" />">Edit Information</a>
            
                    
            
                    
                    <h3>Notification Settings</h3>
                    <ul>
                        <label class="checkbox">
                            <li><input type="checkbox" value="remember-me" th:field="*{remember}"> Remember me on login</li>                   
                        </label>
                        <c:choose>
                            <c:when test="${ InvalidPhoneNumber == null }">
                                <label class="checkbox">
                                    <li><input type="checkbox" value="sms" th:field="*{sms}"> Send notifications via phone (SMS)</li>
                                </label>
                            </c:when>
                        </c:choose>
                    </ul>
                </c:when>
            </c:choose>
            
        </form>
            
    </div> <!-- /container -->
    
    <div class="container well">
        <c:choose>
            <c:when test="${sessionScope.username != null && sessionScope.username == User.username}">
                <h2>My Topics</h2> 
            </c:when>
            <c:otherwise>
                <h2>${User.username}'s Topics</h2>
            </c:otherwise>
        </c:choose>

            <c:set var="topics" value="${topics}" scope="request"/>
            <c:import url="/WEB-INF/views/topics/table.jsp" />
    </div>
 </body>
</html>                
