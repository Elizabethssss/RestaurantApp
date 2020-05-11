<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 3/18/2020
  Time: 4:44 PM
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
    <link rel="stylesheet" href="../styles/dish.css" />
    <link rel="stylesheet" href="../styles/commons/header.css" />
    <link rel="stylesheet" href="../styles/commons/footer.css" />
    <link rel="stylesheet" href="../libs/fontawesome-free-5.12.1-web/css/all.css"/>
    <link rel="stylesheet" href="../libs/owlcarousel/dist/assets/owl.carousel.min.css">
    <link rel="stylesheet" href="../libs/owlcarousel/dist/assets/owl.theme.default.min.css">

    <title>Dish</title>
</head>
<body>
<jsp:include page="commons/header.jsp"/>
<main>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="menu?type=${requestScope.dish.dishType}&page=1">${requestScope.bundle.getString("menu")}</a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">${requestScope.dish.name}</li>
        </ol>
    </nav>
    <div class="alert alert-success text-center ${requestScope.message == null ? 'hide':''}" role="alert">
        ${requestScope.message}
    </div>
    <form method="post" action="dish?id=${requestScope.dish.id}&lang=${sessionScope.locale}">
        <div class="section-4 bg-dark">
            <div class="container p-0">
                <div class="row">
                    <div class="col-md-6">
                        <img class="dish-img" src="../img/dishes/${requestScope.dish.img}" alt="${requestScope.dish.name}" width="530"/>
                    </div>
                    <div class="dish-description col-md-6">
                        <h1 class="text-white">${requestScope.dish.name}</h1>
                        <p class="para-1">${requestScope.dish.about}</p>
                        <p class="weight">${requestScope.bundle.getString("weight")}: ${requestScope.dish.weight}
                            ${requestScope.bundle.getString("weight.g")}</p>
                        <p class="price">${requestScope.bundle.getString("price")}: ${requestScope.dish.getPriceInt()}
                            ${requestScope.bundle.getString("uah")}</p>
                        <button type="submit" class="btn px-4 py-1 btn-outline-warning">
                            ${requestScope.bundle.getString("buy")}
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </form>
        <div class="container carous">
            <h1><span>${requestScope.bundle.getString("ingredients")}:</span></h1>
            <div class="owl-carousel owl-theme">
                <c:forEach var="ingredient" items="${ingredients}">
                    <div class="item ingredient-item">
                        <div>
                            <img src="../img/ingredients/${ingredient.getImg()}" alt="${ingredient.getName()}">
                        </div>
                        <h4>${ingredient.getName()}</h4>
                    </div>
                </c:forEach>
            </div>
        </div>
</main>

<jsp:include page="commons/footer.jsp"/>

<!-- Bootstrap CDN CSS file -->
<script src="../libs/jquery/jquery-3.4.1.min.js"></script>
<script src="../libs/popper/popper.min.js"></script>
<script src="../libs/bootstrap-4.4.1/js/bootstrap.min.js"></script>
<script src="../libs/scrollreveal/scrollreveal.js"></script>
<script src="../libs/owlcarousel/dist/owl.carousel.min.js"></script>
<script>
    window.sr = ScrollReveal({ duration: 1500 });
    sr.reveal(".section-4 .row");
    sr.reveal(".row .para-1");
    $('.owl-carousel').owlCarousel({
        loop:true,
        margin:10,
        responsiveClass:true,
        responsive:{
            0:{
                items:1,
                nav:true
            },
            360:{
                items:2,
                nav:true
            },
            600:{
                items:3,
                nav:false
            },
            1000:{
                items:6,
                nav:true,
                loop:false
            }
        }
    })
</script>
</body>
</html>
