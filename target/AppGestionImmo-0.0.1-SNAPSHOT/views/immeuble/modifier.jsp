<%@page import="sn.isi.dev.entities.Immeuble"%>
<%@page import="sn.isi.dev.dao.Implementations.ImmeubleImpl"%>
<%@page import="sn.isi.dev.dao.Repositories.IImmeuble"%>
<%@page import="sn.isi.dev.dao.connexion.SingletonConnection"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Modifiaction immeuble</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  </head>
  <body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <%@include file="../layouts/navbar.jsp" %>
  	<div class="container-fluid" style="margin-top: 100px">
  		<h3 style="text-align: center">MODIFIER VOTRE IMMEUBLE</h3>
  		<br>
  		<div class="row">
	  		<div class="col-md-3"></div>
	  		<div class="col-md-6">
	  			<%
	  			    SessionFactory sf = SingletonConnection.getSessionFactory();
	  				long id = Long.parseLong(request.getParameter("id"));
	  				IImmeuble iImmeuble = new ImmeubleImpl(sf);
	  				Immeuble immeuble = iImmeuble.gestImmeubleById(id);
                %>
	  			<form class="row g-3" method="post" action="<%=request.getContextPath()%>/modifier-immeuble">
			  		<div class="form-floating mb-3">
					  <input hidden name="id" value="<%=immeuble.getIdImmeuble()%>" type="number" class="form-control" id="floatingInput">
					</div>
			  		<div class="form-floating mb-3">
					  <input name="nom" value="<%=immeuble.getNom()%>" type="text" class="form-control" id="floatingInput" placeholder="Nom Immeuble">
					  <label for="floatingInput">Nom Immeuble</label>
					</div>
					<div class="form-floating mb-3">
					  <input name="adresse" value="<%=immeuble.getAdresse()%>" type="text" class="form-control" id="floatingInput" placeholder="Adresse">
					  <label for="floatingInput">Adresse</label>
					</div>
					<div class="form-floating mb-3">
					  <input name="description" value="<%=immeuble.getDescription()%>" type="text" class="form-control" id="floatingInput" placeholder="Description">
					  <label for="floatingInput">Description</label>
					</div>
				    <button type="submit" class="btn btn-primary mb-3">Modifier</button>
				</form>
	  		</div>
	  		<div class="col-md-3"></div>
  		</div>
  	</div>
  	<%@include file="../layouts/footer.jsp" %>
  </body>
</html>