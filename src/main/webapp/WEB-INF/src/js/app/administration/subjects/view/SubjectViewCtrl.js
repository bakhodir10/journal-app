/*@ngInject*/
module.exports = function ($stateParams, SubSubjectService, subSubjects, subjectList) {
    var _this = this;
    _this.subject = [];
    _this.subSubjects = subSubjects;


    angular.forEach(subjectList, function (value, key) {
        if (value.uuid == $stateParams.subjectUuid) {
            _this.subject = angular.copy(value);
        }
    });
    _this.delete = function (subject) {
        SubSubjectService.delete(subject).then(function (response) {
            if (response != null) {
                var index = _this.subSubjects.indexOf(subject);
                _this.subSubjects.splice(index, 1);
            }
        });
    }
};
