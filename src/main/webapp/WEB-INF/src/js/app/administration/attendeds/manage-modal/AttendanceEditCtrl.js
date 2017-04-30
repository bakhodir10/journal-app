module.exports = function ($state, $uibModalInstance, attendance, AttendanceService) {

    var _this = this;
    _this.attendance = attendance;
    attendance.date != null ?_this.attendance.date = new Date(_this.attendance.date)
        : _this.attendance.date = new Date();
    _this.update = function () {
        AttendanceService.update(attendance).then(function (response) {
            $uibModalInstance.close(response);
        })
    };

    _this.dismiss = function () {
        $uibModalInstance.dismiss('cancel');
    }
};