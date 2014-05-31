makeBoxes = function(startIndex, endIndex, storeId) {
    var boxes = [];
    var isLastIndex;
    var newStartIndex;
    var newEndIndex;
    var moreCommentAjaxRequest = $.ajax({
        url: 'HandleComment?action=getComment&storeId=' + storeId,
        type: 'POST',
        async: false,
        data: {
            startIndex: startIndex,
            endIndex: endIndex
        },
        // pass comment for load part of comment list 
        success: function(response) {
            var json = JSON.parse(response);

            for (var x in json['comments']) {
                var list = createCommentList(json, x);
                var box = document.createElement('div');
                
                $(box).append(list);
                // add box DOM node to array of new elements
                boxes.push(box);
            }

            $("#comment-display").append(boxes).nested("append", boxes);
            isLastIndex = json['isLastIndex'];
            newStartIndex = json['startIndex'];
            newEndIndex = json['endIndex'];
        }
    });
    var newCommentList = [{
            startIndex: newStartIndex,
            endIndex: newEndIndex,
            isLastIndex: isLastIndex
        }];

    return newCommentList;
};

function createCommentList(json, x) {
    var userId = json['comments'][x]['userId'];
    var userName = json['comments'][x]['userName'];
    var feedback = json['comments'][x]['feedback'];
    var score = json['comments'][x]['score'];
    var postedDate = json['comments'][x]['postedDate'];

    var starNum = '';
    for (var x = 1; x <= 5; x++) {
        if (x <= score) {
            starNum += '<li class="rated"></li>';
        } else {
            starNum += '<li></li>';
        }

    }
    return '<div class="' + randomSize(feedback.length) + '">' +
            '<table>' +
            '<tr><td rowspan="2"><img style="margin: 5px;height:50px; id="personalInfo" src="image/profle.png" title="User Photo"/></td>' +
            '<td><h5><a href=HandleUser?action=viewOtherUser&userId=' + userId + '>' + userName + '</a></h5></td></tr>' +
            '<tr><td><div class="rating small fg-yellow">' +
            '<ul>' +
            starNum +
            '</ul>' +
            '</div></td></tr>' +
            '</table>' +
            '<p class="tertiary-text-secondary">' + feedback + '</p>' +
            '<h6>' + postedDate + '</h6>' +
            '</div>';
}

function randomSize(length) {
    var boxSize = '' + 2 + (Math.ceil(Math.random() * 2) + 1);
    if (length < 28) {
        boxSize = '21';
        return 'box size' + boxSize;
    } else if (length > 60 && length < 90) {
        boxSize = '23';
        return 'box size' + boxSize;
    } else if (length > 90) {
        boxSize = '24';
        return 'box size' + boxSize;
    }

    return 'box size' + boxSize;
}


function submitComment(obj) {
    var commentValue = $(obj).parent().siblings('.row').find('textarea').val();
    window.console.log(commentValue);
    var score = $(obj).siblings('#rating_1').attr('score');
    var storeId = $(obj).attr('storeid');
    //get comment from textarea

    $.ajax({
        url: 'HandleComment?action=commentStore',
        type: 'POST',
        async: false,
        data: {
            storeId: storeId,
            comment: commentValue,
            score: score
        },
        // pass comment for load part of comment list 
        success: function(response) {
            window.console.log(response);
            //var json = JSON.parse(response);

        }
    });

    $("#comment-display").empty();
    getCommentFirstTime();
    //renew comment box!
}

function colorOfMarker(x) {
    if (parseInt(parseInt(x) / 100 * 5) === 5)
        return gorgeousCylinderIcon;
    else if (parseInt(parseInt(x) / 100 * 5) === 4)
        return goodCylinderIcon;
    else if (parseInt(parseInt(x) / 100 * 5) === 3)
        return regularCylinderIcon;
    else
        return regularCylinderIcon;
}

