<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CES testes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/" >CES Testes</a>
    </div>
</nav>

<div class="container">
    <div class="row align-items-center mt-5">
        <div class="col">

        </div>
        <div class="col">
            <h2>Видалення тесту</h2>

            <br><br><br>

        <form method="post" action="/deleteTestFile" enctype="multipart/form-data">
            <#list fileList as i>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="${i}" id="flexCheckDefault" name="fileToDelete">
                <label class="form-check-label" for="flexCheckDefault">
                    ${i}
                </label>
            </div>
        </#list>

            <button class="btn btn-success" type="submit" id="button-addon1">Видалити тестування</button>
        </form>

        </div>
        <div class="col">

        </div>
    </div>
</div>




</body>
</html>