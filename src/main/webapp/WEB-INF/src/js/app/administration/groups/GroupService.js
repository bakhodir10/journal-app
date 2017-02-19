module.exports = function ($http) {
    var _this = this;

    _this.findPage = function (subjectUuid) {
        var params = {
            size: 10000000,
            page: 0,
            subjectUuid: subjectUuid || null
        };
        return $http.get('/api/groups', {params: params}).then(function (response) {
            return response.data;
        });
    };

    _this.findOne = function (uuid) {
        var params = {};
        return $http.get('/api/groups/' + uuid, {params: params}).then(function (response) {
            return response.data;
        });
    };

    _this.create = function (subject) {
        return $http.post('/api/groups/', subject).then(function (response) {
            return response.data;
        });
    };

    _this.update = function (subject) {
        return $http.put('/api/groups/' + subject.uuid, subject).then(function (response) {
            return response.data;
        })
    };

    _this.delete = function (subject) {
        return $http.delete('/api/groups/' + subject.uuid);
    }
};