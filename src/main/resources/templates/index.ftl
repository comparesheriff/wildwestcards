<!DOCTYPE HTML>
<html lang="de">
<head>
    <title>
        Wild West Card Game
    </title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <meta name="theme-color" content="#ffffff">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="/js/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="/js/popper.min.js"></script>


    <!-- Datatables Css -->
    <link rel="stylesheet" type="text/css" href="/css/datatables.css">
    <link rel="stylesheet" type="text/css" href="/css/responsive.bootstrap4.css">


    <!-- Bootstrap JavaScript -->
    <script src="/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="/css/footer.css">

    <#if ["register"]?seq_contains(model.page)>
        <script type="text/javascript" src="/js/${model.page}.js"></script>
    </#if>

</head>
<body>
<div class="container">
    <section id="header">
        <div class="container">
            <div class="row text-center text-xs-center text-sm-left text-md-left">
                <div class="col-xs-12 col-sm-4 col-md-4">
                    <h5><a href="/">Home</a></h5>
                </div>
            </div>
        </div>
    </section>
</div>

<#include "./"+model.page+".ftl" >

<div class="container">
    <section id="footer">
        <div class="container">
            <div class="row text-center text-xs-center text-sm-left text-md-left">
                <div class="col-xs-12 col-sm-4 col-md-4">
                    <h5>About</h5>
                    <ul class="list-unstyled quick-links">
                        <li><a href="/c/about"><i class="fa fa-angle-double-right"></i>About the game</a></li>
                        <li><a href="/c/imprint"><i class="fa fa-angle-double-right"></i>Imprint</a></li>
                        <li><a href="/c/privacy"><i class="fa fa-angle-double-right"></i>Privacy</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </section>
</div>

</body>
</html>
