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
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="../styles/authorization.css" />
  <title>Authorization</title>
</head>
<body>
<div class="container ${requestScope.rightSide?'right-panel-active':''}" id="container">
  <div class="form-container sign-up-container">
    <form method="post" action="signUp">
      <h1>Create Account</h1>
      <input name="username" type="text" class="${requestScope.errorsMessages.containsKey("usernameError")?'error':''}"
             placeholder="Username"
             value="Dasha"/>
<%--             value="${requestScope.username}"/>--%>
      <p class="errorMsg">${requestScope.errorsMessages.containsKey("usernameError")?
      requestScope.errorsMessages.get("usernameError"):''}</p>
      <input name="email" type="email" class="${requestScope.errorsMessages.containsKey("emailError")?'error':''}"
             placeholder="Email"
             value="dassshka@kiev.ua"/>
<%--             value="${requestScope.email}"/>--%>
      <p class="errorMsg">${requestScope.errorsMessages.containsKey("emailError")?
            requestScope.errorsMessages.get("emailError"):''}</p>
      <input name="password1" type="password" class="${requestScope.errorsMessages.containsKey("passwordError")?'error':''}"
             placeholder="Password" value="dasha"/>
      <input name="password2" type="password" class="${requestScope.errorsMessages.containsKey("passwordError")?'error':''}"
             placeholder="Repeat  Password" id="pass2" value="dasha"/>
      <p class="errorMsg">${requestScope.errorsMessages.containsKey("passwordError")?
              requestScope.errorsMessages.get("passwordError"):''}</p>
      <button type="submit" class="btn">Sign Up</button>
    </form>
  </div>
  <div class="form-container sign-in-container">
    <form method="post" action="login">
      <h1>Login</h1>
      <input name="email" type="email" class="${requestScope.error?'error':''}"
             placeholder="Email"
             value="dassshka@kiev.ua"/>
<%--             value="${requestScope.email}"/>--%>
      <input name="password" type="password" class="${requestScope.error?'error':''}"
             placeholder="Password" value="dasha"/>
      <p class="errorMsg">${requestScope.error?requestScope.errorMessage:''}</p>
      <button type="submit" class="btn">Login</button>
    </form>
  </div>
  <div class="overlay-container">
    <div class="overlay">
      <div class="overlay-panel overlay-left">
        <h1>Welcome Back!</h1>
        <p>
          To keep connected with us please login with your personal info
        </p>
        <button class="ghost btn" id="loginSlideBtn">Login</button>
      </div>
      <div class="overlay-panel overlay-right">
        <h1>Hello, Friend!</h1>
        <p>Enter your personal details and start journey with us</p>
        <button class="ghost btn" id="signUpSlideBtn">Sign Up</button>
      </div>
    </div>
  </div>
</div>

<footer>
  <p>Created with <img src="../img/heart.png" alt="" /> by Elizabeth.</p>
</footer>

<script src="../app.js"></script>
</body>
</html>
