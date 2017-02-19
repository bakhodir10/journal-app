module.exports = function ($state, user, users, roles, departments, positions, UserService) {

    var _this = this;
    _this.user = user;
    _this.user.role = user.roles == undefined ? null : user.roles[0] == undefined ? null : user.roles[0].uuid;
    _this.roles = roles;
    _this.user.department = user.department == undefined ? null : user.department.uuid;
    _this.departments = departments;
    _this.user.position = user.position == undefined ? null : user.position.uuid;
    _this.positions = positions;
    _this.users = users;

    console.log(_this.users);

    angular.forEach(_this.role, function (value, key) {
        value.id = value.uuid;
    });
    angular.forEach(_this.departments, function (value, key) {
        value.id = value.uuid;
    });
    angular.forEach(_this.positions, function (value, key) {
        value.id = value.uuid;
    });

    _this.process = function () {
        _this.user.uuid == undefined || _this.user.uuid == null ? _this.create(_this.user) : _this.update(_this.user);
    };

    _this.create = function (user) {
        UserService.create(user).then(function (response) {
            swal("Done..!", "Data was successfully saved..!", "success");
            console.log(response);
            _this.users.push(response);
        });
    };

    _this.update = function (user) {
        UserService.update(user).then(function (response) {
            angular.forEach(_this.users, function (value, key) {
                if (value.uuid == user.uuid) {
                    _this.users[key] = user;
                }
            });
            swal("Done..!", "Data was successfully saved..!", "success");
        });
    };
};