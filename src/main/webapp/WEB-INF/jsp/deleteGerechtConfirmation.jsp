<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="${pageContext.response.locale}">
<spring:message code="meal.delete" var="pagetitle"/>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="${pagetitle}"/>
</jsp:include>
<body class="main">
<jsp:include page="nav.jsp"/>
<section class="section">
    <div class="columns is-desktop">
        <div class="column is-one-third">
            <h2 class="title">${pagetitle}</h2>
            <form method="post" action="delete?id=${gerecht.id}">
                <p class="content"><spring:message code="delete.confirm.message"
                                                   arguments="${gerecht.beschrijving}"/></p>
                <div class="field is-grouped is-grouped-right">
                    <p class="control">
                        <button class="button is-primary">
                            <spring:message code="yes"/>
                        </button>
                    </p>
                    <p class="control">
                        <a class="button is-light" href="./change">
                            <spring:message code="no"/>
                        </a>
                    </p>
                </div>
            </form>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>