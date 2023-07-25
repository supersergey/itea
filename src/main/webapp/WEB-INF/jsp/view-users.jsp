<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!doctype html>
<html lang="en" data-bs-theme="light">
<head>
    <title>View Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<div class="container-fluid py-5">
    <div class="p-5 mb-4 bg-body-tertiary rounded-3">
        <h1 class="text-body-emphasis">This is a JSP Demo</h1>
        <p class="lead">
        <table class="table table-stripped table-hover">
            <thead>
            <tr>
                <th scope="col">First Name</th>
                <th scope="col">Last name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.lastName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </p>
    </div>

    <div class="container-fluid py-5">
        <div class="p-5 text-center bg-body-tertiary rounded-3">
            <form action="users" method="POST">
                <h1 class="text-body-emphasis">Add users</h1>
                <div class="mb-3">
                    <label for="userNameInput" class="form-label">Name</label>
                    <input type="text" class="form-control" id="userNameInput" name="firstName" placeholder="John">
                </div>
                <div class="mb-3">
                    <label for="userLastNameInput" class="form-label">Last name</label>
                    <input type="text" class="form-control" id="userLastNameInput" name="lastName"
                           placeholder="Smith">
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary mb-3">Submit</button>
                </div>
            </form>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger" role="alert">${errorMessage}</div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
