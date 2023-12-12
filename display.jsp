<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <%String search = (String) request.getAttribute("search");
        if (search == null || search.isEmpty()) {
            search = "";
        }
    %>
    <h1>Danh sách sản phẩm</h1>

    <form action="welcome" method="post">
        
        <input type="submit" name="action" value="Total">
    </form>

    <form action="welcome" method="post">
       
        <input type="text" placeholder="Nhập sản phẩm cần tìm kiếm" name="search" value="<%=search%>">
        <input type="submit" name="action" value="search">
       
    </form>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Tên sản phẩm</th>
            <th>Số lượng</th>
            <th>Đơn giá</th>
        </tr>
        <% ResultSet rs = (ResultSet) request.getAttribute("ListProduct");
        while (rs.next()) { %>
            <tr>
                <td><%= rs.getInt("ID") %></td>
                <td><%= rs.getString("Name") %></td>
                <td><%= rs.getInt("Quantity") %></td>
                <td><%= rs.getDouble("Price") %></td>
            </tr>
        <% } %>
    </table>

   
    <% Double total = (Double) request.getAttribute("total");
    if (total != null) { %>
        <p>Tổng tiền: <%= total %> (VNĐ)</p>
    <% } %>

    
</body>
</html>
