/*@ngInject*/
module.exports = function ($stateParams, $uibModal, GroupService, groups, subject) {
    var _this = this;
    _this.groups = groups;
    _this.subject = subject;

    _this.editGroup = function (group) {
        $uibModal.open({
            template: require('./../edit/GroupEditTmpl.html'),
            size: 'md',
            controller: require('./../edit/GroupEditCtrl'),
            controllerAs: 'editGroupCtrl',
            resolve: {
                subject: function () {
                    return angular.copy(subject);
                },
                group: function () {
                    return angular.copy(group);
                }
            }
        }).result.then(function (data) {
            if (group == undefined) {
                _this.groups.push(data);
            }
            else {
                angular.forEach(_this.groups, function (value, key) {
                    if (value.uuid == data.uuid) {
                        _this.groups[key] = data;
                    }
                })
            }
        }, function () {
            console.log('dismissed');
        })
    };

    _this.deleteGroup = function (group) {
        swal({
            title: "Are you sure ?",
            text: 'Delete this item',
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "Yes, delete it!",
            confirmButtonColor: "#039BE5"
        }, function () {
            GroupService.delete(group).then(function successCallback() {
                var index = _this.groups.indexOf(group);
                _this.groups.splice(index, 1);
                swal("Deleted..!", "Data was successfully deleted!", "success");
            }, function errorCallback(error) {
                swal("Oops..!", "We couldn't connect to the server!", "error");
            })
        });
    };
};
