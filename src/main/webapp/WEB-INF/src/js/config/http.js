var angular = require('angular');
var Base64 = require('js-base64').Base64;

/*@ngInject*/
module.exports = function ($httpProvider) {
    /*$httpProvider.defaults.transformRequest = function (data, headersGetter, status) {
     console.log('sent', data);
     return angular.toJson({data: Base64.encode(angular.toJson(data))});
     };

     $httpProvider.defaults.transformResponse = function (data, headersGetter, status) {
     var contentType = headersGetter('Content-Type');
     if (status == 401) {

     //TODO loginga redirect qivorish kerak !
     //AuthService.removeToken();
     //$state.go('login');
     }
     if (contentType && contentType.startsWith('application/json')) {
     var decrypted = angular.fromJson(Base64.decode(angular.fromJson(data).data));
     console.log('received', decrypted);
     return decrypted;
     }
     return data;
     };*/
    $httpProvider.defaults.transformRequest = function (data, headersGetter, status) {
        return angular.toJson({data: angular.toJson(data)});
    };

    $httpProvider.defaults.transformResponse = function (data, headersGetter, status) {
        var contentType = headersGetter('Content-Type');
        if (status == 401) {

            //TODO loginga redirect qivorish kerak !
            //AuthService.removeToken();
            //$state.go('login');
        }

        if (status == 403) {
            //var root = $injector.get('$rootScope');
            //console.log(root);
            console.log('403');
        }

        if (contentType && contentType.startsWith('application/json') && status >= 200 && status < 300) {
            var decrypted = angular.fromJson(angular.fromJson(data).data);
            return decrypted;
        }
        return data;
    }
};
