<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 3/20/2020
  Time: 1:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-light">
        <a href="index?lang=${sessionScope.locale}" class="navbar-brand ml-3">Elizabeth</a>
        <button class="navbar-toggler" type="button" data-toggler="collapse" data-target="#navbarMenu"
                aria-controls="navbarMenu" aria-expanded="false" aria-label="Toggle Navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse"></div>
        <div class="collapse navbar-collapse" id="navbarMenu">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item ${requestScope.hActive?'active':''}">
                    <a href="index?lang=${sessionScope.locale}" class="nav-link">${requestScope.bundle.getString("home")}</a>
                </li>
                <li class="nav-item ${requestScope.mActive?'active':''}">
                    <a href="menu?type=MAIN_DISH&page=1&lang=${sessionScope.locale}" class="nav-link">${requestScope.bundle.getString("menu")}</a>
                </li>
                <li class="nav-item ${requestScope.oActive?'active':''}">
                    <a href="myOrders?page=1&lang=${sessionScope.locale}" class="nav-link">${requestScope.bundle.getString("my.orders")}</a>
                </li>
                <c:if test="${sessionScope.get(\"user\").getRole().name() == 'ADMIN'}">
                    <li class="nav-item ${requestScope.aActive?'active':''}">
                        <a href="admin?page=1&lang=${sessionScope.locale}" class="nav-link">${requestScope.bundle.getString("admin.page")}</a>
                    </li>
                </c:if>
            </ul>
            <div class="dropdown mr-3">
                <button class="btn btn-outline-warning dropdown-toggle btn-drop" type="button"
                        id="dropdownMenuButton" data-toggle="dropdown">EN/RU</button>
                <div class="dropdown-menu">
                    <a class="dropdown-item"
                       href="${requestScope['javax.servlet.forward.servlet_path']}?${requestScope['javax.servlet.forward.query_string'].replace("lang=ru", "lang=en")}">
                        ENGLISH
                    </a>
                    <a class="dropdown-item"
                       href="${requestScope['javax.servlet.forward.servlet_path']}?${requestScope['javax.servlet.forward.query_string'].replace("lang=en", "lang=ru")}">
                        RUSSIAN
                    </a>
                </div>
            </div>
            <form class="form-inline my-2 mr-3 my-lg-0">
                <a href="basket?lang=${sessionScope.locale}" class="d-flex btn menu-right-btn btn-warning">${requestScope.bundle.getString("basket")}:
                    <span id="totalDishes" class="ml-2">${sessionScope.get("inBasket")}</span>
                </a>
            </form>
            <a href="logout"><em class="fas fa-sign-out-alt"></em></a>
        </div>
    </nav>
</header>
