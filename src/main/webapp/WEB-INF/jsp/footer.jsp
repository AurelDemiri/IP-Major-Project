<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<footer class="footer">
    <div class="content has-text-centered">
        <form action="/changelanguage" method="post">
            <p>
                <button class="button" name="locale" type="submit" value="en">English</button>
                <button class="button" name="locale" type="submit" value="nl">Nederlands</button>
                <button class="button" name="locale" type="submit" value="fr">fran&ccedil;ais</button>
            </p>
        </form>
        <p>
            <spring:message code="developed.by"/> Aurel Demiri (r0705950)
        </p>
    </div>
</footer>