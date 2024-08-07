<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  	<%@include file="../layouts/navbar.jsp" %>
  	<br>
  	<div class="container-fluid">
  		<div class="row">
  			<div class="col-md-2"></div>
  			<div class="col-md-8">
  				<a href="views/immeuble/creer.jsp"
			  		style="
			  			border: 1px solid #0d6efd;
			  			background-color: #0d6efd;
			  			padding: 10px 10px 10px 10px;
			  			border-radius: 5px;
			  			color: white; 
			  			text-decoration: none;
			  			float: right;
			  			
			  	">
			  		Ajouter immeuble
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
		  			<strong style="color: white">LISTE DES IMMEUBLES</strong>
		  		</div>
		  		<div class="card-body">
		  			<div class="row">
		  				<div class="col-md-8" style="float: left;">
		  					<form action="<%=request.getContextPath()%>/" method="post" style="display: flex; float: end;">
							    <div>
							        <input style="border:1px solid gray;" name="search" type="text" class="form-control" id="floatingInput" placeholder="Votre mot cl� ..." required oninput="toggleButton(this)">
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
						      <th scope="col">Nom Immeuble</th>
						      <th scope="col">Adresse</th>
						      <th scope="col">Description</th>
						      <th scope="col">Image</th>
						      <th scope="col">Actions</th>
						    </tr>
						  </thead>
						  <tbody>
						  	<c:forEach var="immeuble" items="${immeubles}">
                                <tr>
                                    <td>
                                        <c:out value="${immeuble.nom}" />
                                    </td>
                                    <td>
                                        <c:out value="${immeuble.adresse}" />
                                    </td>
                                    <td>
                                        <c:out value="${immeuble.description}" />
                                    </td>
                                    <td>
						                <img src="${pageContext.request.contextPath}/image?id=${immeuble.idImmeuble}" alt="Image de l'immeuble" style="width: 100px; height: auto;" />
						            </td>
							      <td>
		                            <a href="<%=request.getContextPath()%>/form-modifier-immeuble?id=${immeuble.idImmeuble}" style="text-decoration: none">
		                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
		                                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
		                                    <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
		                                </svg>
		                            </a>
		                            <a href="<%=request.getContextPath()%>/supprimer-immeuble?id=${immeuble.idImmeuble}" style="text-decoration: none; color: red">
		                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
		                                    <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5M8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5m3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0"/>
		                                </svg>
		                            </a>
		                            <a href="<%=request.getContextPath()%>/appartement?id=${immeuble.idImmeuble}" style="text-decoration: none; color: blue">
		                               <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-houses-fill" viewBox="0 0 16 16">
										  <path d="M7.207 1a1 1 0 0 0-1.414 0L.146 6.646a.5.5 0 0 0 .708.708L1 7.207V12.5A1.5 1.5 0 0 0 2.5 14h.55a2.5 2.5 0 0 1-.05-.5V9.415a1.5 1.5 0 0 1-.56-2.475l5.353-5.354z"/>
										  <path d="M8.793 2a1 1 0 0 1 1.414 0L12 3.793V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v3.293l1.854 1.853a.5.5 0 0 1-.708.708L15 8.207V13.5a1.5 1.5 0 0 1-1.5 1.5h-8A1.5 1.5 0 0 1 4 13.5V8.207l-.146.147a.5.5 0 1 1-.708-.708z"/>
									   </svg>
		                            </a>
		                            <a href="<%=request.getContextPath()%>/nouveau-appartement?id=${immeuble.idImmeuble}" style="text-decoration: none; color: green">
										<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-house-add-fill" viewBox="0 0 16 16">
										  <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 1 1-1 0v-1h-1a.5.5 0 1 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
										  <path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L8 2.207l6.646 6.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293z"/>
										  <path d="m8 3.293 4.712 4.712A4.5 4.5 0 0 0 8.758 15H3.5A1.5 1.5 0 0 1 2 13.5V9.293z"/>
										</svg>
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
  	<%@include file="../layouts/footer.jsp" %>
  	</div>
  </body>
</html>