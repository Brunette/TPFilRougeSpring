<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="fragments/pageheader.jspf" %>
<div class="content">
<
<h1>${cinema.name}</h1>
<a href="https://maps.google.com/?q=0,0">
			<img class="geobtn" src="thumbs/googlemap.png" width="25" height="35">
			</a>	
					<c:forEach items="${cinema.films}" var="film">
					<div class="cinetile inline">
						<a href="film?cinemaid=${cinema.id}&filmid=${film.id}">
						<img src="movie/${film.thumbLink }" alt="${film.thumbLink }" width="162" height="240">
					</a>
					</div>
					</c:forEach>		 
	    </div>
</div>
<%@ include file="fragments/pagefooter.jspf" %>
