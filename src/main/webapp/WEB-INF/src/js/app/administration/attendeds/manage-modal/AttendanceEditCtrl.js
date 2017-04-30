module.exports = function ($state, $uibModalInstance, attendance, AttendanceService) {

    var _this = this;
    _this.attendance = attendance;
    _this.attendance.date = new Date(_this.attendance.date);
    _this.update = function () {
        console.log(_this.attendance);
        AttendanceService.update(attendance).then(function (response) {
            _this.dismiss();
        })
    };

    _this.dismiss = function () {
        $uibModalInstance.dismiss('cancel');
    }
};