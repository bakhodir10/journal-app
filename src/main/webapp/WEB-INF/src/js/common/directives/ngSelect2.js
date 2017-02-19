"use strict";
module.exports = function () {

    return {
        restrict: "A",
        require: "ngModel",
        scope: {
            select2Data: '=',
            ngModel: '=',
            select2OnChange: '&',
            templateResult: '=',
            select2OnAddOption: '&',
            select2Value: '=',
            select2NewValue: '='
        },
        link: function (scope, element) {
            var _this = this;
            
            jQuery.fn.select2.defaults.set("theme", "bootstrap");

            function templateSelection(state) {
                var r = state.text;
                var d;
                if (r.split("|")) {
                    d = state.text.split("|")[0];
                } else {
                    d = r;
                }
                if (d == 'null') {
                    return null;
                }
                scope.select2Value = d;
                return d;
            }

            scope.$watch('select2Data', function (newValue) {
                scope.select2Data = newValue;
                _this.option = {data: scope.select2Data};

                if (scope.templateResult) {
                    _this.optionTwo = {templateResult: scope.templateResult, templateSelection: templateSelection};
                }

                element.select2(_this.option).val(scope.ngModel);

                element.select2(_this.optionTwo);

                _this.option = {};

                _this.optionTwo = {};
            });

            scope.$watch('select2NewValue', function (newValue) {
                if (scope.select2NewValue != undefined) {
                    element.select2().val(newValue);
                    element.select2();
                }
            });

            if (scope.select2OnChange) {
                element.bind('select2:select', function () {
                    scope.$apply(function () {
                        scope.select2OnChange();
                    });
                });
            }

            if (scope.ngModel != undefined) {
                scope.select2OnChange();
            }
        }
    };

};