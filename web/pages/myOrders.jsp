<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 3/18/2020
  Time: 4:47 PM
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
<%--    <link rel="stylesheet" href="../libs/fontawesome-free-5.12.1-web/css/all.css"/>--%>
    <link rel="stylesheet" href="../styles/myOrders.css" />
    <link rel="stylesheet" href="../styles/commons/header.css" />
    <link rel="stylesheet" href="../styles/commons/footer.css" />

    <script src="https://kit.fontawesome.com/6d63b0bfa0.js" crossorigin="anonymous"></script>
    <title>My Orders</title>
</head>
<body>
<jsp:include page="commons/header.jsp"/>

<main>
    <div class="section-1">
        <div class="container">

            <div class="h1_logout d-flex justify-content-between">
                <h1 class="orders-title"><span>${requestScope.bundle.getString("my.orders")}:</span></h1>
            </div>

            <div class="text-center">
                <c:if test="${requestScope.orderDishMap.isEmpty()}">
                    <div class="no-orders">${requestScope.bundle.getString("no.orders")}</div>
                </c:if>
            </div>

            <c:forEach var="order" items="${requestScope.orders}">
                <div class="row">
                    <div class="col col-12 mb-5">
                        <div class="order-item">
                            <div class="info">
                                <div class="right-block">
                                    <div class="status"><span class="badge ${order.status == "PAYED"?'badge-success' :
                                    order.status == "CONFIRMED"?'badge-danger':'badge-info'}">${order.status.statusDesc}</span></div>
                                    <a class="btn bg-warning pay-btn ${order.status == "CONFIRMED"?'':'hide'}"
                                       href="creditCard?id=${order.id}&lang=${sessionScope.locale}">${requestScope.bundle.getString("pay")}</a>
                                    <div class="price"><span>${order.cost}</span> ${requestScope.bundle.getString("uah")}</div>
                                </div>
                                <div>
                                    <a data-toggle="collapse" href="#collapseDishes-${order.id}" aria-expanded="false">
                                        <span class="order-dish-items badge badge-info">${order.lunches.size() + order.dishes.size()}
                                        </span><em class="double-arrow fas fa-angle-double-right"></em>
                                    </a>
                                </div>
                                <div># <span>${order.id}</span></div>
                                <div class="created-at">${order.createdAt}</div>
                            </div>
                            <div class="info-list collapse" id="collapseDishes-${order.id}">
                                <c:forEach var="dish" items="${order.dishes}">
                                    <div class="info-list-item">
                                        <a class="dish-name" href="dish?id=${dish.key.id}&lang=${sessionScope.locale}">
                                            <img src="../img/dishes-s/${dish.key.img}" alt="" />
                                                ${dish.key.name}
                                        </a>
                                        <div class="divider"></div>
                                        <div class="info-list__item-price">
                                            <span class="info-list__item-price__items">${dish.value}</span> x
                                            <span class="info-list__item-price__cost">${dish.key.price} ${requestScope.bundle.getString("uah")}</span> =
                                            <span class="info-list__item-price__cost">${dish.value * dish.key.price}</span>
                                        </div>
                                    </div>
                                </c:forEach>
                                <c:forEach var="lunch" items="${order.lunches}">
                                    <div class="info-list-item">
                                        <a class="dish-name" href="lunch?id=${lunch.key.id}&lang=${sessionScope.locale}">
                                            <img src="../img/lunches-s/${lunch.key.img}" alt="" />
                                                ${lunch.key.name}
                                        </a>
                                        <div class="divider"></div>
                                        <div class="info-list__item-price">
                                            <span class="info-list__item-price__items">${lunch.value}</span> x
                                            <span class="info-list__item-price__cost">${lunch.key.price} ${requestScope.bundle.getString("uah")}</span> =
                                            <span class="info-list__item-price__cost">${lunch.value * lunch.key.price}</span>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <div class="row pb-5 mb-0">
                <div class="col text-center">
                    <a href="myOrders?page=${requestScope.pageNumber-1}&lang=${sessionScope.locale}"
                       class="page-btn btn site-btn1 px-4 py-3 mr-4 btn-outline-warning ${requestScope.pageNumber<=1?'disabled btn-dis':''}">${requestScope.bundle.getString("previous")}</a>
                    ${requestScope.pageNumber} / ${requestScope.totalPages}
                    <a href="myOrders?page=${requestScope.pageNumber+1}&lang=${sessionScope.locale}"
                       class="page-btn btn site-btn1 px-4 py-3 ml-4 mr-4 btn-outline-warning
                       ${(requestScope.numberOfOrders<=(requestScope.pageNumber*7))?'disabled btn-dis':''}">${requestScope.bundle.getString("next")}</a>
                </div>
            </div>

        </div>
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