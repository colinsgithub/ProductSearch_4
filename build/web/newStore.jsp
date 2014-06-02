<%-- 
    Document   : newStore
    Created on : Jan 31, 2014, 10:49:26 PM
    Author     : poonkaho
--%>

<%@page import="bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <style>
            #map-canvas {
                height: 400px;
                margin: 0px;
                padding: 0px
            }

        </style>
        <link href="css/metro-bootstrap.css" rel="stylesheet">
        <link href="css/metro-bootstrap-responsive.css" rel="stylesheet">
        <link href="css/iconFont.css" rel="stylesheet">
        <link href="css/docs.css" rel="stylesheet">
        <link href="js/prettify/prettify.css" rel="stylesheet">

        <!-- Load JavaScript Libraries -->
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="js/jquery/jquery.widget.min.js"></script>
        <script src="js/jquery/jquery.mousewheel.js"></script>
        <script src="js/prettify/prettify.js"></script>

        <!-- Metro UI CSS JavaScript plugins -->
        <script src="js/load-metro.js"></script>

        <!-- Local JavaScript -->
        <script src="js/docs.js"></script>
        <script src="js/github.info.js"></script>

        <script>
            METRO_LOCALE = 'en';
            METRO_WEEK_START = 1;
        </script>
        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyBQTZl_t-QrF8ExPfToPAjeclIf1DUZ2jc&key=AIzaSyBQTZl_t-QrF8ExPfToPAjeclIf1DUZ2jc&libraries=geometry,places&sensor=false"></script>
        <script>
            var geocoder;
            var map;
            function initialize() {
                geocoder = new google.maps.Geocoder();
                var latlng = new google.maps.LatLng(22.25, 114.166667);
                var mapOptions = {
                    zoom: 10,
                    center: latlng
                };
                map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
            }

            markers = [];
            function codeAddress() {
                for (var x = 0; x < markers.length; x++) {
                    markers[x].setMap(null);
                }

                var address = document.getElementById('addressForCoordinate').value;
                geocoder.geocode({'address': address}, function(results, status) {
                    if (status == google.maps.GeocoderStatus.OK) {
                        map.setCenter(results[0].geometry.location);
                        var marker = new google.maps.Marker({
                            map: map,
                            position: results[0].geometry.location,
                            draggable: true
                        });

                        google.maps.event.addListener(marker, "dragend", function(e) {
                            map.panTo(marker.getPosition());
                            $('#lat').val(marker.getPosition().lat());
                            $('#lng').val(marker.getPosition().lng());
                        });
                        markers.push(marker);
                    } else {
                        alert('Geocode was not successful for the following reason: ' + status);
                    }
                });
            }

            google.maps.event.addDomListener(window, 'load', initialize);

        </script>
        <script src="js/metro.min.js"></script>
    </head>
    <body class="metro">
        <div class="container" style=" margin:5px 60px 40px 60px;">
            <div class="newStore">
                <fieldset>
                    <legend>New Store</legend>
                    <form style="width: 70%;" action="HandleStore?action=newStoreWithValue" method="POST" enctype="multipart/form-data">

                        <label>Store Avatar</label>
                        <div class="input-control text" data-role="input-control" style=" width: 40%;">
                            <input type="file" name="file" id="file">
                        </div>

                        <label>Store Name</label>
                        <div class="input-control text" data-role="input-control" style=" width: 40%;">
                            <input id="storeName" name="storeName" type="text" placeholder="Store Name" required>
                            <button class="btn-clear" tabindex="-1" type="button"></button>
                        </div>

                        <label>Contact</label>
                        <div class="input-control text" data-role="input-control" style=" width: 40%;">
                            <input id="phoneNumber" name="phoneNumber" type="number" placeholder="Phone Number" required>
                            <button class="btn-clear" tabindex="-1" type="button"></button>
                        </div>

                        <label>Address</label>
                        <div class="input-control text" data-role="input-control" style=" width: 80%;">
                            <input id="address" name="address" type="text" placeholder="Address" required>
                            <button class="btn-clear" tabindex="-1" type="button"></button>
                        </div>

                        <label>Coordinate of Address</label>
                        <div class="input-control text" data-role="input-control" style=" width: 80%;">
                            <div id="panel" style=" margin-top: 5px; display:  inline;">
                                <input id="addressForCoordinate" type="textbox" value="HK">
                            </div>
                        </div>
                        <input type="button" value="Geocode" onclick="codeAddress();">
                        <div class="input-control text" data-role="input-control" style=" width: 80%;">
                            <div id="map-canvas"></div>
                        </div>
                        
                        <label>Latitude</label>
                        <div class="input-control text" data-role="input-control" style=" width: 40%;" >
                            <input id="lat" type="text" name="lat" placeholder="Latitude" required>
                            <button class="btn-clear" tabindex="-1" type="button"></button>
                        </div>

                        <label>Longitude</label>
                        <div class="input-control text" data-role="input-control" style=" width: 40%;" >
                            <input id="lng" type="text" name="lng" placeholder="Longitude" required>
                            <button class="btn-clear" tabindex="-1" type="button"></button>
                        </div>
                        
                        <label>Store Create Time</label>                    
                        <div class="input-control text" data-role="input-control" style=" width: 40%;">
                            <input id="storeCreateTime" name="storeCreateTime" type="date" required>
                            <button class="btn-clear" tabindex="-1" type="button"></button>
                        </div>
                        <div class="calendar"></div>
                        <script>
            $(function() {
                var cal = $(".calendar").calendar({
                    multiSelect: false,
                    getDates: function(data) {

                    },
                    click: function(d) {
                        $('#storeCreateTime').val(d);
                    }
                });
            });
                        </script>

                        <label>Category</label>
                        <div class="input-control select" style="width: 30%;">
                            <select id="category" name="category">
                                <%
                                    ArrayList<Category> categories = (ArrayList) request.getAttribute("categories");
                                    for (int x = 0; x < categories.size(); x++) {
                                        out.println("<option value='" + categories.get(x).getCategoryID() + "'>" + categories.get(x).getCategoryName().toUpperCase() + "</option>");
                                    }
                                %>
                            </select>
                        </div>

                        <label>Store Description</label>
                        <div class="input-control textarea">
                            <textarea id="storeDesc" name="storeDesc" required></textarea>
                        </div>
                        <input type="submit" value="Submit" class=" large"/>
                    </form>
                </fieldset>
            </div>
        </div>
    </body>
</html>
