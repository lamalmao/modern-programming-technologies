<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Ошибка</title>
</head>
<body>
<div
        class="container"
        style="
        background-color: beige;
        width: 500px;
        height: 400px;
        margin: 0 auto;
      "
>
    <p style="color: brown"><%= request.getAttribute("message") %></p>
</div>
</body>
</html>
