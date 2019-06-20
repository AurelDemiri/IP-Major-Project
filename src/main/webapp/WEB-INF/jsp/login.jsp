<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize access="isAuthenticated()">
    <c:redirect url="/gerechten"/>
</sec:authorize>

<!DOCTYPE html>
<html lang="${pageContext.response.locale}">
<spring:message code="login" var="pagetitle"/>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="${pagetitle}"/>
</jsp:include>
<body class="main">
<section class="section">
    <div class="hero">
        <div class="hero-body">
            <div class="columns is-centered">
                <div class="column is-one-quarter card">
                    <c:if test="${param.error != null}">
                        <div class="notification is-danger">
                            <spring:message code="login.error"/>
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="notification is-primary">
                            <spring:message code="login.logoutsuccess"/>
                        </div>
                    </c:if>
                    <form method="post" action="login">
                        <div class="field">
                            <label class="label" for="username"><spring:message code="login.username"/></label>
                            <div class="control has-icons-left">
                                <span class="icon is-small is-left">
                                    <i class="fa fa-envelope"></i>
                                </span>
                                <input id="username" name="username" class="input" type="text"
                                       placeholder="<spring:message code="login.username"/>" required/>
                            </div>
                        </div>
                        <div class="field">
                            <label class="label" for="password"><spring:message code="login.password"/></label>
                            <div class="control has-icons-left">
                                <span class="icon is-small is-left">
                                    <i class="fa fa-lock"></i>
                                </span>
                                <input id="password" name="password" class="input" type="password"
                                       placeholder="<spring:message code="login.password"/>" required/>
                            </div>
                        </div>
                        <div class="field">
                            <p class="control">
                                <input type="submit" class="button is-primary" value="<spring:message code="login"/>"/>
                            </p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="footer.jsp"/>
</body>
</html>