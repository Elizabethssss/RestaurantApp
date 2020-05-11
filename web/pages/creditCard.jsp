<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 3/27/2020
  Time: 4:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- Bootstrap CDN CSS file-->
    <link rel="stylesheet" href="../libs/bootstrap-4.4.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../styles/creditCard.css" />
    <link rel="stylesheet" href="../styles/commons/header.css" />
    <link rel="stylesheet" href="../styles/commons/footer.css" />
    <link rel="stylesheet" href="../libs/fontawesome-free-5.12.1-web/css/all.css"/>
    <title>Document</title>
</head>
<body>
<jsp:include page="commons/header.jsp"/>
<main>
    <div class="section-1 bg">
        <form method="post" action="creditCard?id=${requestScope.orderId}&lang=${sessionScope.locale}">
        <div class="container">
            <h1 class="payment-title"><span>${requestScope.bundle.getString("order")} #${requestScope.orderId} ${requestScope.bundle.getString("payment")}:</span></h1>
            <div class="wrapper-card">
                <div class="sendmoney_wrapper-sender">
                    <div class="sendmoney_wrapper-frontSide">
                        <p class="sendmoney_frontSide-title">Elizabeth Bank</p>
                        <div class="sendmoney_frontSide-form">
                            <div class="sendmoney_input">
                                <div class="input">
                                    <span class="input--nao input-group">
                                        <input id="input_name_0" name="cardNumber"
                                               value="4197394215553472"
<%--                                               value="${requestScope.cardNumber}"--%>
                                               class="input__field input__field--nao input_type_text ${requestScope.errorsMessages.containsKey("cardNumberError")?'error':''}"/>
                                    </span>
                                </div>
                            </div>
                            <p class="errorMsg">${requestScope.errorsMessages.containsKey("cardNumberError")?requestScope.errorsMessages.get("cardNumberError"):''}</p>
                            <div class="sendmoney_frontSide-cardExpire">
                                <div class="sendmoney_input">
                                    <div class="input">
                                        <span class="input--nao input-group">
                                            <input id="input_name_2" name="expiredMonth"
                                                   value="11"
<%--                                                   value="${requestScope.expiredMonth}"--%>
                                                   class="input__field input__field--nao input_type_text ${requestScope.errorsMessages.containsKey("dateError")?'error':''}"/>
                                        </span>
                                    </div>
                                </div>
                                <div class="sendmoney_input">
                                    <div class="input">
                                        <span class="input--nao input-group">
                                            <input id="input_name_3" name="expiredYear"
                                                   value="21"
<%--                                                   value="${requestScope.expiredYear}"--%>
                                                   class="input__field input__field--nao input_type_text ${requestScope.errorsMessages.containsKey("dateError")?'error':''}"/>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <p class="errorMsg">${requestScope.errorsMessages.containsKey("dateError")?requestScope.errorsMessages.get("dateError"):''}</p>
                        </div>
                    </div>
                    <div class="sendmoney_wrapper-backSide">
                        <div class="sendmoney_magneticTape"></div>
                        <div class="sendmoney_wrapper-cvv">
                            <div class="sendmoney_input">
                                <div class="input">
                                    <span class="input--nao input-group">
                                        <input type="password" id="input_name_4" name="cvv"
                                               value="123"
                                               class="input__field input__field--nao input_type_text ${requestScope.errorsMessages.containsKey("cvvError")?'error':''}"/>
                                    </span>
                                </div>
                            </div>
                            <div>
                                <p class="sendmoney_cvv">CVV/CVC2</p>
                                <p class="errorMsg error-cvv">${requestScope.errorsMessages.containsKey("cvvError")?requestScope.errorsMessages.get("cvvError"):''}</p>
                            </div>
                        </div>
                    </div>
                    <div class="btn-down">
                        <h3>${requestScope.bundle.getString("total")}: <span>${requestScope.total} ${requestScope.bundle.getString("uah")}</span></h3>
                        <button type="submit" class="btn btn-warning btn-buy">${requestScope.bundle.getString("pay")}</button>
                    </div>
                </div>
            </div>
        </div>
        </form>
    </div>
</main>

<jsp:include page="commons/footer.jsp"/>

<!-- Bootstrap CDN CSS file -->
<script src="../libs/jquery/jquery-3.4.1.min.js"></script>
<script src="../libs/popper/popper.min.js"></script>
<script src="../libs/bootstrap-4.4.1/js/bootstrap.min.js"></script>
<script src="../libs/scrollreveal/scrollreveal.js"></script>
</body>
</html>
