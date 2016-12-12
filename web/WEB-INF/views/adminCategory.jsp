<%--
  Created by IntelliJ IDEA.
  User: SCIP
  Date: 15.08.2016
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="custom" uri="/WEB-INF/custom.tld" %>
<div class="container">
    <div class="col-md-3">
        <form:form action="/admin/adminCategory" method="post" class="form-inline" modelAttribute="category">
            <form:hidden path="id"/>
            <custom:hiddenInputs excludeParams="name, id"/>
            <div class="form-group">
                <%--<input name="name" placeholder="Name">--%>
                <form:errors path="name"/>
                <form:input path="name" placeholder="name"/>
                    <button type="submit" class="btn btn-primary">Create</button>
            </div>
        </form:form>
    </div>
    <div class="col-md-3">
        <form:form action="/admin/adminCategory" method="get" modelAttribute="filter" cssClass="form-inline">
            <custom:hiddenInputs excludeParams="search"/>
            <div class="form-group">
                <form:input path="search" placeholder="search" />
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </form:form>
    </div>
    <div class="col-md-1">
        <div class="dropdown">
            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Sort ID <span class="caret"></span></button>
            <ul class="dropdown-menu">
                <custom:sort innerHtml="ID asc" paramValue="id"/>
                <custom:sort innerHtml="ID desc" paramValue="id,desc"/>
            </ul>
        </div>
    </div>
    <div class="col-md-1">
        <custom:size posibleSizes="1,2,5,10" size="${categories.size}" title="Page size"/>
    </div>

    <table class="table table-hover">

        <tr>
            <td>#</td>
            <td>Name</td>
        </tr>
        <c:forEach items="${categories.content}" var="category">
            <tr>
                <td>${category.id}</td>
                <td>${category.name}</td>
                <td><a href="/admin/adminCategory/categoryWithProperty/${category.id}" class="btn btn-primary">Add String Property</a></td>
                <td><a href="" class="btn btn-primary">Add Integer Property</a></td>
                <td><a href="/admin/adminCategory/delete/${category.id}<custom:allParams/>" class="btn btn-danger">Delete</a> </td>
                <td><a href="/admin/adminCategory/update/${category.id}<custom:allParams/>" class="btn btn-warning">Update</a></td>
            </tr>
        </c:forEach>
    </table>
    <div class="col-md-12 text-center">
        <custom:pageable page="${categories}" cell="<li></li>" container="<ul class='pagination'></ul>" />
    </div>
</div>
