module.exports = function ($state, $uibModalInstance, mark, MarkService) {

    var _this = this;
    _this.mark = mark;
    console.log(mark);

    _this.update = function () {
        console.log(_this.mark);
        MarkService.update(mark).then(function (response) {
            _this.dismiss();
        })
    };

    _this.dismiss = function () {
        $uibModalInstance.dismiss('cancel');
    }
};