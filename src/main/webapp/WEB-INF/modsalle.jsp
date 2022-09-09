<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ include file="fragments/pageheader.jspf"%>
<div class="content">
	<h3>${film.name }</h3>

	<div class="table">
		<div class="tablerow">
			<h3 class="tablecell">Date</h3>
			<h3 class="tablecell">Time</h3>
			<h3 class="tablecell">Film</h3>
			<h3 class="tablecell">Actions</h3>
		</div>
		<c:forEach items="${seances}" var="seance">
			<form id="form_${seance.id }" class="tablerow" method="post" action="modifysalle">
				<div class="tablecell">
					<input type="date" name="date"
						value="${seance.heureSeance.toLocalDate() }" />
				</div>
				<c:if test="${seance.heureSeance.hour < 10 }">
					<div class="tablecell">
						<input type="time" name="time"
							value="0${seance.heureSeance.hour }:${seance.heureSeance.minute }" />
					</div>
				</c:if>
				<c:if test="${seance.heureSeance.hour >= 10 }">
					<div class="tablecell">
						<input type="time" name="time"
							value="${seance.heureSeance.hour }:${seance.heureSeance.minute }" />
					</div>
				</c:if>
				<div class="tablecell">
					<select name="filmid" id="id_film">
						<c:forEach items="${films}" var="film">
							<c:if test="${film.id eq seance.film.id }">
								<option value="${film.id }" selected="selected">${film.name }</option>
							</c:if>
							<c:if test="${film.id != seance.film.id }">
								<option value="${film.id }">${film.name }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="tablecell">
					<input type="hidden" name="salleid" value=${salle.id } />
					<input type="hidden" name="seanceid" value=${seance.id } />
					<button type="submit">Update</button>
					<button onclick="deleteSeance('form_${seance.id }', ${seance.id })">Delete</button>
				</div>
			</form>
		</c:forEach>
		<form class="tablerow" method="post" action="modifysalle">
			<div class="tablecell">
				<input type="date" name="date" />
			</div>
			<div class="tablecell">
				<input type="time" name="time" />
			</div>
			<div class="tablecell">
				<select name="filmid" id="id_film">
					<c:forEach items="${films}" var="film">
						<option value="${film.id }">${film.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="tablecell">
				<input type="hidden" name="salleid" value=${salle.id } />
				<button type="submit">Create</button>
			</div>
		</form>
	</div>
</div>
<%@ include file="fragments/pagefooter.jspf"%>