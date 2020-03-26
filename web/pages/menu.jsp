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
    <link rel="stylesheet" href="../libs/fontawesome-free-5.12.1-web/css/all.css"/>
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
                        <a href="menu?type=SALAD&page=1" class="btn site-btn1 px-4 py-3 mr-4 btn-outline-warning
                            ${requestScope.dishType.equals("SALAD")?'active':''}">SALADS</a>
                        <a href="menu?type=SNACK&page=1" class="btn site-btn1 px-4 py-3 mr-4 btn-outline-warning
                            ${requestScope.dishType.equals("SNACK")?'active':''}">SNACKS</a>
                        <a href="menu?type=SOUP&page=1" class="btn site-btn1 px-4 py-3 mr-4 btn-outline-warning
                            ${requestScope.dishType.equals("SOUP")?'active':''}">SOUPS</a>
                        <a href="menu?type=MAIN_DISH&page=1" class="btn site-btn1 px-4 py-3 mr-4 btn-outline-warning
                            ${requestScope.dishType.equals("MAIN_DISH")?'active':''}">MAIN DISHES</a>
                        <a href="menu?type=DESSERT&page=1" class="btn site-btn1 px-4 py-3 mr-4 btn-outline-warning
                            ${requestScope.dishType.equals("DESSERT")?'active':''}">DESSERTS</a>
                        <a href="menu?type=DRINK&page=1" class="btn site-btn1 px-4 py-3 mr-4 btn-outline-warning
                            ${requestScope.dishType.equals("DRINK")?'active':''}">DRINKS</a>
                        <a href="menu?type=BREAKFAST&page=1" class="btn site-btn1 px-4 py-3 mr-4 btn-outline-warning
                            ${requestScope.dishType.equals("BREAKFAST")?'active':''}">BREAKFASTS</a>
                        <a href="menu?type=LUNCH&page=1" class="btn site-btn1 px-4 py-3 mr-4 btn-outline-warning
                            ${requestScope.dishType.equals("LUNCH")?'active':''}">LUNCHES</a>
                        <a href="menu?type=HOLIDAY&page=1" class="btn site-btn1 px-4 py-3 mr-4 btn-outline-warning
                            ${requestScope.dishType.equals("HOLIDAY")?'active':''}">HOLIDAY SETS</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="section-1">

        <div class="container">
            <div class="row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-xl-3">
                <c:forEach var="dish" varStatus="status" items="${dishes}">
                    <div class="col my-col">
                        <a href="dish?id=${dish.getId()}">
                        <div class="single-content">
                                <img src="../img/dishes-s/${dish.getImg()}" alt="${dish.getName()}" />
                                <div class="text-content">
                                    <h4>${dish.getName()}</h4>
                                    <p>Weight: ${dish.getWeight()}</p>
                                    <h5>Price: ${dish.getPriceInt()} UAH</h5>
                                </div>
                        </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="container">
        <a href="menu?type=${requestScope.dishType}&page=${requestScope.pageNumber-1}"
           class="page-btn btn site-btn1 px-4 py-3 mr-4 btn-outline-warning ${requestScope.pageNumber<=1?'disabled btn-dis':''}">Previous</a>
        ${requestScope.pageNumber} / ${requestScope.totalPages}
        <a href="menu?type=${requestScope.dishType}&page=${requestScope.pageNumber+1}"
           class="page-btn btn site-btn1 px-4 py-3 mr-4 btn-outline-warning ${(requestScope.numberOfDishes<=(requestScope.pageNumber*9))?'disabled btn-dis':''}">Next</a>
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
<script src="https://unpkg.com/scrollreveal"></script>
<script>
    window.sr = ScrollReveal({ duration: 1500 });
    sr.reveal(".site-content .d-flex");
    sr.reveal(".row .single-content");
</script>
</body>
</html>