BookmarkApp = function() {
    var isIEmac = false; /*@cc_on @if(@_jscript&&!(@_win32||@_win16)&&(@_jscript_version<5.5)) isIEmac=true; @end @*/
    var isMSIE = (-[1, ]) ? false : true;
    var cjTitle = document.title; // Bookmark title 
    var cjHref = location.href;   // Bookmark url

    function hotKeys() {
        var ua = navigator.userAgent.toLowerCase();
        var str = '';
        var isWebkit = (ua.indexOf('webkit') != -1);
        var isMac = (ua.indexOf('mac') != -1);

        if (ua.indexOf('konqueror') != -1) {
            str = 'CTRL + B'; // Konqueror
        } else if (window.home || isWebkit || isIEmac || isMac) {
            str = (isMac ? 'Command/Cmd' : 'CTRL') + ' + D'; // Netscape, Safari, iCab, IE5/Mac
        }
        return ((str) ? 'Press ' + str + ' to bookmark this page.' : str);
    }

    function isIE8() {
        var rv = -1;
        if (navigator.appName == 'Microsoft Internet Explorer') {
            var ua = navigator.userAgent;
            var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
            if (re.exec(ua) != null) {
                rv = parseFloat(RegExp.$1);
            }
        }
        if (rv > -1) {
            if (rv >= 8.0) {
                return true;
            }
        }
        return false;
    }

    function addBookmark(a) {
        try {
            if (typeof a == "object" && a.tagName.toLowerCase() == "a") {
                a.style.cursor = 'pointer';
                if ((typeof window.sidebar == "object") && (typeof window.sidebar.addPanel == "function")) {
                    window.sidebar.addPanel(cjTitle, cjHref, ""); // Gecko
                    return false;
                } else if (isMSIE && typeof window.external == "object") {
                    if (isIE8()) {
                        window.external.AddToFavoritesBar(cjHref, cjTitle); // IE 8                    
                    } else {
                        window.external.AddFavorite(cjHref, cjTitle); // IE <=7
                    }
                    return false;
                } else if (window.opera) {
                    a.href = cjHref;
                    a.title = cjTitle;
                    a.rel = 'sidebar'; // Opera 7+
                    return true;
                } else {
                    alert(hotKeys());
                }
            } else {
                throw "Error occured.\r\nNote, only A tagname is allowed!";
            }
        } catch (err) {
            alert(err);
        }
    }

    return {
        addBookmark: addBookmark
    };
}();


function idenClassOfStore(x) {
    if (parseInt(parseInt(x) / 100 * 5) === 5)
        return 0;
    else if (parseInt(parseInt(x) / 100 * 5) === 4)
        return 1;
    else if (parseInt(parseInt(x) / 100 * 5) === 3)
        return 2;
    else
        return 2;
}


function handleNoGeolocation(errorFlag) {
    if (errorFlag) {
        var content = 'Error: The Geolocation service failed.';
    } else {
        var content = 'Error: Your browser doesn\'t support geolocation.';
    }

    var options = {
        map: map,
        position: new google.maps.LatLng(60, 105),
        content: content
    };
    var infowindow = new google.maps.InfoWindow(options);
    map.setCenter(options.position);
}


function zoomIn() {
    var zoomLevel = parseInt(map.getZoom()) + 1;
    map.setZoom(zoomLevel);
}

function zoomOut() {
    var zoomLevel = parseInt(map.getZoom()) - 1;
    map.setZoom(zoomLevel);
}


function setCircle(radius, pos) {
    var populationOptions = {
        strokeColor: '#FF0000',
        strokeOpacity: 0.8,
        strokeWeight: 2,
        fillColor: '#FF0000',
        fillOpacity: 0.20,
        map: map,
        center: pos,
        radius: radius,
        editable: true
                //Circle can be edited
    };
    // Add the circle for this city to the map.
    cityCircle = new google.maps.Circle(populationOptions);
    google.maps.event.addListener(cityCircle, "radius_changed", function(e) {
        $("#slider").slider({
            value: cityCircle.getRadius()
        });
        $("#distance").val(parseInt(cityCircle.getRadius()));
        lopStore();
        changeZoom();
    });
    google.maps.event.addListener(cityCircle, "center_changed", function() {
        //cityCircle.setCenter(marker1.getPosition());
        map.panTo(cityCircle.getCenter());
        map.setCenter(cityCircle.getCenter());
        lopStore();
    });
}
