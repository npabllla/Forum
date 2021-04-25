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
    </style>
</head>
<body>
<div class="container">
    <form style="margin-top: 15px">
        <h3>
            <a style="color: black" href="<c:url value='/'/>"> Форум job4j</a>
            <a class="header" style="font-size: medium">Текущий пользователь: ${user.name}</a>
        </h3>
        <a class="header" href="<c:url value='/login'/>" style="margin-top: -15px">Сменить пользователя</a>
    </form>
    <form>
        <a class="btn btn-outline-secondary" href="<c:url value='/load?id=${post.id}'/>">Редактировать тему</a>
    </form>
    <form style="margin-top: 10px">
        <div class="card">
            <div class="card-header">
                <h5 style="float: left">Тема: <c:out value="${post.name}"/> (Автор: <c:out value="${post.author.name}"/>)</h5>
                <h5 style="float: right">Дата изменения: <c:out value="${post.created}"/></h5>
            </div>
            <div class="card-body">
                <h4>Описание:</h4>
                <c:out value="${post.description}"/>
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