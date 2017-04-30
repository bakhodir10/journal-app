/*@ngInject*/
module.exports = function ($state, AuthService) {
    var _this = this;

    _this.credentials = {
        username: 'admin',
        password: 'qwerty'
    };

    _this.process = function () {
        AuthService.process(_this.credentials.username, _this.credentials.password, true).then(function (token) {
            $state.go('app.main.home');
        }).catch(function (response) {
            return console.log('error', response);
        });
    };
};
