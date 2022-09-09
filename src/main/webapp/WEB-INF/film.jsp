<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="fragments/pageheader.jspf"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="content">
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="today" value="${now}" pattern="yyyy-MM-dd" />
	<c:if test="${!empty cinema }">
		<h1>${cinema.name }</h1>
	</c:if>
	<div class="filmdetails">
		<div class="leftbuffer"></div>
		<div class="leftpane">
			<img src="movie/poster/${film.thumbLink }" alt="${film.thumbLink }"
				width="243" height="360">
		</div>
		<div class="rightpane">
			<h1>${film.name}</h1>
			<div class="rightinfo">
				<span class="filminfo">${film.info}</span>
			</div>
			<hr>
			<div class="rightdetails">
				<div>
					<c:forEach items="${film.genres}" var="genre">
						<div class="genretile">
							<span>${genre.name }</span>
						</div>
					</c:forEach>
				</div>
				<span><i>${film.duration} minutes</i></span>
			</div>			
			<div>
				<c:if test="${!empty cinema }">
				<form action="film" method="get">
				<input type="hidden" name="cinemaid" value="${param.cinemaid }"/>
				<input type="hidden" name="filmid" value="${param.filmid }"/>
					<input type="date" name="date" value="${date }" min="${today}" />
					<button value="submit">Submit</button>
				</form>
					<form action="reserve" method="post">
					<div class="seancelist">
						<c:forEach items="${cinema.seances}" var="seance" varStatus="seanceLoop">
								<input type="radio" class="hidden" name="sc" value="${seance.id }" id="sc${seance.id }"  
								<c:if test="${seanceLoop.index == '0'}">
								checked
								</c:if>>
								<label class="seancel" for="sc${seance.id }">${seance.heureSeance.hour}:${seance.heureSeance.minute}</label>
						</c:forEach>
						</div>
						<label for="quantity"># of seats:</label><input type="number" id="quantity" name="quantity" value="1"/>
						<c:if test="${!empty sessionScope.signedin}">
							<button value="submit">Reserve</button>
						</c:if>
						<c:if test="${empty sessionScope.signedin}">
							<button onclick="openForm()" type="button">Reserve</button>
						</c:if>
					</form>
				</c:if>
			</div>			
		</div>
	</div>
</div>
<%@ include file="fragments/pagefooter.jspf"%>
