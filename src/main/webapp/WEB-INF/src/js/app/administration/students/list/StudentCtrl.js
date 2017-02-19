/*@ngInject*/
module.exports = function ($state, $rootScope, SubjectService) {
    var _this = this;
    
    SubjectService.findPage().then(function (response) {
        _this.subjectList = response;
    });

    _this.delete = function (subject) {
        SubjectService.delete(subject).then(function (response) {
            if (response != null) {
                var index = _this.subjectList.indexOf(subject);
                _this.subjectList.splice(index, 1);
            }
            $state.go('app.main.settings.subjects');
        });
    }
};
