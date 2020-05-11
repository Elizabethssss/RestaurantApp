<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 3/30/2020
  Time: 10:03 PM
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
    <link rel="stylesheet" href="../styles/dish.css" />
    <link rel="stylesheet" href="../styles/commons/header.css" />
    <link rel="stylesheet" href="../styles/commons/footer.css" />
    <link rel="stylesheet" href="../libs/fontawesome-free-5.12.1-web/css/all.css"/>
    <link rel="stylesheet" href="../libs/owlcarousel/dist/assets/owl.carousel.min.css">
    <link rel="stylesheet" href="../libs/owlcarousel/dist/assets/owl.theme.default.min.css">

    <title>Lunch</title>
</head>
<body>
<jsp:include page="commons/header.jsp"/>
<main class="lunch-page">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="menu?type=${requestScope.lunch.lunchType}&page=1">${requestScope.bundle.getString("menu")}</a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">${requestScope.lunch.name}</li>
        </ol>
    </nav>
    <div class="alert alert-success text-center ${requestScope.message == null ? 'hide':''}" role="alert">
        ${requestScope.message}
    </div>
    <form method="post" action="lunch?id=${requestScope.lunch.id}&lang=${sessionScope.locale}">
        <div class="section-4 bg-dark">
            <div class="container p-0">
                <div class="row">
                    <div class="col-md-6">
                        <img src="../img/lunches/${requestScope.lunch.img}" alt="${requestScope.lunch.name}" width="530"/>
                    </div>
                    <div class="col-md-6">
                        <h1 class="text-white">${requestScope.lunch.name}</h1>
                        <p class="para-1">${requestScope.lunch.description}</p>
                        <p class="weight">${requestScope.bundle.getString("weight")}: ${requestScope.weight}
                            ${requestScope.bundle.getString("weight.g")}</p>
                        <p class="price">${requestScope.bundle.getString("price")}: ${requestScope.price}
                            ${requestScope.bundle.getString("uah")}</p>
                        <button type="submit" class="btn-buy btn px-4 py-1 btn-outline-warning" ${requestScope.disabled?'disabled':''}>
                            ${requestScope.bundle.getString("buy")}
                        </button>
                        <p class="para-1">${requestScope.bundle.getString("cant.buy.lunch")} ${requestScope.lunch.timeFrom}
                            ${requestScope.bundle.getString("to")} ${requestScope.lunch.timeTo}</p>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <div class="container carous">
        <h1><span>${requestScope.bundle.getString("check.dishes")}:</span></h1>
        <div class="owl-carousel owl-theme">
            <c:forEach var="dish" items="${requestScope.lunch.dishes}">
                <a href="dish?id=${dish.id}">
                <div class="item ingredient-item">
                    <div>
                        <img src="../img/dishes-s/${dish.img}" alt="${dish.name}">
                    </div>
                    <h4>${dish.name}</h4>
                </div>
                </a>
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
            600:{
                items:2,
                nav:false
            },
            1000:{
                items:4,
                nav:true,
                loop:false
            }
        }
    })
</script>
</body>
</html>
