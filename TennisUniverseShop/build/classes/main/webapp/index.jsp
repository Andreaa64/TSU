<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Collection<?> products = (Collection<?>) request.getAttribute("products");
if(products == null) {
	response.sendRedirect("./product?red=index");	
	return;
}

ProductBean product = (ProductBean) request.getAttribute("product");

%>

<!DOCTYPE html>

<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,main.java.it.unisa.model.ProductBean,main.java.it.unisa.model.Cart"%>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>TUS - Home</title>

  <!-- 
    - favicon
  -->
  <link rel="shortcut icon" href="./img/pallina.png" type="image/svg+xml">

  <!-- 
    - custom css link
  -->
  <link rel="stylesheet" href="./style.css">

  <!-- 
    - google font link
  -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link
    href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@300;400;500;600;700&family=Roboto:wght@400;500;700&display=swap"
    rel="stylesheet">

  <!-- 
    - preload banner
  -->
  <link rel="preload" href="./assets/images/hero-banner.png" as="image">

</head>

<body id="top">

  <!-- 
    - #HEADER
  -->

  <header class="header" data-header>
    <div class="container">

      <div class="overlay" data-overlay></div>

      <a href="#" class="logo">
        <img src="./img/logo_senza_sfondo.svg" width="160" height="50" alt="tennis logo">
      </a>

      <button class="nav-open-btn" data-nav-open-btn aria-label="Open Menu">
        <ion-icon name="menu-outline"></ion-icon>
      </button>

      <nav class="navbar" data-navbar>

        <button class="nav-close-btn" data-nav-close-btn aria-label="Close Menu">
          <ion-icon name="close-outline"></ion-icon>
        </button>

        <a href="#" class="logo">
          <img src="./img/logo_senza_sfondo.svg" width="190" height="50" alt="tennis logo">
        </a>

        <ul class="navbar-list">

          <li class="navbar-item">
            <a href="#" class="navbar-link">Home</a>
          </li>

          <li class="navbar-item">
            <a href="#" class="navbar-link">About</a>
          </li>

          <li class="navbar-item">
            <a href="./ProductView.jsp" class="navbar-link">Prodotti</a>
          </li>

          <li class="navbar-item">
            <a href="#" class="navbar-link">Blog</a>
          </li>

          <li class="navbar-item">
            <a href="#" class="navbar-link">Contatti</a>
          </li>

        </ul>

        <ul class="nav-action-list">

          <li>
            <button class="nav-action-btn">
              <ion-icon name="search-outline" aria-hidden="true"></ion-icon>

              <span class="nav-action-text">Search</span>
            </button>
          </li>

          <li>
            <a href="./login-form.jsp" class="nav-action-btn">
              <ion-icon name="person-outline" aria-hidden="true"></ion-icon>

              <span class="nav-action-text">Login / Register</span>
            </a>
          </li>

          <li>
            <button class="nav-action-btn">
              <ion-icon name="heart-outline" aria-hidden="true"></ion-icon>

              <span class="nav-action-text">Wishlist</span>


              <!-- deve diventare dinamico, per adesso e statico -->
              <data class="nav-action-badge" value="5" aria-hidden="true">5</data>
            </button>
          </li>

          <li>
          <!-- Somma carello da far diventare dinamico -->
          <form action="./pagamento.html">
            <button type="submit" class="nav-action-btn">
              <ion-icon name="bag-outline" aria-hidden="true"></ion-icon>

              <data class="nav-action-badge" value="4" aria-hidden="true">4</data>
            </button>
            </form> 
          </li>

        </ul>

      </nav>

    </div>
  </header>





  <main>
    <article>

      <!-- 
        - #HERO
      -->

      <section class="section hero" style="background-image: url('./img/sfondotennis.jpg')">
        <div class="container">

  	        <h2 class="h1 hero-title">
            Tennis Universe Shop <strong>Shop Collection</strong>
          </h2>

          <p class="hero-text" style="color: white">
            Scopri i nostri migliori prodotti: dalle racchette ai borsoni, per dominare Wimbledon!
            
          </p>

          <button class="btn btn-primary">
            <span>Shop Now</span>

            <ion-icon name="arrow-forward-outline" aria-hidden="true"></ion-icon>
          </button>

        </div>
      </section>





      <!-- 
        - #COLLECTION
      -->

      <section class="section collection">
        <div class="container">

          <ul class="collection-list has-scrollbar">

            <li>
            <h3 class="h4 card-title">Sinner Collections</h3>
              <div class="collection-card" style="background-image: url('./img/sinner.jpg')">
                
				<br><br><br><br><br>
                <a href="#" class="btn btn-secondary">
                   <span>Explore All</span>

                  <ion-icon name="arrow-forward-outline" aria-hidden="true"></ion-icon>
                </a>
              </div>
            </li>

            <li>
            <h3 class="h4 card-title">Alcaraz Collections</h3>
              <div class="collection-card" style="background-image: url('./img/Alcaraz.jpg')">
                
				<br><br><br><br><br>
                <a href="#" class="btn btn-secondary">
                  <span>Explore All</span>

                  <ion-icon name="arrow-forward-outline" aria-hidden="true"></ion-icon>
                </a>
              </div>
            </li>

            <li>
            <h3 class="h4 card-title">Nadal Collections</h3>
              <div class="collection-card" style="background-image: url('./img/nadal.jpg')">
                
				<br><br><br><br><br>
                <a href="#" class="btn btn-secondary">
                  <span>Explore All</span>

                  <ion-icon name="arrow-forward-outline" aria-hidden="true"></ion-icon>
                </a>
              </div>
            </li>

          </ul>

        </div>
      </section>





      <!-- 
        - #PRODUCT
      -->

      <section class="section product">
        <div class="container">

          <h2 class="h2 section-title">Più venduti!</h2>

          <ul class="filter-list">

            <li>
              <button class="filter-btn  active">All</button>
            </li>

            <li>
              <button class="filter-btn">Borsoni</button>
            </li>

            <li>
              <button class="filter-btn">Racchette</button>
            </li>

            <li>
              <button class="filter-btn">Palline</button>
            </li>

            <li>
              <button class="filter-btn">Accessori</button>
            </li>
            
            <li>
              <button class="filter-btn">Corde</button>
            </li>

          </ul>

          <ul class="product-list">

