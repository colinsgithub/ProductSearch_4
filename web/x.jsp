<!DOCTYPE html>
<html>
    <head>
        <style>
            html {
                width: 100%;
                height: 100%;
            }

            body {
                width: 100%;
                height: 100%;
                margin: 0;
            }

            #map_canvas {
                width: 100%;
                height: 100%;
            }
        </style>

        <script src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
    </head>
    <body onload="initialize()">
        <div id="map_canvas"></div>

        <script>

        function createMarker(width, height, radius) {

            var canvas, context;

            canvas = document.createElement("canvas");
            canvas.width = 30;
            canvas.height = 50;

            context = canvas.getContext("2d");

            context.clearRect(0, 0, width, height);

            // background is yellow
            context.fillStyle = "rgba(255,255,0,1)";

            // border is black
            context.strokeStyle = "rgba(0,0,0,1)";

            var width = 30;
            var height = 30;
            var radius = 20;

            context.beginPath();
            context.moveTo(radius, 0);
            context.lineTo(width - radius, 0);
            context.quadraticCurveTo(width, 0, width, radius);
            context.lineTo(width, height - radius);
            context.quadraticCurveTo(width, height, width - radius, height);
            context.lineTo(radius, height);
            context.quadraticCurveTo(0, height, 0, height - radius);
            context.lineTo(0, radius);
            context.quadraticCurveTo(0, 0, radius, 0);
            context.closePath();

            context.fill();
            context.stroke();


            var width = 25;
            var height = 25;
            var radius = 15;

            context.beginPath();
            context.moveTo(radius, 0);
            context.lineTo(width - radius, 0);
            context.quadraticCurveTo(width, 0, width, radius);
            context.lineTo(width, height - radius);
            context.quadraticCurveTo(width, height, width - radius, height);
            context.lineTo(radius, height);
            context.quadraticCurveTo(0, height, 0, height - radius);
            context.lineTo(0, radius);
            context.quadraticCurveTo(0, 0, radius, 0);
            context.closePath();

            context.fill();
            context.stroke();



            return canvas.toDataURL();

        }


        // example
        function initialize() {

            // creating a map
            var map = new google.maps.Map(document.getElementById("map_canvas"), {
                zoom: 4,
                center: new google.maps.LatLng(-25.363882, 131.044922),
                mapTypeId: google.maps.MapTypeId.ROADMAP
            });

            // creating a marker
            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(-25.363882, 131.044922),
                map: map,
                title: "Hello World!",
                icon: createMarker(25, 25, 15)
            });
        }

        </script>
    </body>
</html>