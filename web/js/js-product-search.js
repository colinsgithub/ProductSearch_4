/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
urls = window.location.pathname.split("/");
context = urls[1];


$(function() {
    var bootstrapButton = $.fn.button.noConflict(); // return $.fn.button to previously assigned value
    $.fn.bootstrapBtn = bootstrapButton;
    
    $(".nav-item").children("a").hover(function(e) {
        if ($(".nav-item").children(".nav-list").css('display') == "none") {
            $(".nav-item").children(".nav-list").hide();
        }
        $(e.target).parent().children(".nav-list").show();
    });
    $(".nav-item").children(".nav-list").mouseleave(function(e) {
        $(e.target).parent().children(".nav-list").hide();
        $(e.target).parent().parent(".nav-list").hide();
        if ($(e.target).parent().parent().parent().attr('class') == 'nav-list') {
            $(e.target).parent().parent().parent().hide();
        }
    });
    $(".nav-list").children('div').children('a').hover(function(e) {
        $(".nav-list").children('div').children('.nav-list-2').hide();
        $(e.target).parent().children(".nav-list-2").show();
    });



    $(".fa-refresh").click(function(e) {
        $("#sb_output").submit();
    });

    $("#btn-filter").click(function(e) {
        $("#filterNav").fadeToggle(200);
    });

    $("#search-bar input").autocomplete({
        source: function(request, response) {
            $.ajax({
                url: "/" + context + "/MainSearch",
                dataType: "json",
                data: {
                    "action": "search",
                    "keywords": $("#search-bar input").val()
                },
                success: function(data) {
                    response($.map(data, function(item) {
                        return {
                            name: item.name,
                            count: item.count,
                            categoryID: item.categoryID
                        };
                    }));
                }
            });
        }
    }).data("ui-autocomplete")._renderItem = function(ul, item) {
        var params = {
            "categoryID": item.categoryID,
            "keywords": $("#search-bar input").val()
        };
        return $("<li>").data("ui-autocomplete-item", item)
                .append("<a href='/" + context + "/SearchProduct?categoryID=" + item.categoryID + "&keywords=" + $("#search-bar input").val() + "'><i class='ui-autocomplete-item-name'>"
                        + item.name + "</u><b class='ui-autocomplete-item-count'> "
                        + item.count + " Result(s)</b></a>")
                .appendTo(ul);
    };


    $('#MCarousel').carousel();
});

function onSubmit() {
    var $txt = $(".search");
    if ($txt.val().length > 0) {
        $("#sb_output").submit();
    }
}


