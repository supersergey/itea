<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!doctype html>
<html lang="en" data-bs-theme="light">
<head>
    <title>View User's Posts</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-md fixed-top bg-body-tertiary border-bottom border-bottom-dark">
    <div class="container-fluid">
        <span class="navbar-brand">Demo Web</span>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/users">Users</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid py-5">

    <h1 class="text-body-emphasis">This is a JSP Demo</h1>
    <p class="lead">
        <c:if test="${not empty errorMessage}">
    <div class="alert alert-danger" role="alert">${errorMessage}</div>
    </c:if>
    <div class="container-fluid py-5">
        <div class="p-5 text-center bg-body-tertiary rounded-3 ">
            <form action="posts" method="POST">
                <h1 class="text-body-emphasis">Add post</h1>
                <div class="mb-3">
                    <label for="title" class="form-label">Title</label>
                    <input type="text" class="form-control" id="title" name="title"
                           placeholder="Title">
                </div>
                <div class="mb-3">
                    <label for="body" class="form-label">Body</label>
                    <input type="text" class="form-control" id="body" name="body" placeholder="Body">
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary mb-3 btn-sm">Add post</button>
                </div>
            </form>
        </div>
    </div>
    <div class="p-5 mb-4 bg-body-tertiary rounded-3">
    <table class="table table-stripped table-hover">
        <thead>
        <tr>
            <th scope="col">Title</th>
            <th scope="col">Body</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${posts}" var="posts">
            <tr>
                <td>${posts.title}</td>
                <td>${posts.body}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </p>
</div>
</div>
</body>
</html>