<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<jsp:include page="../header.jsp"/>
<body>

<div class="container">
    <c:choose>
        <c:when test="${cityForm['new']}">
            <h1>Add City</h1>
            <c:set value="post" var="methodName"/>
        </c:when>
        <c:otherwise>
            <h1>Update City</h1>
            <c:set value="put" var="methodName"/>
        </c:otherwise>
    </c:choose>
    <br/>

    <spring:url value="/city/" var="cityActionUrl"/>

    <form:form class="form-horizontal" method="${methodName}" modelAttribute="cityForm" action="${cityActionUrl}">

        <form:hidden path="id"/>

        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Name</label>
                <div class="col-sm-10">
                    <form:input path="name" type="text" class="form-control"
                                id="name" placeholder="Name"/>
                    <form:errors path="name" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="district">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">District</label>
                <div class="col-sm-10">
                    <form:input path="district" class="form-control"
                                id="district" placeholder="District"/>
                    <form:errors path="district" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="population">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Population</label>
                <div class="col-sm-10">
                    <form:input path="population" class="form-control"
                                id="population" placeholder="Population"/>
                    <form:errors path="population" class="control-label"/>
                </div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${cityForm['new']}">
                        <button type="button" class="btn-lg btn-primary pull-right" id="idSaveCity">Add
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn-lg btn-primary pull-right" id="idUpdateCity">Update
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</div>

<jsp:include page="../footer.jsp"/>

<script>
    $(document).ready(function () {
        $("#idUpdateCity").click(function () {
            var cityForm = {
                id: $("#id").val(),
                name: $("#name").val(),
                district: $("#district").val(),
                population: $("#population").val()
            };
            $.ajax({
                url: "${cityActionUrl}",
                type: "${methodName}",
                data: JSON.stringify(cityForm),
                contentType:"application/json; charset=utf-8",
                success: function (data) {
                    $('#container').html(data);
                }
            });
        });


    });
</script>

</body>
</html>