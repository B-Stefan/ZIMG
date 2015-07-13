$(document).ready(function() {
    $("#comment-submit").click(function() {

        var comment = $("#comment-textarea").val();

        if(comment.length < 5) {
            alert("Your comment is to short!");
        }

        // @todo: send stuff here
    });

    $("#add-tag-textfield").keypress(function (e) {
        if (e.which == 13) {
            var tag = $("#add-tag-textfield").val();

            var tagContainer = $("<div/>", { class: "tag" });
            var tagIcon = $("<span/>", { class: "glyphicon glyphicon-tag" });
            $(tagContainer).append(tagIcon);
            $(tagContainer).append(tag);

            $("#tag-box").append(tagContainer);

            // @todo: send stuff here

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
        alert("marked as fav");
        // @todo: send stuff here
    });
});