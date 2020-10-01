<%--
  Created by IntelliJ IDEA.
  User: Lizz
  Date: 3/15/2020
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="../styles/authorization.css" />
    <title>Authorization</title>
</head>
<body>
<div class="locale">
    <a href="login?lang=en">EN</a> / <a href="login?lang=ru">RU</a>
</div>
<div class="container ${requestScope.rightSide?'right-panel-active':''}" id="container">
    <div class="form-container sign-up-container">
        <form method="post" action="signUp?lang=${sessionScope.get("locale")}">
            <h1>${requestScope.bundle.getString("create.account")}</h1>
            <input name="username" type="text" class="${requestScope.errorsMessages.containsKey("usernameError")?'error':''}"
                   placeholder="${requestScope.bundle.getString("username")}"
                   value="Dasha"/>
            <%--             value="${requestScope.username}"/>--%>
            <p class="errorMsg">${requestScope.errorsMessages.containsKey("usernameError")?
                    requestScope.errorsMessages.get("usernameError"):''}</p>
            <input name="email" type="email" class="${requestScope.errorsMessages.containsKey("emailError")?'error':''}"
                   placeholder="${requestScope.bundle.getString("email")}"
                   value="dassshka@kiev.ua"/>
            <%--             value="${requestScope.email}"/>--%>
            <p class="errorMsg">${requestScope.errorsMessages.containsKey("emailError")?
                    requestScope.errorsMessages.get("emailError"):''}</p>
            <input name="password1" type="password" class="${requestScope.errorsMessages.containsKey("passwordError")?'error':''}"
                   placeholder="${requestScope.bundle.getString("password")}"
                   value="dasha"/>
            <input name="password2" type="password" class="${requestScope.errorsMessages.containsKey("passwordError")?'error':''}"
                   placeholder="${requestScope.bundle.getString("re.password")}" id="pass2"
                   value="dasha"/>
            <p class="errorMsg">${requestScope.errorsMessages.containsKey("passwordError")?
                    requestScope.errorsMessages.get("passwordError"):''}</p>
            <button type="submit" class="btn">${requestScope.bundle.getString("sign.up")}</button>
        </form>
    </div>
    <div class="form-container sign-in-container">

        <form method="post" action="login?lang=${sessionScope.get("locale")}">
            <h1>${requestScope.bundle.getString("login")}</h1>
            <input name="email" type="email" class="${requestScope.error?'error':''}"
                   placeholder="${requestScope.bundle.getString("email")}"
            <%--             value="admin@admin.com"/>--%>
                         value="dassshka@kiev.ua"/>
<%--                   value="${requestScope.email}"/>--%>
            <input name="password" type="password" class="${requestScope.error?'error':''}"
                   placeholder="${requestScope.bundle.getString("password")}"
            <%--             value="admin"/>--%>
                         value="dasha"/>
<%--            />--%>
            <p class="errorMsg">${requestScope.error?requestScope.errorMessage:''}</p>
            <button type="submit" class="btn">${requestScope.bundle.getString("login")}</button>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <h1>${requestScope.bundle.getString("sign.up.h1")}</h1>
                <p>${requestScope.bundle.getString("sign.up.p")}</p>
                <button class="ghost btn" id="loginSlideBtn">${requestScope.bundle.getString("login")}</button>
            </div>
            <div class="overlay-panel overlay-right">
                <h1>${requestScope.bundle.getString("login.h1")}</h1>
                <p>${requestScope.bundle.getString("login.p")}</p>
                <button class="ghost btn" id="signUpSlideBtn">${requestScope.bundle.getString("sign.up")}</button>
            </div>
        </div>
    </div>
</div>

<footer>
    <p>${requestScope.bundle.getString("footer.1")} <img src="../img/heart.png" alt="" /> ${requestScope.bundle.getString("footer.2")}</p>
</footer>

<script src="../app.js"></script>
</body>
</html>
