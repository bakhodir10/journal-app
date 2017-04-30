module.exports = function ($state, AuthService) {

    var _this = this;

    _this.exitApp = function () {
        AuthService.removeToken();
        $state.go('login');
    };

    _this.getSubjects = function () {
    }

};