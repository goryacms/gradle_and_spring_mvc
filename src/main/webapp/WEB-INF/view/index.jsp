<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<header>
    <link href="webjars/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
</header>

<body>

<div class="container">


    <h1>All Users</h1>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>#ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>framework</th>
            <th>Action</th>
        </tr>
        </thead>

            <tr>
                <td>
                        dsafsdfsdfsdf
                </td>
                <td></td>
                <td></td>
                <td>

                </td>
                <td>
                    <spring:url value="/users/1" var="userUrl" />
                    <spring:url value="/users/1/delete" var="deleteUrl" />
                    <spring:url value="/users/1/update" var="updateUrl" />

                    <button class="btn btn-info"
                            onclick="location.href='${userUrl}'">Query</button>
                    <button class="btn btn-primary"
                            onclick="location.href='${updateUrl}'">Update</button>
                    <button class="btn btn-danger"
                            onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
                </td>
            </tr>

    </table>

</div>


</body>
</html>