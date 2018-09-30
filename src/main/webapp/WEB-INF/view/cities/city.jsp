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

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert"
                    aria-label="Close">
                <span aria-hidden="true">×</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h1>City Detail</h1>
    <br />

    <div class="row">
        <label class="col-sm-2">ID</label>
        <div class="col-sm-10">${city.id}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Name</label>
        <div class="col-sm-10">${city.name}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">District</label>
        <div class="col-sm-10">${city.district}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Population</label>
        <div class="col-sm-10">${city.population}</div>
    </div>

</div>

<jsp:include page="../footer.jsp" />

</body>
</html>