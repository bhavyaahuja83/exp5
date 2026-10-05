# Student Management System

A Maven WAR practical using Jakarta Servlets, JSP, JDBC, and PostgreSQL. It works with the existing `public.students` table (`id`, `name`, `course`, `email`) and does not create or migrate a database table.

## Database configuration

1. Find Tomcat's `CATALINA_BASE` directory. This is the instance directory containing `conf`, `logs`, and `webapps`.
2. Copy `config/student-db.properties.example` to `%CATALINA_BASE%\conf\student-db.properties` on Windows (or `$CATALINA_BASE/conf/student-db.properties` on Linux/macOS).
3. In that copied file, replace the `password` placeholder with the Supabase database password. Keep the configuration file outside this repository and do not commit it.
4. The direct Supabase database host may require IPv6. If your network cannot resolve or reach it, open the Supabase project's **Connect** panel, select **Session pooler**, and use its host, port, database, and user values in this file. For the `url` property use `jdbc:postgresql://<HOST>:<PORT>/<DATABASE>?sslmode=require`; use the pooler-provided user and your database password for the other properties.

The application reloads this file whenever it opens a database connection, so editing it does not require rebuilding the WAR or restarting Tomcat.

The URL is in PostgreSQL JDBC form:

```text
jdbc:postgresql://db.nygekeajoxkpnjuzaiys.supabase.co:5432/postgres?sslmode=require
```

## Build and deploy

Requirements: JDK 17, Maven, and Apache Tomcat 10 or later. The app compiles against Jakarta Servlet 5, which is compatible with Tomcat 10+; the Servlet API is supplied by Tomcat and is not bundled in the WAR.

From the project directory, build the WAR:

```text
mvn clean package
```

The build creates `target/exp5.war`, which Tomcat deploys at the `/exp5` context path. Stop Tomcat, remove any older `exp5` deployment from `%CATALINA_BASE%\webapps\` (or `$CATALINA_BASE/webapps/`), copy the new WAR there, then start Tomcat. Open:

```text
http://localhost:8080/exp5/
```

If Tomcat reports a database connection error, confirm that the external properties file exists, the Supabase password is correct, and the database allows connections from your network. Check Tomcat's logs for the underlying JDBC error.

## Test CRUD

1. Open **Add student**, enter a name, course, and email, then submit. The new row should appear in **All students**.
2. Open **All students** to verify the `SELECT` results and displayed ID.
3. Choose **Edit**, change one or more fields, and save. Confirm the updated values in the table.
4. Choose **Delete** and confirm. The row should disappear.

Every create, update, and delete is submitted with POST. All SQL values supplied by the form are bound using JDBC `PreparedStatement` parameters.