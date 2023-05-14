<%@
        page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Готово!</title>
</head>
<body>
<div
        class="container"
        style="
        margin: 50px auto;
        width: 600px;
        height: fit-content;
        background-color: aquamarine;"
>
    <p style="font-size: 20px; color: #9c8686">Файл: ${fileName}</p>
    <p>
        ${fileData}
    </p>
</div>
</body>
</html>
