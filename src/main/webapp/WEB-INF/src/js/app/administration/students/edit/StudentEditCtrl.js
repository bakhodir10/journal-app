module.exports = function ($uibModalInstance, $state, $stateParams, subject, group, StudentService) {
    var _this = this;
    _this.appForm = {};
    _this.appForm.subject = subject.uuid;
    _this.appForm.group = group.uuid;

    _this.createStudent = function () {
        StudentService.create(_this.appForm).then(function successCallback(response) {
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