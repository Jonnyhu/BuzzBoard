<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>BuzzBoard</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="<c:url value="/resources/css/bootstrap.min.css" />"
        rel="stylesheet"  type="text/css" />
  <link href="<c:url value="/resources/css/main.css" />"
        rel="stylesheet"  type="text/css" />
</head>

<body>
    <div id="wrap">

        <c:import url="/WEB-INF/views/tags/navbar.jsp"/>

        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <!--<div class="jumbotron">
                      <c:import url="/WEB-INF/views/tags/banner.jsp"/>
                    </div>-->

                    <div class="row">
                      <div class="col-md-12">

                        <hr class="soften">

                        <decorator:body />

                      </div>
                    </div><!--/col-->
                </div><!--/row-->
            </div><!--/col-->
          </div><!--/row-->

          <hr class="soften">
    </div>

    <c:import url="/WEB-INF/views/tags/footer.jsp"/>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/boot.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/test.js" />"></script>
</body>
</html>
