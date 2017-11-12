
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Home Page</title>
        <!-- Bootstrap core CSS -->
        <link href="bootstrap.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="homepage/homepage.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.bootcss.com/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>

    </head>

    <body class="body">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-dark navbar-inverse fixed-top" style="background-color: #00BFFF">
                <a class="navbar-brand" href="#">Gerenciador de Condomínios</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">

                            <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" class="navbar-brand"href="#">Informativos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Transparência</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Reservas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Visitas</a>
                        </li>
                    </ul>
                    <div>
                        <form class="form-inline my-2 my-lg-0" action="ServletLoginLogout" method="post" >  
                            <input type="hidden" name="ACTION" value="LOGOUT"/>
                            <input class="btn btn-info" type="submit" value="Sair">
                        </form>     
                    </div>
                </div>
            </nav>

            <main role="main">

                <div class="jumbotron">
                    <h1 class="display-3">Bem vindo</h1>
                    <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
                </div>

                <div class="row marketing">
                    <div class="col-lg-6">
                        <h4>Subheading</h4>
                        <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

                        <h4>Subheading</h4>
                        <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

                        <h4>Subheading</h4>
                        <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>

                        <h4>Subheading</h4>
                        <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
                    </div>

                    <div class="col-lg-6">
                        <h4>Subheading</h4>
                        <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

                        <h4>Subheading</h4>
                        <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

                        <h4>Subheading</h4>
                        <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>

                        <h4>Subheading</h4>
                        <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
                    </div>
                </div>

            </main>


        </div>

    </body> 
</html>
