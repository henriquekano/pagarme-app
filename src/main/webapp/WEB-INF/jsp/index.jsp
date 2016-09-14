<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

	<head>
	
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	
	    <title>Shop Homepage - Start Bootstrap Template</title>
	
	    <!-- Bootstrap Core CSS -->
	    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
	
	    <!-- Custom CSS -->
	    <link href="/resources/css/shop-homepage.css" rel="stylesheet">
	
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	
	</head>

	<body>
	
	    <!-- Navigation -->
	    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	        <div class="container">
	            <!-- Brand and toggle get grouped for better mobile display -->
	            <div class="navbar-header">
	                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	                    <span class="sr-only">Toggle navigation</span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                    <span class="icon-bar"></span>
	                </button>
	                <a class="navbar-brand" href="#">Start Bootstrap</a>
	            </div>
	            <!-- Collect the nav links, forms, and other content for toggling -->
	            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	                <ul class="nav navbar-nav">
	                    <li>
	                        <a href="#">About</a>
	                    </li>
	                    <li>
	                        <a href="#">Services</a>
	                    </li>
	                    <li>
	                        <a href="#">Contact</a>
	                    </li>
	                </ul>
	            </div>
	            <!-- /.navbar-collapse -->
	        </div>
	        <!-- /.container -->
	    </nav>
	
	    <!-- Page Content -->
	    <div class="container">
	
	        <div class="row">
	
	            <div class="col-md-12">
	
	                <div class="row carousel-holder">
	
	                    <div class="col-md-12">
	                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
	                            <ol class="carousel-indicators">
	                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
	                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
	                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
	                            </ol>
	                            <div class="carousel-inner">
	                                <div class="item active">
	                                    <img class="slide-image" src="http://placehold.it/800x300" alt="">
	                                </div>
	                                <div class="item">
	                                    <img class="slide-image" src="http://placehold.it/800x300" alt="">
	                                </div>
	                                <div class="item">
	                                    <img class="slide-image" src="http://placehold.it/800x300" alt="">
	                                </div>
	                            </div>
	                            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
	                                <span class="glyphicon glyphicon-chevron-left"></span>
	                            </a>
	                            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
	                                <span class="glyphicon glyphicon-chevron-right"></span>
	                            </a>
	                        </div>
	                    </div>
	
	                </div>
	
	                <div class="row">
	                	<c:forEach var="index" begin="1" end="5">
	                		<div class="col-sm-4 col-lg-4 col-md-4">
		                        <div class="thumbnail">
		                            <img src="http://placehold.it/320x150" alt="">
		                            <div class="caption">
		                                <h4 class="pull-right">$24.99</h4>
		                                <h4><a href="#">First Product</a>
		                                </h4>
		                                <p>See more snippets like this online store item at <a target="_blank" href="http://www.bootsnipp.com">Bootsnipp - http://bootsnipp.com</a>.</p>
		                            </div>
		                            
		                        </div>
		                        <div class="col-sm-12">
	                            	<form>
			                        	<button class="btn btn-primary">Comprar</button>
			                        </form>
	                            </div>
		                    </div>
	                	</c:forEach>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- /.container -->
	
	    <div class="container">
	
	        <hr>
	
	        <!-- Footer -->
	        <footer>
	            <div class="row">
	                <div class="col-lg-12">
	                    <p>Copyright &copy; Your Website 2014</p>
	                </div>
	            </div>
	        </footer>
	
	    </div>
	    <!-- /.container -->
	
	    <!-- jQuery -->
	    <script src="/resources/js/jquery.js"></script>
	
	    <!-- Bootstrap Core JavaScript -->
	    <script src="/resources/js/bootstrap.min.js"></script>
	
	</body>

</html>

<!-- 
<html>
<head>
	<script src="https://assets.pagar.me/js/pagarme.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>

<form id="payment_form" action="/payment/doPay" method="POST">
    Número do cartão: <input type="text" id="card_number"/>
    <br/>
    Nome (como escrito no cartão): <input type="text" id="card_holder_name"/>
    <br/>
    Mês de expiração: <input type="text" id="card_expiration_month"/>
    <br/>
    Ano de expiração: <input type="text" id="card_expiration_year"/>
    <br/>
    Código de segurança: <input type="text" id="card_cvv"/>
    <br/>
    <div id="field_errors">
    </div>
    <br/>
    <input type="submit"></input>
</form>
<script>
	$(document).ready(function() { // quando o jQuery estiver carregado...
	    PagarMe.encryption_key = "ek_test_uwG3LGV3fsKOSn4jAzbqEFslNB13Eo";

	    var form = $("#payment_form");

	    form.submit(function(event) { // quando o form for enviado...
	        // inicializa um objeto de cartão de crédito e completa
	        // com os dados do form
	        var creditCard = new PagarMe.creditCard();
	        creditCard.cardHolderName = $("#payment_form #card_holder_name").val();
	        creditCard.cardExpirationMonth = $("#payment_form #card_expiration_month").val();
	        creditCard.cardExpirationYear = $("#payment_form #card_expiration_year").val();
	        creditCard.cardNumber = $("#payment_form #card_number").val();
	        creditCard.cardCVV = $("#payment_form #card_cvv").val();

	        // pega os erros de validação nos campos do form
	        var fieldErrors = creditCard.fieldErrors();

	        //Verifica se há erros
	        var hasErrors = false;
	        for(var field in fieldErrors) { hasErrors = true; break; }

	        if(hasErrors) {
	            // realiza o tratamento de errors
	            alert(fieldErrors);
	        } else {
	            // se não há erros, gera o card_hash...
	            creditCard.generateHash(function(cardHash) {
	                // ...coloca-o no form...
	                form.append($('<input type="hidden" name="card_hash">').val(cardHash));
	                // e envia o form
	                console.log(form);
	                form.get(0).submit();
	            });
	        }

	        return false;
	    });
	});
	</script>
</body>
</html> -->
