<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="../../libs/bootstrap-4.4.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../../styles/menu.css" />
    <link rel="stylesheet" href="../../styles/commons/header.css" />
    <link rel="stylesheet" href="../../styles/commons/footer.css" />
    <link rel="stylesheet" href="../../styles/error.css" />
<%--    <link rel="stylesheet" href="../../libs/fontawesome-free-5.12.1-web/css/all.css"/>--%>

    <script src="https://kit.fontawesome.com/6d63b0bfa0.js" crossorigin="anonymous"></script>
    <title>Error</title>
</head>
<body>
<jsp:include page="../commons/header.jsp"/>
<main class="error">
    <h1>${requestScope.bundle.getString("error")}</h1>
    <p>${requestScope.bundle.getString("error.500")}</p>
    <p>${requestScope.bundle.getString("error.500.msg")}</p>
    <a href="index?lang=${sessionScope.locale}">${requestScope.bundle.getString("go.home")}</a>
</main>
<jsp:include page="../commons/footer.jsp"/>
<script src="../../libs/scrollreveal/scrollreveal.js"></script>
</body>
</html>