module.exports = function ($state, $uibModalInstance, mark, MarkService) {

    var _this = this;
    _this.mark = mark;

    _this.update = function () {
        MarkService.update(mark).then(function (response) {
            $uibModalInstance.close(response);
        })
    };

    _this.dismiss = function () {
        $uibModalInstance.dismiss('cancel');
    }
};