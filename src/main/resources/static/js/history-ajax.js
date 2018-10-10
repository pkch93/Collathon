var petId = document.getElementById("history-getajax").dataset.petid;
$("#history-getajax").on("click",function(){
    $.ajax({
        type: "GET",
        url: petId + "/history",
        dataType: "JSON",
        success: function(result){
            console.log(result);
            console.log("success");
        },
        error: function(req, status, error) {
            console.log(req.status + "\n" + req.responseText + "\n" + error);
        }
    });
});
