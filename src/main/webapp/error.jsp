<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Request Error · Student Records</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
</head>
<body>
    <header class="site-header">
        <a class="brand" href="${pageContext.request.contextPath}/index.html"><span class="brand-mark">S</span> Student Records</a>
        <nav aria-label="Main navigation"><a class="nav-link" href="${pageContext.request.contextPath}/students">All students</a></nav>
    </header>
    <main class="form-shell">
        <p class="eyebrow">REQUEST NOT COMPLETED</p>
        <h1>Something went wrong</h1>
        <p class="error-copy"><c:out value="${requestScope.errorMessage}"/></p>
        <a class="button" href="${pageContext.request.contextPath}/students">Back to students</a>
    </main>
    <footer class="site-footer">Advanced Internet Programming · Student Records</footer>
</body>
</html>