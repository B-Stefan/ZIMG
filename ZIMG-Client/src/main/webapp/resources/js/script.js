$(document).ready(function() {
    // Data for CSRF
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var urlForTags = document.URL + "/tag";
    var urlForFavorites = document.URL + "/favorite";
    var urlForUnFavorites = document.URL + "/unfavorite";
    var urlForUpvote = document.URL + "/upvote";
    var urlForUnUpvote = document.URL + "/unupvote";

    $("#add-tag-textfield").keypress(function (e) {
        // Enter is 13
        if (e.which == 13) {
            var tag = $(this).val();

            $.ajax({
                method: "POST",
                url: urlForTags,
                headers: {
                    "X-CSRF-TOKEN": token
                },
                data: { tag: tag }
            }).done(function( msg ) {
                console.log("added new tag: " + tag);
                var tagContainer = $("<div/>", { class: "tag" });
                var tagIcon = $("<span/>", { class: "glyphicon glyphicon-tag" });
                $(tagContainer).append(tagIcon);
                $(tagContainer).append(tag);
                $("#tag-box").append(tagContainer);
            });

            $("#add-tag-textfield").val("");
        }
    });

    $("#imageLink").mouseenter(function() {
        $("#mark-as-favorite").fadeIn();
    });

    $("#imageLink").mouseleave(function() {
        $("#mark-as-favorite").fadeOut();
    });

    $("#mark-as-favorite").click(function(){

        var url = $("#mark-as-favorite").hasClass("fav-marked") ? urlForUnFavorites : urlForFavorites;

        $.ajax({
            method: "POST",
            url: url,
            headers: {
                "X-CSRF-TOKEN": token
            }
        }).done(function( msg ) {

            if($("#mark-as-favorite").hasClass("fav-marked")) {
                console.log("unfav the image");
                $("#mark-as-favorite").removeClass("fav-marked");
            } else {
                console.log("fav the image");
                $("#mark-as-favorite").addClass("fav-marked");
            }

        });

        return false;
    });


    $(".upvote-button").click(function(){

        var url = $(".upvote-button").hasClass("upvote-marked") ? urlForUnUpvote : urlForUpvote;

        $.ajax({
            method: "POST",
            url: url,
            headers: {
                "X-CSRF-TOKEN": token
            }
        }).done(function( msg ) {

            if($(".upvote-button").hasClass("upvote-marked")) {
                console.log("unupvote the image");
                $(".upvote-count").html(parseInt($(".upvote-count").html()) - 1);
                $(".upvote-button").removeClass("upvote-marked");
            } else {
                console.log("upvote the image");
                $(".upvote-count").html(parseInt($(".upvote-count").html()) + 1);
                $(".upvote-button").addClass("upvote-marked");
            }

        });

        return false;
    });

    $(".overview-entry").find("img").each(function(index,el){
        var $el = $(el);
        var img = new Image();
        img.src = $el.attr("image-src");
        img.onload = function(){
            $el.fadeOut(500,function(){
                $el.removeClass("loading");
                $el.attr("src",img.src);
            })
        };
        $el.on("load", function(){
            $el = $(this);
            $el.fadeIn(1000)
        });
        if($el.attr("file-name").indexOf(".gif") > -1){
            $el.mouseenter(function(){
                $(this).attr("src","/uploads/" + $(this).attr("file-name"));
            });
            $el.mouseleave(function(){
                $(this).attr("src","/thumbnail/" + $(this).attr("image-id"));
            });
        }
    })
});