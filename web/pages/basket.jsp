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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"/>
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
            <h1 class="basket-title">Your dishes:</h1>
            <div class="row">
                <c:forEach var="dish" items="${requestScope.dishes}">
                    <div class="basket-item col-10">
                        <a href="#" class="close"><p class="m-0">x</p></a>
                        <div class="basket-item-img">
                            <img src="../img/dishes-s/${dish.key.img}" alt=""/>
                        </div>
                        <div>
                            <h2>${dish.key.name}</h2>
                            <p>
                                ${dish.key.about}
                            </p>
                            <div class="number">
                                <button class="down_count btn btn-dark" title="Down">
                                    <span>&#8722;</span>
                                </button>
                                <h3 class="counter">${dish.value}</h3>
                                <button class="up_count btn btn-dark" title="Up">
                                    <span>&#43;</span>
                                </button>
                            </div>
                            <p class="mt-3 price">
                                Price: <span>${dish.key.price * dish.value}</span> UAH
                            </p>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="total-price mt-4 text-right">
                <h2 class="total">Total: <span>123456789$</span></h2>
                <button class="btn btn-dark mb-4">Buy</button>
            </div>

            <div class="clear"></div>

    </div>
    </div>
</main>
<jsp:include page="commons/footer.jsp"/>

<!-- Bootstrap CDN CSS file -->
<script
        src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"
></script>
<script
        src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"
></script>
<script
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"
></script>
<script src="https://unpkg.com/scrollreveal"></script>
</body>
</html>
