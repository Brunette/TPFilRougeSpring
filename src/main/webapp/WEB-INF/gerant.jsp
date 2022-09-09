<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="fragments/pageheader.jspf"%>
<div class="content">
	<h1>Espace Gérant</h1>
	<h2>${cinema.name}</h2>
	<h3>Par Film</h3>
	<table>
		<tr>
			<th>Title</th>
			<th>Horaire et Salles</th>
		</tr>
		<c:forEach items="${cinema.films}" var="film">
			<tr>
				<td>${film.name }</td>
				<td><a
					href="modifyfilm?filmid=${film.id }&cinemaid=${cinema.id} ">EDIT</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<h3>Par Salle</h3>
	<table>
		<tr>
			<th>Salle</th>
			<th>Horaires et Salle</th>
		</tr>
		<c:forEach items="${cinema.salles}" var="salle">
			<tr>
				<td>${salle.salleNum }</td>
				<td><a href="modifysalle?salleid=${salle.id }">EDIT</a></td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${sessionScope.superuser.cinema == cinema}">
	<h3>Modications</h3>
	<div class="table">
		<div class="tablerow">
			<h3 class="tablecell">Date</h3>
			<h3 class="tablecell">Time</h3>
			<h3 class="tablecell">Salle</h3>
			<h3 class="tablecell filmname">Film</h3>
			<h3 class="tablecell">Type</h3>
			<h3 class="tablecell">User</h3>
			<h3 class="tablecell">Action</h3>
		</div>
		<c:forEach items="${modifications}" var="modification">
			<div class="tablerow">
				<div class="tablecell">
					<span>${modification.heureSeance.toLocalDate() }</span>
				</div>
				<div class="tablecell">
					<span>${modification.heureSeance.hour }:${modification.heureSeance.minute }</span>
				</div>
				<div class="tablecell">
					<span>${modification.salleId }</span>
				</div>
				<div class="tablecell filmname">
					<span>${modification.film.name }</span>
				</div>
				<div class="tablecell">
					<span>${modification.type }</span>
				</div>
				<div class="tablecell">
					<span>${modification.userEditor.username }</span>
				</div>
				<div class="tablecell">
					<form action="modifymod" method="post">
						<input type="hidden" name="modificationid"
							value=${modification.id } />
						<button type="submit">Accept</button>
					</form>
					<form action="modifymod" method="post">
					<input type="hidden" name="deleteid"
							value=${modification.id } />
						<button
							onclick="refuseMod('form_${modificaton.id }', ${modificaton.id })">Refuse</button>
					</form>
				</div>
			</div>
		</c:forEach>
		</div>
	</c:if>
	
	<%@ include file="fragments/pagefooter.jspf"%>