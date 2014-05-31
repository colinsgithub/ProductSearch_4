<%-- 
    Document   : chart
    Created on : Mar 25, 2014, 10:12:59 AM
    Author     : poonkaho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
            google.load("visualization", "1", {packages: ["corechart"]});


            function show() {
                drawChart();
                window.console.log($('body'));
            }

            function drawChart() {
                data = google.visualization.arrayToDataTable([
                    ['Genre', 'Gorgeous', 'Romance', 'Mystery/Crime', 'General',
                        'Western', 'Literature', {role: 'annotation'}],
                    ['', 10, 24, 20, 32, 18, 5, '']
                ]);

                options = {
                    width: 300,
                    height: 50,
                    legend: {position: 'top', maxLines: 3},
                    bar: {groupWidth: '75%'},
                    isStacked: true
                };

                new google.visualization.BarChart(document.getElementById('chart_div')).draw(data, options);

                new google.visualization.BarChart(document.getElementById('chart_div2')).draw(data, options);
            }
        </script>
    </head>
    <body>
        <div id="chart_div"></div>

        <div id="chart_div2"></div>

        <button onclick="show();">show</button>
        <button onclick="test();">Test</button>
        <button onclick="change();">change</button>

        <div id="new"></div>
        <script>
            function test() {
                //var tag = $('#chart_div');
                //window.console.log(tag);

                //$('#new').append(tag);
                
                var newDiv = document.createElement('div');
                xxx = new google.visualization.BarChart(newDiv);
                $('#new').append(newDiv);
                xxx.draw(data, options);
            }

            function change() {
                
                data = google.visualization.arrayToDataTable([
                    ['Genre', 'Gorgeous', 'Romance', 'Mystery/Crime', {role: 'annotation'}],
                    ['', 10, 24, 20, '']
                ]);
                
                xxx.draw(data, options);
                
                
            }

        </script>
    </body>
</html>
