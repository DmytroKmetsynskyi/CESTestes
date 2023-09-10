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
        <a class="navbar-brand" href="/" >CES Tests 🛟</a>
    </div>
</nav>

<!--style="width: 18rem;"-->
<!--class="card-img"-->

<br><br><br>
<div class="container container">
    <div class="row">
        <div class="card ">
            <div class="card-body">
                <form action="/results/${fileName}" method="POST">
                    <#list questions as i>
                        <br><h3>${i[0]}</h3><br>

                        <#if i[1]??>
                            <img src="${i[1]}" style="border-radius: 2.5%;" class="mb-3 mx-auto" alt=""><br>
                        </#if>

                        <#list i[3] as i_>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="flexRadio_${i[2]}" value="${i_}" id="flexRadioDefault1">
                                <label class="form-check-label" for="flexRadioDefault1">
                                    ${i_}
                                </label>
                            </div>
                        </#list>
                    </#list>

                <br>
                <button type="submit" class="btn btn-success">Перевірити тест</button>

                </form>
            </div>
        </div>
    </div>
</div>
<br><br><br>

</body>
</html>