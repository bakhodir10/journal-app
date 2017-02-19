module.exports = function ($uibModalInstance, group, subject, GroupService) {
    var _this = this;
    if (group != undefined)
        _this.appForm = group;
    else _this.appForm = {};
    _this.appForm.subject = subject.uuid;

    _this.process = function () {
        (_this.appForm.uuid == undefined || _this.appForm.uuid == null) ? _this.create(_this.appForm) : _this.update(_this.appForm)
    };

    _this.create = function (group) {
        GroupService.create(group).then(function successCallback(response) {
            swal("Done..!", "Data was successfully saved..!", "success");
            $uibModalInstance.close(response);
        }, function errorCallback(error) {
            swal("Oops!", "Something went wrong on the page!", "error");
            $uibModalInstance.dismiss('cancel');
        });
    };

    _this.update = function (group) {
        GroupService.update(group).then(function successCallback(response) {
            swal("Done..!", "Data was successfully saved..!", "success");
            $uibModalInstance.close(response);
        }, function errorCallback(error) {
            swal("Oops!", "Something went wrong on the page!", "error");
            $uibModalInstance.dismiss('cancel');
        });
    };

    _this.dismiss = function () {
        $uibModalInstance.dismiss('cancel');
    }
};