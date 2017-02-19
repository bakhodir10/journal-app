module.exports = function ($state, AuthService) {

    var _this = this;

    _this.exitApp = function () {
        console.log('exit');
        AuthService.removeToken();
        $state.go('login');
    };

    _this.getSubjects = function () {
        console.log('subjects');
    }

};