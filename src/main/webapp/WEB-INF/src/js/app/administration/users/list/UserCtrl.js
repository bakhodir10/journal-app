module.exports = function ($state, users, UserService) {
    var _this = this;

    _this.users = users;

    _this.delete = function (user) {
        UserService.delete(user).then(function (response) {
            var index = _this.users.indexOf(user);
            _this.users.splice(index, 1);
            $state.go('app.main.settings.users');
        })
    }
};