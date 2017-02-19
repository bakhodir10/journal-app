module.exports = function ($http) {
    var _this = this;

    _this.findPage = function () {
        var params = {
            size: 10000000,
            page: 0
        };
        return $http.get('/api/marks', {params: params}).then(function (response) {
            return response.data;
        });
    };

    _this.findOne = function (uuid) {
        var params = {};
        return $http.get('/api/marks/' + uuid, {params: params}).then(function (response) {
            return response.data;
        });
    };

    _this.create = function (subject) {
        return $http.post('/api/marks/', subject).then(function (response) {
            return response.data;
        });
    };

    _this.update = function (subject) {
        return $http.put('/api/marks/' + subject.uuid, subject).then(function (response) {
            return response.data;
        })
    };

    _this.delete = function (subject) {
        return $http.delete('/api/marks/' + subject.uuid);
    }
};