<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="fragments/pageheader.jspf"%>
<div class="content">
	<h1>Espace Admin</h1>
	<h3>Modify Cinemas</h3>
	<c:forEach items="${cinemas}" var="cinema">
		<p>
			<span>${cinema.id } </span> <span><a
				href="gerant?cinemaid=${cinema.id }">${cinema.name }</a> </span> <span>${cinema.gerant.user.username }</span>
		</p>
	</c:forEach>
	<h3>Modify Users</h3>

	<div class="tablerow">
		<h3 class="tablecell">Identifiant</h3>
		<h3 class="tablecell">Email</h3>
		<h3 class="tablecell">Compte</h3>
		<h3 class="tablecell">Pouvoir</h3>
		<h3 class="tablecell">Actions</h3>
	</div>
	<c:forEach items="${users}" var="user">
		<form id="form_${user.id }" class="tablerow" method="post"
			action="modifyuser">
			<div class="tablecell">
				<span>${user.nom}</span>
			</div>
			<div class="tablecell">
				<span>${user.email}</span>
			</div>
			<div class="tablecell">
				<span>Standard</span>
			</div>
			<div class="tablecell">
				<c:if test="${!empty user.superuser }">
					<c:if test="${user.superuser.admin }">
						<span>Admin</span>
					</c:if>
					<c:if test="${!user.superuser.admin }">
						<span>${user.superuser.cinema.id }</span>
					</c:if>
				</c:if>
				<c:if test="${empty user.superuser }">
					<span>User</span>
				</c:if>
			</div>
			<div class="tablecell">
				<input type="hidden" name="userid" value=${user.id } />
				<button type="submit">Delete</button>
			</div>
		</form>
	</c:forEach>
</div>
<%@ include file="fragments/pagefooter.jspf"%>
