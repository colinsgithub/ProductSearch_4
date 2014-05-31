<%@page import="java.util.List"%>
<%@page import="bean.Comment"%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<!DOCTYPE html>
<%@page import="bean.Store"%>
<%@page import="java.util.ArrayList"%>
<html>
    <head>
        <title>Product Search</title>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
        <meta charset="utf-8">

        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyBQTZl_t-QrF8ExPfToPAjeclIf1DUZ2jc&key=AIzaSyBQTZl_t-QrF8ExPfToPAjeclIf1DUZ2jc&libraries=geometry,places&sensor=false"></script>

        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <%-- draw bar api--%>

        <script src="js/markerwithlabel.js"></script>
        <script src="js/pace.min.js"></script>

        <link href='http://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>

        <%--css for nested--%>
        <link href="css/nested.css" rel="stylesheet" type="text/css" media="screen" />
        <%--css for normal--%>
        <link href="css/normal.css" rel="stylesheet" type="text/css" media="screen" />
        <%--css for metro ui--%>
        <link href="css/metro-bootstrap.css" rel="stylesheet" type="text/css" media="screen" />
        <%--css for metro iconFont--%>
        <link href="css/iconFont.css" rel="stylesheet"/>
        <link href="css/docs.css" rel="stylesheet">

        <%--source from JQuery UI--%>

        <link rel="stylesheet" href="css/loadingBar.css">
        <link rel="stylesheet" href="css/jquery-ui.css">
        <link rel="stylesheet" href="css/dialog.css">

        <link rel="stylesheet" href="css/jquery.dataTables.css">

        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>


        <%--javascript for mixitup--%>
        <script src="js/jquery.mixitup.min.js"></script>

        <%--javascript for nested--%>
        <script src="js/jquery.nested.js"></script>
        <script src="js/makeboxes.js"></script>
        <%--javascript for three.js--%>
        <script src="http://www.html5canvastutorials.com/libraries/three.min.js"></script>
        <%--javascript for datatable--%>
        <script src="js/jquery.dataTables.js"></script>
        <%--javascript for metro-motify--%>
        <script src="js/metro-notify.js"></script>
        <%--javascript for metro-rating--%>
        <script src="js/metro-rating.js"></script>
        <%--javascript for metro-carousel--%>
        <script src="js/metro-carousel.js"></script>
        <%--javascript for metro--%>
        <%--<script src="js/metro.min.js"></script>--%>

        <script>
            //init slider bar
            $(function() {
                $("#slider").slider({
                    value: 500,
                    min: 0,
                    max: 2400,
                    step: 1,
                    slide: function(event, ui) {
                        cityCircle.setRadius(ui.value);
                    },
                    stop: function(event, ui) {
                        $("#distance").val(ui.value);
                        map.setCenter(cityCircle.getCenter());
                        lopStore();
                        changeZoom();
                    }
                });
                $("#distance").val($("#slider").slider("value"));
            });
            //input slider bar's value
            $(document).ready(function() {
                $("#distance").change(function() {
                    $("#slider").slider({
                        value: $("#distance").val()
                    });
                    cityCircle.setRadius(parseInt($("#distance").val()));
                    map.setCenter(cityCircle.getCenter());
                    lopStore();
                    changeZoom();
                });
                //animation for menu button
                $('#menu img').mouseenter(
                        function(event, ui) {
                            $(event.target).animate({
                                height: '60px'
                            }, 100);
                        }).mouseleave(
                        function(event, ui) {
                            $(event.target).animate({
                                height: '45px'
                            }, 100);
                        });
            });</script>

        <script>
            var rendererOptions = {
                draggable: true
            };
            var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
            var directionsService = new google.maps.DirectionsService();
            var map;
            var markersArray = [];
            function initialize() {
                if (navigator.geolocation) {

                    var mapOptions = {
                        zoom: 16,
                        disableDefaultUI: true,
                        panControl: true,
                        streetViewControl: true
                    };
                    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
                    geoLocation(); //current location

                } else {
                    // Browser doesn't support Geolocation
                    handleNoGeolocation(false);
                }

                searchBoxDefault();
                //markers's meaning
                markerList();
                //
                //
                //directionsDisplay.setMap(map);
                //directionsDisplay.setPanel(document.getElementById('directionsPanel'));
                //google.maps.event.addListener(directionsDisplay, 'directions_changed', function() {
                //  computeTotalDistance(directionsDisplay.getDirections());
                //});
                //calcRoute();

                //detect the event of zoom level changed
                google.maps.event.addListener(map, 'zoom_changed', zoomChanged);
            }

            function lopStore() {
                deleteOverlays(); //remove previous markers
            <%
                String lat = "";
                String lng = "";
                String storeName = "";
                String storeId = "";
                ArrayList<Store> stores = null;
                int size = 0;

                if (request.getAttribute("stores") != null) {
                    stores = (ArrayList<Store>) request.getAttribute("stores");
                    size = stores.size();

                    for (int x = 0; x < stores.size(); x++) {
                        lat = "" + stores.get(x).getLatitude();
                        lng = "" + stores.get(x).getLongitude();
                        storeName = stores.get(x).getStoreName();

                        storeId = "" + stores.get(x).getStoreID();
                        //out.println("varstoreName = " + stores.get(x).getStoreName());
                        out.println("var latLngB = new google.maps.LatLng('" + lat + "', '" + lng + "', 10);");
                        out.println("storeName = \"" + storeName + "\";");
                        out.println("storeId = \"" + storeId + "\";");
                        out.println("storeRank = '" + stores.get(x).getRank() + "'");
                        out.println("storePhoneNumber = '" + stores.get(x).getPhoneNumber() + "'");
                        out.println("storeAddress = '" + stores.get(x).getAddress() + "'");
                        out.println("storeAvatar = '" + stores.get(x).getStoreAvatar() + "'");

                        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProductSearch_3PU");
                        EntityManager em = factory.createEntityManager();
                        em.getTransaction().begin();

                        //Store store = em.find(Store.class, storeId);

                        Query query = em.createQuery("select c.rank, count(c.rank) from Comment c where c.commentPK.storeID = :storeID group by c.rank");
                        query.setParameter("storeID", Integer.parseInt(storeId));
                        //get a sorted comment list
                        List countList = query.getResultList();
                        em.getTransaction().commit();
                        em.close();
                        factory.close();

                        //out.println("verfiyRange(radius, latLngWithInRangeArray, latLngB, storeName, storeId);");
                        out.println("dropMarker(latLngB);");

                    }

                    out.println("createList();");
                }
            %>
            }
            function dropMarker(latLngB) {
                if (markerFilterList[idenClassOfStore(storeRank)] === null) {
                    return;
                }

                distanceWithMarker1 = google.maps.geometry.spherical.computeDistanceBetween(marker1.getPosition(), latLngB);
                var distance = google.maps.geometry.spherical.computeDistanceBetween(map.getCenter(), latLngB);
                if (distance < cityCircle.getRadius()) {
                    var marker = new MarkerWithLabel({
                        map: map,
                        position: latLngB,
                        content: 'Store',
                        name: (storeName).toUpperCase(),
                        distance: parseInt(distanceWithMarker1),
                        cover: storeAvatar,
                        storeID: storeId,
                        rank: storeRank,
                        icon: colorOfMarker(storeRank),
                        storeAddress: storeAddress,
                        storePhoneNumber: storePhoneNumber,<%--shape: {
                            coords: [0, 0, 20],
                            type: "circle"
                        },
            --%>
                        labelContent: "1",
                        labelAnchor: new google.maps.Point(20, 50),
                        labelClass: "labels", // the CSS class for the label
                        labelStyle: {opacity: 1}
                    });
                    google.maps.event.addListener(marker, 'click', function() {
                        //alert('Marker Click');
                        cityCircle.setCenter(marker.getPosition());
                        //map.panTo(marker.getPosition());
                        //map.setZoom(map.getZoom() + 1);
                    });
                    var markerRecord = true;
                    if (markersArray.length !== 0) {
                        for (i in markersArray) {
                            var distanceBetStores = google.maps.geometry.spherical.computeDistanceBetween(markersArray[i].getPosition(), marker.getPosition());
                            if (distanceBetStores < (cityCircle.getRadius() * 0.15)) {
                                markersArray[i].labelContent = parseInt(markersArray[i].labelContent) + 1;
                                markersArray[i][0].push(marker); //push overlaying marker to one
                                marker.setMap(null);
                                markerRecord = false;
                                break;
                            }
                        }
                    }
                    if (markerRecord) {
                        marker[0] = []; //marker creates a list to record others markers which is overlayed 
                        //marker[0].push(marker); //a list should incude the main marker;
                        markersArray.push(marker);
                    }
                    //all stores with radius
                    //createListOfStores(parseInt(distance));
                }
            }

            layersColor = [16724736, 16750848, 26265];
            var gorgeousCylinderIcon = createSimpleCylinder(layersColor[0]).toDataURL();
            var goodCylinderIcon = createSimpleCylinder(layersColor[1]).toDataURL();
            var regularCylinderIcon = createSimpleCylinder(layersColor[2]).toDataURL();
            function createSimpleCylinder(color) {
                // renderer
                var renderer = new THREE.WebGLRenderer();
                renderer.setSize(80, 70);
                var render = renderer.domElement;
                // camera
                var camera = new THREE.PerspectiveCamera(45, window.innerWidth / window.innerHeight, 1, 1000);
                camera.position.z = 140;
                // scene
                var scene = new THREE.Scene();
                var directionalLight = new THREE.DirectionalLight(0xffffff, 1);
                directionalLight.position.set(200, 100, -10);
                scene.add(directionalLight);
                group = new THREE.Object3D();
                group.position.y = 5;
                scene.add(group);
                group.rotation.x = 3.50;
                var topRadius = 23;
                var bottomRadius = 23;
                // cone
                // API: THREE.CylinderGeometry(bottomRadius, topRadius, height, segmentsRadius, segmentsHeight)
                var cone = new THREE.Mesh(new THREE.CylinderGeometry(1, topRadius, 30, 15, 1, false), new THREE.MeshPhongMaterial({
                    color: 7788287,
                    ambient: 72083,
                    emissive: color,
                    specular: 72083,
                    shininess: 30,
                    blending: 2,
                    opacity: 1,
                    transparent: true,
                    wireframe: false
                }));
                cone.overdraw = true;
                cone.rotation.x = 0;
                cone.position.set(0, 50, 0);
                group.add(cone);
                // cylinder
                // API: THREE.CylinderGeometry(bottomRadius, topRadius, height, segmentsRadius, segmntsHeight)

                var chartHeight = -30;
                var position = chartHeight *= -1;
                var cylinder = new THREE.Mesh(new THREE.CylinderGeometry(bottomRadius, topRadius, 60, 15, 1, false), new THREE.MeshPhongMaterial({
                    color: 7788287,
                    ambient: 72083,
                    emissive: color,
                    specular: 72083,
                    shininess: 30,
                    blending: 2,
                    opacity: 1,
                    transparent: false,
                    wireframe: false

                }));
                cylinder.overdraw = true;
                cylinder.rotation.x = 0;
                cylinder.position.set(0, position -= 25, 0);
                group.add(cylinder);
                renderer.render(scene, camera);
                return render;
            }

            function createCylinder(layer, oneLayer, twoLayer, threeLayer) {
                // renderer
                var renderer = new THREE.WebGLRenderer();
                renderer.setSize(80, 70);
                var render = renderer.domElement;
                // camera
                var camera = new THREE.PerspectiveCamera(45, window.innerWidth / window.innerHeight, 1, 1000);
                camera.position.z = 140;
                // scene
                var scene = new THREE.Scene();
                var directionalLight = new THREE.DirectionalLight(0xffffff, 1);
                directionalLight.position.set(200, 200, 30);
                scene.add(directionalLight);
                group = new THREE.Object3D();
                group.position.y = 5;
                scene.add(group);
                group.rotation.x = 3.50;
                var topRadius = 23;
                var bottomRadius = 23;
                // cone
                // API: THREE.CylinderGeometry(bottomRadius, topRadius, height, segmentsRadius, segmentsHeight)
                var cone = new THREE.Mesh(new THREE.CylinderGeometry(1, topRadius, 30, 15, 1, false), new THREE.MeshPhongMaterial({
                    color: 7788287,
                    ambient: 72083,
                    emissive: 7788287,
                    specular: 72083,
                    shininess: 30,
                    blending: 2,
                    opacity: 1,
                    transparent: true,
                    wireframe: false
                }));
                cone.rotation.x = 0;
                cone.position.set(0, 50, 0);
                group.add(cone);
                //total num of markers
                var total = oneLayer + twoLayer + threeLayer;
                var oneLayerHeight = (oneLayer / total) * 60;
                var twoLayerHeight = (twoLayer / total) * 60;
                var threeLayerHeight = (threeLayer / total) * 60;
                var layersHeight = [threeLayerHeight, twoLayerHeight, oneLayerHeight];
                var layerPosition = 50; //base position of 3d marker
                var previousLayerHeight = 30; //height of cone
                for (var x = 2; x > -1; x--) {
                    if (layersHeight[x] !== 0) {
                        layerPosition -= ((previousLayerHeight / 2) + (layersHeight[x] / 2));
                        previousLayerHeight = layersHeight[x];
                        // cylinder
                        // API: THREE.CylinderGeometry(bottomRadius, topRadius, height, segmentsRadius, segmntsHeight)
                        var cylinder = new THREE.Mesh(new THREE.CylinderGeometry(bottomRadius, topRadius, layersHeight[x], 15, 1, false), new THREE.MeshPhongMaterial({
                            color: 7788287,
                            ambient: 72083,
                            emissive: layersColor[x],
                            specular: 72083,
                            shininess: 30,
                            blending: 2,
                            opacity: 1,
                            transparent: false,
                            wireframe: false
                        }));
                        cylinder.rotation.x = 0;
                        cylinder.position.set(0, layerPosition, 0);
                        group.add(cylinder);
                    }
                }

                // render
                renderer.render(scene, camera);
                return render;
            }

            grade = ["Bad", "Poor", "Regular", "Good", "Gorgeous"];
            function createList() {
                if (markersArray.length === 0)// no marker on map, then return
                    return;
                for (var i in markersArray) {
                    if (markersArray[i][0].length === 0) {
                        var infoString =
                                "<table class='metro' id='storeInfoWindwo' style='width:330px;margin:10px;'>" +
                                "<tr>" +
                                "<td rowspan='7'><img  style = 'border-radius: 6px;margin: 0 5px 0 10px;' src='" + markersArray[i].cover + "' width='130px' height='130px'/></td>" +
                                "<td style='font-size:30px;'>" + markersArray[i].name + "</td>" +
                                "</tr>" +
                                "<tr><td><div class='rank'><div id='score' style='cursor:pointer;width:100px;'>";
                        var displayRank = parseInt(parseInt(markersArray[i].rank) / 100 * 5);
                        for (var x = 0; x < displayRank; x++) {
                            infoString += "<img style='width:20px;' src='lib/img/star-on.png' alt='1' title='" + grade[x] + "'>";
                        }
                        for (var x = displayRank; x < 5; x++) {
                            infoString += "<img style='width:20px;' src='lib/img/star-off.png' alt='1' title='" + grade[x] + "'>";
                        }

                        infoString += "<tr><td style='display: flex;'><img style='height:25px;' src='image/compasses.png'/><div style='margin: auto 0px auto 10px;'>" + markersArray[i].distance + "m</div></td></tr>";
                        infoString += "</div></td></tr>" +
                                "<tr><td><button class='default' id='enterStore' storeId='" + markersArray[i].storeID + "' onclick='directEnterStore(this);' style='width: 100%;'>Enter Store</button></td></tr>" +
                                "<tr><td><button class='default' onclick='sizeCenterPane();' style='width: 100%;'>Stores List</button></td></tr>" +
                                //"<tr><td><a href=\"javascript:calcRoute(this);" + "\" x='" + "happy" + "'>Direct</a></td></tr>" +
                                "<tr><td><button class='default' id='comment' storeId='" + markersArray[i].storeID + "' onclick='comment(this);' style='width: 100%;'>Comment</button></td></tr>" +
                                "</table>";
                        infoString += "<a style='width:25px;height:25px;' storeId='" + markersArray[i].storeID + "' onclick='addToFollowList(this);'><img style='width:25px;' src='image/heart.png' ></a>";
                        var infoWindow = addInfoWindow(markersArray[i], infoString);
                    } else {
                        var infoList =
                                "<div id='storeInfoWindowList' style='height:300px;overflow-x:auto'>" +
                                //'<div id="chart_div"></div>' +
                                "<table style='width:250px;margin:7px;'>";
                        var sort_by = function(field, reverse, primer) {
                            var key = primer ?
                                    function(x) {
                                        return primer(x[field]);
                                    } :
                                    function(x) {
                                        return x[field];
                                    };
                            reverse = [-1, 1][ +!!reverse];
                            return function(a, b) {
                                return a = key(a), b = key(b), reverse * ((a > b) - (b > a));
                            };
                        };
                        var MarkerListForInfoBox = [];
                        MarkerListForInfoBox.push(markersArray[i]);
                        for (var x in markersArray[i][0]) {
                            MarkerListForInfoBox.push(markersArray[i][0][x]);
                        }

                        MarkerListForInfoBox.sort(sort_by('rank', false, parseInt));
                        markersArray[i]['Regular'] = 0;
                        markersArray[i]['Good'] = 0;
                        markersArray[i]['Gorgeous'] = 0;
                        // for generate bar chart for reference

                        for (var x in MarkerListForInfoBox) {
                            infoList +=
                                    "<tr>" +
                                    "<td rowspan='3'><img style='margin:10px 10px 10px 0;width:75px;border-radius: 5px;' src='" + MarkerListForInfoBox[x].cover + "'/></td>" +
                                    "<td style='font-size:20px;height:20px;'>" + MarkerListForInfoBox[x].name + "</td>" +
                                    "</tr>" +
                                    "<tr>" +
                                    "<td style='display: flex;'><img style='height:25px;' src='image/compasses.png'/><div style='height:25px;margin-left:10px;'>" + MarkerListForInfoBox[x].distance + "m<div></td>" +
                                    "</tr>" +
                                    "<tr><td><div class='rank'><div id='score' style='cursor:pointer;width:100px;'>";
                            var displayRank = parseInt(parseInt(MarkerListForInfoBox[x].rank) / 100 * 5);
                            if (displayRank <= 3) {
                                markersArray[i]['Regular']++;
                            } else if (displayRank === 4) {
                                markersArray[i]['Good']++;
                            } else {
                                markersArray[i]['Gorgeous']++;
                            }

                            for (var x = 0; x < displayRank; x++) {
                                infoList += "<img style='width:20px;' src='lib/img/star-on.png' alt='1' title='" + grade[x] + "'>";
                            }
                            for (var x = displayRank; x < 5; x++) {
                                infoList += "<img style='width:20px;' src='lib/img/star-off.png' alt='1' title='" + grade[x] + "'>";
                            }

                            infoList += "</div></td></tr>";
                        }
                        infoList += "</table></div>";
                        markersArray[i].setIcon(createCylinder(3, markersArray[i]['Regular'], markersArray[i]['Good'], markersArray[i]['Gorgeous']).toDataURL());
                        var listOfInfoWindow = addListOfInfoWindow(markersArray[i], infoList);
                    }
                }
                createMarkersPageList();
                //right panel data 
            }

            function comment(obj) {
                var storeId = $(obj).attr('storeId');
                $("#dialog-commentList").dialog({
                    autoOpen: false,
                    height: 600,
                    width: 1000,
                    draggable: false,
                    modal: true,
                    resizable: false,
                    minHeight: 200,
                    minWidth: 455,
                    maxWidth: 1100,
                    maxHeight: 570,
                    buttons: {
                        "Comment": function() {
                            var box = document.createElement('div');
                            box.className = 'box size42';
                            $(box).append('<div class="Grid" style="margin:10px;">' +
                                    '<div class="row"><textarea style="width: 100%;resize:none;" id="comment" rows="5" placeholder="Type Your Comment..."></textarea></div>' +
                                    '<div class="row">' +
                                    '<div class="rating" id="rating_1">' +
                                    '<ul>' +
                                    '<li class="rated"></li>' +
                                    '<li class="rated"></li>' +
                                    '<li></li>' +
                                    '<li></li>' +
                                    '<li></li>' +
                                    '</ul>' +
                                    '<span style="line-height: 25px;" class="score-hint"></span>' +
                                    '</div>' +
                                    '<button style="float: right;" class="Primary" storeId="' + storeId + '" onclick="submitComment(this);">Submit</button></div></div>');
                            $("#comment-display").prepend(box).nested("prepend", box);

                            $("#rating_1").rating({
                                static: false,
                                score: 2,
                                stars: 5,
                                showHint: true,
                                hints: ['Bad', 'Poor', 'Regular', 'Good', 'Gorgeous'],
                                showScore: true,
                                scoreHint: "Current score: ",
                            });
                        },
                        "Close": function() {
                            $(this).dialog("close");
                        }
                    },
                    close: function() {

                    }
                });
                $("#dialog-commentList").dialog("open");
                $("#loading").remove();
                $("#dialog-commentList").parent().prepend("<div id='loading' class='loading' style='display: none;'></div>");
                //get comment from servlet
                $("#comment-display").empty();
                //clear older data

                getCommentFirstTime = function() {
                    $.ajax({
                        url: 'HandleComment?action=getComment&storeId=' + storeId,
                        type: 'POST',
                        data: {
                            startIndex: 0,
                            endIndex: 9
                        },
                        // pass comment for load part of comment list 
                        success: function(response) {
                            var json = JSON.parse(response);
                            for (var x in json['comments']) {
                                var userId = json['comments'][x]['userId'];
                                var userName = json['comments'][x]['userName'];
                                var feedback = json['comments'][x]['feedback'];
                                var postedDate = json['comments'][x]['postedDate'];
                                /*
                                 $("#comment-display").append("<a href=HandleUser?action=viewOtherUser&userId=" + userId + ">" + userName + "</a>");
                                 $("#comment-display").append("<div style=''>" + feedback + "</div>");
                                 */
                                $("#comment-display").append(
                                        '<div class="' + randomSize(feedback.length) + '">' +
                                        '<div style="display: flex;"><img style="margin: 5px;height:50px; id="personalInfo" src="image/profle.png" title="User Photo"/>' +
                                        '<div><a href=HandleUser?action=viewOtherUser&userId=' + userId + '>' + userName + '</a>' +
                                        '<br/>' + postedDate + '</div></div><cpan>' + feedback + '</cpan></div>');
                            }

                            $("#comment-display").nested({
                                resizeToFit: false,
                                minWidth: 100,
                                gutter: 5
                            });
                            $("#dialog-commentList").scroll(loadMore);
                            var isLastIndex = json['isLastIndex'];
                            var startIndex = json['startIndex'];
                            var endIndex = json['endIndex'];
                            function loadMore() {
                                if ($("#dialog-commentList").scrollTop() + $("#dialog-commentList").height() > $("#comment-display").height() - 1) {
                                    if (!isLastIndex) {
                                        var commentListRequest = makeBoxes(startIndex, endIndex, json['storeId']);
                                        isLastIndex = commentListRequest[0]['isLastIndex'];
                                        startIndex = commentListRequest[0]['startIndex'];
                                        endIndex = commentListRequest[0]['endIndex'];
                                    }
                                }
                            }
                        }
                    });
                };
                getCommentFirstTime();
            }

            function addToFollowList(obj) {
                $.ajax({
                    url: 'HandleTag?action=addTag&' + 'storeId=' + obj.getAttribute('storeId'),
                    type: 'POST',
                    success: function(response) {
                        var isLogin = (response === 'false');
                        if (isLogin) {
                            BookmarkApp.addBookmark(obj);
                            //$('#personalInfo').click();
                        } else {
                            displayNotify("Message", "Add To Favorite List Successfully...");
                        }
                    }
                });
            }




            function directEnterStore(obj) {
                sizeCenterPane();
            }

            function RightArrow(obj) {
                var x = $(obj).find('#rightArrow');
                if ($(obj).find('#rightArrow').hasClass('shown')) {
                    x.hide();
                    $(x).removeClass('shown');
                }
                else {
                    x.show();
                    $(x).addClass('shown');
                }
            }

            function sortList(obj) {
                var pageDisplayed = [];
                for (var x in markerPageArray) {
                    for (var y in markerPageArray[x])
                        pageDisplayed.push(markerPageArray[x][y]);
                }

                //sort field function;
                var sort_by = function(field, reverse, primer) {
                    var key = primer ?
                            function(x) {
                                return primer(x[field]);
                            } :
                            function(x) {
                                return x[field];
                            };
                    reverse = [-1, 1][ +!!reverse];
                    return function(a, b) {
                        return a = key(a), b = key(b), reverse * ((a > b) - (b > a));
                    };
                };
                var sortBy;
                if (parseInt($(obj).attr('sortBy')) === 0) {
                    sortBy = false;
                    $(obj).attr('sortBy', 1);
                } else {
                    sortBy = true;
                    $(obj).attr('sortBy', 0);
                }

                if ($(obj).attr('sortKey') === 'name')
                    pageDisplayed.sort(sort_by($(obj).attr('sortKey'), sortBy, function(a) {
                        return a.toUpperCase();
                    }));
                else if ($(obj).attr('sortKey') === 'distance' || $(obj).attr('sortKey') === 'rank') {
                    pageDisplayed.sort(sort_by($(obj).attr('sortKey'), sortBy, parseInt));
                }

                markerPageArray = []; // each page means an array
                var page = [];
                var countColumn = 0;
                for (var x = 0; x < pageDisplayed.length; x++) {
                    countColumn++;
                    page.push(pageDisplayed[x]);
                    if (countColumn === 5) {
                        markerPageArray.push(page);
                        page = [];
                        countColumn = 0;
                    }
                }
                if (page.length !== 0)
                    markerPageArray.push(page);
                createListOfStores();
            }


            function createMarkersPageList() {
                var pageDisplayed = [];
                for (var x in markersArray) {
                    pageDisplayed.push(markersArray[x]);
                    for (var y in markersArray[x][0])
                        pageDisplayed.push(markersArray[x][0][y]);
                }

                var sort_by = function(field, reverse, primer) {
                    var key = primer ?
                            function(x) {
                                return primer(x[field]);
                            } :
                            function(x) {
                                return x[field];
                            };
                    reverse = [-1, 1][ +!!reverse];
                    return function(a, b) {
                        return a = key(a), b = key(b), reverse * ((a > b) - (b > a));
                    };
                };
                pageDisplayed.sort(sort_by('rank', false, parseInt));
                markerPageArray = []; // each page means an array
                var page = [];
                var countColumn = 0;
                for (var x = 0; x < pageDisplayed.length; x++) {
                    countColumn++;
                    page.push(pageDisplayed[x]);
                    if (countColumn === 5) {
                        markerPageArray.push(page);
                        page = [];
                        countColumn = 0;
                    }
                }
                if (page.length !== 0)
                    markerPageArray.push(page);
                $('#sortList button').find('i').removeClass();
                $('#preSortSelected').siblings('button').find('i').addClass('icon-embed on-right');
                $('#preSortSelected').find('i').addClass('icon-arrow-down-5 on-right');
                createListOfStores();
            }

            function createListOfStores() {
                //create a sort bar
                $('#mainContent #leftPanel #Grid').remove(); //removre stores list
                $('#mainContent #leftPanel').append("<ul style='padding-left: 0px;' id='Grid' class='listview'></ul>");
                $('#pageList').remove();
                var pageButton = "<div class='metro' id='pageList' style='padding-left: 10px;'>" +
                        "<div id='pageBox' class='pagination'><ul>" +
                        "<li class='first' onclick=changeFirstPage()><a><i class='icon-first-2'></i></a></li>" +
                        "<li class='prev' onclick=changePreviousPage()><a><i class='icon-previous'></i></a></li>" +
                        "<li id='list-FirstPageNum' class='active' onclick=changeListPage(this)><a>1</a></li>";
                for (var x = 2; x <= markerPageArray.length; x++) {
                    if (markerPageArray.length === x) {
                        pageButton += "<li id='list-LastPageNum' onclick=changeListPage(this)><a>" + x + "</a></li>";
                        break;
                    }
                    pageButton += "<li onclick=changeListPage(this)><a>" + x + "</a></li>";
                }
                pageButton += "<li class='next'  onclick=changeNextPage()><a><i class='icon-next'></i></a></li>" +
                        "<li class='last' onclick=changeLastPage()><a><i class='icon-last-2'></i></a></li>" +
                        "</ul></div></div>";
                var pageDisplayed = markerPageArray[0];
                createPageObject(pageDisplayed);
                $('#Grid').mixitup();
                $('#mainContent #leftPanel').append(pageButton);
            }

            function displayDetail(obj) {
                if ($(obj).next('li').hasClass('showed')) {
                    $(obj).next('li').removeClass('showed').hide('slow');
                } else {
                    $(obj).next('li').addClass('showed')
                    $(obj).next('li').show('slow');
                }
            }

            function changeFirstPage() {
                $('#pageList #list-FirstPageNum').click();
            }
            function changeLastPage() {
                $('#pageList #list-LastPageNum').click();
            }
            function changePreviousPage() {
                $('#pageList .active').prev().click();
            }
            function changeNextPage() {
                $('#pageList .active').next().click();
            }

            function createPageObject(pageDisplayed) {
                for (var x = 0; x < pageDisplayed.length; x++) {
                    var starNum = '';
                    var displayRank = parseInt(parseInt(pageDisplayed[x].rank) / 100 * 5);
                    for (var i = 0; i < displayRank; i++)
                        starNum += '<li class="rated"></li>';
                    for (var i = displayRank; i < 5; i++)
                        starNum += '<li></li>';
                    $('#mainContent #leftPanel #Grid').append(
                            '<div class="listview mix" onclick=displayDetail(this);>' +
                            '<a href="#" class="list bg-lightBlue fg-white shadow">' +
                            '<div class="list-content">' +
                            '<img src="' + pageDisplayed[x].cover + '" class="icon">' +
                            '<div class="data">' +
                            '<span class="list-title">' + pageDisplayed[x].name + '</span>' +
                            '<div class="rating small no-margin fg-yellow"><ul>' +
                            starNum +
                            '</ul></div>' +
                            '<span class="list-remark">' + pageDisplayed[x].distance + 'm</span>' +
                            '</div>' +
                            '</div>' +
                            '</a>' +
                            "</div>" +
                            "<li onmouseout='RightArrow(this);' onmouseover='RightArrow(this);' id='rowStore' style='display:none;padding: 0px 0px 10px 15px;'>" +
                            "<table style='width: 360px'>" +
                            "<tr><td style='font-size:25px;font-family: Roboto;padding:0px 0px 10px 0px;'>" + pageDisplayed[x].name + "</td>" +
                            "<td rowspan='5' width='40px;font-weight: 600;'><div id='rightArrow' class='controlArrow next' storeId='" + pageDisplayed[x].storeID + "' onclick='viewMoreDetail(this);'></div><td></tr>" +
                            "<td style='font-size:15px;font-family: Roboto;display: flex;'><img style='height:25px;' src='image/compasses.png'/><div style='height:25px;margin-left:10px;'>" + pageDisplayed[x].distance + "m</div></td></tr>" +
                            "<tr><td style='font-size:15px;font-family: Roboto;display: flex;'><img style='margin-top: auto;height: 25px;margin-bottom: auto;' src='image/location.png'/><div style='width:70%;margin-left:10px;'>" + pageDisplayed[x].storeAddress + "</div></td></tr>" +
                            "<tr><td style='font-size:15px;font-family: Roboto;display: flex;'><img style='height:25px;' src='image/contact.png'/><div style='height:25px;margin-left:10px;'>" + pageDisplayed[x].storePhoneNumber + "</div></td></tr>" +
                            "</table>" +
                            "</li >"
                            );
                }
            }

            function changeListPage(page) {
                $('#pageList li').removeClass('active');
                $(page).addClass('active');
                $('#mainContent #leftPanel #Grid').remove(); //removre stores list
                $('#mainContent #leftPanel').append("<ul style='padding-left: 0px;' id='Grid'></ul>");
                window.console.log($(page).find('a'));
                var pageNum = parseInt($(page).find('a').html() - 1);
                var pageDisplayed = markerPageArray[pageNum];
                createPageObject(pageDisplayed);
                $('#Grid').mixitup();
                $('#pageList').appendTo('#mainContent #leftPanel');
            }

            var infoWindowList = []; //for each time only open a info window
            function addInfoWindow(marker, message) {
                var infoWindow = new google.maps.InfoWindow({
                    content: message
                });
                infoWindowList.push(infoWindow);
                google.maps.event.addListener(marker, 'mouseover', function() {
                    closeAllInfoWindow();
                    infoWindow.open(map, marker);
                });
                /*
                 google.maps.event.addListener(marker, 'mouseout', function() {
                 infoWindow.close();
                 });
                 */
                return infoWindow;
            }

            function addListOfInfoWindow(marker, message) {

                var infoWindow = new google.maps.InfoWindow({
                    content: message
                });
                infoWindowList.push(infoWindow);
                google.maps.event.addListener(marker, 'click', function() {
                    closeAllInfoWindow();
                    infoWindow.open(map, marker);
                    draw(marker);
                });
                /*
                 google.maps.event.addListener(marker, 'mouseout', function() {
                 infowindow.close();
                 });
                 */
                return infoWindow;
            }

            function closeAllInfoWindow() {
                for (i in infoWindowList) {
                    infoWindowList[i].close();
                }
            }

            markerFilterList = ["Gorgeous", "Good", "Regular"];
            function markerFilter(obj) {
                if (obj.checked === false) {
                    for (var i in markerFilterList) {
                        if (markerFilterList[i] === $(obj).val()) {
                            markerFilterList[i] = null;
                            //set null to filter the result
                        }
                    }
                } else {
                    if ($(obj).val() === "Gorgeous") {
                        markerFilterList[0] = "Gorgeous";
                    }
                    else if ($(obj).val() === "Good") {
                        markerFilterList[1] = "Good";
                    }
                    else if ($(obj).val() === "Regular") {
                        markerFilterList[2] = "Regular";
                    }
                }
                lopStore();
            }

            markerColorList = ["marker/centre.png", gorgeousCylinderIcon, goodCylinderIcon, regularCylinderIcon];
            function markerList() {
                //the list of marker
                map.controls[google.maps.ControlPosition.LEFT_BOTTOM].push(document.getElementById('legend'));
                var legend = document.getElementById('legend');
                var value = ["Current", "Gorgeous", "Good", "Regular"];
                for (var i = 0; i < markerColorList.length; i++) {
                    var div = document.createElement('div');
                    div.style.display = 'table';
                    if (i === 0) {
                        //div.innerHTML = '<input type="checkbox" checked="checked" id="markerFilter" onchange="markerFilter(this);" name="markerFilter" value="' + value[i] + '" style="height:40px;"/>';
                        div.innerHTML += '<img src="' + markerColorList[i] + '" height="50px" width="50"/>' + '<div style="display: table-cell;vertical-align: middle;">' + value[i] + '</div>';
                    } else {
                        div.innerHTML = '<input type="checkbox" checked="checked" id="markerFilter" onchange="markerFilter(this);" name="markerFilter" value="' + value[i] + '" style="height:40px;width:20px;"/>';
                        div.innerHTML += '<img src="' + markerColorList[i] + '" height="60px" width="60px"/>' + '<div style="display: table-cell;vertical-align: middle;">' + value[i] + '</div>';
                    }
                    legend.appendChild(div);
                }
            }

            changeZoomBySlide = false;
            //identify zoom changed by event or slide
            function changeZoom() {
                var z = 75;
                for (y = 0; y < 20; y++) {
                    if (z * Math.pow(2, y) >= parseInt($("#distance").val())) {
                        changeZoomBySlide = true;
                        map.setZoom(19 - y);
                        break;
                    }
                }
            }

            function zoomChanged() {
                closeAllInfoWindow();
                if (changeZoomBySlide) {
                    changeZoomBySlide = false;
                    return;
                }
                var zoom_level = map.getZoom();
                var z = 75;
                var nRadius = z * Math.pow(2, 19 - zoom_level);
                cityCircle.setRadius(nRadius);
                $("#slider").slider({
                    value: nRadius
                });
                $("#distance").val(nRadius);
                //map.panTo(marker1.getPosition());
                map.panTo(cityCircle.getCenter());
                lopStore();
            }

            function geoLocation() {
                navigator.geolocation.getCurrentPosition(function(position) {
                    var pos = new google.maps.LatLng(position.coords.latitude,
                            position.coords.longitude);
                    map.setCenter(pos);
                    setCircle(500, pos);
                    marker1 = new MarkerWithLabel({
                        position: pos,
                        draggable: true,
                        raiseOnDrag: true,
                        map: map,
                        icon: {
                            size: new google.maps.Size(55, 55),
                            scaledSize: new google.maps.Size(55, 55),
                            url: "marker/centre.png"
                        }
                    });
                    var iw1 = new google.maps.InfoWindow({
                        content: "Home For Sale"
                    });
                    google.maps.event.addListener(marker1, "click", function(e) {
                        //iw1.open(map, this);
                    });
                    google.maps.event.addListener(marker1, "dragend", function(e) {
                        cityCircle.setCenter(marker1.getPosition());
                        map.panTo(marker1.getPosition());
                        map.setCenter(marker1.getPosition());
                        lopStore();
                    });
                    lopStore();
                }, function() {
                    handleNoGeolocation(true);
                });
            }

            function sizeCenterPane() {
                $('#Grid').mixitup();
                $('#mainContent').animate({right: '-40px'});
                $('#map-canvas').animate({left: '-20%'});
                //$('#distanceBar').animate({right: '45%'});
                $('#listsStores').attr('src', 'image/arrow-right.png').attr('onclick', 'sizeLeftPane();');
            }

            function returnMapUI() {
                $('#specificStore').animate({width: '65%'}, function() {
                    $('#mainContent #leftPanel').show();
                    $('#specificStore').animate({right: '-70%'});
                    $('#mainContent').animate({right: '-40px'});
                    $('#storeBox').animate({height: '0px'});
                    $('#storeDetail').hide();
                    $('#productofStore').empty();
                }).removeClass('fullScreen');
            }

            function productsListFullScreen() {
                $('#products').css({width: '100%'});
                if ($('#specificStore').hasClass('fullScreen')) {
                    $('#specificStore').animate({width: '65%'});
                    $('#specificStore').removeClass('fullScreen');
                } else {
                    $('#specificStore').animate({width: '100%'});
                    $('#specificStore').addClass('fullScreen');
                }
            }

            function sizeLeftPane() {
                //$('#distanceBar').animate({top: '23px'});
                $('#mainContent').animate({right: '-50%'});
                $('#map-canvas').animate({left: '0%'});
                //$('#distanceBar').animate({right: '5%'});
                $('#listsStores').attr('src', 'image/arrow-left.png').attr('onclick', 'sizeCenterPane();');
            }

            function clearOverlays() {
                if (markersArray) {
                    for (i in markersArray) {
                        markersArray[i].setMap(null);
                    }
                }
            }

            // Shows any overlays currently in the array
            function showOverlays() {
                if (markersArray) {
                    for (i in markersArray) {
                        markersArray[i].setMap(map);
                    }
                }
            }

            // Deletes all markers in the array by removing references to them
            function deleteOverlays() {
                if (markersArray) {
                    for (i in markersArray) {
                        markersArray[i].setMap(null);
                    }
                    markersArray.length = 0;
                }
            }

            function searchBoxDefault() {
                var input = /** @type {HTMLInputElement} */(
                        document.getElementById('pac-input'));
                var types = document.getElementById('type-selector');
                map.controls[google.maps.ControlPosition.TOP_CENTER].push(input);
                map.controls[google.maps.ControlPosition.TOP_CENTER].push(types);
                var autocomplete = new google.maps.places.Autocomplete(input);
                autocomplete.bindTo('bounds', map);
                var infowindow = new google.maps.InfoWindow();
                var markerPlace = new google.maps.Marker({
                    map: map
                });
                google.maps.event.addListener(autocomplete, 'place_changed', function() {
                    infowindow.close();
                    markerPlace.setVisible(false);
                    var place = autocomplete.getPlace();
                    if (!place.geometry) {
                        return;
                    }

                    // If the place has a geometry, then present it on a map.
                    if (place.geometry.viewport) {
                        map.fitBounds(place.geometry.viewport);
                    } else {
                        map.setCenter(place.geometry.location);
                        map.setZoom(17); // Why 17? Because it looks good.
                    }
                    markerPlace.setIcon(/** @type {google.maps.Icon} */({
                        url: place.icon,
                        size: new google.maps.Size(71, 71),
                        origin: new google.maps.Point(0, 0),
                        anchor: new google.maps.Point(17, 34),
                        scaledSize: new google.maps.Size(35, 35)
                    }));
                    markerPlace.setPosition(place.geometry.location);
                    markerPlace.setVisible(true);
                    var address = '';
                    if (place.address_components) {
                        address = [
                            (place.address_components[0] && place.address_components[0].short_name || ''),
                            (place.address_components[1] && place.address_components[1].short_name || ''),
                            (place.address_components[2] && place.address_components[2].short_name || '')
                        ].join(' ');
                    }

                    infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
                    infowindow.open(map, markerPlace);
                });
                // Sets a listener on a radio button to change the filter type on Places
                // Autocomplete.
                function setupClickListener(id, types) {
                    var radioButton = document.getElementById(id);
                    google.maps.event.addDomListener(radioButton, 'click', function() {
                        autocomplete.setTypes(types);
                    });
                }

                setupClickListener('changetype-all', []);
                setupClickListener('changetype-establishment', ['establishment']);
                setupClickListener('changetype-geocode', ['geocode']);
            }

            function calcRoute(object) {
                //var origin = marker1.getPosition();
                /*var destination = marker1.getPosition();
                 var request = {
                 origin: origin,
                 destination: destination,
                 //waypoints: [{location: 'Bourke, NSW'}, {location: 'Broken Hill, NSW'}],
                 travelMode: google.maps.TravelMode.DRIVING
                 };
                 directionsService.route(request, function(response, status) {
                 if (status == google.maps.DirectionsStatus.OK) {
                 directionsDisplay.setDirections(response);
                 }
                 });
                 */
            }

            function computeTotalDistance(result) {
                var total = 0;
                var myroute = result.routes[0];
                for (var i = 0; i < myroute.legs.length; i++) {
                    total += myroute.legs[i].distance.value;
                }
                total = total / 1000.0;
                document.getElementById('total').innerHTML = total + ' km';
            }

            function viewPersonalInfo() {
            }

            function openFavList() {
                $('#dialog-tagList').dialog({
                    autoOpen: false,
                    height: 400,
                    width: 500,
                    draggable: false,
                    modal: true,
                    resizable: false,
                    close: function() {
                        $('.loading').remove();
                        $('#tag-display').empty();
                    }
                });

                $('#tag-display').html(
                        '<table id="tag-table" class="display" cellspacing="0" width="100%;line-height: 20px;">' +
                        '<thead>' +
                        '<tr>' +
                        '<th></th>' +
                        '<th>Store Name</th>' +
                        '<th></th>' +
                        '</tr>' +
                        '</thead>' +
                        '</table>'
                        );
                $.ajax({
                    url: 'HandleTag',
                    type: 'POST',
                    success: function(response) {
                        var isLogin = (response === 'false');
                        if (isLogin) {
                            $('#personalInfo').click();
                        } else {
                            /* Formatting function for row details - modify as you need */
                            function format(d) {
                                // `d` is the original data object for the row
                                return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:0px;width:100%;">' +
                                        '<tr>' +
                                        '<td>Address :</td>' +
                                        '<td>' + d.storeAddress + '</td>' +
                                        '</tr>' +
                                        '<tr>' +
                                        '<td>Creation Time</td>' +
                                        '<td>' + d.creationTime + '</td>' +
                                        '</tr>' +
                                        '</table>';
                            }

                            var table = $('#tag-table').DataTable({
                                "ajax": "HandleTag?action=getTags",
                                "iDisplayLength": 10,
                                "bLengthChange": false,
                                "bDestroy": true,
                                "columns": [
                                    {
                                        "class": 'details-control',
                                        "orderable": false,
                                        "data": null,
                                        "defaultContent": ''
                                    },
                                    {"data": "storeName"},
                                    {
                                        "class": 'tag-remove',
                                        "orderable": false,
                                        "data": 'tagID',
                                        "defaultContent": ''
                                    }
                                ],
                                "order": [[1, 'asc']],
                                "oLanguage": {
                                    "oPaginate": {
                                        "sNext": "",
                                        "sPrevious": ""
                                    },
                                    "sInfo": ""
                                },
                                "bSearchable": false,
                                "aoColumnDefs": [
                                    {"aTargets": [1],
                                        "fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
                                            $(nTd).attr("storeID", oData.storeID);
                                            $(nTd).attr("onclick", "closeTagDialog(this)");
                                        }
                                    },
                                    {"aTargets": [2],
                                        "fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
                                            $(nTd).empty();
                                            $(nTd).attr("tagId", sData);
                                        }
                                    }
                                ]
                            });

                            // Add event listener for opening and closing details
                            $('#tag-table tbody').on('click', 'td.details-control', function() {
                                var tr = $(this).parents('tr');
                                var row = table.row(tr);
                                if (row.child.isShown()) {
                                    // This row is already open - close it
                                    row.child.hide();
                                    tr.removeClass('shown');
                                }
                                else {
                                    // Open this row
                                    row.child(format(row.data())).show();
                                    tr.addClass('shown');
                                }
                            });
                            $('#tag-table tbody').on('click', 'td.tag-remove', function() {
                                var tagId = $(this).attr('tagid');
                                window.console.log(tagId);
                                $.ajax({
                                    url: 'HandleTag?action=cancelTag&' + 'tagId=' + tagId,
                                    type: 'POST',
                                    success: function(response) {
                                        var isLogin = (response === 'false');
                                        if (isLogin) {
                                            $('#personalInfo').click();
                                        } else {
                                            $("#dialog-tagList").parent().append("<div id='loading' class='loading' style='display: none;'></div>");
                                            displayNotify('Message', 'Romove Successfully...');
                                        }
                                    }
                                });
                            });
                            $("#dialog-tagList").parent().prepend("<div id='loading' class='loading' style='display: none;'></div>");
                            $('#dialog-tagList').dialog('open');
                            $("#dialog-tagList").parent().position({
                                of: $("#personalInfo").parent(),
                                my: "right" + " " + "top",
                                at: "left" + " " + "top",
                                collision: "fit" + " " + "fit"
                            });
                        }
                    }
                });
            }

            function closeTagDialog(obj) {
                $('#dialog-tagList').dialog('close');
                viewMoreDetail(obj);
            }

            //select a store in stores list
            function viewMoreDetail(obj) {
                $('#mainContent').scrollTop(0);
                $('#storeBox').animate({height: '100%'});
                $('#specificStore').animate({right: '0%'});
                $('#mainContent').animate({right: '65%'});
                $('#storeDetail').show();
                $('#mainContent #leftPanel').hide();

                $.ajax({
                    url: 'HandleStore?action=getAStore&storeId=' + $(obj).attr('storeId'),
                    type: 'POST',
                    success: function(response) {
                        var store = JSON.parse(response);
                        $('#storeDetail #storeImg').attr('src', store.storeAvatar);
                        var starNum = '';
                        var rank = parseInt(parseInt(store.rank) / 100 * 5);
                        for (var i = 0; i < rank; i++)
                            starNum += '<li class="rated"></li>';
                        for (var i = rank; i < 5; i++)
                            starNum += '<li></li>';
                        $('#storeDetail dl').html(
                                '<dt>Store Name</dt>' +
                                '<dd>' + (store.storeName).toUpperCase() + '</dd>' +
                                '<dt>Rank</dt>' +
                                '<dd><div class="rating">' +
                                '<ul>' +
                                starNum +
                                '</ul>' +
                                '</div></dd>' +
                                '<dt>Contact</dt>' +
                                '<dd>' + store.phoneNumber + '</dd>' +
                                '<dt>Create Time</dt>' +
                                '<dd>' + store.createTime + '</dd>' +
                                '<dt>Following</dt>' +
                                '<dd>' + store.addedCount + '</dd>' +
                                '<dt>Address</dt>' +
                                '<dd>' + store.storeAddress + '</dd>' +
                                '<dt>Description</dt>' +
                                '<dd>' + store.storeDesc + '</dd>'
                                );
                    }});

                $('#productofStore').html('<table id="products" class="display" cellspacing="0" style="width=100%;"></table>');

                /* Formatting function for row details - modify as you need */
                function format(d) {
                    // `d` is the original data object for the row
                    var merchandiseImages = '';


                    if (d.merchandiseImage2 !== "") {
                        merchandiseImages += '<div class="slide"><img src="' + d.merchandiseImage2 + '" style="height:100%;"></div>';
                    }

                    if (d.merchandiseImage3 !== "") {
                        merchandiseImages += '<div class="slide"><img src="' + d.merchandiseImage3 + '" style="height:100%;"></div>';
                    }

                    var displayFeatures = '';
                    if (d.features !== 'null') {
                        var features = JSON.parse(d.features);

                        for (var key in features) {
                            var displayKey = (key.charAt(0).toUpperCase() + key.slice(1)).match(/[A-Z]?[a-z]+|[0-9]+/g);
                            var displayDeature = features[key].toString().charAt(0).toUpperCase() + features[key].toString().slice(1);
                            displayFeatures += '<tr><td>' + displayKey.join('');
                            +'</td>';
                            displayFeatures += '<td>' + displayDeature + '</td></tr>';
                        }
                    }
                    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;width=100%;">' +
                            '<tr>' +
                            '<td  colspan="2" style="height:220px;background-color: white;">' +
                            '<div class="carousel">' +
                            '<div class="slide">' +
                            '<img src="' + d.merchandiseImage1 + '" style="height:100%;">' +
                            '</div>' +
                            merchandiseImages +
                            '</div>' +
                            '</td>' +
                            '</tr>' +
                            '<tr>' +
                            '<td style="width:130px;">Product Name</td>' +
                            '<td>' + d.merchandiseName + '</td>' +
                            '</tr>' +
                            '<tr>' +
                            '<td>Brand</td>' +
                            '<td>' + d.merchandiseBrandName + '</td>' +
                            '</tr>' +
                            '<tr>' +
                            '<tr>' +
                            '<td>Color</td>' +
                            '<td>' + d.merchandiseColor + '</td>' +
                            '</tr>' +
                            displayFeatures +
                            '<tr>' +
                            '<td>Listing Year</td>' +
                            '<td>' + d.listingYear + '</td>' +
                            '</tr>' +
                            '<tr>' +
                            '<td>Description</td>' +
                            '<td>' + d.merchandiseDesc + '</td>' +
                            '</tr>' +
                            '</table>';
                }

                var table = $('#products').DataTable({
                    "ajax": 'HandleStore?action=getAStoreProductList&storeId=' + $(obj).attr('storeId'),
                    "lengthMenu": [10, 25, 50],
                    "columns": [
                        {
                            "class": 'details-control',
                            "orderable": false,
                            "data": null,
                            "defaultContent": '<h2><i class="icon-arrow-right-3"></i><h2>'
                        },
                        {
                            "title": "Product Name",
                            "class": "center",
                            "data": "merchandiseName"
                        },
                        {
                            "title": "Price",
                            "class": "center",
                            "data": "merchandisePrice"
                        },
                        {
                            "title": "Year",
                            "class": "center",
                            "data": "listingYear"
                        },
                        {
                            "title": "Image",
                            "class": "center",
                            "orderable": false,
                            "searchable": false,
                            "data": "merchandiseImage"
                        }
                    ],
                    "order": [[1, 'asc']],
                    "aoColumnDefs": [
                        {"aTargets": [2],
                            "fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
                                window.console.log(oData);
                                var value = '$'
                                value += $(nTd).html();
                                $(nTd).empty().append(value);
                            }
                        },
                        {"aTargets": [4],
                            "fnCreatedCell": function(nTd, sData, oData, iRow, iCol) {
                                var value = $(nTd).html();
                                $(nTd).empty();
                                $(nTd).append("<img class='span1' src = " + value + "></img>");
                            }
                        }
                    ]
                });



                // Add event listener for opening and closing details
                $('#products tbody').on('click', 'td.details-control', function() {
                    var tr = $(this).parents('tr');
                    var row = table.row(tr);

                    if (row.child.isShown()) {
                        $(this).find('i').removeClass().addClass('icon-arrow-right-3');
                        // This row is already open - close it
                        row.child.hide();
                        tr.removeClass('shown');
                    }
                    else {
                        $(this).find('i').removeClass().addClass('icon-arrow-down-3');
                        // Open this row
                        row.child(format(row.data())).show();
                        tr.addClass('shown');
                        $('.carousel').carousel({
                            direction: 'right',
                            effect: "switch",
                            auto: false,
                            width: 'auto',
                            height: '100%'
                        });
                    }
                });
                $("#listsStores").removeAttr('onclick');
                $("#listsStores").on('click', function() {
                    returnMapUI();
                    $("#listsStores").off('click');
                    $('#listsStores').attr('onclick', 'sizeLeftPane();');
                });
            }

            google.maps.event.addDomListener(window, 'load', initialize);</script>

        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
            google.load("visualization", "1", {packages: ["corechart"]});
            function draw(marker) {
                var data = google.visualization.arrayToDataTable([
                    ['Genre', 'Gorgeous', 'Good', 'Regular', {role: 'annotation'}],
                    ['',
                        marker['Gorgeous'],
                        marker['Good'],
                        marker['Regular'],
                        '']
                ]);
                var view = new google.visualization.DataView(data);
                view.setColumns([0, 1,
                    {calc: "stringify",
                        sourceColumn: 1,
                        type: "string",
                        role: "annotation"},
                    2]);
                options = {
                    width: 300,
                    height: 50,
                    legend: {position: 'top', maxLines: 5},
                    bar: {groupWidth: '75%'},
                    colors: ["ff3300", "006699", "ffff00"],
                    isStacked: true
                };
                var newDiv = document.createElement('div');
                var bar = new google.visualization.BarChart(newDiv);
                bar.draw(data, options);
                $('#storeInfoWindowList').parent().prepend(newDiv);
                layersColor = [16724736, 16750848, 26265];
            }

        </script>
    </head>

    <body style=" overflow-x: hidden;overflow-y: hidden; ">
        <input id="pac-input" class="controls" type="text" style=" left: 15px;" placeholder="Enter a Product/Location">
        <div id="type-selector" class="controls">
            <input type="radio" name="type" id="changetype-all" checked="checked">
            <label for="changetype-all">All</label>

            <input type="radio" name="type" id="changetype-establishment">
            <label for="changetype-establishment">Product</label>

            <input type="radio" name="type" id="changetype-geocode">
            <label for="changetype-geocode">Location</label>
        </div>

        <div id="map-canvas"></div>
        <div id="directionsPanel" style="float:right;width:30%;height:100%">
            <p>Total Distance: <span id="total"></span></p>
        </div>

        <div id="distanceBar" style="display: inline-flex; position: absolute; z-index: 2;right: 45px;top: 23px;">
            <div id="slider" style="width: 150px;margin: auto 0 auto 0;"></div>
            <input type="text" placeholder="Distance" id="distance" style="border:0; color:#f6931f; font-weight:bold; margin-left: 15px;width: 50px;"/>
            <div style="border:0; color:#f6931f;">m</div>
        </div>

        <div id="legend" style="background: white;padding: 15px"></div>
        <div id="menu" class="menu" style="text-align: right; position: absolute;right: 10px; top:15%; z-index: 4;">
            <div style=" height: 65px;"><img id="personalInfo" src="image/profle.png" title="Personal Profile"/></div>
            <div style=" height: 65px;"><img id="accurateSearch" onclick="accurateSearch();" src="image/magnifyingglass.png" title="Accurate Search"/></div>
            <div style=" height: 65px;"><img id="favList" onclick="openFavList();" src="image/follow.png" title="Tap List"/></div>
            <div style=" height: 65px;"><img id="chat" src="image/chat.png" title="Chat"/></div>
            <div id="uiDirect"  style=" height: 65px;"><img id="listsStores" src="image/arrow-left.png" onclick="sizeCenterPane();" title="Store List"/></div>
            <div style=" height: 65px;"><img id="zoomin" onclick="zoomIn();" src="image/zoomin.png" title="Zoom In"/></div>
            <div style=" height: 65px;"><img id="zoomout" onclick="zoomOut();" src="image/zoomout.png" title="Zoom Out"/></div>
        </div>
        <script>
            $("#personalInfo").click(function() {
                $.ajax({
                    url: 'HandleUser?action=getInfo',
                    type: 'POST',
                    success: function(login) {
                        if (login === "false") {
                            var userId = $("#userId"),
                                    password = $("#password"),
                                    allFields = $([]).add(userId).add(password),
                                    tips = $(".validateTips");
                            $("#dialog-form").dialog({
                                autoOpen: false,
                                height: 400,
                                width: 350,
                                draggable: false,
                                modal: true,
                                resizable: false,
                                buttons: {
                                    "Log In": function() {
                                        var bValid = true;
                                        allFields.removeClass("ui-state-error");
                                        if (bValid) {
                                            $.ajax({
                                                url: 'HandleUser?action=login&userId=' + userId.val() + '&password=' + password.val(),
                                                type: 'POST',
                                                success: function(response) {
                                                    var isLogin = (response === 'true');
                                                    if (isLogin) {
                                                        $("#dialog-form").dialog("close");
                                                        $(".validateTips").html("");
                                                        displayNotify("Message", "Login Successfully...");
                                                    } else {
                                                        $(".validateTips").html("ID or Password is not correct.");
                                                    }
                                                }
                                            });
                                        }
                                    },
                                    Cancel: function() {
                                        $(this).dialog("close");
                                    }
                                },
                                close: function() {
                                    $("#dialog-form").parent().empty();
                                    allFields.val("").removeClass("ui-state-error");
                                    $(".validateTips").html("");
                                    $('.loading').remove();
                                }
                            });
                            $("#dialog-form").dialog("open");
                            $("#dialog-form").parent().position({
                                of: $("#personalInfo").parent(),
                                my: "right" + " " + "top",
                                at: "left" + " " + "top",
                                collision: "fit" + " " + "fit"
                            });
                            $("#dialog-form").parent().prepend("<div id='loading' class='loading' style='display: none;'></div>");
                            $("#dialog-form").parent().append('<div class="loginByOthers"><img src="image/fb.png"/><img src="image/twitter.png"/><img src="image/googleplus.png"/></div>');
                        } else {
                            $("#personalInfo-display").empty();
                            $("#dialog-personalInfo").dialog({
                                height: 450,
                                width: 350,
                                modal: true,
                                resizable: false,
                                draggable: false,
                                buttons: {
                                    'More': function() {
                                        window.location.href = "HandleUser?action=getUserInfo";
                                    },
                                    'Logout': function() {

                                        $.ajax({
                                            url: 'HandleUser?action=logout',
                                            type: 'POST',
                                            success: function(response) {
                                                var isLogout = (response === 'true');
                                                if (isLogout) {
                                                    $("#dialog-personalInfo").dialog("close");
                                                    $(".loading").remove();
                                                    displayNotify("Message", "Logout Successfully");
                                                }
                                            }});
                                    }
                                }
                            });
                            $("#dialog-personalInfo").parent().position({
                                of: $("#personalInfo").parent(),
                                my: "right" + " " + "top",
                                at: "left" + " " + "top",
                                collision: "fit" + " " + "fit"
                            });
                            var json = JSON.parse(login);
                            $('#personalInfo-display').html(
                                    '<div class="grid">' +
                                    '<div class="row">' +
                                    '<dl>' +
                                    '<dt>User ID</dt>' +
                                    '<dd>' + json['user']['userID'] + '</dd>' +
                                    '<dt>Name</dt>' +
                                    '<dd>' + (json['user']['userName']).toString().toUpperCase() + '</dd>' +
                                    '<dt>Credit</dt>' +
                                    '<dd>' + json['user']['credit'] + '</dd>' +
                                    '<dt>Email Address</dt>' +
                                    '<dd>' + json['user']['email'] + '</dd>' +
                                    '</dl>' +
                                    '</div>' +
                                    '</div>'
                                    );
                            $("#personalInfo-display").parentsUntil('#ui-dialog').prepend("<div id='loading' class='loading' style='display: none;'></div>");
                        }
                    },
                    fail: function() {
                        alert('error');
                    }
                });
            });</script>

        <div id="mainContent" class="metro">
            <div id="storeBox" class="storeBox">
                <div id="storeDetail" class="grid fluid" style="display: none; padding-left: 60px;">
                    <div class="row">
                        <img id="storeImg" class="shadow span5" style="margin:10px 0px 0px 10px;" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAYAAAA5ZDbSAAAESklEQVR4Xu2dWUsjURSET9S4oSYoIhIwaBDiQ3BHRcX/7r6BGH3QhygSEMQVd9zi1IEWlWEmxpjpKeqCMJruuVZ9/Z2+8zSRs7OzgmnRNhARYFq2HkyAufkKMDlfARZg9gbI8+kdLMDkDZDHk8ECTN4AeTwZLMDkDZDHk8ECTN4AeTwZLMDkDZDHk8ECTN4AeTwZLMDkDZDHk8ECTN4AeTwZLMDkDZDHk8ECTN4AeTwZLMDkDZDHk8ECTN4AeTwZLMDkDZDHk8ECTN4AeTwZLMDkDZDHk8ECTN4AeTwZLMDkDZDHk8ECTN4AeTwZLMDkDZDHk8ECTN4AeTwZLMDkDZDHk8ECTN4AeTwZLMDkDZDHk8ECTN4AeTwZLMD/poH9/X07ODiw8fFxq6+v91/i+fnZtra27Pz83Gpqaqyrq8uSyaR/VigUbHt7205OTiwSiVhPT49/Xuyq9H7F/l7fvS50Bl9cXNiv/+rH9vb2PNvk5KQ1NDT4n9fX1w2fV1VV2cvLi/8MIPG1ublpx8fHVldXZw8PDw48k8lYR0fHHzuq9H7fBfbV+0MHeHZ21gEFa2pqyg3Gz/AZ4M7MzNj19bWtra1ZbW2tTUxM2NzcnH82PT3tDwiAx+NxS6fTls1mrbq62oaGhvy+nZ0dfxAGBgZsfn6+rPuNjIx8lcGPXh86wE9PTz5il5eX7e7uzgLAMPbw8NCi0ahbeXp6ahsbG9bU1GSDg4O2sLDgYxuAMcrxMOB73I/rMNZbW1vt/v7ebm9vrbe318f7T+yHBy0sK3SAg2IAGLYFgN8XBkMBDWMY4zkWi/n3bW1tDhvQABh2Y8Q/Pj66qcFYx/Wjo6MfGJRzPzygYVn/HeDd3V3L5/PeXyKRsL6+PrcSBmPsAuhng2EUDlG5XM7vGxsbs+bm5qIAl7qfAP+lgd8ZhYMXvvA+7e/v95GLFRgbjGSYv7q6ao2Njf5+hrl4R+M6rPb2dr///SrnfjK4iMd7aWnJbm5u3kZ0ADEYsy0tLX44wgkbhydcj3drZ2enXV1d+XhPpVLW3d3t/7Q6Ojry9zWuwd/x+YRdzv2KiFexS0I7omHg5eXlG2CM4cXFxbf3aNAQTtgYywC3srLy4T07PDzssHHahlU4gAE0xm5wIMM0wCrXfmE6YCFXaAGX8ojDTIDGgq0/vSq9Xyl5qACXUgD7PQJMTliABZi8AfJ4MliAyRsgjyeDBZi8AfJ4MliAyRsgjyeDBZi8AfJ4MliAyRsgjyeDBZi8AfJ4MliAyRsgjyeDBZi8AfJ4MliAyRsgjyeDBZi8AfJ4MliAyRsgjyeDBZi8AfJ4MliAyRsgjyeDBZi8AfJ4MliAyRsgjyeDBZi8AfJ4MliAyRsgjyeDBZi8AfJ4MliAyRsgjyeDBZi8AfJ4MliAyRsgjyeDBZi8AfJ4MliAyRsgjyeDBZi8AfJ4MliAyRsgjyeDyQG/Asqg8d7PXAYjAAAAAElFTkSuQmCC">
                        <button id="returnToMap" class="primary span2 offset2" onclick="returnMapUI();"><i class="icon-undo"></i></button>
                        <button id="fullScreen" class="primary span2" onclick="productsListFullScreen();"><i class="icon-fullscreen-alt"></i></button>
                    </div>

                    <div class="row">
                        <dl class="horizontal span11">
                            <dt>Store Name</dt>
                            <dd></dd>
                            <dt>Rank</dt>
                            <dd></dd>
                            <dt>Contact</dt>
                            <dd></dd>
                            <dt>Create Time</dt>
                            <dd></dd>
                            <dt>Address</dt>
                            <dd></dd>
                            <dt>Description</dt>
                            <dd></dd>
                        </dl>
                    </div>

                    <div class="row">

                    </div>
                </div>
            </div>
            <div id="leftPanel">
                <nav class="navigation-bar dark">
                    <nav class="navigation-bar-content">
                        <item class="element">List of Stores</item>
                        <item class="element-divider"></item>
                    </nav>
                </nav>
                <div id="sortList" style=" margin: 10px;" class="metro">
                    <button sortKey="name" class="inverse" sortBy="0" onclick="sortList(this);">Name<i class="icon-embed on-right"></i></button>
                    <button sortKey="distance" class="inverse" sortBy="0" onclick="sortList(this);">Distance<i class="icon-embed on-right"></i></button>
                    <button id="preSortSelected" class="inverse" sortKey="rank" sortBy="1" onclick="sortList(this);">Rank<i class="icon-arrow-down-5 on-right"></i></button>
                </div>
                <script>
            $('#sortList button').on('click', function() {
                if ($(this).find('i').hasClass('icon-arrow-down-5')) {
                    $('#sortList button i').removeClass();
                    $(this).find('i').addClass('icon-arrow-up-5 on-right');
                    $(this).siblings('button').find('i').addClass('icon-embed on-right');
                } else if ($(this).find('i').hasClass('icon-arrow-up-5')) {
                    $('#sortList button i').removeClass();
                    $(this).find('i').addClass('icon-arrow-down-5 on-right');
                    $(this).siblings('button').find('i').addClass('icon-embed on-right');
                } else {
                    $('#sortList button i').removeClass();
                    $(this).find('i').addClass('icon-arrow-down-5 on-right');
                    $(this).siblings('button').find('i').addClass('icon-embed on-right');
                }
            });
                </script>
            </div>
        </div>

        <div id="specificStore" class="metro" style="overflow-y: auto;">
            <legend>Products</legend>
            <div id="productofStore" class="metro" style="height: 100%;margin-right: 70px; margin-left: 50px;">

            </div>
        </div>

        <div id="dialog-form" class="diaglog-form">
            <div class="login-title">Log In</div>
            <p class="validateTips" style=" height: 10px; color:  red;"></p>
            <form>
                <input type="text" name="userId" id="userId" placeholder="User ID" class="text ui-widget-content ui-corner-all">
                <input type="password" name="password" id="password" placeholder="Password" value="" class="text ui-widget-content ui-corner-all">
            </form>
        </div>
        <div id="dialog-personalInfo" class="metro">
            <div>
                <legend>Personal Profile</legend>
            </div>
            <div id="personalInfo-display" class="personalInfo-display"></div>
        </div>

        <div id="dialog-tagList">
            <div class="login-title">Favorite List</div>
            <div id="tag-display" class="metro">
            </div>
        </div>

        <div id="dialog-commentList" class="metro">
            <legend>Store Comment</legend>
            <div id="comment-display" class="comment-display">
            </div>
        </div>

        <script>
            //loading bar for all ajax request
            $(document).bind("ajaxSend", function() {
                $("#loading").show();
            }).bind("ajaxComplete", function() {
                $("#loading").hide();
            }
            );
        </script>

        <canvas id="container"></canvas>

    </body>
</html>