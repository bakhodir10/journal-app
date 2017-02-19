/*@ngInject*/
module.exports = function ($stateParams, $uibModal, subject, group, students, StudentService) {
    var _this = this;
    _this.subject = subject;
    _this.group = group;
    _this.students = students;
    console.log(students);
    console.log(group);
    _this.count = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18];

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


    _this.saveData = function ($event, mark, student) {
        console.log($event);
        console.log($event.currentTarget.innerText);
        console.log(mark);
        console.log(student);
    };

    _this.change = function (mark) {
        console.log(mark);
    }
};
