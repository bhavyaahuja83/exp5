<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Edit Student · Student Records</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
</head>
<body>
    <header class="site-header">
        <a class="brand" href="${pageContext.request.contextPath}/index.html"><span class="brand-mark">S</span> Student Records</a>
        <nav aria-label="Main navigation">
            <a class="nav-link" href="${pageContext.request.contextPath}/index.html">Home</a>
            <a class="nav-link active" href="${pageContext.request.contextPath}/students">All students</a>
            <a class="button button-small" href="${pageContext.request.contextPath}/add-student.html">Add student</a>
        </nav>
    </header>
    <main class="form-shell">
        <a class="back-link" href="${pageContext.request.contextPath}/students">← All students</a>
        <p class="eyebrow">EDIT RECORD · ID <c:out value="${student.id}"/></p>
        <h1>Edit student</h1>
        <p class="form-intro">Update the student's details below.</p>
        <form class="student-form" action="${pageContext.request.contextPath}/students/update" method="post">
            <input type="hidden" name="id" value="<c:out value='${student.id}'/>">
            <label for="name">Name</label>
            <input id="name" name="name" type="text" value="<c:out value='${student.name}'/>" required>
            <label for="course">Course</label>
            <input id="course" name="course" type="text" value="<c:out value='${student.course}'/>" required>
            <label for="email">Email</label>
            <input id="email" name="email" type="email" value="<c:out value='${student.email}'/>" required>
            <div class="form-actions">
                <button class="button" type="submit">Save changes</button>
                <a class="text-link" href="${pageContext.request.contextPath}/students">Cancel</a>
            </div>
        </form>
    </main>
    <footer class="site-footer">Advanced Internet Programming · Student Records</footer>
</body>
</html>