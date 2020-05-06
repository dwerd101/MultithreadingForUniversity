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



<#if notNumbers??>

    <div class="jumbotron text-center">
        <div class="container">
            <h2 class="display-3">😢 Я не умею буквы сортировать...</h2>
            <p><a class="btn btn-primary btn-lg" href="/list_tasks/task/3" role="button">Вернуться назад</a></p>
        </div>
    </div>
<#else>
<#if listIsEmpty??>
    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">

        <div class="container">
            <div class="row">
                <div class="col">
                </div>
                <div class="col">
                </div>
                <div class="col">
                </div>
            </div>
            <form action="/list_tasks/task/3" method="post" >
                <div class="form-group">
                    <label for="formGroupExampleInput">Напишите,пожалуйста,числа!</label>
                    <input type="text" class="form-control text-center" id="formGroupExampleInput" placeholder="Введите через пробелы: '12 13', либо запятыми: '12,13', либо все и сразу: '12 ,13' или '12, 13' или '12 , 13'" name="text" required>
                    <small id="text2" class="form-text text-muted">Время многопоточности👍🏻</small>
                </div>
                <div class="form-group text-center">
                    <button type="submit" class="btn btn-primary ">Отправить</button>
                </div>
            </form>
        </div>
    </div>
<#else>
<#if list2??>

    <table class="table table-striped table-bordered table-responsive-sm text-center">
        <thead class="table-dark">
        <tr>
            <th scope="col">Сортировка пошла!☕</th>
        </tr>
        </thead>
        <tbody>
        <#list list3 as l>
        <tr>
            <td>${l}</td>
        </tr>
        </tbody>
        </#list>
        <tr>
            <td>Итого:&nbsp;
                <#list list2 as l>
                    ${l}&nbsp;
                </#list>
            </td>
        </tr>
    </table>

    <form action="/list_tasks/task/3" method="post">
        <div class="form-group text-center">
            <button type="submit" class="btn btn-primary ">Назад</button>
        </div>
    </form>
<#else>
<#if list1??>
<table class="table table-striped table-bordered table-responsive-sm text-center">
    <thead class="table-dark">
    <tr>
        <th scope="col">Вывод потоков</th>
    </tr>
    </thead>
    <tbody>
    <#list list1 as l>
    <tr>
        <td>${l}</td>
    </tr>
    </#list>
    </tbody>
</table>

<form action="/list_tasks" method="post">
    <div class="form-group text-center">
        <button type="submit" class="btn btn-primary">Назад</button>
    </div>
</form>
<#else>


<#if lists??>
    <table class="table table-striped table-bordered table-responsive-sm text-center">
        <thead class="table-dark">
        <tr>
            <th scope="col">Вывод потоков</th>
        </tr>
        </thead>

        <tbody>
        <#list lists as l>
        <tr>
            <td>${l}</td>
        </tr>
    </#list>
        </tbody>
    </table>

    <form action="/list_tasks/task/1" method="post">
    <div class="form-group text-center">
        <button type="submit" class="btn btn-primary ">Назад</button>
    </div>
    </form>
<#else>
<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">

    <div class="container">
        <div class="row">
            <div class="col">
            </div>
            <div class="col">
            </div>
            <div class="col">
            </div>
        </div>
<form action="/list_tasks/task/1" method="post" >
    <div class="form-group">
        <label for="formGroupExampleInput">Напишите, что хотите!</label>
        <input type="text" class="form-control text-center" id="formGroupExampleInput" placeholder="Введите текст" name="text" required>
        <small id="text2" class="form-text text-muted">Время многопоточности 👍🏻</small>
    </div>
    <div class="form-group text-center">
    <button type="submit" class="btn btn-primary ">Отправить</button>
    </div>
</form>
    </div>
</div>

</#if>
</#if>
</#if>
</#if>
</#if>
<#include "footer.ftl">

</body>
</html>