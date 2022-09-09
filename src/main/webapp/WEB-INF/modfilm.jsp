<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="fragments/pageheader.jspf"%>
<div class="content">
	<h3>${film.name }</h3>
	<div class="table">
		<div class="tablerow">
			<h3 class="tablecell">Date</h3>
			<h3 class="tablecell">Time</h3>
			<h3 class="tablecell">Salle</h3>
			<h3 class="tablecell">Actions</h3>
		</div>
		<c:forEach items="${seances}" var="seance">
			<form id="form_${seance.id }" class="tablerow" method="post" action="modifyfilm">
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
					<input type="number" name="salleid" value="${seance.salle.id }"/>						
				</div>
				<div class="tablecell">
					<input type="hidden" name="filmid" value=${film.id } />
					<input type="hidden" name="seanceid" value=${seance.id } />
					<input type="hidden" name="cinemaid" value=${cinema.id } />
					<button type="submit">Update</button>
					<button onclick="deleteSeance('form_${seance.id }', ${seance.id })">Delete</button>
				</div>
			</form>
		</c:forEach>
		<form class="tablerow" method="post" action="modifyfilm">
			<div class="tablecell">
				<input type="date" name="date" />
			</div>
			<div class="tablecell">
				<input type="time" name="time" />
			</div>
			<div class="tablecell">
					<input type="number" name="salleid" value="${seance.salleid }"/>						
			</div>
			<div class="tablecell">
				<input type="hidden" name="filmid" value=${film.id } />
				<input type="hidden" name="cinemaid" value=${cinema.id } />
				<button type="submit">Create</button>
			</div>
		</form>
</div>
<%@ include file="fragments/pagefooter.jspf"%>
