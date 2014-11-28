<%-- 
    Document   : editprofile
    Created on : Nov 12, 2014, 11:43:15 AM
    Author     : strainal
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    
  <body>
    <div class="container well bs-component container">  
        <h2>My Profile</h2>
        <c:choose>
        <c:when test="${request.getAttribute('CPassError') != null}">
            <p>
                ${CPassError}
            </p>
        </c:when>
        </c:choose>
        
        <form class="form" role="form" method="POST" action="<c:url value="/profile/edit/${User.username}" />" th:object="${UserProfile}"/>  
            <h3>Edit ${User.username}'s Account Information</h3>
            <label>Email: ${User.email}</label>
                <p><input class="form-control" type="text" name="email" th:field="*{email}" /></p>
                
                <c:choose>
                    <c:when test="${ InvalidPhoneNumber == 1 }">
                        <li>Phone Number: ${User.phonenumber} <span class="label label-warning">Invalid Phone Number</span></li>                                    
                    </c:when>
                        
                    <c:otherwise>
                        <label>Phone number: ${User.phonenumber}</label> 
                        <p><input class="form-control" type="text" name="phonenumber" th:field="*{phonenumber}" /></p>
                    </c:otherwise>                            
                </c:choose>
                <c:choose>
                <c:when test="${ ValidationButton == 1 }">
                    <button id="validation_button" onclick = "get_validation_number('${User.getPhonenumber()}')" type="button" class="btn btn-warning" 
                       data-toggle="tooltip" 
                       data-placement="right" 
                       title="" data-original-title="To receive phone notifications you must verify your phone number first. You will get an automatic phone call where you have to enter verification number that will be shown here." 
                       href="<c:url value="" />" >Verify My Phone</button>
                </c:when> 
                
                
                <c:when test="${ ValidationCode != null }">
                    <button id="validation_button" onclick = "get_validation_number('${User.getPhonenumber()}')" type="button" class="btn btn-warning" 
                       data-toggle="tooltip" 
                       data-placement="right" 
                       title="" data-original-title="To receive phone notifications you must verify your phone number first. You will get an automatic phone call where you have to enter verification number that will be shown here." 
                       href="<c:url value="" />" >Verify My Phone</button>
                </c:when> 
                
                
                </c:choose>
                
                <label>Password:</label>
                <p><input class="form-control" type="password" name="password" placeholder="To change password, type here" th:field="*{password}" /></p>
                <label>Confirm Password:</label>
                <p><input class="form-control" type="password" placeholder="Must match above" name="cpass" th:field="*{cpass}" /></p>
                
              </div>           
            <h3>Notification Settings</h3>
            <ul>
                <li><input type="checkbox" value="remember-me" th:field="*{remember}"> Remember me on login</li>
            <c:choose>            
                <c:when test="${ InvalidPhoneNumber == null }">
                     <li><input type="checkbox" value="sms" th:field="*{sms}"> Send notifications via phone (SMS)</li>
                </c:when>
                
            </c:choose>
            </ul>
            <button type="submit" class="btn btn-success">Save Changes</button>
        </form>
    </div> <!-- /container -->
 </body>
</html>           
