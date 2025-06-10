$(document).ready(function () {
    $("#date-picker").datepicker({
        format: 'dd/mm/yyyy',
    });
    $("#time-picker").timepicker({
    });
    runInputSpinner();
});