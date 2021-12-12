<%@ page import="ru.job4j.dream.store.DbStore" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>

</head>
<body>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script>
    function sendGreeting() {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/dreamjob/city.do',
            data: JSON.stringify({
                name: $('#ct').val()
            }),
            dataType: 'json'
        }).done(function (data) {
            $('#cityList li:last').append(`<li>${city.name}</li>`)
        }).fail(function (err) {
            console.log(err);
        });
    }

    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/dreamjob/city.do',
            dataType: 'json'
        }).done(function (data) {
            for (var city of data) {
                $('#cityList li:last').append(`<li>${city.name}</li>`)
            }
        }).fail(function (err) {
            console.log(err);
        });
    });
</script>

<div class="container">
    <form>
        <div class="form-group">
            <label for="ct">Добавить новый город</label>
            <input type="city" class="form-control" id="ct" aria-describedby="cityHelp"
                   placeholder="Ввести город">
        </div>
        <button type="button" class="btn btn-primary" onclick="sendGreeting()">Enter</button>
    </form>
    <br>
    Города:
    <ul id="cityList">
        <li></li>
    </ul>
</div>

</body>
</html>