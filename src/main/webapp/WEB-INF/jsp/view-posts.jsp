<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!doctype html>
<html lang="en" data-bs-theme="light">
<head>
    <title>View Posts</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="container text-center">

    <div class="row gy-5">
        <div class="col-4">
            <div class="p-3">
                <b>
                    Post title
                </b>
            </div>
        </div>
        <div class="col-8">
            <div class="p-3">
                <b>
                    Post body
                </b>
            </div>
        </div>
    </div>

    <c:forEach items="${posts}" var="post">
          <div class="row gy-5">
                <div class="col-4">
                    <div class="p-3">
                        ${post.title}
                    </div>
                </div>
                <div class="col-8">
                    <div class="p-3">
                        ${post.body}
                    </div>
                </div>
          </div>
    </c:forEach>
</div>
<div class="container-fluid py-5">
        <div class="p-5 text-center bg-body-tertiary rounded-3">
            <form action="posts" method="POST">
                <h1 class="text-body-emphasis">Add post</h1>
                <div class="mb-3">
                    <input type="text" class="form-control" id="postTitleInput" name="title" placeholder="Title">
                </div>
                <div class="mb-3">
                    <input type="text" class="form-control" id="postBodyInput" name="body"
                           placeholder="Body">
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary mb-3">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>