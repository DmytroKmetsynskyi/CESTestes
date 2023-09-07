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

    <div class="container container">
        <div class="row">
            <div class="col align-items-center">



            </div>
        </div>
    </div>


    <div class="container">
        <div class="row align-items-center">
            <div class="col">

            </div>

            <div class="col">
                <#list arrayListofLists as i>
                <br>
                    <div class="card" style="width: 18rem;">
                        <img src="${i[2]}" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">${i[0]}</h5>
                            <p class="card-text">${i[1]}</p>
                            <a href="/viewTest/${i[3]}" class="btn btn-primary">Переглянути тест детальніше</a>
                        </div>
                    </div>
                <br>
                </#list>
            </div>

            <div class="col">

            </div>
        </div>
    </div>

</body>
</html>