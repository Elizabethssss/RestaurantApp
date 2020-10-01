<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <!-- Bootstrap CDN CSS file-->
  <link rel="stylesheet" href="../libs/bootstrap-4.4.1/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="../styles/index.css" />
  <link rel="stylesheet" href="../styles/commons/header.css" />
  <link rel="stylesheet" href="../styles/commons/footer.css" />
<%--  <link rel="stylesheet" href="../libs/fontawesome-free-5.12.1-web/css/all.css"/>--%>

  <script src="https://kit.fontawesome.com/6d63b0bfa0.js" crossorigin="anonymous"></script>
  <title>${requestScope.bundle.getString("home")}</title>
</head>
<body>
<jsp:include page="commons/header.jsp"/>
<main>
  <div class="container-fluid p-0">
    <div class="site-content">
      <div class="d-flex justify-content-center shadow">
        <div class="d-flex flex-column">
          <h1 class="site-title">The Elizabeth's Restaurant</h1>
          <p class="site-desc">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo</p>
          <div class="d-flex flex-row">
            <a href="menu?type=MAIN_DISH&page=1&lang=${sessionScope.locale}" class="btn site-btn1 px-4 py-3 mr-4 btn-outline-warning">
              ${requestScope.bundle.getString("make.order")}
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="section-1">
    <div class="container text-center">
      <h1 class="heading-1">${requestScope.bundle.getString("index.1")}</h1>
      <p class="para-1">Lorem ipsum dolor sit amet consectetur adipisicing elit. Incidunt,
        consequatur! Rerum sequi recusandae id molestias totam fuga mollitia, impedit voluptates
        assumenda dolor et minima reprehenderit necessitatibus harum iste! A, veritatis?</p>

      <div class="row justify-content-center text-center">
        <div class="col-md-4">
          <div class="card">
            <img src="../img/img1.jpg" alt="Image1" class="card-img-top" />
            <div class="card-body">
              <h4 class="card-title">${requestScope.bundle.getString("service")}</h4>
              <p class="card-text">Lorem ipsum dolor, sit amet consectetur adipisicing elit.
                Quos quas odit placeat explicabo enim veniam!</p>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card">
            <img src="../img/img2.jpg" alt="Image2" class="card-img-top" />
            <div class="card-body">
              <h4 class="card-title">${requestScope.bundle.getString("restaurant")}</h4>
              <p class="card-text">Lorem ipsum dolor, sit amet consectetur adipisicing elit.
                Quos quas odit placeat explicabo enim veniam!</p>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card">
            <img src="../img/img3.jpg" alt="Image3" class="card-img-top" />
            <div class="card-body">
              <h4 class="card-title">${requestScope.bundle.getString("dishes")}</h4>
              <p class="card-text">Lorem ipsum dolor, sit amet consectetur adipisicing elit.
                Quos quas odit placeat explicabo enim veniam!</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="section-2">
    <div class="container-fluid">
      <div class="d-flex justify-content-end">
        <div class="d-flex flex-column m-4">
          <h1 class="heading-1"> ${requestScope.bundle.getString("index.2")}</h1>
          <p class="para">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Porro
            impedit repellat error.</p>
          <a href="myOrders?page=1&lang=${sessionScope.locale}" class="btn btn-danger">
            ${requestScope.bundle.getString("my.orders")}
          </a>
        </div>
      </div>
    </div>
  </div>

  <div class="section-3">
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <div class="d-flex flex-row">
            <em class="fas fa-question fa-3x m-2"></em>
            <div class="d-flex flex-column">
              <h3 class="m-2">${requestScope.bundle.getString("support")}</h3>
              <p class="m-2">Lorem ipsum dolor sit amet consectetur adipisicing elit. Nulla tenetur
                illo porro facere aliquid aliquam iure nobis facilis officiis officia corrupti
                nostrum sapiente delectus, aut animi autem sequi reprehenderit incidunt!</p>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="d-flex flex-row">
            <em class="fas fa-seedling fa-3x m-2"></em>
            <div class="d-flex flex-column">
              <h3 class="m-2">${requestScope.bundle.getString("health.food")}</h3>
              <p class="m-2">Lorem ipsum dolor sit amet consectetur adipisicing elit. Nulla tenetur
                illo porro facere aliquid aliquam iure nobis facilis officiis officia corrupti
                nostrum sapiente delectus, aut animi autem sequi reprehenderit incidunt!</p>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="d-flex flex-row">
            <em class="fas fa-rocket fa-3x m-2"></em>
            <div class="d-flex flex-column">
              <h3 class="m-2">${requestScope.bundle.getString("speed")}</h3>
              <p class="m-2">Lorem ipsum dolor sit amet consectetur adipisicing elit. Nulla tenetur
                illo porro facere aliquid aliquam iure nobis facilis officiis officia corrupti
                nostrum sapiente delectus, aut animi autem sequi reprehenderit incidunt!</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="section-4 bg-dark">
    <div class="container">
      <div class="row">
        <div class="col-md-7">
          <img src="../img/img5.jpg" alt="Image-5" width="590" />
        </div>
        <div class="col-md-5">
          <h1 class="text-white">${requestScope.bundle.getString("index.3")}</h1>
          <a href="menu?type=MAIN_DISH&page=1&lang=${sessionScope.locale}">${requestScope.bundle.getString("company.menu")}</a>
          <p class="para-1">Lorem ipsum dolor sit amet consectetur adipisicing elit. Qui porro
            ipsum illo! Sed at nam cum neque, magnam ab fugiat.</p>
        </div>
      </div>
    </div>
  </div>
</main>
<jsp:include page="commons/footer.jsp"/>

<!-- Bootstrap CDN CSS file -->
<script src="../libs/jquery/jquery-3.4.1.min.js"></script>
<script src="../libs/popper/popper.min.js"></script>
<script src="../libs/bootstrap-4.4.1/js/bootstrap.min.js"></script>
<script src="../libs/scrollreveal/scrollreveal.js"></script>
<script>
  window.sr = ScrollReveal({ duration: 1000 });
  sr.reveal(".site-content .d-flex");
  sr.reveal(".section-1 .card");
  sr.reveal(".section-2 .d-flex");
  sr.reveal(".section-3 .col-md-4");
  sr.reveal(".section-4 .col-md-7, .col-md-5");
</script>
</body>
</html>
