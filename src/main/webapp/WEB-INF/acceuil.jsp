<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="fragments/pageheader.jspf"%>
<div class="content">
	<form action="acceuil" method="get">
		<label for="id_filter_ville">Filter:</label><input
			id="id_filter_ville" type="search" placeholder="Ville or CodePostal"
			name="filter" />
	</form>
	<div class="cinelist">
		<c:if test="${empty cinemas}">
			<span>No cinema's found with that information.</span>
		</c:if>
		<c:if test="${!empty cinemas}">
			<c:forEach items="${cinemas}" var="cinema" varStatus="cinemaLoop">
				<div class="cinelistitem">
					<div class="tileheader">
						<a class="cinematitle" href="cinema?cinemaid=${cinema.id}">${cinema.name}</a>
						<div class="geobtn">
							<span class="cinemaville"> ${cinema.address.ville} </span> <a
								class="" href="https://maps.google.com/?q=0,0"> <img
								src="thumbs/googlemap.png" width="25" height="35">
							</a>
						</div>
					</div>
					<div class="cinetiles">
					<button class="scrollbutton left" type="button" onmousedown="goLeft('cinetile_${cinemaLoop.index }')">&#60;</button>					
					<div id="cinetile_${cinemaLoop.index }" class="inner_cinetiles nobars">									
							<c:forEach items="${cinema.films}" var="film" varStatus="filmLoop">
								<div class="cinetile">
									<a href="film?cinemaid=${cinema.id}&filmid=${film.id}"> <img
										src="movie/${film.thumbLink }" alt="${film.thumbLink }"
										width="162" height="240">
									</a>
								</div>
							</c:forEach>
													
					</div>
					<button class="scrollbutton right" type="button" onmousedown="goRight('cinetile_${cinemaLoop.index }')">&#62;</button>
					</div>
					
				</div>
			</c:forEach>
		</c:if>
	</div>

</div>
<%@ include file="fragments/pagefooter.jspf"%>
