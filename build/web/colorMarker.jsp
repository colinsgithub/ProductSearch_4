<%-- 
    Document   : colorMarker
    Created on : Apr 13, 2014, 10:34:33 AM
    Author     : poonkaho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                <%-- background: #2e353f; --%>
            }

            .pin {
                width: 30px;
                height: 30px;
                border-radius: 50% 50% 50% 0;
                background: #89849b;
                position: absolute;
                transform:rotate(-45deg);
                -webkit-transform:rotate(-45deg); /* Opera, Chrome, and Safari */
                left: 70%;
                top: 50%;
                margin: -20px 0 0 -20px;
            }

            .pin:after {
                content: '';
                width: 14px;
                height: 14px;
                margin: 8px 0 0 8px;
                background: #2e353f;
                position: absolute;
                border-radius: 50%;
                box-shadow: inset 0px 2px 0px #252A32;
            }

            .bounce {
                animation-name: bounce;
                animation-fill-mode: both;
                animation-duration: 1s;
            }

            .pulse {
                background: rgba(0,0,0,0.2);
                border-radius: 50%;
                height: 14px;
                width: 14px;
                position: absolute;
                left: 50%;
                top: 50%;
                margin: 11px 0px 0px -12px;
                transform: rotateX(55deg);
                -webkit-transform:rotate(55deg); /* Opera, Chrome, and Safari */
                z-index: -2;
            }
            .pulse:after {
                content: "";
                border-radius: 50%;
                height: 40px;
                width: 40px;
                position: absolute;
                margin: -13px 0 0 -13px;
                animation: pulsate 1s ease-out;
                animation-iteration-count: infinite;
                opacity: 0.0;
                box-shadow: 0 0 1px 2px #89849b;
                animation-delay: 1.1s;
            }


        </style>
    </head>
    <body>


        <canvas id="container"></canvas>
        <script src="http://www.html5canvastutorials.com/libraries/three.min.js"></script>
        <script defer="defer">
            createCylinder = function() {

                // renderer
                var renderer = new THREE.WebGLRenderer();
                renderer.setSize(80, 80);

                document.body.appendChild(renderer.domElement);


                var render = renderer.domElement;


                // camera
                var camera = new THREE.PerspectiveCamera(45, window.innerWidth / window.innerHeight, 1, 1000);
                camera.position.z = 200;

                // scene
                var scene = new THREE.Scene();

                var directionalLight = new THREE.DirectionalLight(0xffffff, 1);
                directionalLight.position.set(200, 200, 200);
                scene.add(directionalLight);

                group = new THREE.Object3D();
                group.position.y = 0;
                scene.add(group);


                group.rotation.x = 4;
                var topRadius = 15;
                var bottomRadius = 15;
                // cone
                // API: THREE.CylinderGeometry(bottomRadius, topRadius, height, segmentsRadius, segmentsHeight)
                var cone = new THREE.Mesh(new THREE.CylinderGeometry(2, topRadius, 30, 360, 1, false), new THREE.MeshPhongMaterial({
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
                cone.overdraw = true;
                cone.rotation.x = 0;
                cone.position.set(0, 0, 0);
                group.add(cone);
                window.console.log(cone);


                // cylinder
                // API: THREE.CylinderGeometry(bottomRadius, topRadius, height, segmentsRadius, segmntsHeight)

                var chartHeight = 20;
                var position = chartHeight *= -1;
                var cylinder = new THREE.Mesh(new THREE.CylinderGeometry(bottomRadius, topRadius, 20, 360, 1, false), new THREE.MeshPhongMaterial({
                    color: 7788287,
                    ambient: 72083,
                    emissive: 16523523,
                    specular: 72083,
                    shininess: 30,
                    blending: 2,
                    opacity: 1,
                    transparent: false,
                    wireframe: false

                }));
                cylinder.overdraw = true;
                cylinder.rotation.x = 0;
                cylinder.position.set(0, position -= 5, 0);
                group.add(cylinder);

                var cylinder = new THREE.Mesh(new THREE.CylinderGeometry(bottomRadius, topRadius, 20, 360, 1, false), new THREE.MeshPhongMaterial({
                    color: 7788287,
                    ambient: 72083,
                    emissive: 72083,
                    specular: 72083,
                    shininess: 30,
                    blending: 2,
                    opacity: 1,
                    transparent: false,
                    wireframe: false

                }));
                cylinder.overdraw = true;
                cylinder.rotation.x = 0;
                cylinder.position.set(0, position -= 20, 0);
                group.add(cylinder);

                var cylinder = new THREE.Mesh(new THREE.CylinderGeometry(bottomRadius, topRadius, 20, 360, 1, false), new THREE.MeshPhongMaterial({
                    color: 7788287,
                    ambient: 72083,
                    emissive: 14608396,
                    specular: 72083,
                    shininess: 30,
                    blending: 2,
                    opacity: 1,
                    transparent: false,
                    wireframe: false

                }));
                cylinder.overdraw = true;
                cylinder.rotation.x = 0;
                cylinder.position.set(0, position -= 20, 0);
                group.add(cylinder);

                // render
                renderer.render(scene, camera);

                return render;
            };
        </script>

        <script src="http://maps.googleapis.com/maps/api/js?sensor=false" ></script>
        <div id="map" style="height : 640px; width : 640px" ></div>
        <script>
            var cylinder = createCylinder();

            var map = new google.maps.Map(document.getElementById('map'), {
                center: new google.maps.LatLng(25, 121),
                mapTypeId: google.maps.MapTypeId.ROADMAP,
                zoom: 4
            });


            function initialize() {
                for (var i = 1; i <= 5; ++i) {

                    new google.maps.Marker({
                        icon: cylinder.toDataURL(),
                        map: map,
                        position: new google.maps.LatLng(25, 105 + i * 5)
                    });
                }
            };

            google.maps.event.addDomListener(window, 'load', initialize);
                </script>

    </script>

</body>
</html>
