<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<form action="fsin" method="post">
<div align="center">
<input type="text" id="ipdt" name="ipdt" placeholder="hora/data" required/>
<input type="number" id="iph" name="iph" placeholder="humidade" required/>
<input type="number" id="ipp" name="ipp" placeholder="pressao" required/>
<input type="number" id="ipt" name="ipt" placeholder="temperatura" required/>
</div>
<br>
<div align="center">
<input type="submit" id="bt" name="bt" value="Inserir" />
<input type="submit" id="bt" name="bt" value="Atualizar" />
<input type="submit" id="bt" name="bt" value="Excluir" />
<input type="submit" id="bt" name="bt" value="Buscar" />
<input type="submit" id="bt" name="bt" value="Listar" />
</div>
<br>
<c:if test="${not empty lst}">
<table>
<thead>
<tr>
<th>data</th>
<th>pressao</th>
<th>humidade</th>
<th>temperatura</th>
</tr>
</thead>
<tbody>
<c:forEach items="${lst}" var="v">
<tr>
<td><c:out value="${v.lDT }"></c:out></td>
<td><c:out value="${v.pRESSURE }"></c:out></td>
<td><c:out value="${v.hUMIDITY }"></c:out></td>
<td><c:out value="${v.tEMPERATURE }"></c:out></td>
</tr>
</c:forEach>
</tbody>
</table>
</c:if>
</form>
</body>
</html>