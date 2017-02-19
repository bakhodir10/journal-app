module.exports = function ($http) {
    var _this = this;

    _this.findPage = function (groupUuid) {
        var params = {
            size: 10000000,
            page: 0,
            groupUuid: groupUuid
        };
        return $http.get('/api/students', {params: params}).then(function (response) {
            return response.data;
        });
    };

    _this.findOne = function (uuid) {
        var params = {};
        return $http.get('/api/students/' + uuid, {params: params}).then(function (response) {
            return response.data;
        });
    };

    _this.create = function (student) {
        return $http.post('/api/students', student).then(function (response) {
            return response.data;
        });
    };

    _this.update = function (student) {
        return $http.put('/api/students/' + student.uuid, student).then(function (response) {
            return response.data;
        })
    };

    _this.delete = function (student) {
        return $http.delete('/api/students/' + student.uuid);
    }
};