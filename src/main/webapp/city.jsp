<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

</head>
<body>

<script>
        function sendGreeting() {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/dreamjob/cities',
            dataType: 'json'
        }).done(function (data) {
            for (var city of data) {
                let li = "<li>" + city.name + "</li>";
                $('#cityList li:last').append(li);
            }
        }).fail(function (err) {
            console.log(err);
        });
    }
</script>

<table class="table">
    <title>Выбор города</title>
    <thead>
    <tr>
        <th scope="col">Индекс</th>
        <th scope="col">Название города</th>
    </tr>
    </thead>
    <tbody>

    </tbody>

</table>
</body>
</html>