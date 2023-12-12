<%-- 
    Document   : login.jsp
    Created on : Dec 11, 2023, 7:48:55 PM
    Author     : Thành Nguyễn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Đăng nhập</h1>
        <form method="post" action="dang-nhap">
            <table>
            <tr>
                <td>
                    Email :
                </td>
                <td>
                    <input type="email" name="email" required>
                </td>
            </tr>
            <tr>
                <td>
                    Password :
                </td>
                <td>
                    <input type="password" name="password" required>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" name="login" value ="Đăng nhập">
                </td>
                <td>
                    <input type="submit" name="reset" value ="Reset">
                </td>
             <tr>
                    </table>
        </form>
    </body>
</html>
