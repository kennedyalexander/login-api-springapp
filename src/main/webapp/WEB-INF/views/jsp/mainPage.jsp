<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/resources/core/css/bootstrap.css" var="bootstrapCss" />
<spring:url value="/resources/core/css/bootstrap-theme.css" var="bootstrapThemeCss" />
<spring:url value="/resources/core/css/personalcss.css" var="personalCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${bootstrapThemeCss}" rel="stylesheet" />
<link href="${personalCss}" rel="stylesheet" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>${title}</title>


</head>

    <body>
${title}
    HELLO

</body>
</html>