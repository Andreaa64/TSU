<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./product?red=ProductView");	
		return;
	}
	
	ProductBean product = (ProductBean) request.getAttribute("product");
	
	Cart cart = (Cart) request.getAttribute("cart");
	
	String user = (String) session.getAttribute("username");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,main.java.it.unisa.model.ProductBean,main.java.it.unisa.model.Cart"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<title>Storage DS/BF </title>
</head>

<body>
	<p align="right"> <%=user %> </p>
	<h2>Products</h2>
	<a href="product">List</a>
	<table border="1">
		<tr>
			<th>Codice <a href="product?sort=idP">Sort</a></th>
			<th>tipo <a href="product?sort=tipo">Sort</a></th>
			<th>Descrizione <a href="product?sort=descrizione">Sort</a></th>
			<th>Action</th>
		</tr>
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProductBean bean = (ProductBean) it.next();
		%>
		<tr>
			<td><%=bean.getIdP()%></td>
			<td><%=bean.getTipo()%></td>
			<td><%=bean.getDescrizione()%></td>
			<td><a href="product?action=delete&id=<%=bean.getIdP()%>">Elimina</a><br>
				<a href="product?action=read&id=<%=bean.getIdP()%>">Dettagli</a><br>
				<a href="product?action=addC&id=<%=bean.getIdP()%>">Aggiungi al carrello</a>
				</td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">Nessun prodotto disponibile</td>
		</tr>
		<%
			}
		%>
	</table>
	
	<h2>Dettagli</h2>
	<%
		if (product != null) {
	%>
	<table border="1">
		<tr>
			<th>Descrizione</th>
			<th>Tipo</th>
			<th>Immagine</th>
		</tr>
		<tr>
			<td><%=product.getDescrizione()%></td>
			<td><%=product.getTipo()%></td>
			<td> <img src="<%=product.getImg() %>"> </td>
		</tr>
	</table>
	<%
		}
	%>
	<h2>Insert</h2>
	<form action="product" method="post">
		<input type="hidden" name="action" value="insert"> 
		
		<label for="tipo">Tipo:</label><br> 
		<input name="tipo" type="text" maxlength="20" required placeholder="inserisci tipo"><br> 
		
		<label for="descrizione">Descrizione:</label><br>
		<textarea name="descrizione" maxlength="100" rows="3" required placeholder="inserisci descrizione"></textarea><br>
		
		<label for="prezzo">Prezzo:</label><br> 
		<input name="prezzo" type="number" min="0" value="0" required><br>

		<label for="marca">Marca:</label><br> 
		<input name="marca" type="text" maxlength="20" required placeholder="inserisci marca"><br> 
		
		<label for="colore">Colore:</label><br> 
		<input name="colore" type="color" required><br>
		
		<label for="quantita">Quantità:</label><br> 
		<input name="quantita" type="number" min="1" value="1" required><br>
         
        <label for="immagine">immagine:</label><br> 
		<input name="immagine" type="text" maxlength="100" required placeholder="inserisci immagine"><br> 
         
		<input type="submit" value="Aggiungi"><input type="reset" value="Reset">
	</form>
	
	<!-- Da Aggiustare -->
	<% if(cart != null) { %>
		<h2>Carrello</h2>
		<table border="1">
		<tr>
			<th>Name</th>
			<th>Quantità </th>
			<th>Action</th>
		</tr>
		<% List<ProductBean> prodcart = cart.getProducts(); 	
		   for(ProductBean beancart: prodcart) {
		%>
		<tr>
			<td><%=beancart.getTipo()%></td>
			<td> <%=beancart.getQC() %> </td>
			<td><a href="product?action=deleteC&id=<%=beancart.getIdP()%>">Cancella dal carrello</td>
		</tr>
		<%} %>
	</table>
	<form action="product" method="post">
	<input type="hidden" name="action" value="Order"> 
    <input type="submit" name="order" value="Order">
	</form>
	<% } %>	
</body>
</html>	