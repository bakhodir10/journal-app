module.exports = function () {
    return {
        restrict: 'A',
        scope: {
            ngPlaceholder: '='
        },
        link: function (scope, element, attr) {
            BootstrapLayoutScrollable.scrollable.init(element)

        }
    }
};
