<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">                                   
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="<c:url value="/" />"><strong>Buzz</strong>Board</a>
        </div>
        <div class="navbar-collapse collapse">  
          <ul class="nav navbar-nav">
           <li ><a href="<c:url value="/topics/" />">Browse Topics</a></li>
<c:choose>
    <c:when test="${sessionScope.email != null}">
           <li ><a href="<c:url value="/" />">My Topics</a></li>
           <li ><a href="<c:url value="/profile/" />">My Profile</a></li>
           <li ><a href="<c:url value="/logout" />">Logout</a></li> 
    </c:when>

    <c:otherwise>
           <li class=""><a href="<c:url value="/login" />">Login</a></li>
           <li class="active"><a href="<c:url value="/signup/" />">Signup</a></li>
    </c:otherwise>
</c:choose>
           
          </ul>
        </div>   			      		 
  </div>
</div>