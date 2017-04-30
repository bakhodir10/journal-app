/*@ngInject*/
module.exports = function ($stateParams, $uibModal, subject, group, students, StudentService, AttendanceService) {
    var _this = this;
    _this.subject = subject;
    _this.group = group;
    _this.students = students;
    _this.data = '';
    _this.attendances = group.attendances;

    _this.createStudent = function () {
        $uibModal.open({
            template: require('./../edit/StudentEditTmpl.html'),
            size: 'md',
            controller: require('./../edit/StudentEditCtrl'),
            controllerAs: 'editStudentCtrl',
            resolve: {
                subject: function () {
                    return angular.copy(subject);
                },
                group: function () {
                    return angular.copy(group);
                }
            }
        }).result.then(function (data) {
            _this.students.push(data);
        }, function () {
            console.log('dismissed');
        })
    };

    _this.deleteStudent = function (student) {
        swal({
            title: "Are you sure ?",
            text: 'Delete this item',
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "Yes, delete it!",
            confirmButtonColor: "#039BE5"
        }, function () {
            StudentService.delete(student).then(function successCallback() {
                var index = _this.students.indexOf(student);
                _this.students.splice(index, 1);
                swal("Deleted..!", "Data was successfully deleted!", "success");
            }, function errorCallback(error) {
                swal("Oops..!", "We couldn't connect to the server!", "error");
            })
        });
    };

    _this.dismiss = function () {
        $uibModalInstance.dismiss('cancel');
    };


    _this.changeAttendance = function (attendance) {
        $uibModal.open({
            template: require('./../../attendeds/manage-modal/AttendanceEditTmpl.html'),
            size: 'md',
            controller: require('./../../attendeds/manage-modal/AttendanceEditCtrl'),
            controllerAs: 'ctrl',
            resolve: {
                attendance: function () {
                    return angular.copy(attendance);
                }
            }
        }).result.then(function (data) {
            // angular.forEach(_this.attendances, function (value, key) {
            //     if (value.uuid == data.uuid) this.attendance[key] = data;
            // });
            // location.reload();

        }, function () {
            console.log('dismissed');
        })
    };

    _this.changeMark = function (mark) {
        $uibModal.open({
            template: require('./../../marks/manage-modal/MarkEditTmpl.html'),
            size: 'md',
            controller: require('./../../marks/manage-modal/MarkEditCtrl'),
            controllerAs: 'ctrl',
            resolve: {
                mark: function () {
                    return angular.copy(mark);
                }
            }
        }).result.then(function (data) {
            // angular.forEach(_this.attendances, function (value, key) {
            //     if (value.uuid == data.uuid) this.attendance[key] = data;
            // });
            // location.reload();
        }, function () {
            console.log('dismissed');
        })
    };
};
