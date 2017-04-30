module.exports = function ($http) {
    var _this = this;

    _this.findPage = function () {
        var params = {
            size: 10000000,
            page: 0
        };
        return $http.get('/api/attendances', {params: params}).then(function (response) {
            return response.data;
        });
    };

    _this.findOne = function (uuid) {
        var params = {};
        return $http.get('/api/attendances/' + uuid, {params: params}).then(function (response) {
            return response.data;
        });
    };

    _this.create = function (attendance) {
        return $http.post('/api/attendances', attendance).then(function (response) {
            return response.data;
        });
    };

    _this.update = function (attendance) {
        return $http.put('/api/attendances/' + attendance.uuid, attendance).then(function (response) {
            return response.data;
        })
    };

    _this.delete = function (attendance) {
        return $http.delete('/api/attendances/' + attendance.uuid);
    }
};