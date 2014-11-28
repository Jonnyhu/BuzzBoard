<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Topic</title>
    </head>
    <body>
        <div class="container well bs-component container">   
            <h1>${topic.getTitle()}</h1>
            <h3>by <a  href="<c:url value='/profile/'/>${topic.getAuthor()}">${topic.getAuthor()}</a></h3>
            <hr/>
            <h4>${topic.getContent()}</h4>
        <table class="table table-striped">
            <tr>
                <th>
                                 
                    <c:choose>
                    <c:when test="${sessionScope.username != null && sessionScope.username != topic.getAuthor()}">
                    <form role="form" action="<c:url value="/topics/subscribeUser" />" method="POST">
                            <input type="hidden" class="form-control" name="userId" value="${sessionScope.id}">
                            <input type="hidden" class="form-control" name="topicId" value="${topicid}">
                            <input type="hidden" class="form-control" name="action" value="${isUserSubscribed}">
                            <input type="hidden" class="form-control" name="author" value="${topic.getAuthor()}"
                            <br/>
                            <c:choose>
                                <c:when test="${isUserSubscribed}">
                                       <button type="submit" class="btn btn-lg btn-danger pull-right">Unsubscribe From ${topic.getAuthor()}</button>
                                </c:when>

                                <c:otherwise>
                                       <button type="submit" class="btn btn-lg btn-danger pull-right">Subscribe To ${topic.getAuthor()}</button>
                                </c:otherwise>
                            </c:choose>
                            
                    </form>
                    </c:when>
                    </c:choose>
                </th>
            </tr>
            
            <c:forEach items="${replies}" var="replies">
                <tr>
                    <td>
                          <div class="panel panel-default">
                            <div class="panel-body">
                                ${replies.getContent()} <br/> <a  href="<c:url value='/profile/'/>${replies.getAuthor()}">${replies.getAuthor()}</a> 
                                ${replies.getTimestamp()}
                            </div>
                        </div>
                      
                    
                    </td>
                </tr>
            </c:forEach>

            <c:choose>
            <c:when test="${sessionScope.username != null}">
                <c:if test="${topic.getState() != 'CLOSED'}">
                    <tr>
                        <td>
                            <form role="form" action="<c:url value="/topics/reply" />" method="POST">
                                <textarea class="form-control" name="content" rows="3"></textarea>
                                <input type="hidden" class="form-control" name="author" value="${sessionScope.username}">
                                <input type="hidden" class="form-control" name="topicId" value="${topicid}">
                                <br/>
                                <button type="submit" class="btn btn-lg btn-success pull-right">Reply</button>
                            </form>                        
                        </td>
                    </tr>
                </c:if>
                <tr>
                    <td>
                        <form role="form" action="<c:url value="/topics/subscribe" />" method="POST">
                            <input type="hidden" class="form-control" name="userId" value="${sessionScope.id}">
                            <input type="hidden" class="form-control" name="topicId" value="${topicid}">
                            <input type="hidden" class="form-control" name="action" value="${isSubscribed}">
                            <br/>
                        <c:choose>
                            <c:when test="${isSubscribed}">
                                   <button type="submit" class="btn btn-lg btn-danger pull-right">Unsubscribe from topic</button>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${sessionScope.username != topic.getAuthor()}">
                                        <button type="submit" class="btn btn-lg btn-danger pull-right">Subscribe to topic</button>
                                    </c:when>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                        </form>
                    </td>
                </tr>
                <c:if test="${sessionScope.username == topic.getAuthor()}">
                    <tr>
                        <td>
                            <form role="form" action="<c:url value="/topics/delete" />" method="POST">
                                <input type="hidden" class="form-control" name="userId" value="${sessionScope.id}">
                                <input type="hidden" class="form-control" name="topicId" value="${topicid}">
                                <br/>
                                <button type="submit" class="btn btn-lg btn-success pull-right">Delete Topic</button>
                            </form>

                            <c:if test="${topic.getState() != 'CLOSED'}">
                                <form role="form" action="<c:url value="/topics/close" />" method="POST">
                                    <input type="hidden" class="form-control" name="userId" value="${sessionScope.id}">
                                    <input type="hidden" class="form-control" name="topicId" value="${topicid}">
                                    <br/>
                                    <button type="submit" class="btn btn-lg btn-success pull-right">Close Topic</button>
                                </form>
                                    
                                <a href="<c:url value="/topics/edit/" />${topicid}" class="btn btn-lg btn-success pull-right">Edit Topic</button>
                            </c:if>
                        </td>
                    </tr>
                </c:if>
            </c:when>
            <c:otherwise>
                <tr>
                    <td>
                          <div class="jumbotron">
                            <h2>Join BuzzBoard today to respond to this topic!</h2>
                            <p><a class="btn btn-primary" href="<c:url value="/signup/" />">Signup today</a> | <a class="btn btn-success"  href="<c:url value="/login/" />">Login</a></p>
                        </div>
                        
                        
                    </td>
                </tr>
            </c:otherwise>
            </c:choose>
        </tbody>
        </table>
        </div>
    </body>
</html>
