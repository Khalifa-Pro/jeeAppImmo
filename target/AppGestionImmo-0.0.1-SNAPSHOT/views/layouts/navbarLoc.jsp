<nav class="navbar navbar-expand-lg bg-primary">
		  <div class="container-fluid">
		    <a class="navbar-brand" href="<%=request.getContextPath()%>/" style="color: orange; font-weight: bold; font-size: 25px;">SEN'IMMEUBLE</a>
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarNav">
		      <ul class="navbar-nav">
		        <li class="nav-item">
		          <a class="nav-link" href="#" style="color: white;">Contrat de locations</a>
		        </li>
		         <li class="nav-item">
		          <a class="nav-link" href="#" style="color: white;">Historique de paiements</a>
		        </li>
		        <li>
			        <button class="btn btn-success">
				        Notifications(1)
				    </button>
		        </li>
		        <li class="nav-item">
			        <form action="" method="post">
			        	<button type="submit" class="btn btn-danger">
			        		D�connexion
			        	</button>
			        </form>
			    </li>
		      </ul>
		    </div>
		  </div>
	</nav>
  </body>
</html>