require('./../css/icon.css');
require('./../css/select2.min.css');
require('./../css/select2-bootstrap.css');
require('./../css/app.css');
require('angular-loading-bar/build/loading-bar.css');
require('./../css/bootstrap-datepicker.min.css');
require('./../fonts/fontawesome-webfont.svg');
require('./../css/font-awesome.css');
require('./../css/theme.css');
require('sweetalert/dist/sweetalert.css');
require('sweetalert/lib/sweetalert.js');

require('angular')
    .module('app', [
        require('angular-ui-bootstrap'),
        require('angular-ui-router'),
        require('angular-loading-bar')])
    .config(require('./config/http'))
    .config(require('./config/routes'))
    .config(['cfpLoadingBarProvider', function (cfpLoadingBarProvider) {
        cfpLoadingBarProvider.includeSpinner = true;
    }])
    .run(require('./run')).run(function ($templateCache) {
    $templateCache.put("uib/template/pagination/pagination.html", require('./common/pagination/PaginationTmpl.html'))
})
    .directive('ijroScrollable', require('./common/directives/ijroScrollable'))
    .filter('t', require('./common/filters/t'))
    .service('AuthService', require('./common/services/AuthService'))
    .service('SubjectService', require('./app/administration/subjects/SubjectService'))
    .service('GroupService', require('./app/administration/groups/GroupService'))
    .service('StudentService', require('./app/administration/students/StudentService'))
    .service('MarkService', require('./app/administration/marks/MarkService'))
    .service('UserService', require('./app/administration/users/UserService'))
    .service('AttendanceService', require('./app/administration/attendeds/AttendanceService'));
