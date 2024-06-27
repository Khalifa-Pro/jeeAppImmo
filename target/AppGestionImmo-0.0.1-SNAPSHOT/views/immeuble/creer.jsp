<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  	<div class="container-fluid" style="margin-top: 100px">
  		<h3 style="text-align: center">AJOUTER VOTRE IMMEUBLE</h3>
  		<br>
  		<div class="row">
	  		<div class="col-md-3"></div>
	  		<div class="col-md-6">
	  			<form class="row g-3" method="post" action="<%=request.getContextPath()%>/ajouter-immeuble" enctype="multipart/form-data">
			  		<div class="form-floating mb-3">
					  <input name="nom" type="text" class="form-control" id="floatingInput" placeholder="Nom de l'immeuble">
					  <label for="floatingInput">Nom Immeuble</label>
					</div>
					<div class="form-floating mb-3">
					  <input name="adresse" type="text" class="form-control" id="floatingInput" placeholder="Adresse">
					  <label for="floatingInput">Adresse</label>
					</div>
					<div class="form-floating mb-3">
					  <textarea cols="5" rows="20" name="description" type="" class="form-control" id="floatingInput" placeholder="Description"></textarea>
					  <label for="floatingInput">Description</label>
					</div>
					<div class="form-floating mb-3">
					  <input name="image" type="file" class="form-control" id="floatingInput" placeholder="Image de l'immeuble">
					  <label for="floatingInput">Image Immeuble</label>
					</div>
				    <button type="submit" class="btn btn-primary mb-3">Ajouter</button>
				</form>
	  		</div>
	  		<div class="col-md-3"></div>
  		</div>
  	</div>
  	<%@include file="../layouts/footer.jsp" %>
  </body>
</html>