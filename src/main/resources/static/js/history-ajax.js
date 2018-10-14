var petId = document.getElementById("history-getajax").dataset.petid;
var petHistories = $("#pet-histories");
var historyButton = $("#history-register");
var historyModal = $("#history__modal");
$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: petId + "/history",
        dataType: "JSON",
        success: function (result) {
            var displayHistory = petHistories.children(".histories");
            if(result.length === 0){
                displayHistory.html("<h1 class='history history--zero'>등록된 이력이 없습니다.</h1>");
            }
            else {
                result.forEach(function (h) {
                    displayHistory.prepend(
                        "<div class='history'>" +
                            "<div class='history__top'>" +
                                "<span class='title-elt'>"+ h.title + "</span>"+
                                "<span class='address-elt'>"+ h.address + "</span>"+
                            "</div>" +
                            "<div class='history__main'>" + h.content + "</div>" +
                        "</div>"
                    );
                });
                $('.single-item').slick();
            }
        },
        error: function (req, status, error) {
            console.log(req.status + "\n" + req.responseText + "\n" + error);
        }
        })
});

historyButton.click(function(event){
    event.preventDefault();
    historyModal.css("display","none");

    $.post({
        url: petId + "/history",
        data: {
            title: $("#history-title").val(),
            address: $("#history-address").val(),
            content: $("#history-content").val()
        },
        dataType: "JSON",
        success: function() {
                location.reload();
        },
        error: function (req, status, error) {
            console.log(req.status + "\n" + req.responseText + "\n" + error);
        }
    });
});
