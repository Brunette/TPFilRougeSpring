<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="fragments/pageheader.jspf"%>
<div class="content">
	<h1>Welcome ${user.username }</h1>
	<div id="id_profile_consult" class="profileinfo">
		<div>
			<span>Email: </span><span>${user.email }</span>
		</div>
		<div>
			<span>Code Postal: </span><span>${user.codePostal }</span>
		</div>
		<div>
			<span>Subscribe to newsletter: </span> <span>${user.subNews}</span>
		</div>
		<div>
		<button onclick="edit_on()">Modify</button>
		</div>
	</div>
	<form id="id_profile_edit" class="profileinfo" method="post" action="modifyprofile">
		<div>
			<label for="id_email">Email: </label><input id="id_email"
				name="email" type="email" value=${user.email } />
		</div>
		<div>
			<label for="id_codepostal">Code Postal: </label><input id="id_cpo"
				name="cpo" type="text" value="${user.codePostal }" />
		</div>
		<div>
			<label for="id_newsletter">Subscribe to newsletter: </label> <input
				id="id_subscribe" name="subscribe" type="checkbox" 
				<c:if test="${user.subNews }">
								checked
								</c:if>> 
								
		</div>
		<div>
		<button type="submit">Save</button>
		<button onclick="edit_off()">Cancel</button>
		</div>
	</form>
	<div>
		<span>List of reservations ${reservations.size() }:</span>
		<c:forEach items="${reservations}" var="reservation">
			<p>
				<span>${reservation.seance.film.name }</span> <span>${reservation.seance.heureSeance }</span>
				<span>${reservation.nbPlaces }</span>
			</p>
		</c:forEach>
	</div>
</div>
<%@ include file="fragments/pagefooter.jspf"%>