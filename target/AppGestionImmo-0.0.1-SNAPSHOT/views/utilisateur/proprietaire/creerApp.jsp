<%@page import="sn.isi.dev.entities.Immeuble"%>
<%@page import="java.util.List"%>
<%@page import="sn.isi.dev.dao.Implementations.ImmeubleImpl"%>
<%@page import="sn.isi.dev.dao.Repositories.IImmeuble"%>
<%@page import="sn.isi.dev.dao.connexion.SingletonConnection"%>
<%@page import="org.hibernate.SessionFactory"%>
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
    <%@include file="../../layouts/navbarProp.jsp" %>
  	<div class="container-fluid" style="margin-top: 100px">
  		<h3 style="text-align: center">AJOUTER VOTRE APPARTEMENT</h3>
  		<br>
  		<div class="row">
	  		<div class="col-md-3"></div>
	  		<div class="col-md-6">
	  			<form class="row g-3" method="post" action="<%=request.getContextPath()%>/ajouter-appartement" enctype="multipart/form-data">
			    <input type="hidden" name="idImmeuble" value="${immeuble.idImmeuble}">
			    <div class="form-floating mb-3">
			        <input name="numero_appt" type="text" class="form-control" id="floatingInput" placeholder="Numéro de l'appartement">
			        <label for="floatingInput">N° Appartement</label>
			    </div>
			    <div class="form-floating mb-3">
			        <input name="nombre_pieces" type="text" class="form-control" id="floatingInput" placeholder="Nombre de pièces">
			        <label for="floatingInput">Nombre de pièces</label>
			    </div>
			    <div class="form-floating mb-3">
			        <input name="superficie" type="text" class="form-control" id="floatingInput" placeholder="Superficie">
			        <label for="floatingInput">Superficie</label>
			    </div>
			    <div class="form-floating mb-3">
			        <input name="image" type="file" class="form-control" id="floatingInput" placeholder="Image de l'appartement">
			        <label for="floatingInput">Image appartement</label>
			    </div>
			    <div class="form-floating mb-3">
			        <input name="loyer" value="0" type="text" class="form-control" id="floatingInput" hidden="">
			    </div>
			    <div class="form-floating mb-3">
			        <input name="archiver" value="0" type="text" class="form-control" id="floatingInput" hidden="">
			    </div>
			    <button type="submit" class="btn btn-primary mb-3">Ajouter</button>
			</form>
	  		</div>
	  		<div class="col-md-3"></div>
  		</div>
  	</div>
  	<%@include file="../../layouts/footer.jsp" %>
  </body>
</html>
