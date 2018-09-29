<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="header.jsp" />
<body>
    <jsp:include page="menu.jsp" />
    <div class="container">
        <h2>Start page</h2>
        <div class="alert alert-info">
            <strong>About this site!</strong> This is a test site for trying gradle, spring mvc in conjunction with mysql. Topic: cities, countries and populations
        </div>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>