module.exports = function () {
    return {
        restrict: 'A',
        scope: {
            syncHeight: '='
        },
        link: function (scope, element, attr) {
            scope.syncHeight = element.height();
        }
    }
};
