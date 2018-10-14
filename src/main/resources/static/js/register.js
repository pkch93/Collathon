$(document).ready(function() {
    var categoryButton = $("#register-category");
    var categoryModal = $("#category-modal");
    categoryButton.on("click", function () {
        categoryModal.toggle();
    });

    var breedButton = $("#register-breed");
    var breedModal = $("#breed-modal");
    breedButton.on("click", function () {
        breedModal.toggle();
    });

    var categories = $(".required__category");
    var categoryValue = $("#category-value");
    categories.each(function (i, c) {
        $(c).on("click", function () {
            categories.each(function (i, c) {
                $(c).css("background-color", "rgb(239, 197, 97)").css("color", "#000");
            });
            $(c).css("background-color", "#000").css("color", "#fff");
            var category = $(c).data("category");
            categoryValue.val(category);
            breedModal.toggle();
            if(category === "개") $("#dogbreed").toggle();
            else if(category === "고양이") $("#catbreed").toggle();
        });
    });

    var breeds = $(".required__breed");
    var breedValue = $("#breed-value");
    breeds.each(function (i, b) {
        $(b).on("click", function () {
            breeds.each(function (i, b) {
                $(b).css("background-color", "rgb(239, 197, 97)").css("color", "#000");
            });
            $(b).css("background-color", "#000").css("color", "#fff");
            var breed = $(b).data("breed");
            breedValue.val(breed);
        });
    });
    console.log(categoryValue);
    console.log(breedValue);

    if(categoryValue.val() !== "") categoryButton.css("background-color", "#000").css("color", "#fff");
    else categoryButton.css("background-color", "rgb(239, 197, 97)").css("color", "#000");
    if(breedValue.val() !== "") breedButton.css("background-color", "#000").css("color", "#fff");
    else breedButton.css("background-color", "rgb(239, 197, 97)").css("color", "#000");
});