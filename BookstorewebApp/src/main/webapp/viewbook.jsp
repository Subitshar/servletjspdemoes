<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>BookId</th>
			<th>Book ISBN</th>
			<th>Book Name</th>
			<th>Book Price</th>
		</tr>
		<c:forEach var="Books" items="${books}">
			<tr>
				<td>${Books.id}</td>
				<td>${Books.isbn}</td>
				<td>${Books.name}</td>
				<td>${Books.price}</td>


			</tr>
		</c:forEach>
	</table>
	
	<a href="Home.html">Home </a>


</body>
</html>