<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="form" prefix="f" %>
<%@ taglib uri="maths" prefix="m" %>

<jsp:useBean id="now" class="java.util.Date"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>