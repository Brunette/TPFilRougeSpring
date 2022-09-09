<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="fragments/pageheader.jspf"%>
<div class="content">
	<div>
		<form action="films" method="get">
			<div class="genretiles">
				<c:forEach items="${genres}" var="genre">
					<input type="checkbox" class="hidden" name="${genre.name }"
						id="cb${genre.name }"
						<c:if test="${not empty param[genre.name]}" >
							checked	
						</c:if>/>
					<label for="cb${genre.name }">${genre.name }</label>
				</c:forEach>
			</div>
			<button>Submit</button>
			<button type="button" onclick="httpBlankGet('films')">Clear</button>
		</form>
	</div>
	<c:forEach items="${films}" var="film">
		<a href="film?&filmid=${film.id}"> <img
			src="movie/${film.thumbLink }" alt="${film.thumbLink }" width="162"
			height="240">
		</a>
	</c:forEach>
</div>
<%@ include file="fragments/pagefooter.jspf"%>
