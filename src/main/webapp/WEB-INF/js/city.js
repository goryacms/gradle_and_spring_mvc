
$(document).ready(function () {
    function  getAllCities() {
        alert();
        $.ajax({
            url : '/api/city/',
            success : function(data) {
                $('#container').html(data);
            }
        });
    }
});