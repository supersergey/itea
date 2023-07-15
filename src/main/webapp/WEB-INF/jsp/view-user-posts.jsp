<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!doctype html>
<html lang="en" data-bs-theme="light">
<head>
    <title>View Posts</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-md fixed-top bg-body-tertiary border-bottom border-bottom-dark">
    <div class="container-fluid">
        <span class="navbar-brand">Java Web</span>
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

<div class="container-fluid" style="margin-top: 80px">
    <c:choose>
        <c:when test="${not empty errorMessage}">
            <div class="alert alert-danger" role="alert">${errorMessage}</div>
        </c:when>
        <c:otherwise>
            <div>
                <form action="posts" method="POST">
                    <h1 class="text-body-emphasis">Write new post</h1>
                    <div class="mb-3">
                        <label for="title" class="form-label">Title</label>
                        <input type="text" class="form-control" id="title" name="postTitle" placeholder="Write title">
                    </div>
                    <div class="mb-3">
                        <label for="body" class="form-label">Body</label>
                        <textarea class="form-control" id="body" name="postBody" rows="3"></textarea>
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary mb-3">Submit</button>
                    </div>
                </form>
                <c:if test="${not empty emptyFieldsErrorMessage}">
                    <div class="alert alert-danger" role="alert">${emptyFieldsErrorMessage}</div>
                </c:if>
            </div>
            <div class="list-group">
                <c:forEach items="${posts}" var="post">
                    <div class="list-group-item list-group-item-action">
                        <div class="card">
                            <div class="card-header">${post.title}</div>
                            <div class="card-body">
                                <p class="card-text">${post.body}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>