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
    <c:choose>
        <c:when test="${cityForm['new']}">
            <h1>Add City</h1>
        </c:when>
        <c:otherwise>
            <h1>Update City</h1>
        </c:otherwise>
    </c:choose>
    <br />

    <spring:url value="/city" var="cityActionUrl" />

    <form:form class="form-horizontal" method="post"
               modelAttribute="cityForm" action="${cityActionUrl}">

        <form:hidden path="id" />

        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Name</label>
                <div class="col-sm-10">
                    <form:input path="name" type="text" class="form-control"
                                id="name" placeholder="Name" />
                    <form:errors path="name" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="district">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">District</label>
                <div class="col-sm-10">
                    <form:input path="district" class="form-control"
                                id="district" placeholder="District" />
                    <form:errors path="district" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="population">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Population</label>
                <div class="col-sm-10">
                    <form:input path="population" class="form-control"
                                id="population" placeholder="Population" />
                    <form:errors path="population" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${cityForm['new']}">
                        <button type="submit" class="btn-lg btn-primary pull-right">Add
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn-lg btn-primary pull-right">Update
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</div>

<jsp:include page="../footer.jsp" />

</body>
</html>