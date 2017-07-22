<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:useBean id="getUrl" scope="session" class="com.wernercd.awsgen.GeneratePreSignedUrls" />
<jsp:setProperty name="getUrl" property="accessKeyId" />
<jsp:setProperty name="getUrl" property="secretKeyId" />
<jsp:setProperty name="getUrl" property="bucket" />
<jsp:setProperty name="getUrl" property="expirationInMinutes" />
<jsp:setProperty name="getUrl" property="files" />
<% getUrl.GenerateUrls(); %>
<c:forEach var="file" items="${getUrl.getFileUrls()}" >
  <a class="btn btn-info" role="button" href="${file.getUrl()}">${file.getFile()}</a><br />
</c:forEach>
</body>
</html>
