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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous"/>
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
                <a href="menu?type=${requestScope.dish.dishType}&page=1">Menu</a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">${requestScope.dish.name}</li>
        </ol>
    </nav>
    <div class="alert alert-success ${requestScope.message == null ? 'hide':''}" role="alert" style="text-align: center">
        ${requestScope.message}
    </div>
    <form method="post" action="buy?id=${requestScope.dish.id}">
        <div class="section-4 bg-dark">
            <div class="container p-0">
                <div class="row">
                    <div class="col-md-6">
                        <img src="../img/dishes/${requestScope.dish.img}" alt="${requestScope.dish.name}" width="530"/>
                    </div>
                    <div class="col-md-6">
                        <h1 class="text-white">${requestScope.dish.name}</h1>
                        <p class="para-1">${requestScope.dish.about}</p>
                        <p class="weight">Weight: ${requestScope.dish.weight} g</p>
                        <p class="price">Price: ${requestScope.dish.price} UAH</p>
                        <button type="submit" class="btn px-4 py-1 btn-outline-warning">Buy</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
        <div class="container carous">
            <h1><span>Ingredients:</span></h1>
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
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<script src="../libs/owlcarousel/dist/owl.carousel.min.js"></script>
<script src="https://unpkg.com/scrollreveal"></script>
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
