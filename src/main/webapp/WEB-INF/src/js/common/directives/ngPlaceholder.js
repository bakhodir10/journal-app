module.exports = function () {
    return {
        restrict: 'A',
        scope: {
            ngPlaceholder: '='
        },
        link: function (scope, element, attr) {
            scope.$watch('placeholder', function () {
                element[0].placeholder = scope.ngPlaceholder;
            });
        }
    }
};
