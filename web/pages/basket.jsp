<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 3/24/2020
  Time: 7:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- Bootstrap CDN CSS file-->
    <link rel="stylesheet" href="../libs/bootstrap-4.4.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../libs/fontawesome-free-5.12.1-web/css/all.css"/>
    <link rel="stylesheet" href="../styles/basket.css" />
    <link rel="stylesheet" href="../styles/commons/header.css" />
    <link rel="stylesheet" href="../styles/commons/footer.css" />
    <title>Basket</title>
</head>
<body>
<jsp:include page="commons/header.jsp"/>
<main>
    <div class="section-1 bg">
        <div class="container">
            <h1 class="basket-title"><span>Your dishes:</span></h1>
            <div class="row">
                <c:forEach var="dish" items="${requestScope.dishes}">
                    <div class="basket-item fade-out col-10" data-dish-id="${dish.key.id}">
                        <button class="close" data-dish-action="remove">x</button>
                        <div class="basket-item-img">
                            <img src="../img/dishes-s/${dish.key.img}" alt="" />
                        </div>
                        <div>
                            <h2>${dish.key.name}</h2>
                            <p>
                                ${dish.key.about}
                            </p>
                            <div class="number">
                                <button class="down_count btn p-m-btn" title="Down" data-dish-action="minus"
                                        ${dish.value == 1 ? 'disabled':''}>–</button>
                                <h3 class="counter number-of-item">${dish.value}</h3>
                                <button class="up_count btn p-m-btn" title="Up" data-dish-action="plus">+</button>
                            </div>
                            <p class="mt-3 price">
                            Price: <span class="number-of-item">4</span> <span class="price-ex" style="display:inline-block">x</span> <span>${dish.key.getPriceInt()}</span> UAH = <span class="price-of-items">567</span> UAH
                            </p>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="row">
                <div class="col-10 total-price mt-4 text-right">
                    <h2 class="total">Total: <span id="totalPrice">${requestScope.totalPrice}</span> UAH</h2>
                    <button class="btn btn-dark mb-4">Place an order</button>
                </div>
            </div>

            <div class="clear"></div>

    </div>
    </div>
</main>
<jsp:include page="commons/footer.jsp"/>

<!-- Bootstrap CDN CSS file -->
<script src="../libs/jquery/jquery-3.4.1.min.js"></script>
<script src="../libs/popper/popper.min.js"></script>
<script src="../libs/bootstrap-4.4.1/js/bootstrap.min.js"></script>
<script src="../libs/scrollreveal/scrollreveal.js"></script>
<script src="../js/basket.js"></script>
</body>
</html>