<!-- Inizire da qua -->

<%
            int i=0;
			if (products != null && products.size() != 0) {
				
				Iterator<?> it = products.iterator();
				while (it.hasNext() && i<8) {
					i++;
					ProductBean bean = (ProductBean) it.next();
		%>
            <li class="product-item">
              <div class="product-card" tabindex="0">

                <figure class="card-banner">
                  <img src="./assets/images/product-1.jpg" width="312" height="350" loading="lazy"
                    alt="Running Sneaker Shoes" class="image-contain">

                  <div class="card-badge">New</div>

                  <ul class="card-action-list">

                    <li class="card-action-item">
                      <button class="card-action-btn" aria-labelledby="card-label-1">
                        <ion-icon name="cart-outline"></ion-icon>
                      </button>

                      <div class="card-action-tooltip" id="card-label-1">Add to Cart</div>
                    </li>

                    <li class="card-action-item">
                      <button class="card-action-btn" aria-labelledby="card-label-2">
                        <ion-icon name="heart-outline"></ion-icon>
                      </button>

                      <div class="card-action-tooltip" id="card-label-2">Add to Whishlist</div>
                    </li>

                    <li class="card-action-item">
                      <button class="card-action-btn" aria-labelledby="card-label-3">
                        <ion-icon name="eye-outline"></ion-icon>
                      </button>

                      <div class="card-action-tooltip" id="card-label-3">Quick View</div>
                    </li>

                    <li class="card-action-item">
                      <button class="card-action-btn" aria-labelledby="card-label-4">
                        <ion-icon name="repeat-outline"></ion-icon>
                      </button>

                      <div class="card-action-tooltip" id="card-label-4">Compare</div>
                    </li>

                  </ul>
                </figure>

                <div class="card-content">


                  <h3 class="h3 card-title">
                    <a href="#"><%=bean.getTipo() %> di <%=bean.getMarca() %></a>
                  </h3>

                  <data class="card-price" value="180.85"><%= bean.getPrezzo() %></data>

                </div>

              </div>
            </li>
            <%
            }
			}
				%>



          </ul>

        </div>
      </section>

     

      <!-- 
        - #SERVICE
      -->

      <section class="section service">
        <div class="container">

          <ul class="service-list">

            <li class="service-item">
              <div class="service-card">

                <div class="card-icon">
                  <img src="./img/service-1.png" width="53" height="28" loading="lazy" alt="Service icon">
                </div>

                <div>
                  <h3 class="h4 card-title">Consegna Gratuita</h3>

                  <p class="card-text">
                    Per gli ordini sopra <span>€150</span>
                  </p>
                </div>

              </div>
            </li>

            <li class="service-item">
              <div class="service-card">

                <div class="card-icon">
                  <img src="./img/service-2.png" width="43" height="35" loading="lazy" alt="Service icon">
                </div>

                <div>
                  <h3 class="h4 card-title">Pagamento veloce</h3>

                  <p class="card-text">
                    100% pagamento sicuro!
                  </p>
                </div>

              </div>
            </li>

            <li class="service-item">
              <div class="service-card">

                <div class="card-icon">
                  <img src="./img/service-3.png" width="40" height="40" loading="lazy" alt="Service icon">
                </div>

                <div>
                  <h3 class="h4 card-title">Rimborso assicurato</h3>

                  <p class="card-text">
                    Entro 30 giorni
                  </p>
                </div>

              </div>
            </li>

            <li class="service-item">
              <div class="service-card">

                <div class="card-icon">
                  <img src="./img/service-4.png" width="40" height="40" loading="lazy" alt="Service icon">
                </div>

                <div>
                  <h3 class="h4 card-title">Supporto 24/7</h3>

                  <p class="card-text">
                    Supporto veloce!
                  </p>
                </div>

              </div>
            </li>

          </ul>

        </div>
      </section>





      <!-- 
        - #INSTA POST
      -->

      <section class="section insta-post">

        <ul class="insta-post-list has-scrollbar">

          <li class="insta-post-item">
            <img src="./assets/images/insta-1.jpg" width="100" height="100" loading="lazy" alt="Instagram post"
              class="insta-post-banner image-contain">

            <a href="#" class="insta-post-link">
              <ion-icon name="logo-instagram"></ion-icon>
            </a>
          </li>

          <li class="insta-post-item">
            <img src="./assets/images/insta-2.jpg" width="100" height="100" loading="lazy" alt="Instagram post"
              class="insta-post-banner image-contain">

            <a href="#" class="insta-post-link">
              <ion-icon name="logo-instagram"></ion-icon>
            </a>
          </li>

          <li class="insta-post-item">
            <img src="./assets/images/insta-3.jpg" width="100" height="100" loading="lazy" alt="Instagram post"
              class="insta-post-banner image-contain">

            <a href="#" class="insta-post-link">
              <ion-icon name="logo-instagram"></ion-icon>
            </a>
          </li>

          <li class="insta-post-item">
            <img src="./assets/images/insta-4.jpg" width="100" height="100" loading="lazy" alt="Instagram post"
              class="insta-post-banner image-contain">

            <a href="#" class="insta-post-link">
              <ion-icon name="logo-instagram"></ion-icon>
            </a>
          </li>

          <li class="insta-post-item">
            <img src="./assets/images/insta-5.jpg" width="100" height="100" loading="lazy" alt="Instagram post"
              class="insta-post-banner image-contain">

            <a href="#" class="insta-post-link">
              <ion-icon name="logo-instagram"></ion-icon>
            </a>
          </li>

          <li class="insta-post-item">
            <img src="./assets/images/insta-6.jpg" width="100" height="100" loading="lazy" alt="Instagram post"
              class="insta-post-banner image-contain">

            <a href="#" class="insta-post-link">
              <ion-icon name="logo-instagram"></ion-icon>
            </a>
          </li>

          <li class="insta-post-item">
            <img src="./assets/images/insta-7.jpg" width="100" height="100" loading="lazy" alt="Instagram post"
              class="insta-post-banner image-contain">

            <a href="#" class="insta-post-link">
              <ion-icon name="logo-instagram"></ion-icon>
            </a>
          </li>

          <li class="insta-post-item">
            <img src="./assets/images/insta-8.jpg" width="100" height="100" loading="lazy" alt="Instagram post"
              class="insta-post-banner image-contain">

            <a href="#" class="insta-post-link">
              <ion-icon name="logo-instagram"></ion-icon>
            </a>
          </li>

        </ul>

      </section>

    </article>
  </main>





  <!-- 
    - #FOOTER
  -->

  <footer class="footer">

    <div class="footer-top section">
      <div class="container">

        <div class="footer-brand">

          <a href="#" class="logo">
            <img src="./img/logo_senza_sfondo.svg" width="160" height="50" alt="Tennis logo">
          </a>

          <ul class="social-list">

            <li>
              <a href="#" class="social-link">
                <ion-icon name="logo-instagram"></ion-icon>
              </a>
            </li>

          </ul>

        </div>

        <div class="footer-link-box">

          <ul class="footer-list">

            <li>
              <p class="footer-list-title">Contattaci</p>
            </li>

            <li>
              <a href="tel:+3931149188" class="footer-link">
                <ion-icon name="call"></ion-icon>

                <span class="footer-link-text">+3931149188</span>
              </a>
            </li>

            <li>
              <a href="mailto:tennisUniverse@help.com" class="footer-link">
                <ion-icon name="mail"></ion-icon>

                <span class="footer-link-text">tennisUniverse@help.com</span>
              </a>
            </li>

          </ul>

          <ul class="footer-list">

            <li>
              <p class="footer-list-title"> Account</p>
            </li>

            <li>
              <a href="#" class="footer-link">
                <ion-icon name="chevron-forward-outline"></ion-icon>

                <span class="footer-link-text"> Account</span>
              </a>
            </li>

            <li>
              <a href="#" class="footer-link">
                <ion-icon name="chevron-forward-outline"></ion-icon>

                <span class="footer-link-text">Vedi Carello</span>
              </a>
            </li>

            <li>
              <a href="#" class="footer-link">
                <ion-icon name="chevron-forward-outline"></ion-icon>

                <span class="footer-link-text">Wishlist</span>
              </a>
            </li>


          </ul>


        </div>

      </div>
    </div>

    <div class="footer-bottom">
      <div class="container">

        <p class="copyright">
          &copy; 2024 <a href="#" class="copyright-link">I Paguri</a>. hakuna matata! 
        </p>

      </div>
    </div>

  </footer>





  <!-- 
    - #GO TO TOP
  -->

  <a href="#top" class="go-top-btn" data-go-top>
    <ion-icon name="arrow-up-outline"></ion-icon>
  </a>





  <!-- 
    - custom js link
  -->
  <script src="./js/script.js"></script>

  <!-- 
    - ionicon link
  -->
  <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
  <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

</body>

</html>