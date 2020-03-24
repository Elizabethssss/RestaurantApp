<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 3/20/2020
  Time: 1:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-light">
        <a href="index" class="navbar-brand ml-3">Elizabeth</a>
        <button class="navbar-toggler" type="button" data-toggler="collapse" data-target="#navbarMenu"
                aria-controls="navbarMenu" aria-expanded="false" aria-label="Toggle Navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse"></div>
        <div class="collapse navbar-collapse" id="navbatMenu">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item ${requestScope.hActive?'active':''}">
                    <a href="index" class="nav-link">Home</a>
                </li>
                <li class="nav-item ${requestScope.mActive?'active':''}">
                    <a href="menu?type=MAIN_DISH&page=1" class="nav-link">Menu</a>
                </li>
                <li class="nav-item ${requestScope.pActive?'active':''}">
                    <a href="profile" class="nav-link">Profile</a>
                </li>
            </ul>

            <form class="form-inline my-2 my-lg-0">
                <a href="#" class="d-flex btn menu-right-btn btn-warning">Basket: <span class="ml-2">
                    ${sessionScope.get("inBasket")}
                </span></a>
            </form>
        </div>
    </nav>
</header>
