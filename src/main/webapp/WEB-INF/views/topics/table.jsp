<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
    <c:when test="${not empty topics}">
        <table class="table table-striped">
            <thead>
                <th>Title</th>
                <th>Author</th>
                <th>Posted date</th>
                <th></th>
            </thead>

            <tbody>
                <c:forEach items="${topics}" var="item">
                    <tr>
                    <td>${item.title}</td>
                    <td>
                        <a  href="<c:url value='/profile/'/>${item.author}">${item.author}</a>
                    </td>
                    <td>${item.timestamp}</td>
                    <td><a class="btn btn-success" href="<c:url value="/topics/"/>${item.id}">View</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>

    <c:otherwise>
        <h3>No topics to display.</h3>
    </c:otherwise>
</c:choose>