<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="header.jsp" />

<body>

<div class="container">
    <h1>Error Page</h1>
    <div class="alert alert-info">
        <strong>Error!</strong> ${errorMsg}
    </div>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>