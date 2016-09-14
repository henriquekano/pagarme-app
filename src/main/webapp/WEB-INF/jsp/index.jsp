<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Start Bootstrap</a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li>
						<a class="dropdown">
							<button class="btn btn-default dropdown-toggle" type="button"
								id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="true">
								Dropdown <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="#">Separated link</a></li>
							</ul>
						</a>
					</li>
					<li><a href="#">Services</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
			</div>
		</div>
		<!-- /.container -->
	</nav>

	<!-- Page Content -->
	<div class="container">
		<c:if test="${success}">
			Sucesso!
		</c:if>
		<c:if test="${not empty erros}">
			<c:forEach var="erro" items="${erros.errosAnswer}">
				${erro.message}
			</c:forEach>
		</c:if>
		<div class="row">

			<div class="col-md-12">

				<div class="row">
					<c:forEach var="index" begin="1" end="5">
						<div class="col-sm-4 col-lg-4 col-md-4">
							<div class="thumbnail">
								<img src="http://placehold.it/320x150" alt="">
								<div class="caption">
									<h4 class="pull-right">$24.99</h4>
									<h4>
										<a href="#">First Product</a>
									</h4>
									<p>Descrição!</p>
								</div>

							</div>
							<button type="button" class="btn btn-primary btn-lg"
								data-toggle="modal" data-target="#payment-modal">Comprar</button>
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

	<!-- payment modal -->

	<div class="modal fade" tabindex="-1" role="dialog" id="payment-modal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<form id="payment_form" action="/payment/doPay" method="POST">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Coloque suas informações</h4>
					</div>
					<div class="modal-body">

						Número do cartão: <input type="text" id="card_number" /> <br />
						Nome (como escrito no cartão): <input type="text"
							id="card_holder_name" /> <br /> Mês de expiração: <input
							type="text" id="card_expiration_month" /> <br /> Ano de
						expiração: <input type="text" id="card_expiration_year" /> <br />
						Código de segurança: <input type="text" id="card_cvv" /> <br />
						<div id="field_errors"></div>
					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-default"></input>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- jQuery -->
	<script src="/resources/js/jquery.js"></script>

	<!-- Pagarme JS -->
	<script src="https://assets.pagar.me/js/pagarme.min.js"></script>
	<script>
		$(document)
				.ready(
						function() { // quando o jQuery estiver carregado...
							PagarMe.encryption_key = "ek_test_uwG3LGV3fsKOSn4jAzbqEFslNB13Eo";

							var form = $("#payment_form");

							form
									.submit(function(event) { // quando o form for enviado...
										// inicializa um objeto de cartão de crédito e completa
										// com os dados do form
										var creditCard = new PagarMe.creditCard();
										creditCard.cardHolderName = $(
												"#payment_form #card_holder_name")
												.val();
										creditCard.cardExpirationMonth = $(
												"#payment_form #card_expiration_month")
												.val();
										creditCard.cardExpirationYear = $(
												"#payment_form #card_expiration_year")
												.val();
										creditCard.cardNumber = $(
												"#payment_form #card_number")
												.val();
										creditCard.cardCVV = $(
												"#payment_form #card_cvv")
												.val();

										// pega os erros de validação nos campos do form
										var fieldErrors = creditCard
												.fieldErrors();

										//Verifica se há erros
										var hasErrors = false;
										for ( var field in fieldErrors) {
											hasErrors = true;
											break;
										}

										if (hasErrors) {
											// realiza o tratamento de errors
											alert(fieldErrors);
										} else {
											// se não há erros, gera o card_hash...
											creditCard
													.generateHash(function(
															cardHash) {
														// ...coloca-o no form...
														form
																.append($(
																		'<input type="hidden" name="card_hash">')
																		.val(
																				cardHash));
														// e envia o form
														console.log(form);
														form.get(0).submit();
													});
										}

										return false;
									});
						});
	</script>

	<!-- Bootstrap Core JavaScript -->
	<script src="/resources/js/bootstrap.min.js"></script>

</body>

</html>

<!-- 
<html>
<head>
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>



</body>
</html> -->
