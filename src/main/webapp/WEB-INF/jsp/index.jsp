<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>GimmeYour$</title>

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
				<a class="navbar-brand" href="#">GimmeYour$</a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li >
						<a class="dropdown">
							<div data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								Cancelar Transações
							</div>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
								<c:forEach items="${transactions}" var="transaction">
									<li>
										<form action="/payment/refund" method="POST">
											<input type="hidden" name="transaction_id"
												value="${transaction.transactionId}" /> <input
												type="submit" value="${transaction.amount}" />
										</form>
									</li>
								</c:forEach>
							</ul>
						</a>
					</li>
					<li >
						<a class="dropdown">
							<div data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								Payables
							</div>
							<div  class="dropdown-menu" aria-labelledby="dropdownMenu1">
								<c:forEach items="${payables}" var="payable">
									<button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="left" title="object: ${payable.object}
id: ${payable.id}
status: ${payable.status}
amount: ${payable.amount}
fee: ${payable.fee}
installment: ${payable.installment}
transaction_id: ${payable.transaction_id}
split_rule_id: ${payable.split_rule_id}
payment_date: ${payable.payment_date}
type: ${payable.type}
payment_method: ${payable.payment_method}
date_created: ${payable.date_created}">Tooltip on left</button>
									
								</c:forEach>
							</div>
							
						</a>
					</li>
					<li data-toggle="modal" data-target="#card-register-modal">
						<a>
							Registre seu cartão
						</a>
					</li>
					<li class="pull-right">
						<a href="/login?logout">
							Logout
						</a>
					</li>
				</ul>
			</div>
		</div>
		<!-- /.container -->
	</nav>

	<!-- Page Content -->
	<div class="container">
		<c:if test="${success}">
			<div class="alert alert-success" role="alert">${message}</div>
		</c:if>
		<c:if test="${not empty erros}">
			<div class="alert alert-danger" role="alert">
				<span class="glyphicon glyphicon-exclamation-sign"
					aria-hidden="true"> </span>

				<c:forEach var="erro" items="${erros.errors}">
					<span class="sr-only">${erro.parameter_name}:</span>
					${erro.message}
				</c:forEach>
			</div>

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
									<c:choose>
										<c:when test="${currentUser.cardRegistered}">
											<form action="/payment/doPayAutomatic" method="POST">
												<button class="pull-right btn btn-primary btn-lg">
													One-click buy
												</button>
											</form>
											
										</c:when>
									</c:choose>
									<form action="/payment/doPay" method="POST">
										<input type="submit" class="pull-right btn btn-primary btn-lg">
											Comprar
										</input>
									</form>
									
								</div>

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

	<!-- payment modal -->
	<div class="modal fade" tabindex="-1" role="dialog" id="payment-modal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Coloque suas informações</h4>
				</div>
				<form class="form-horizontal" id="payment_form" action="/payment/doPay" method="POST">
					<div class="modal-body">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-5 control-label">Número do cartão:</label>
							<div class="col-sm-6">
								<input class="form-control" type="text" id="card_number" />
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-5 control-label">Nome (como escrito no cartão):</label>
							<div class="col-sm-6">
								<input class="form-control" type="text" id="card_holder_name" />
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-5 control-label"> Mês de expiração:</label>
							<div class="col-sm-6">
								 <input class="form-control" type="text" id="card_expiration_month" />
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-5 control-label">Ano de expiração:</label>
							<div class="col-sm-6">
								 <input class="form-control" type="text" id="card_expiration_year" />
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-5 control-label"> Código de segurança:</label>
							<div class="col-sm-6">
								 <input class="form-control" type="text" id="card_cvv" />
							</div>
						</div>
						<div class="form-group">
						    <div class="col-sm-offset-5 col-sm-2">
						    	<input type="submit" class="btn btn-default"></input>
							</div>
						</div>
						
					</div>
				
				</form>
				
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<div class="modal fade" tabindex="-1" role="dialog" id="card-register-modal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Coloque suas informações</h4>
				</div>
				<form class="form-horizontal" id="payment_form" action="/registerCard" method="POST">
					<div class="modal-body">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-5 control-label">Número do cartão:</label>
							<div class="col-sm-6">
								<input class="form-control" type="text" id="card_number" />
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-5 control-label">Nome (como escrito no cartão):</label>
							<div class="col-sm-6">
								<input class="form-control" type="text" id="card_holder_name" />
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-5 control-label"> Mês de expiração:</label>
							<div class="col-sm-6">
								 <input class="form-control" type="text" id="card_expiration_month" />
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-5 control-label">Ano de expiração:</label>
							<div class="col-sm-6">
								 <input class="form-control" type="text" id="card_expiration_year" />
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-5 control-label"> Código de segurança:</label>
							<div class="col-sm-6">
								 <input class="form-control" type="text" id="card_cvv" />
							</div>
						</div>
						<div class="form-group">
						    <div class="col-sm-offset-5 col-sm-2">
						    	<input type="submit" class="btn btn-default"></input>
							</div>
						</div>
						
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
	<!-- Bootstrap Core JavaScript -->
	<script src="/resources/js/bootstrap.min.js"></script>
	<!-- Pagarme JS -->
	<script src="https://assets.pagar.me/js/pagarme.min.js"></script>
	<script src="/resources/js/pagarme_hash_generator.js"></script>

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
