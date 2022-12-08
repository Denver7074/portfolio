<!DOCTYPE html>
<html>
<head>
    <title>Информация об учителе</title>
</head>
<body>
<h3>Подробная информация об учителе ${user.name}</h3>
<b>Email: </b> ${user.email}<br>
<h4>Ученики ${user.name}</h4>
<#list students as student>
    <div>
        <p><b>${student.FIO}</b> ${student.age} лет | <a href="/student/${student.id}">Подробнее...</a></p>
    </div>
<#else>
    <h3>Учеников нет</h3>
</#list>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>