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

    <br>

    <div id="carouselExample" class="carousel carousel-dark slide">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="https://i.pinimg.com/564x/11/55/b4/1155b41d9d00c8dd7d770a8e4d019f55.jpg" class="d-block w-100 mx-auto" style="max-width: 480px; max-height: 320px;" alt="...">
            </div>
            <div class="carousel-item">
                <img src="https://i.pinimg.com/564x/df/ea/4a/dfea4af684904ad725a7108ec17ea0f4.jpg" class="d-block w-100 mx-auto" style="max-width: 480px; max-height: 320px;" alt="...">
            </div>
            <div class="carousel-item">
                <img src="https://i.pinimg.com/564x/51/0d/f3/510df3b5f6b409b2cbfcb1b55257cb87.jpg" class="d-block w-100 mx-auto" style="max-width: 480px; max-height: 320px;" alt="...">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Предыдущий</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Следующий</span>
        </button>
    </div>

    <br>
    <hr>
    <br>

    <div class="container">
        <h5 class="text-center">
            Система оценки экипажа (Crew Evaluation System) или тесты CES онлайн – это комплекс тестов, направленный на проверку базовых знаний морских специалистов в областях, определенных Конвенцией ПДНВ. Комплекс заданий был разработан компанией Seagull (в последствии переименованной в Ocean Technologies Group) норвежцами в 1995 году, а онлайн версия теста появилась в 2010 в виде компьютерной обучающей программы. Данный тест очень популярен, и наверняка известен многим морякам. Системой тестирования CES для оценки компетентности состава пользуются более 350 компаний по всему миру.

            Каждый Seagull CES тест состоит из определенного числа модулей (CBT module или CBT CD), которые и составляют всё содержание этого теста. Например, CES тест для механиков может содержать в себе модули, касающиеся оборудования, механизмов и устройств судна, но помимо этого такой тест наверняка будет дополнен общими, базовыми модулями, ответы на которые должен знать каждый моряк – Fire-fighting, Survival, Firts Aid и т. д.
        </h5>
    </div>

    <br>
    <hr>
    <br>

    <div class="container">
        <div class="row align-items-center">
            <#list arrayListofLists as i>
                <div class="col-md-4 mb-3">
                    <div class="card" style="width: 18rem;">
                        <img src="${i[2]}" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">${i[0]}</h5>
                            <a href="/viewTest/${i[3]}" class="btn btn-primary">Просмотреть тест подробнее</a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>

    <br>

</body>
</html>