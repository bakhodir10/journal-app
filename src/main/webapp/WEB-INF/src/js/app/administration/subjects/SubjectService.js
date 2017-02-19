module.exports = function ($http) {
    var _this = this;

    _this.findPage = function (page, size) {
        var params = {
            size: size || 10,
            page: page
        };
        return $http.get('/api/subjects', {params: params}).then(function (response) {
            return response.data;
        });
    };

    _this.findOne = function (uuid) {
        var params = {};
        return $http.get('/api/subjects/' + uuid, {params: params}).then(function (response) {
            return response.data;
        });
    };

    _this.create = function (subject) {
        return $http.post('/api/subjects/', subject).then(function (response) {
            return response.data;
        });
    };

    _this.update = function (subject) {
        return $http.put('/api/subjects/' + subject.uuid, subject).then(function (response) {
            return response.data;
        })
    };

    _this.delete = function (subject) {
        return $http.delete('/api/subjects/' + subject.uuid);
    }
};