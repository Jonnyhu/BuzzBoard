<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Browse Topics</title>
    </head>
    <body>
        <c:choose>
    <c:when test="${sessionScope.email != null}">
        <a href='<c:url value="/topics/new" />' class="btn btn-success btn-lg">Post a new topic...</a>
        <hr/>
    </c:when>

    <c:otherwise>
    </c:otherwise>
</c:choose>
        <div class="container well">
          
        <form role="form" action="<c:url value="/topics/searchresult" />" method="GET">
 
   
          <div class="form-group">
            <label class="control-label">Search Topics</label>
            <div class="input-group">
                <span class="input-group-addon"></span>
                <input type="text" class="form-control" name="topickey" placeholder="">
                <span class="input-group-btn">
                   <button type="submit" class="btn btn-success right ">Search Topics</button>
                </span>
            </div>
            </div>
        </form>
          
        </div>
        <div class="container well">
        <h1>Browse Topics</h1>
        <c:import url="/WEB-INF/views/topics/table.jsp" />
        </div>
        
    </body>
</html>
