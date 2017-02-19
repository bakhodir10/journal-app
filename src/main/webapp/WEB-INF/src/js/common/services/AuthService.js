/*@ngInject*/
module.exports = function ($http, $q, $window, $rootScope) {
    var _this = this;

    var tokenName = 'X-AUTH-TOKEN';

    var currentUser;

    _this.hasAccess = function (arguments) {
        if (currentUser == null) return false;

        for (var i = 0; i < arguments.length; i++) {
            var el = arguments[i];
            for (var j = 0; j < currentUser.role.permissions.length; j++) {
                var element = currentUser.role.permissions[j];
                if (el === element) {
                    return true;
                }
            }
        }
        return false;
    };

    _this.getCurrentUser = function () {
        var deferred = $q.defer();
        if (currentUser) {
            deferred.resolve(currentUser);
        } else {
            var token = _this.getToken();
            if (token) {
                _this.checkToken(token).then(function (user) {
                    $rootScope.currentUser = user;
                    currentUser = user;
                    deferred.resolve(user);
                })
            } else {
                deferred.resolve(null);
            }
        }
        return deferred.promise;
    };

    _this.getToken = function () {
        return $window.sessionStorage.getItem(tokenName);
    };

    _this.setToken = function (token) {
        $window.sessionStorage.setItem(tokenName, token);
        $http.defaults.headers.common[tokenName] = token;
    };

    _this.removeToken = function () {
        $window.sessionStorage.removeItem(tokenName);
        delete $http.defaults.headers.common[tokenName];
    };

    _this.updateToken = function () {
        $http.defaults.headers.common['X-AUTH-TOKEN'] = _this.getToken();
    };

    _this.checkToken = function (token, persist) {
        var promise = $http.get('api/token/check', {
            headers: {
                'X-AUTH-TOKEN': token
            }
        }).then(function (response) {
            return response.data;
        });
        return persist ? promise.then(function (token) {
            _this.setToken(token);
            return token;
        }) : promise;
    };

    _this.process = function (username, password, persist) {
        var authorization = 'Basic ' + $window.btoa(username + ':' + password);
        var promise = $http.get('api/token/generate', {
            headers: {
                Authorization: authorization
            }
        }).then(function (response) {
            return response.data;
        });
        return persist ? promise.then(function (token) {
            currentUser = token.user;
            _this.setToken(token.uuid);
            return token;
        }) : promise;
    };
};
