module.exports = function ($state, AuthService, currentUser) {
    var _this = this;

    console.log(currentUser);

    _this.currentUser = currentUser;

    _this.logout = function () {
        AuthService.removeToken();
        $state.go('login');
    };
};
