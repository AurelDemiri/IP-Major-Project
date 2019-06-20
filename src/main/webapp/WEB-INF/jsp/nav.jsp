<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="navbar is-primary" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <span class="navbar-item is-size-4 has-text-weight-semibold">
            Umami Project
        </span>
        <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false" data-target="navbarBasic">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    </div>
    <div id="navbarBasic" class="navbar-menu">
        <div class="navbar-start">
            <a class="navbar-item" href="/">
                <spring:message code="home"/>
            </a>
            <sec:authorize access="hasRole('ADMIN')">
                <a class="navbar-item" href="/gerechten/change">
                    <spring:message code="meals.edit"/>
                </a>
                <a class="navbar-item" href="/gerechten/add">
                    <spring:message code="meal.add"/>
                </a>
            </sec:authorize>
        </div>
        <div class="navbar-end">
            <sec:authorize access="isAuthenticated()">
                <div class="navbar-item">
                    <div class="field">
                        <p class="control">
                            <a class="button" href="/logout">
                        <span class="icon">
                            <i class="fas fa-sign-out-alt"></i>
                        </span>
                                <span><spring:message code="logout"/></span>
                            </a>
                        <p>
                    </div>
                </div>
            </sec:authorize>
        </div>
    </div>
</nav>