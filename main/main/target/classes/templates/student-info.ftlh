<!DOCTYPE html>
<html>
<head>
    <title>Подробнее</title>
</head>
<body>
<h1>Портфолио</h1><hr>
<h4>Подробная информация об ученике</h4>

<#list images as img>
    <img src="/images/${img.id}" height="60px"/><br><br>
</#list>
<b>ФИО: </b>${student.FIO}<br>
<b>Класс: </b>${student.grade}<br>
<b>Возраст: </b>${student.age}<br>
<b>Адрес: </b>${student.address}<br>
<b>Количество достижений: </b>${student.contests}<br>
<b>Классный руководитель: </b><a href="/user/${student.user.id}">${student.user.name}</a><br>
<hr>
<form action="/student/delete/${student.id}" method="post">
    <input type="submit" value="Удалить ученика"/>
</form>
</body>
</html>