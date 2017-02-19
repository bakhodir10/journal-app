module.exports = function ($state, $rootScope, AuthService, currentUser) {
    var _this = this;

    _this.currentUser = currentUser;

    _this.logout = function () {
        AuthService.removeToken();
        $state.go('login');
    };

    $rootScope.$on('clickEvent', function () {
        $state.go('login');
        console.log('appCtrl');
    })

};
