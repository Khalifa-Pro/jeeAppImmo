<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SEN'IMMEUBLE</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  </head>
  <body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  	<%@include file="../../layouts/navbarLoc.jsp" %>
  	<br>
  	<div class="container-fluid">
  		<div class="row">
  			<div class="col-md-2"></div>
  			<div class="col-md-8">
  				<a href="<%=request.getContextPath()%>/nouveau-appartement?id=${immeuble.idImmeuble}"
			  		style="
			  			border: 1px solid #0d6efd;
			  			background-color: #0d6efd;
			  			padding: 10px 10px 10px 10px;
			  			border-radius: 5px;
			  			color: white; 
			  			text-decoration: none;
			  			float: right;
			  		">
			  		Ajouter appartement
			  	</a>
  			</div>
  			<div class="col-md-2"></div>
  		</div>
  	</div>
  	<br>
  	<div class="row">
  		<div class="col-md-2"></div>
  		<div class="col-md-8">
  			<div class="card">
		  		<div class="card-header bg-primary">
		  			<strong style="color: white">LISTE DES APPARTEMENTS</strong>
		  		</div>
		  		<div class="card-body">
		  			<div class="row">
		  				<div class="col-md-8" style="float: left;">
		  					<form action="<%=request.getContextPath()%>/" method="post" style="display: flex; float: end;">
							    <div>
							        <input style="border:1px solid gray;" name="search" type="text" class="form-control" id="floatingInput" placeholder="Votre mot clé ..." required oninput="toggleButton(this)">
							    </div>
							    <button id="submitButton" style="margin-left: 5px;" type="submit" class="btn btn-success" disabled>
							        Rechercher
							    </button>
							</form>
							
							<script>
							    // This script will ensure the button remains enabled or disabled based on the input value
							    function toggleButton(input) {
							        document.getElementById('submitButton').disabled = input.value.trim() === "";
							    }
							</script>
		  								
		  				</div>
		  				<div class="col-md-2"></div>
		  			</div>
		  			<br>
		  			<table class="table table-bordered table-striped">
						<thead>
						    <tr>
						      <th scope="col">N° Appartements</th>
						      <th scope="col">Nombre de pièces</th>
						      <th scope="col">Superficie</th>
						      <th scope="col">Image</th>
						      <th scope="col">Actions</th>
						    </tr>
						  </thead>
						  <tbody>
						  	<c:forEach var="appartement" items="${appartements}">
                                <tr>
                                    <td>
                                        <c:out value="${appartement.numero_appt}" />
                                    </td>
                                    <td>
                                        <c:out value="${appartement.nombre_pieces}" />
                                    </td>
                                    <td>
                                        <c:out value="${appartement.superficie}" />
                                    </td>
                                    <td>
						                <img src="${pageContext.request.contextPath}/imageApp?id=${appartement.idAppartement}" alt="Image de l'appartement" style="width: 100px; height: auto;" />
						            </td>
							      <td>
		                            <a href="#" style="text-decoration: none">
		                                <button class="btn btn-primary">
		                                	Louer
		                                </button>
		                            </a>
		                            <a href="#" style="text-decoration: none; color: red">
		                               <button class="btn btn-success">
		                                	Payer
		                                </button>
		                            </a>
		                          </td>
						    	</tr>
						    </c:forEach>
						  </tbody>
					</table>
		  		</div>
		  	</div>
  		</div>
  		<div class="col-md-2"></div>
  	</div>
  	<br>
  	<div>
  	<%@include file="../../layouts/footer.jsp" %>
  	</div>
  </body>
</html>
