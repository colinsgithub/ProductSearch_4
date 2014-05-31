<%-- 
    Document   : personalInfo
    Created on : Mar 18, 2014, 1:00:41 PM
    Author     : poonkaho
--%>

<%@page import="bean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>


        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery-2.0.3.min.js"></script> 
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>

        <link href="js/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet">
        <script src="js/bootstrap3-editable/js/bootstrap-editable.js"></script>


        <script>
            $(document).ready(function() {

                //$.fn.editable.defaults.mode = 'inline'; 
                $('#info a').editable({
                    disabled: true,
                    pk: '1',
                    url: 'HandleUser?action=updateUser',
                    success: function(response, newValue) {
                        if (response.status == 'error')
                            return response.msg; //msg will be shown in editable form
                    }

                });

                $('#edit-button').click(function(e) {
                    e.stopPropagation();

                    $('#info a').editable('toggleDisabled');
                });
            });

        </script>
        <style>
            body {
                width: 100%;
                height: 100%;
                margin: 0px;
                overflow: hidden;
                min-width: 1200px;
                min-height: 600px;
                width: 1200px;
                height: 600px;
            }

            .main {
                left: 0%;
                position: absolute;
                width: 400%;
                height: 100%;
                background: #F5F5F5;
                overflow: hidden;
            }

            .controlArrow.next {
                visibility: visible;
                right: 15px;
                border-width: 38.5px 0 38.5px 34px;
                border-color: transparent transparent transparent #fff;
            }

            .controlArrow.prev {
                visibility: hidden;
                left: 15px;
                width: 0;
                border-width: 38.5px 34px 38.5px 0;
                border-color: transparent #fff transparent transparent;
            }

            .controlArrow {
                position: absolute;
                top: 50%;
                cursor: pointer;
                width: 0;
                height: 0;
                border-style: solid;
                margin-top: -38px;
            }

            .page {
                float: left;
                position: absolute;
            }

            #page1 {

                height: 100%;
                width: 25%;
                left:0%;
            }
            #page2 {

                height: 100%;
                width: 25%;
                left:25%;
            }
            #page3 {

                height: 100%;
                width: 25%;
                left:50%;
            }

            .personalDetail {
                padding:10px;
            }

            .header {
                font-size: 60px;
                color: #282828;
                font-family: Raleway, sans-serif;
                text-align: center;
                font-weight: 100;
                height: 130px;
            }

            .infoMenu {
                top: 10px;
                width: 1200px;
                position: absolute;
                z-index: 10;
                font-size: 30px;
                color: #282828;
                font-family: Raleway, sans-serif;
                font-weight: 100;
            }

            .infoMenu > div {
                width: 50%;
                margin: 0 auto;
            }

            .infoMenu ul {
                text-align: center;
                float: left;
                list-style: none;

            }

            .infoMenu li {
                border-left:1.3px solid #282828;
                padding: 10px 15px;
                float: left;
                margin: 0 .1px;
                -webkit-transition: all 0.5s ease-in-out;
                -moz-transition: all 0.5s ease-in-out;
                -o-transition: all 0.5s ease-in-out;
                transition: all 0.5s ease-in-out;
            }

            .infoMenu ul:last-child {
                border-right:1.3px solid #282828;
            }

            .infoMenu li:hover {
                background: #fff;	
                border-bottom:5px solid tomato;
            }

            .info {
                width: 600px;
                margin: 0 auto;
            }

            .info div {
                font-size: 20px;
                margin: 5px 3px;
                color: #282828;
                font-family: Raleway, sans-serif;
                font-weight: 100;
            }

            label {
                margin-right: 4px;
            }

            .popover.top .arrow {
                bottom: -16px;
            }

        </style>

    </head>
    <body>
        <div id="infoMenu" class="infoMenu">
            <div>
                <ul>
                    <li><a>Personal Profile</a></li>
                    <li><a>Store</a></li>
                    <li><a>Preference</a></li>
                </ul>
            </div>
        </div>

        <div id="main" class="main">
            <div id="page1" class="page">
                <div class="personalDetail">
                    <div class="header"></div>


                    <%
                        User user = (User) request.getAttribute("user");
                    %>
                    <div id="info" class="info">
                        <div><label>User ID :</label><a href="#" id="userID" data-type="text" data-title="Enter User ID"><%=user.getUserID()%></a></div>
                        <div><label>User Name :</label><a href="#" id="userName" data-type="text" data-title="Enter User Name"><%=user.getUserName().toUpperCase()%></a></div>
                        <div><label>Gender :</label><a href="#" id="sex" data-type="text" data-title="Enter Sex"><%=user.getSex()%></a></div>
                        <div><label>Age :</label><a href="#" id="age" data-type="text" data-title="Enter Age"><%=user.getAge()%></a></div>
                        <div><label>Email :</label><a href="#" id="email" data-type="text" data-title="Enter Email"><%=user.getEmail()%></a></div>
                        <div><label>User Description :</label><a href="#" id="userDesc" data-type="textarea" data-title="Enter Description"><%=user.getUserDesc()%></a></div>
                        <div><button>Chang Password</button></div>
                        <div><button id="edit-button">Edit Data</button></div>
                    </div>


                </div>
            </div>
            <div id="page2" class="page">
                <div class="header"></div>
                <div id="info" class="info">
                    <div><%=user.getUserID()%></div>

                    <div></div>
                    <div></div>
                </div>
            </div>

            <div id="page3" class="page">
                <div class="header"></div>
                <div id="info" class="info">
                    <div><%=user.getUserID()%></div>

                    <div></div>
                    <div></div>
                </div>
            </div>
        </div>

        <div class="controlArrow prev" onclick="left();"></div>
        <div class="controlArrow next" onclick="right();"></div>

        <script>
            pos = 0;

            function left() {
                pos += 100;
                x();
            }

            function right() {
                pos -= 100;
                x();
            }

            function x() {
                if (pos <= -200) {
                    $('.controlArrow.next').css({visibility: 'hidden'});
                } else if (pos === 0) {
                    $('.controlArrow.prev').css({visibility: 'hidden'});
                } else {
                    $('.controlArrow.next').css({visibility: 'visible'});
                    $('.controlArrow.prev').css({visibility: 'visible'});
                }

                $('.main').animate({
                    left: '' + pos + '%'
                });
            }
        </script>
    </body>
</html>
