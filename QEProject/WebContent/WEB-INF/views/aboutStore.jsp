<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <!--Bootstrap library link-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!--Navigation bar, Carousel Banner & About section CSS sheet-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarStyles.css">
    <!--Social Media section sheet-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/socialMediaStyles.css">

    <!--Icon that appears on Website's tab-->
    <link rel='icon' href='${pageContext.request.contextPath}/Images/favicon.ico' type='image/x-icon'/>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/staticInfoPage.css">
    
    <title>Musicians Headquarters</title>

</head>

<body>

    <!--Navigation bar at the top of the page-->
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">

        <!--Small image in navbar-->
        <img src="${pageContext.request.contextPath}/Images/clef.png" width="30" height="40" class="d-inline-block align-top" alt="">
      Musicians Headquarters</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <!--List of items within navbar.
    Specify redirects to other pages here!-->
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">

        <li class="nav-item active">
          <a class="nav-link" href="${pageContext.request.contextPath}/HomeServlet">Home <span class="sr-only">(current)</span></a>
        </li>

          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/HomeServlet/signIn">Inventory</a>
          </li>

        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/HomeServlet/showRepairsPage">Repairs</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/HomeServlet/showLessonsPage">Lessons</a>
        </li>

        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/HomeServlet/showAboutPage">About Store</a>
        </li>
        
      </ul>
    </div>
  </nav>
    
    <!--Banner slide show of pictures.-->
    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
    
            <!--Specify banner images here!
            Make sure they are 1400x600 Resolution!-->

            <div class="carousel-item active">
                <img src="${pageContext.request.contextPath}/Images/homeBannerFour.png" class="d-block w-100" alt="...">
            </div>

            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/Images/lessonsBannerFinal.png" class="d-block w-100" alt="...">
            </div>

            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/Images/lessonsBannerTwo.jpg" class="d-block w-100" alt="...">
            </div>

    
        </div>
        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
    </div>

    <!--Content of the page. Includes picture and embbeded video-->
    <div class="container">
    
      <div class="col-md-10 blogShort">
        <h1>More than a music store</h1>
        <img src="${pageContext.request.contextPath}/Images/aboutImageOne.jpg" alt="post img" class="pull-left img-responsive postImg img-thumbnail margin10">
        <article>
          <p>

           Musicians Headquarters has been serving the Dallas Metro area for over 40 years by providing a great
           selection of musical instruments and audio equipment.

          </p>
          <p>

              We offer Guitar, Drums and Bass lessons for players of all ages and skill levels. 
              The lessons are one-on-one and can be about any topic of your choosing. We even provide all the equipment you might need to start learning!
              We offer the lessons through Johnnie Davison, an experienced musician with over 40 years of playing and teaching. Whether your
              goal is to rock for a crowd or impress your friends at a party, know that you are in good hands when you take lessons at Musicians
              Headquarters.
  
          </p>
         
        </article>
                    
      </div>
    </div>
  </div>

   <!--Footer of the entire webpage-->
   <div class="footer-dark">
      <footer>
          
        <p class="copyright">Musicians Headquarters © 2019</p>
         
      </footer>
    </div>

  
 
</body>

<!--JQuery library link-->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

       
</html>