<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="../header.jsp" />
<body>

<div class="container">
    <jsp:include page="../menu.jsp" />
    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert"
                    aria-label="Close">
                <span aria-hidden="true">×</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h1>All Cities</h1>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>#ID</th>
            <th>Name</th>
            <th>District</th>
            <th>Population</th>
        </tr>
        </thead>

        <c:forEach var="city" items="${listCity}">
            <tr>
                <td>
                        ${city.id}
                </td>
                <td>${city.name}</td>
                <td>${city.district}</td>
                <td>
                        ${city.population}
                </td>
                <td>
                    <spring:url value="/city/${city.id}" var="cityUrl" />
                    <spring:url value="/city/${city.id}/delete" var="deleteUrl" />
                    <spring:url value="/city/${city.id}/update" var="updateUrl" />

                    <button class="btn btn-info" onclick="location.href='${cityUrl}'">Query</button>
                    <button class="btn btn-primary"
                            onclick="location.href='${updateUrl}'">Update</button>
                    <button class="btn btn-danger"
                            onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>

<jsp:include page="../footer.jsp" />

</body>
</html>