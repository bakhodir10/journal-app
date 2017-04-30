/*@ngInject*/
module.exports = function ($http) {
    var _this = this;

    _this.findPage = function (page, size, certificateFetch, positionFetch, departmentFetch) {
        var param = {
            page: page || 0,
            size: size || 10,
            certificateFetch: certificateFetch == null ? null : certificateFetch,
            positionFetch: positionFetch == null ? null : positionFetch,
            departmentFetch: departmentFetch == null ? null : departmentFetch
        };
        return $http.get('api/users', {params: param}).then(function (response) {
            return response.data.content;
        })
    };

    _this.findOne = function (uuid, roleFetch, certificateFetch, positionFetch, departmentFetch) {
        var params = {
            roleFetch: roleFetch == null ? null : roleFetch,
            certificateFetch: certificateFetch == null ? null : certificateFetch,
            positionFetch: positionFetch == null ? null : positionFetch,
            departmentFetch: departmentFetch == null ? null : departmentFetch
        };
        return $http.get('/api/users/' + uuid, {params: params}).then(function (response) {
            return response.data;
        });
    };

    _this.create = function (user) {
        return $http.post('/api/users', user).then(function (response) {
            return response.data;
        });
    };

    _this.update = function (user) {
        return $http.put('/api/users/' + user.uuid, user).then(function (response) {
            return response.data;
        })
    };

    _this.delete = function (user) {
        return $http.delete('/api/users/' + user.uuid);
    }
};