<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <!-- Bootstrap CDN CSS file-->
  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
        crossorigin="anonymous"/>
  <link rel="stylesheet" href="../styles/index.css" />
  <link rel="stylesheet" href="../styles/commons/header.css" />
  <link rel="stylesheet" href="../styles/commons/footer.css" />
  <link rel="stylesheet" href="../libs/fontawesome-free-5.12.1-web/css/all.css"/>
  <title>Home</title>
</head>
<body>
<jsp:include page="commons/header.jsp"/>
<main>
  <div class="container-fluid p-0">
    <div class="site-content">
      <div class="d-flex justify-content-center shadow">
        <div class="d-flex flex-column">
          <h1 class="site-title">The Elizabeth's Restaurant</h1>
          <p class="site-desc">Lorem, ipsum dolor sit amet consectetur adipisicing elit. Ullam, et.</p>
          <div class="d-flex flex-row">
            <a href="menu?type=MAIN_DISH&page=1" class="btn site-btn1 px-4 py-3 mr-4 btn-outline-warning">Make order</a>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div class="section-1">
    <div class="container text-center">
      <h1 class="heading-1">Delicious Food & Something About Us</h1>
      <p class="para-1">Lorem ipsum dolor sit amet consectetur adipisicing elit. Incidunt,
        consequatur! Rerum sequi recusandae id molestias totam fuga mollitia, impedit voluptates
        assumenda dolor et minima reprehenderit necessitatibus harum iste! A, veritatis?</p>

      <div class="row justify-content-center text-center">
        <div class="col-md-4">
          <div class="card">
            <img src="../img/img1.jpg" alt="Image1" class="card-img-top" />
            <div class="card-body">
              <h4 class="card-title">Title</h4>
              <p class="card-text">Lorem ipsum dolor, sit amet consectetur adipisicing elit.
                Quos quas odit placeat explicabo enim veniam!</p>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card">
            <img src="../img/img2.jpg" alt="Image2" class="card-img-top" />
            <div class="card-body">
              <h4 class="card-title">Title</h4>
              <p class="card-text">Lorem ipsum dolor, sit amet consectetur adipisicing elit.
                Quos quas odit placeat explicabo enim veniam!</p>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card">
            <img src="../img/img3.jpg" alt="Image3" class="card-img-top" />
            <div class="card-body">
              <h4 class="card-title">Title</h4>
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
          <h1 class="heading-1">Love & Share Your Love</h1>
          <p class="para">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Porro
            impedit repellat error.</p>
          <a href="#" class="btn btn-danger">My Profile</a>
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
              <h3 class="m-2">24/7 Support</h3>
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
              <h3 class="m-2">Health Food</h3>
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
              <h3 class="m-2">Speed</h3>
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
          <h1 class="text-white">Don't Know Where Get Food For Company?</h1>
          <a href="#">Company Menu</a>
          <p class="para-1">Lorem ipsum dolor sit amet consectetur adipisicing elit. Qui porro
            ipsum illo! Sed at nam cum neque, magnam ab fugiat.</p>
        </div>
      </div>
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
<script src="https://unpkg.com/scrollreveal"></script>
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
