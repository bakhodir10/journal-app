/*@ngInject*/

module.exports = function ($uibModal, SubjectService) {

    var _this = this;
    _this.subjects = [];

    _this.getSubjects = function () {
        SubjectService.findPage(_this.page - 1 || 0, 8).then(function (response) {
            _this.subjects = response.content;
            _this.totalElements = response.totalElements;
        });
    };
    _this.getSubjects();

    _this.editSubject = function (subject) {
        $uibModal.open({
            template: require('./../administration/subjects/edit/SubjectEditTmpl.html'),
            size: 'md',
            controller: require('./../administration/subjects/edit/SubjectEditCtrl'),
            controllerAs: 'editSubjectCtrl',
            resolve: {
                subject: function () {
                    return angular.copy(subject);
                }
            }
        }).result.then(function (data) {
            if (subject == undefined) {
                _this.subjects.push(data);
            }
            else {
                angular.forEach(_this.subjects, function (value, key) {
                    if (value.uuid == data.uuid) {
                        _this.subjects[key] = data;
                    }
                })
            }
        }, function () {
        })
    };

    _this.deleteSubject = function (subject) {
        swal({
            title: "Are you sure ?",
            text: 'Delete this item',
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            confirmButtonText: "Yes, delete it!",
            confirmButtonColor: "#039BE5"
        }, function () {
            SubjectService.delete(subject).then(function successCallback() {
                var index = _this.subjects.indexOf(subject);
                _this.subjects.splice(index, 1);
                swal("Deleted..!", "Data was successfully deleted!", "success");
            }, function errorCallback(error) {
                swal("Oops..!", "We couldn't connect to the server!", "error");
            })
        });
    };
};