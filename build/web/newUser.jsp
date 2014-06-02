<%-- 
    Document   : newUser
    Created on : Jan 31, 2014, 10:52:58 PM
    Author     : poonkaho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="/HandleUser" method="get">
            <table>
                <tr>
                    <td>
                        User ID:
                    </td>
                    <td>
                        <input type="text" id="userID"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Sex:
                    </td>
                    <td>
                        <input type="radio" name="sex" value="male">Male
                        <input type="radio" name="sex" value="female">Female
                    </td>
                </tr>
                <tr>
                    <td>
                        Age:
                    </td>
                    <td>
                        <input type="number" name="age" min="1"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        E-mail:
                    </td>
                    <td>
                        <input type="email" name="email" autocomplete="off">
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" value="Submit"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
