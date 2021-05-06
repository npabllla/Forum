<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Форум job4j</title>

    <style>
        .header {
            display: inline;
            float: right;
        }
        .btn-outline-danger{
            margin-left: 15px;
        }
        .card {
            margin-top: 25px;
        }
    </style>
</head>
<body>
<div class="container">
    <form style="margin-top: 15px">
        <h3>
            <a style="color: black" href="<c:url value='/'/>"> Форум job4j</a>
            <a class="header" style="font-size: medium">Текущий пользователь: ${user.username}</a>
        </h3>
        <a class="header" href="<c:url value='/logout'/>" style="margin-top: -15px">Сменить пользователя</a>
    </form>
    <form>
        <c:if test="${user.authority.authority == 'ROLE_ADMIN' || user == post.author}">
            <a class="btn btn-outline-secondary" href="<c:url value='/load?id=${post.id}'/>">Редактировать тему</a>
            <a class="btn btn-outline-danger" href="<c:url value='/delete?id=${post.id}'/>">Удалить тему</a>
        </c:if>
    </form>
    <form>
        <div class="card">
            <div class="card-header">
                <h5 style="float: left">Тема: <c:out value="${post.name}"/> (Автор: <c:out
                        value="${post.author.username}"/>)</h5>
                <h5 style="float: right">Дата изменения: <c:out value="${post.created}"/></h5>
            </div>
            <div class="card-body">
                <h4>Описание:</h4>
                <c:out value="${post.description}"/>
                <div style="margin-top: 25px">
                    <a class="btn btn-outline-primary" href="<c:url value='/comment?id=${post.id}'/>">Добавить
                        комментарий</a>
                </div>
                <c:forEach items="${comments}" var="comment">
                    <div class="card">
                        <div class="card-header">
                            <h6 style="float: left">
                                Комментарий от <c:out value="${comment.author.username}"/>'а
                            </h6>
                            <h6 style="float: right">
                                Дата создания: <c:out value="${comment.created}"/>
                            </h6>
                        </div>
                        <div class="card-body">
                            <h5>Содержание:</h5>
                            <c:out value="${comment.content}"/>
                            <div style="margin-top: 25px">
                                <a class="btn btn-outline-primary" href="<c:url value='/answer?id=${comment.id}'/>">
                                    Ответить</a>
                                <c:if test="${user.authority.authority == 'ROLE_ADMIN'}">
                                    <a class="btn btn-outline-danger" href="<c:url value='/deleteComment?id=${comment.id}'/>">
                                        Удалить комментарий</a>
                                </c:if>
                                <c:forEach items="${comment.answers}" var="answer">
                                    <div class="card">
                                        <div class="card-header">
                                            <a style="float: left">
                                                Ответ от <c:out value="${answer.author.username}"/>'а</a>
                                            <a style="float: right">
                                                Дата создания: <c:out value="${answer.created}"/></a>
                                        </div>
                                        <div class="card-body">
                                            <c:out value="${answer.content}"/>
                                            <c:if test="${user.authority.authority == 'ROLE_ADMIN'}">
                                                <a class="btn btn-outline-danger" style="float: right"
                                                   href="<c:url value='/deleteAnswer?id=${answer.id}'/>">
                                                    Удалить ответ</a>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </form>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>