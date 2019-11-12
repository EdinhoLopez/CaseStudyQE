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
    
    <title>Musicians Headquarters</title>
</head>
<!--Home Page of Website-->
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
            <img src="${pageContext.request.contextPath}/Images/storeBannerTest.jpg" class="d-block w-100" alt="...">
        </div>
     
      <div class="carousel-item">
          <img src="${pageContext.request.contextPath}/Images/storeBannerOneFinal.png" class="d-block w-100" alt="...">
      </div>

        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/Images/sgBanner.png" class="d-block w-100" alt="...">
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

  <!--Padding between carousel and text section-->
  <div style="background-color: black; padding-block: 35px; color: black">
   
    sample

  </div>
  
  <!--About section located under carousel banner-->
  <section class="about-sec parallax-section" id="about">
      <div class="container">
        <div class="row">
          <div class="col-md-4">
            <h2>Big<br>
              Enough to Serve You<br>
              <small>Small Enough to be Personal</small></h2>
          </div>

          <!--Text on the left of about section-->
          <div class="col-md-4">
            <p>Musicians Headquarters has been in business for
                40 years Serving the Dallas Metro Area. We carry a lot of major bands,
                 but we are known for our great selection of great used products. We buy,
                  sell, trade, consign, rent, and offer lessons.</p>
            <p>We are an independently owned
                company. Stop in and see what we have to offer!! New and used products arriving
                 daily. Let our friendly staff take care of you musical needs. Most of our
                  staff are professional musicians.</p>
          </div>

          <!--Text on the right of about section-->
          <div class="col-md-4">
              <p>We also offer the growing Hispanic market such specialty 
                items such as Bajo Sextos, Accordians, Guitarones, Requintos,
                 Congas, Etc. We also cater to Churches in the area. Our knowledgeable
                  staff can help you fix your church up with the correct gear. Se habla Español!
                   </p>
                    <p>- The Staff</p>

            <!--More info button on About section
            The Button will redict to the storeInfo page-->
            <p><a href="${pageContext.request.contextPath}/HomeServlet/showAboutPage" class="btn btn-dark btn-capsul">About us</a></p>
          </div>
        </div>
      </div>
  </section>

    
  <!--Padding between About section and Products slide section-->
  <div style="background-color: black; padding-block: 35px; color: black">
   
        sample
    
  </div>

  <!--Contact information section in Home Page-->
  <div class="container-fluid">
      <div class="row text-center">
        <div class="col-sm-3 col-xs-6 first-box">
            <h1><span class="glyphicon glyphicon-earphone"></span></h1>
            <h3>Phone</h3>
            <p>(972)-285-0509</p><br>
        </div>
        <div class="col-sm-3 col-xs-6 second-box">
            <h1><span class="glyphicon glyphicon-home"></span></h1>
            <a href="https://goo.gl/maps/xBL9hp4dU3CMhp5N7"><h3>Location</h3></a>
            <p>11935 Lake June Rd. Balch Springs, Texas 75180</p><br>
        </div>
       
        <div class="col-sm-3 col-xs-6 third-box">
            <h1><span class="glyphicon glyphicon-send"></span></h1>
            <a href = "mailto: abc@example.com"><h3>E-mail</h3></a>
            <p>Bruceswar@aol.com</p><br>
        </div>
        <div class="col-sm-3 col-xs-6 fourth-box">
          <h1><span class="glyphicon glyphicon-leaf"></span></h1>
          <a href="http://www.musiciansheadquarters.com/index.html"><h3>Web</h3></a>
            <p>www.musiciansheadquarters.com</p><br>
        </div>
      </div>
  </div>


  <!--Opening hours section-->

  <section class="openHours">

      <div class="row">

          <div class="col-sm-3"></div>
      
            <div class="col-sm-6">
      
              <div class="business-hours">
                <h2 class="title">Opening Hours</h2>
                <ul class="list-unstyled opening-hours">
                <li>Sunday <span class="pull-right">Closed</span></li>
                <li>Monday <span class="pull-right">2:00PM - 6:00PM</span></li>
                <li>Tuesday <span class="pull-right">2:00PM - 6:00PM</span></li>
                <li>Wednesday <span class="pull-right">Closed</span></li>
                <li>Thursday <span class="pull-right">2:00PM - 6:00PM</span></li>
                <li>Friday <span class="pull-right">2:00PM - 6:00PM</span></li>
                <li>Saturday <span class="pull-right">11:00AM - 6:00PM</span></li>
                </ul>
                
              </div>
      
              
              
          
          </div>
        </div>

  </section>
  
  <!--Social media section with buttons-->
  <section>
    <div class="container">
    
      <div class="kpx_login">
          <h3 class="kpx_authTitle">Keep up with the store!
          <div class="row kpx_row-sm-offset-3 kpx_socialButtons">

            <!--Facebook button-->
            <div class="col-xs-2 col-sm-2">
              <a href="https://www.facebook.com/Musicians-Headquarters-123458257724204/" class="btn btn-lg btn-block kpx_btn-facebook" data-toggle="tooltip" data-placement="top" title="Facebook">
                <i class="fa fa-facebook fa-2x"></i>
                <span class="hidden-xs"></span>
              </a>
            </div>

            <!--Yelp Button-->
            <div class="col-xs-2 col-sm-2">
              <a href="https://www.yelp.com/biz/musicians-headquarters-balch-springs" class="btn btn-lg btn-block kpx_btn-twitter" data-toggle="tooltip" data-placement="top" title="Yelp">
                <i class="fa fa-yelp fa-2x"></i>
                <span class="hidden-xs"></span>
              </a>
            </div>  

            <!--Ebay Button-->
            <div class="col-xs-2 col-sm-2">
              <a href="#" class="btn btn-lg btn-block kpx_btn-google-plus" data-toggle="tooltip" data-placement="top" title="EBay">
                <i class="fa fa-shopping-basket fa-2x"></i>
                <span class="hidden-xs"></span>
              </a>
            </div>  
      </div><br>
      
      </div>	    	
</div>

  </section>
  
  
 


  <!--Footer of the entire webpage-->
  <div class="footer-dark">
    <footer>
        
      <p class="copyright">Musicians Headquarters © 2019</p>
       
    </footer>
  </div>
      

</body>

<!--Js Script imports. JQuery library link. Bootstrap library. Custom scripts-->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script src="../Scripts/HomePageScripts.js"></script>
</html>