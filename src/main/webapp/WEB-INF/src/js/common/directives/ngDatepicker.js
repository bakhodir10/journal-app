"use strict";
module.exports = function () {

    return {
        restrict: "A",
        require: "ngModel",
        scope: {
          ngModel: '='
        },
        link: function(scope, element) {
            var startDate = scope.ngModel;

            function formatDate(date) {
                console.log(date);
                var d = new Date(date),
                    month = '' + (d.getMonth() + 1),
                    day = '' + d.getDate(),
                    year = d.getFullYear();

                if (month.length < 2) month = '0' + month;
                if (day.length < 2) day = '0' + day;

                return [year, month, day].join('-');
            }

            if (startDate == undefined) {
                startDate = formatDate(new Date());
            }
            element.val(startDate);
            element.datepicker({
                format: "yyyy-mm-dd"
            });
            //element.datepicker('update', new Date());

        }
    };

};