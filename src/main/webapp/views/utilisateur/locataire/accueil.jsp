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
  	<div class="container-fluid">
  	  <br>
  	  <br>
      <c:forEach var="immeuble" items="${immeubles}">
      	<a href="<%=request.getContextPath()%>/appartement-dispo?id=${immeuble.idImmeuble}" style="text-decoration: none;">
      		<button style="margin-left: 10px;" class="shadow p-3 mb-5 bg-body-tertiary">
         <style>
            .icon-text-container {
               display: flex;
               align-items: center; /* Pour aligner les éléments verticalement */
            }

            .icon-text-container svg {
               margin-right: 5px; /* Pour ajouter un espace entre l'icône et le texte */
            }
         </style>
         <img style="width: 320px;height: 300px; box-shadow: 1px 1px 1px 5px rgb(255, 255, 255);" src="${pageContext.request.contextPath}/image?id=${immeuble.idImmeuble}" alt="image">
         <div style="width: 320px; height: 70px;box-shadow: 1px 1px 1px 5px rgb(255, 255, 255); background-color: aliceblue; text-align: center;font-weight: bold;font-size: 15px">
            <div>
               ${immeuble.nom}
            </div>
            <br>
            <div class="icon-text-container">
               <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-geo-alt-fill" viewBox="0 0 16 16">
				  <path d="M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10m0-7a3 3 0 1 1 0-6 3 3 0 0 1 0 6"/>
				</svg>
               <span style="font-weight: bold;">Adresse: <span style="font-weight: 300">${immeuble.adresse}</span></span>
            </div>
         </div>
      </button>
      	</a>
      </c:forEach>
  	<%@include file="../../layouts/footer.jsp" %>
  	</div>
  </body>
</html>
