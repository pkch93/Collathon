var modal = $("#history__modal");
var modalButton = $("#history__modal-button");
var modalClose = $("#modal--close");
modalButton.on("click", function(){
    modal.toggle();
});
modalClose.on("click", function () {
   modal.toggle();
});