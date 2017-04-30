/*@ngInject*/
module.exports = function ($http, $rootScope, $stateParams, $state, AuthService) {
    var token = AuthService.updateToken();
    $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams, options) {
        if (toState.name !== 'login' && !AuthService.getToken()) {
            event.preventDefault();
            $state.go('login');
        }

        // else if (toState.name === 'app.main.createApplication') {
        //     if ($rootScope.hasAccess('CREATE_ALL_ENTITY', 'CREATE_APPLICATION', 'CREATE_ALL_OWN_DEPARTMENT_APPLICATION', 'CREATE_OWN_DEPARTMENT_APPLICATION')) {
        //     }
        //     else {
        //         // event.preventDefault();
        //         // $state.go('app.main.home');
        //         // sweetAlert("Ooops...!", "You don't have a Permission to this event", "error");
        //     }
        // }
        // else if (toState.name === 'app.main.editApplication') {
        //     if (($stateParams.appUuid
        //         && $rootScope.hasAccess('UPDATE_ALL_ENTITY', 'UPDATE_APPLICATION', 'UPDATE_ALL_OWN_DEPARTMENT_APPLICATION', 'UPDATE_OWN_DEPARTMENT_APPLICATION'))) {
        //     }
        //     else {
        //         event.preventDefault();
        //         sweetAlert("Ooops...!", "You don't have a Permission to this event", "error");
        //     }
        // }
        // else if (toState.name === 'app.main.viewApplication') {
        //     if (($stateParams.appUuid
        //         && $rootScope.hasAccess('VIEW_ALL_ENTITY', 'VIEW_APPLICATION', 'VIEW_ALL_OWN_DEPARTMENT_APPLICATION', 'VIEW_OWN_DEPARTMENT_APPLICATION'))) {
        //     }
        //     else {
        //         event.preventDefault();
        //         sweetAlert("Ooops...!", "You don't have a Permission to this event", "error");
        //     }
        // }
        // else if (toState.name === 'app.main.settings.users') {
        //     if (($rootScope.hasAccess('VIEW_ALL_ENTITY', 'VIEW_USER', 'VIEW_ALL_OWN_DEPARTMENT_USER', 'VIEW_OWN_DEPARTMENT_USER'))) {
        //     }
        //     else {
        //         event.preventDefault();
        //         sweetAlert("Ooops...!", "You don't have a Permission to this event", "error");
        //     }
        // }
        // else if (toState.name === 'app.main.settings.users.edit') {
        //     if (($stateParams.userUuid
        //         && $rootScope.hasAccess('UPDATE_ALL_ENTITY', 'UPDATE_USER', 'UPDATE_ALL_OWN_DEPARTMENT_USER', 'UPDATE_OWN_DEPARTMENT_USER'))) {
        //     }
        //     else {
        //         event.preventDefault();
        //         sweetAlert("Ooops...!", "You don't have a Permission to this event", "error");
        //     }
        // }
        // else if (toState.name === 'app.main.settings.users.add') {
        //     if (($rootScope.hasAccess('CREATE_ALL_ENTITY', 'CREATE_USER', 'CREATE_ALL_OWN_DEPARTMENT_USER', 'CREATE_OWN_DEPARTMENT_USER'))) {
        //     }
        //     else {
        //         event.preventDefault();
        //         sweetAlert("Ooops...!", "You don't have a Permission to this event", "error");
        //     }
        // }
    });

    $rootScope.hasAccess = function () {
        return AuthService.hasAccess(arguments);
    }
};
