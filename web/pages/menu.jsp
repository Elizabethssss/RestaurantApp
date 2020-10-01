<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 3/18/2020
  Time: 4:45 PM
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
    <link rel="stylesheet" href="../styles/menu.css" />
    <link rel="stylesheet" href="../styles/commons/header.css" />
    <link rel="stylesheet" href="../styles/commons/footer.css" />
<%--    <link rel="stylesheet" href="../libs/fontawesome-free-5.12.1-web/css/all.css"/>--%>

    <script src="https://kit.fontawesome.com/6d63b0bfa0.js" crossorigin="anonymous"></script>
    <title>Menu</title>
</head>
<body>
<jsp:include page="commons/header.jsp"/>
<main>
    <div class="container-fluid p-0">
        <div class="site-content ${requestScope.dishType}">
            <div class="d-flex justify-content-center shadow">
                <div class="d-flex flex-column">
                    <h1 class="site-title">The Elizabeth's Menu</h1>
                    <p class="site-desc">Lorem ipsum dolor sit amet consectetur, adipisicing elit. Illo, beatae.</p>
                    <div class="menu-list d-flex flex-row flex-wrap justify-content-center">
                        <a href="menu?type=SALAD&page=1&lang=${sessionScope.locale}" class="btn site-btn1 px-4 py-3 mr-4
                         btn-outline-warning ${requestScope.dishType.equals("SALAD")?'active':''}">
                            ${requestScope.bundle.getString("salads")}
                        </a>
                        <a href="menu?type=SNACK&page=1&lang=${sessionScope.locale}" class="btn site-btn1 px-4 py-3 mr-4
                         btn-outline-warning ${requestScope.dishType.equals("SNACK")?'active':''}">
                            ${requestScope.bundle.getString("snacks")}
                        </a>
                        <a href="menu?type=SOUP&page=1&lang=${sessionScope.locale}" class="btn site-btn1 px-4 py-3 mr-4
                         btn-outline-warning ${requestScope.dishType.equals("SOUP")?'active':''}">
                            ${requestScope.bundle.getString("soups")}
                        </a>
                        <a href="menu?type=MAIN_DISH&page=1&lang=${sessionScope.locale}" class="btn site-btn1 px-4 py-3 mr-4
                         btn-outline-warning ${requestScope.dishType.equals("MAIN_DISH")?'active':''}">
                            ${requestScope.bundle.getString("main.dishes")}
                        </a>
                        <a href="menu?type=DESSERT&page=1&lang=${sessionScope.locale}" class="btn site-btn1 px-4 py-3 mr-4
                         btn-outline-warning ${requestScope.dishType.equals("DESSERT")?'active':''}">
                            ${requestScope.bundle.getString("desserts")}
                        </a>
                        <a href="menu?type=DRINK&page=1&lang=${sessionScope.locale}" class="btn site-btn1 px-4 py-3 mr-4
                         btn-outline-warning ${requestScope.dishType.equals("DRINK")?'active':''}">
                            ${requestScope.bundle.getString("drinks")}
                        </a>
                        <a href="menu?type=BREAKFAST&page=1&lang=${sessionScope.locale}" class="btn site-btn1 px-4 py-3 mr-4
                         btn-outline-warning ${requestScope.dishType.equals("BREAKFAST")?'active':''}">
                            ${requestScope.bundle.getString("breakfasts")}
                        </a>
                        <a href="menu?type=LUNCH&page=1&lang=${sessionScope.locale}" class="btn site-btn1 px-4 py-3 mr-4
                         btn-outline-warning ${requestScope.dishType.equals("LUNCH")?'active':''}">
                            ${requestScope.bundle.getString("lunches")}
                        </a>
                        <a href="menu?type=HOLIDAY&page=1&lang=${sessionScope.locale}" class="btn site-btn1 px-4 py-3 mr-4
                         btn-outline-warning ${requestScope.dishType.equals("HOLIDAY")?'active':''}">
                            ${requestScope.bundle.getString("holiday.sets")}
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="section-1">

        <div class="container">
            <div class="row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-xl-3">
                <c:choose>
                    <c:when test="${requestScope.dishType.equals(\"BREAKFAST\") ||
                     requestScope.dishType.equals(\"LUNCH\") ||requestScope.dishType.equals(\"HOLIDAY\")}">
                        <c:forEach var="lunch" items="${requestScope.lunchMap}">
                            <div class="col my-col">
                                <a href="lunch?id=${lunch.key.id}&lang=${sessionScope.locale}">
                                    <div class="single-content">
                                        <img src="../img/lunches-s/${lunch.key.img}" alt="${lunch.key.name}" />
                                        <div class="text-content">
                                            <h4>${lunch.key.name}</h4>
                                            <h5>${requestScope.bundle.getString("price")}: ${lunch.value}
                                                    ${requestScope.bundle.getString("uah")}
                                            </h5>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="dish" items="${dishes}">
                            <div class="col my-col">
                                <a href="dish?id=${dish.getId()}&lang=${sessionScope.locale}">
                                    <div class="single-content">
                                        <img src="../img/dishes-s/${dish.getImg()}" alt="${dish.getName()}" />
                                        <div class="text-content">
                                            <h4>${dish.getName()}</h4>
                                            <p>${requestScope.bundle.getString("weight")}: ${dish.getWeight()}
                                                    ${requestScope.bundle.getString("weight.g")}
                                            </p>
                                            <h5>${requestScope.bundle.getString("price")}: ${dish.getPriceInt()}
                                                    ${requestScope.bundle.getString("uah")}
                                            </h5>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="container">
        <a href="menu?type=${requestScope.dishType}&page=${requestScope.pageNumber-1}&lang=${sessionScope.locale}"
           class="page-btn btn site-btn1 px-4 py-3 mr-4 btn-outline-warning ${requestScope.pageNumber<=1?'disabled btn-dis':''}">
            ${requestScope.bundle.getString("previous")}
        </a>
        ${requestScope.pageNumber} / ${requestScope.totalPages}
        <c:choose>
            <c:when test="${requestScope.dishType.equals(\"BREAKFAST\") ||
                     requestScope.dishType.equals(\"LUNCH\") ||requestScope.dishType.equals(\"HOLIDAY\")}">
                <a href="menu?type=${requestScope.dishType}&page=${requestScope.pageNumber+1}&lang=${sessionScope.locale}" class="page-btn btn site-btn1
                 px-4 py-3 mr-4 btn-outline-warning ${(requestScope.numberOfLunches<=(requestScope.pageNumber*9))?'disabled btn-dis':''}">
                        ${requestScope.bundle.getString("next")}
                </a>
            </c:when>
            <c:otherwise>
                <a href="menu?type=${requestScope.dishType}&page=${requestScope.pageNumber+1}&lang=${sessionScope.locale}" class="page-btn btn site-btn1
                 px-4 py-3 mr-4 btn-outline-warning ${(requestScope.numberOfDishes<=(requestScope.pageNumber*9))?'disabled btn-dis':''}">
                        ${requestScope.bundle.getString("next")}
                </a>
            </c:otherwise>
        </c:choose>
    </div>
</main>
<jsp:include page="commons/footer.jsp"/>

<!-- Bootstrap CDN CSS file -->
<script src="../libs/jquery/jquery-3.4.1.min.js"></script>
<script src="../libs/popper/popper.min.js"></script>
<script src="../libs/bootstrap-4.4.1/js/bootstrap.min.js"></script>
<script src="../libs/scrollreveal/scrollreveal.js"></script>
<script>
    window.sr = ScrollReveal({ duration: 1500 });
    sr.reveal(".site-content .d-flex");
    sr.reveal(".row .single-content");
</script>
</body>
</html>
