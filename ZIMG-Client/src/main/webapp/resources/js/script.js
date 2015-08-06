$(document).ready(function() {
    // Data for CSRF
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    var urlForTags = document.URL + "/tag";
    var urlForFavorites = document.URL + "/favorite";
    var urlUnForFavorites = document.URL + "/unfavorite";

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

        var url = $("#mark-as-favorite").hasClass("marked") ? urlUnForFavorites : urlForFavorites;

        $.ajax({
            method: "POST",
            url: url,
            headers: {
                "X-CSRF-TOKEN": token
            }
        }).done(function( msg ) {

            if($("#mark-as-favorite").hasClass("marked")) {
                console.log("remove to favorites.");
                $("#mark-as-favorite").removeClass("marked");
            } else {
                console.log("added to favorites.");
                $("#mark-as-favorite").addClass("marked");
            }

        });

        return false;
    });

    $("img.overview-entry").each(function(index,el){
        var $el = $(el);
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