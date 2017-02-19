module.exports = function ($rootScope, $state, $stateParams, $uibModalInstance, subject, SubjectService) {
    var _this = this;
    _this.appForm = subject;

    _this.process = function () {
        (_this.appForm.uuid == undefined || _this.appForm.uuid == null) ? _this.create(_this.appForm) : _this.update(_this.appForm)
    };

    _this.create = function (subject) {
        SubjectService.create(subject).then(function successCallback(response) {
            swal("Done..!", "Data was successfully saved..!", "success");
            $uibModalInstance.close(response);
        }, function errorCallback(error) {
            swal("Oops!", "Something went wrong on the page!", "error");
            $uibModalInstance.dismiss('cancel');
        });
    };

    _this.update = function (subject) {
        SubjectService.update(subject).then(function successCallback(response) {
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