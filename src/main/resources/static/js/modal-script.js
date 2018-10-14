var toggleMenubar = $("#toggle-menubar");
var menubar = $("#menubar");
toggleMenubar.on("click", function() {
    if(menubar.hasClass("menubar--none")){
        menubar.removeClass("menubar--none");
    }
    else {
        menubar.addClass("menubar--none")
    }
});