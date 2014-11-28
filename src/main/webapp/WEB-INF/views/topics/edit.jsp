<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Topic</title>
    </head>
    <body>
         <div class="well bs-component container"> 
        <form class="form-horizontal" action="#" method="post">
            <fieldset>
                <legend>Edit Topic</legend>
                <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">Title</label>
                    <div class="col-lg-10">
                        <div class="form-control-wrapper"><input type="text" name="title" value="${topic.getTitle()}" th:field="*{title}" class="form-control empty" id="inputEmail" placeholder="Topic Title"><span class="material-input"></span></div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="col-lg-2 control-label">Content</label>
                    <div class="col-lg-10">
                        <div class="form-control-wrapper"><textarea rows="20" type="text" name="content" th:field="*{content}" class="form-control empty" id="inputPassword" >${topic.getContent()}</textarea><span class="material-input"></span></div>
                       
                    </div>
                </div>
                
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button type="submit" class="btn btn-primary">Update</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </form>
    </div>
    </body>
</html>
