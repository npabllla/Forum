<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>

    <title>Вход</title>

    <style>
        .card {
            margin: 0 auto;
            width: 400px;
            height: 375px
        }
        .form-group {
            width: 300px;
        }
    </style>
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <div class="card">
            <div class="card-header">
                <h4>Вход</h4>
            </div>
            <div class="card-body">
                <form class="form" name='login' action="<c:url value='/login'/>" method='POST'>
                    <div class="form-group">
                        <h6>Почта</h6>
                        <input id="email" type='text' class="form-control" name='email'
                               placeholder="Введите вашу почту">
                    </div>
                    <div class="form-group">
                        <h6>Пароль</h6>
                        <input id="password" type='password' class="form-control" name='password'
                               placeholder="Введите ваш пароль"/>
                    </div>
                    <button name="submit" type="submit" class="btn btn-primary" style="margin-top: 15px">Вход</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
                <form action="<c:url value='/reg'/>">
                    <button class="btn btn-primary">Регистариция</button>
                    <label id="wrongUser" style="color: red">
                        <c:if test="${not empty error}">
                            ${error}
                        </c:if>
                    </label>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>