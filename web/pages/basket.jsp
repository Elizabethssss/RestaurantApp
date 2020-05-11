<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 3/24/2020
  Time: 7:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
            <h1 class="basket-title"><span>${requestScope.bundle.getString("your.dishes")}:</span></h1>
            <c:if test="${requestScope.dishes.isEmpty() && requestScope.lunchIntegerMap.isEmpty()}">
                <div class="no-dishes">${requestScope.bundle.getString("no.dishes")}</div>
            </c:if>

            <div class="row">
                <c:forEach var="dish" items="${dishes}">
                    <div class="basket-item fade-out col-10" data-type="dish" data-id="${dish.key.id}">
                        <button class="close" data-action="remove">x</button>
                        <div class="basket-item-img">
                            <img src="../img/dishes-s/${dish.key.img}" alt="" />
                        </div>
                        <div>
                            <h2>${dish.key.name}</h2>
                            <p>${dish.key.about}</p>
                            <div class="number">
                                <button class="down_count btn p-m-btn" title="Down" data-action="minus"
                                    ${dish.value == 1 ? 'disabled':''}>–</button>
                                <h3 class="counter number-of-item">${dish.value}</h3>
                                <button class="up_count btn p-m-btn" title="Up" data-action="plus">+</button>
                            </div>
                            <p class="mt-3 price">
                                ${requestScope.bundle.getString("price")}: <span class="number-of-item">${dish.value}</span>
                                    <span class="price-ex">x</span><span>${dish.key.getPriceInt()}</span>
                                        ${requestScope.bundle.getString("uah")} = <span class="price-of-items">
                                        ${dish.value * dish.key.getPriceInt()}</span> ${requestScope.bundle.getString("uah")}
                            </p>
                        </div>
                    </div>
                </c:forEach>
                <c:forEach var="lunch" items="${requestScope.lunchIntegerMap}">
                    <div class="basket-item fade-out col-10" data-type="lunch" data-id="${lunch.key.id}">
                        <button class="close" data-action="remove">x</button>
                        <div class="basket-item-img">
                            <img src="../img/lunches-s/${lunch.key.img}" alt="" />
                        </div>
                        <div>
                            <h2>${lunch.key.name}</h2>
                            <p>${lunch.key.description}</p>
                            <div class="number">
                                <button class="down_count btn p-m-btn" title="Down" data-action="minus"
                                    ${lunch.value == 1 ? 'disabled':''}>–</button>
                                <h3 class="counter number-of-item">${lunch.value}</h3>
                                <button class="up_count btn p-m-btn" title="Up" data-action="plus">+</button>
                            </div>
                            <p class="mt-3 price">
                                ${requestScope.bundle.getString("price")}: <span class="number-of-item">${lunch.value}</span>
                                    <span class="price-ex">x</span><span>${lunch.key.price}</span>
                                        ${requestScope.bundle.getString("uah")} = <span class="price-of-items">
                                        ${lunch.value * lunch.key.price}</span> ${requestScope.bundle.getString("uah")}
                            </p>
                        </div>
                    </div>
                </c:forEach>
            </div>
                <div class="row ${(requestScope.dishes.isEmpty() && requestScope.lunchIntegerMap.isEmpty()) ? 'hide':''}">
                    <div class="col-10 total-price mt-4 text-right">
                        <h2 class="total">${requestScope.bundle.getString("total")}: <span id="totalPrice">${requestScope.totalPrice}</span>
                            ${requestScope.bundle.getString("uah")}</h2>
                        <a href="placeOrder" class="btn btn-dark mb-4">${requestScope.bundle.getString("place.order")}</a>
                    </div>
                </div>

<%--            <div class="clear"></div>--%>

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
