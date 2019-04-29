<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta charset="UTF-8">
    <title>Details</title>
    <script type="text/javascript" src="<c:url value="/js/jquery-1.11.3.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/updateDocument.js"/>"></script>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/fonts/font-awesome-4.3.0/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/main.css"/>" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <div class="row">
                    <div class="col-md-12 col-md-offset-6 text-center">
                        <p>
                            This is a sample application developed using mSpace platform.<br>
                            You may change the code and User interface as per your requirements and connect to mSpace platform.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </nav>
    <div class="wrapper">
        <div class="container-fluid">
            <div class="content-block without-left-menu">
                <div class="row">
                    <div class="container">
                        <h3 class="main-topic">Status Notification Portal</h3>
                        <div class="row margin-top-20" >
                            <div class="col-md-12">
                                <div class="text-right">
                                <a href="http://localhost:9001/service-status-inquiry/create/request" class="btn btn-success btn-info right">
                                    <span class="glyphicon glyphicon-plus"></span> Add
                                </a>
                                </div>
                                <br>
                                <div class="page-card">
                                    <form:form method="POST" id="documentForm" modelAttribute="document" class="form-signin">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th style="width: 90px">Reference Number</th>
                                            <th style="width: 200px">Full Name</th>
                                            <th style="width: 200px">Address</th>
                                            <th>NIC</th>
                                            <th>Created Date</th>
                                            <th>Updated Date</th>
                                            <th>Status</th>
                                            <th style="width: 50px">Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <c:forEach var="passport" items="${document}">
                                        <tr>
                                            <td>${passport.refNo}</td>
                                            <td>${passport.fullName}</td>
                                            <td>${passport.address}</td>
                                            <td>${passport.nic}</td>
                                            <td>${passport.createDate}</td>
                                            <td>${passport.lastUpdateDate}</td>
                                            <td>${passport.status}</td>
                                            <td>
                                                <div class="dropdown">
                                                    <div   class="dropdown-toggle" id="dropdownMenu1"
                                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                        <a class="btn btn-default">
                                                            <i class="fa fa-ellipsis-v"></i>
                                                        </a>
                                                    </div>
                                                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                                        <li>
                                                            <a href="#" onclick="updateDocumentStatus('${passport.refNo}', 'START')" id="start" role="button">Start</a>
                                                            <a href="#" onclick="updateDocumentStatus('${passport.refNo}', 'PROCESSING')" id="processing" role="button">Processing</a>
                                                            <a href="#" onclick="updateDocumentStatus('${passport.refNo}', 'DONE')" id="done" role="button">Done</a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </td>
                                        </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>