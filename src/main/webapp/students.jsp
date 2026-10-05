<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>All Students · Student Records</title>
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
    <main class="records-shell">
        <div class="page-heading">
            <div>
                <p class="eyebrow">ACADEMIC RECORDS</p>
                <h1>All students</h1>
                <p class="form-intro">Browse and manage student details.</p>
            </div>
            <a class="button" href="${pageContext.request.contextPath}/add-student.html">+ Add student</a>
        </div>

        <c:if test="${param.message == 'added' || param.message == 'updated' || param.message == 'deleted'}">
            <div class="notice" role="status">
                <c:choose>
                    <c:when test="${param.message == 'added'}">Student added successfully.</c:when>
                    <c:when test="${param.message == 'updated'}">Student updated successfully.</c:when>
                    <c:otherwise>Student deleted successfully.</c:otherwise>
                </c:choose>
            </div>
        </c:if>

        <c:choose>
            <c:when test="${empty students}">
                <section class="empty-state">
                    <p class="eyebrow">NO RECORDS YET</p>
                    <h2>Your student list is empty.</h2>
                    <p>Add a student to see their details here.</p>
                    <a class="button" href="${pageContext.request.contextPath}/add-student.html">Add first student</a>
                </section>
            </c:when>
            <c:otherwise>
                <div class="table-wrap">
                    <table>
                        <thead>
                            <tr><th>ID</th><th>Name</th><th>Course</th><th>Email</th><th>Actions</th></tr>
                        </thead>
                        <tbody>
                            <c:forEach var="student" items="${students}">
                                <tr>
                                    <td class="id-cell"><c:out value="${student.id}"/></td>
                                    <td class="name-cell"><c:out value="${student.name}"/></td>
                                    <td><c:out value="${student.course}"/></td>
                                    <td><c:out value="${student.email}"/></td>
                                    <td>
                                        <div class="row-actions">
                                            <a class="small-action" href="${pageContext.request.contextPath}/students/edit?id=${student.id}">Edit</a>
                                            <form action="${pageContext.request.contextPath}/students/delete" method="post" onsubmit="return confirm('Delete this student record?');">
                                                <input type="hidden" name="id" value="<c:out value='${student.id}'/>">
                                                <button class="small-action delete-action" type="submit">Delete</button>
                                            </form>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </main>
    <footer class="site-footer">Advanced Internet Programming · Student Records</footer>
</body>
</html>