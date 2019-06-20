<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="${pageContext.response.locale}">
<spring:message code="meal.edit" var="pagetitle"/>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="${pagetitle}"/>
</jsp:include>
<body class="main">
<jsp:include page="nav.jsp"/>
<section class="section">
    <div class="columns is-desktop">
        <div class="column is-half">
            <h2 class="title">${pagetitle}</h2>
            <c:if test="${errors.size() > 0 }">
                <div class="notification is-danger">
                    <ul>
                        <c:forEach var="error" items="${errors}">
                            <li>${error.defaultMessage}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
            <form method="post" action="update?id=${gerecht.id}">
                <div class="field is-horizontal">
                    <div class="field-label">
                        <label class="label" for="beschrijving"><spring:message code="description"/></label>
                    </div>
                    <div class="field-body">
                        <div class="field">
                            <div class="control">
                                <input class="input" type="text" required maxlength="50" id="beschrijving"
                                       name="beschrijving" value="${gerecht.beschrijving}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="field is-horizontal">
                    <div class="field-label">
                        <label class="label" for="prijs"><spring:message code="price"/></label>
                    </div>
                    <div class="field-body">
                        <div class="field">
                            <div class="control">
                                <input class="input" type="number" required min="0.1" max="10" step="0.01" id="prijs"
                                       name="prijs" value="${gerecht.prijs}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="field is-horizontal">
                    <div class="field-label">
                        <label class="label" for="type"><spring:message code="type"/></label>
                    </div>
                    <div class="field-body">
                        <div class="field">
                            <div class="control">
                                <div class="select">
                                    <select id="type" name="type" required>
                                        <option<c:if test="${gerecht.type == 'DAGSCHOTEL'}"> selected</c:if>
                                                value="DAGSCHOTEL"><spring:message code="DAGSCHOTEL"/></option>
                                        <option<c:if test="${gerecht.type == 'SOEP'}"> selected</c:if> value="SOEP">
                                            <spring:message code="SOEP"/></option>
                                        <option<c:if test="${gerecht.type == 'VEGGIE'}"> selected</c:if> value="VEGGIE">
                                            <spring:message code="VEGGIE"/></option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="field is-grouped is-grouped-right">
                    <p class="control">
                        <a class="button is-light" href="./change">
                            <spring:message code="cancel"/>
                        </a>
                    </p>
                    <p class="control">
                        <button class="button is-primary">
                            <spring:message code="apply"/>
                        </button>
                    </p>
                </div>
            </form>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>