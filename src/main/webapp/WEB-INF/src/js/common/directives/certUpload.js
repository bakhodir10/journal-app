module.exports = function () {
  return {
    restrict: 'A',
    scope: {
      certUpload: '='
    },
    link: function (scope, element) {
      element.bind('change', function (changeEvent) {
        var reader = new FileReader();
        reader.onload = function (loadEvent) {
          scope.$apply(function () {
            var content = loadEvent.target.result;
            console.log(content.length);
            scope.certUpload = content;
          });
        };
        reader.readAsDataURL(changeEvent.target.files[0]);
      });
    }
  }
};
