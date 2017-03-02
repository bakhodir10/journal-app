/*@ngInject*/
module.exports = function ($stateProvider, $urlRouterProvider, $urlMatcherFactoryProvider) {
    $urlRouterProvider.otherwise('/');
    $urlMatcherFactoryProvider.strictMode(false);
    $stateProvider
        .state('login', {
            url: '/login',
            template: require('./../login/LoginTmpl.html'),
            controller: require('./../login/LoginCtrl'),
            controllerAs: 'login'
        })
        .state('app', {
            abstract: true,
            template: require('./../app/AppTmpl.html'),
            resolve: {
                currentUser: function (AuthService) {
                    return AuthService.getCurrentUser();
                }
            }
        })
        .state('app.main', {
            views: {
                'navigation': {
                    template: require('./../app/navbar/NavbarTmpl.html')
                },
                'scrollScript': {
                    template: require('./../app/ScrollScriptTmpl.html')
                },
                '': {
                    template: '<div ui-view></div>'
                }
            },
            controller: require('../app/navbar/NavbarCtrl'),
            controllerAs: 'navbarCtrl'
        })
        .state('app.main.home', {
            url: '/',
            template: require('./../app/main/MainTmpl.html'),
            controller: require('./../app/main/MainCtrl'),
            controllerAs: 'mainCtrl'
        })
        .state('app.main.home.groups', {
            url: 'subjects/:subjectUuid/groups',
            template: require('./../app/administration/groups/view/GroupViewTmpl.html'),
            controller: require('./../app/administration/groups/view/GroupViewCtrl'),
            controllerAs: 'groupViewCtrl',
            resolve: {
                groups: function ($stateParams, GroupService) {
                    return GroupService.findPage($stateParams.subjectUuid).then(function (response) {
                        return response.content;
                    });
                },
                subject: function ($stateParams, SubjectService) {
                    return SubjectService.findOne($stateParams.subjectUuid);
                }
            }
        })

        .state('app.main.attended', {
            url: '/subjects/:subjectUuid/groups/:groupUuid/attended',
            template: require('./../app/administration/students/view/StudentViewTmpl.html'),
            controller: require('./../app/administration/students/view/StudentViewCtrl'),
            controllerAs: 'studentViewCtrl',
            resolve : {
                group: function ($stateParams, GroupService) {
                    return GroupService.findOne($stateParams.groupUuid);
                },
                subject: function ($stateParams, SubjectService) {
                    return SubjectService.findOne($stateParams.subjectUuid);
                },
                students : function ($stateParams, StudentService) {
                    return StudentService.findPage($stateParams.groupUuid).then(function (response) {
                        return response.content;
                    });
                }
            }
        })
        .state('app.main.students', {
            url: '/subjects/:subjectUuid/groups/:groupUuid/students',
            template: require('./../app/administration/students/view/StudentViewTmpl.html'),
            controller: require('./../app/administration/students/view/StudentViewCtrl'),
            controllerAs: 'studentViewCtrl',
            resolve : {
                group: function ($stateParams, GroupService) {
                    return GroupService.findOne($stateParams.groupUuid);
                },
                subject: function ($stateParams, SubjectService) {
                    return SubjectService.findOne($stateParams.subjectUuid);
                },
                students : function ($stateParams, StudentService) {
                    return StudentService.findPage($stateParams.groupUuid).then(function (response) {
                        return response.content;
                    });
                }
            }
        });
};
