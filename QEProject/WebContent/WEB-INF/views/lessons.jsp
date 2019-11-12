<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
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
    
    <title>Musicians Headquaters</title>

</head>


<body>

   <!--Navigation bar at the top of the page-->
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">

        <!--Small image in navbar-->
        <img src="${pageContext.request.contextPath}/Images/clef.png" width="30" height="40" class="d-inline-block align-top" alt="">
      Musicians Headquaters</a>
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
                <img src="${pageContext.request.contextPath}/Images//lessonsBannerFinal.png" class="d-block w-100" alt="...">
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
        <h1>Start your lessons today!</h1>
        <img src="../Images/placeholder300x300.png" alt="post img" class="pull-left img-responsive postImg img-thumbnail margin10">
        <article>
          <p>
            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text
            ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only 
            five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release
            of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of
            Lorem Ipsum.
          </p>
          <p>
            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text
            ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only 
            five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release
            of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of
            Lorem Ipsum.
          </p>
                               
          <h2>In-person and One-on-one!</h2>
          <br>
          
          <div id="fb-root"></div>
          <script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v4.0"></script>
          <div class="fb-video" data-href="https://www.facebook.com/123458257724204/videos/vb.123458257724204/1702875589782455/?type=2&amp;theater" data-width="1000" data-show-text="false">
          <blockquote cite="https://developers.facebook.com/123458257724204/videos/1702875589782455/" class="fb-xfbml-parse-ignore"><a href="https://developers.facebook.com/123458257724204/videos/1702875589782455/"></a>
          <p></p>Posted by <a href="https://www.facebook.com/Musicians-Headquarters-123458257724204/">Musicians Headquarters</a> on Tuesday, December 19, 2017</blockquote></div>


          <h2>Contact Us!</h2>
          <p>
            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text
            ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only 
            five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release
            of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of
            Lorem Ipsum.
          </p>
                             
                         
                         
        </article>
                    
      </div>
    </div>
  </div>


</body>

<!--JQuery library link-->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


</html>