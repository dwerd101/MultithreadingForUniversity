<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>
<body>

<#--Шапка -->
<#include "header.ftl">

    <table class="table table-striped table-bordered table-responsive-sm text-center">
        <thead class="table-dark">
        <tr>
            <th scope="col" >#</th>
            <th scope="col">Cписок Задач</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td></td>
            <th scope="row">Легкие задания</th>
        </tr>
        <tr>
            <th scope="row">1</th>
            <td><a href="/list_tasks/1">Основной поток создает дочерний. Родительский и дочерний потоки должны распечатать по десять строк текста.</a></td>
        </tr>
        <tr>
            <td></td>
            <th scope="row">Обычная сложность</th>
        </tr>
        <tr>
            <th scope="row">2</th>
            <td> <a href="/list_tasks/2">Основной поток создает дочерний. Родительский и дочерний потоки должны распечатать по десять строк текста. При этом вывод должен быть синхронизирован таким образом, чтобы родительский и дочерний потоки выводили строки строго по очереди.</a></td>
        </tr>
        <tr>
            <td></td>
            <th scope="row">Повышенная сложность</th>
            </tr>
        <tr>
            <th scope="row">3</th>
            <td><a href="/list_tasks/3">Создать распараллеливающийся алгоритм сортировки одномерного массива.</a></td>
        </tr>
        </tbody>
    </table>

<#include "footer.ftl">

</body>
</html>