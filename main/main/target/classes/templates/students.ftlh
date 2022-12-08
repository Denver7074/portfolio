<!DOCTYPE html>
<html>
<head>
    <title>Главная страница</title>
</head>
<body>
<h1>Ученики</h1><hr>
<#if user.email??>
    <h3>Имя пользователя: <i>${user.name}</i></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти"/>
    </form>
<#else>
    <a href="/login">Войти</a></h1>
</#if>
<form action="/" method="get">
    Поиск по ФИО: <input type="text" name="FIO"><br>
    <input type="submit" value="Поиск"/>
</form>
<#list students as student>
    <div>
        <p><b>${student.FIO}</b> ${student.age} лет | <a href="/student/${student.id}">Подробнее...</a></p>
    </div>
<#else>
<h3>Учеников нет</h3>
</#list>
<#if user.email??>
<hr>
<h3>Добавить ученика</h3>
<form action="/student/create" method="post" enctype="multipart/form-data"c>
    ФИО: <input type="text" name="FIO"/><br><br>
    Класс: <input type="text" name="grade"/><br><br>
    Возраст: <input type="number" name="age"/><br><br>
    Адрес: <input type="text" name="address"/><br><br>
    Количество достижений: <input type="number" name="contests"/><br><br>
    Первая фотография: <input type="file" name="file1"/><br><br>
    Вторая фотография: <input type="file" name="file2"/><br><br>
    Третья фотография: <input type="file" name="file3"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить ученика"/>
</form>
</#if>
</body>
</html>


