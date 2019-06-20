<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="${pageContext.response.locale}">
<spring:message code="meals.edit" var="pagetitle"/>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="${pagetitle}"/>
</jsp:include>
<body class="main">
<jsp:include page="nav.jsp"/>
<section class="section">
    <h2 class="title">${pagetitle}</h2>
    <c:choose>
        <c:when test="${gerechten.size() > 0}">
            <div class="overflowx-auto">
                <table class="table">
                    <thead>
                    <tr>
                        <th><spring:message code="description"/></th>
                        <th><spring:message code="price"/></th>
                        <th><spring:message code="type"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="gerecht" items="${gerechten}">
                        <tr>
                            <td>${gerecht.beschrijving}</td>
                            <td class="nowordwrap">&euro; <fmt:formatNumber type="number" minFractionDigits="2"
                                                         maxFractionDigits="2" value="${gerecht.prijs}"/></td>
                            <td><spring:message code="${gerecht.type}"/></td>
                            <td>
                                <a class="button is-light"
                                   href="update?id=${gerecht.id}"><spring:message code="edit"/></a>
                            </td>
                            <td>
                                <a class="button is-danger"
                                   href="delete?id=${gerecht.id}"><spring:message code="delete"/></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:when>
        <c:otherwise>
            <h3 class="subtitle"><spring:message code="menu.empty"/></h3>
        </c:otherwise>
    </c:choose>
    <a class="button is-primary is-medium mb-1" href="add"><spring:message code="meal.add"/></a>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>