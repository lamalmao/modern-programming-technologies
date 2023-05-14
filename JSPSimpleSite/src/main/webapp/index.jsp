<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Загрузка данных</title>
</head>
<body>
<div class="content" style="margin: 15% auto; width: 24%">
    <form action="addStudent" method="post">
        <input type="text" name="surname" placeholder="Фамилия"/>
        <input type="text" name="name" placeholder="Имя"/>
        <input type="text" name="patronymic" placeholder="Отчество"/>
        <hr/>
        <p></p>
        <input type="text" name="subject" placeholder="Предмет"/>
        <input type="text" name="grade" placeholder="Оценка"/>
        <input type="submit" value="Сохранить"/>
    </form>
</div>
</body>
</html>
