$(document)
		.ready(
				function() { // quando o jQuery estiver carregado...
					PagarMe.encryption_key = "ek_test_uwG3LGV3fsKOSn4jAzbqEFslNB13Eo";

					var form = $("#payment_form,#card-registration");

					form
							.submit(function(event) { // quando o form for
														// enviado...
								// inicializa um objeto de cartão de crédito e
								// completa
								// com os dados do form
								var creditCard = new PagarMe.creditCard();
								creditCard.cardHolderName = $(this).find("#card_holder_name")
										.val();
								creditCard.cardExpirationMonth = $(this).find("#card_expiration_month")
										.val();
								creditCard.cardExpirationYear = $(this).find("#card_expiration_year")
										.val();
								creditCard.cardNumber = $(this).find("#card_number").val();
								creditCard.cardCVV = $(this).find("#card_cvv").val();

								// pega os erros de validação nos campos do form
								var fieldErrors = creditCard.fieldErrors();

								// Verifica se há erros
								var hasErrors = false;
								for ( var field in fieldErrors) {
									hasErrors = true;
									break;
								}

								if (hasErrors) {
									// realiza o tratamento de errors
									alert(fieldErrors);
								} else {
									var formElement = $(this);
									// se não há erros, gera o card_hash...
									creditCard
											.generateHash(function(cardHash) {
												// ...coloca-o no form...
												formElement
														.append($(
																'<input type="hidden" name="card_hash">')
																.val(cardHash));
												// e envia o form
												console.log(formElement);
												formElement.get(0).submit();
											});
								}

								return false;
							});
				});