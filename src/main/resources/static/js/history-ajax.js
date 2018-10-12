var petId = document.getElementById("history-getajax").dataset.petid;
var petHistories = $("#pet-histories");
var historyToggle = $("#history-getajax");
$(document).ready(function(){
    $.ajax({
        type: "GET",
        url: petId + "/history",
        dataType: "JSON",
        success: function (result) {
            var displayHistory = petHistories.children(".histories");
            if(result.length === 0){
                displayHistory.html("<h1>등록된 이력이 없습니다.</h1>");
            }
            else {
                result.forEach(function (h) {
                    displayHistory.prepend(
                        "<div>" +
                        "<div>" + h.title + "</div>" +
                        "<div>" + h.date + "</div>" +
                        "<div>" + h.address + "</div>" +
                        "<div>" + h.content + "</div>" +
                        "</div>"
                    );
                });
                $('.single-item').slick();
            }
            console.log(result);
            console.log("success");
        },
        error: function (req, status, error) {
            console.log(req.status + "\n" + req.responseText + "\n" + error);
        }
        })
});