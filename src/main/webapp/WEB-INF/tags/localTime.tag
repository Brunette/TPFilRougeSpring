<%@ tag body-content="empty" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="time" required="true" type="java.time.LocalTime" %>
<%@ attribute name="pattern" required="false" type="java.lang.String" %>

<c:if test="${empty pattern}">
    <c:set var="pattern" value="hh:mm:ss"/>
</c:if>

<fmt:parseTime value="${time}" pattern="hh:mm:ss" var="parsedDate" type="time"/>
<fmt:formatTime value="${parsedTime}" type="time" pattern="${pattern}"/>