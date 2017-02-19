'use strict';

/*@ngInject*/
module.exports = function ($http, $q, $window, $filter, $uibModal) {
    var _this = this;

    _this.select2CleanChild = function (object) {
        angular.forEach(object, function (value, key) {
            console.log(value);
            if (value.children.length == 0) {
                delete value.children;
            }
        });
        return object;
    };
    _this.openDocument = function () {
        $uibModal.open({
            template: require('../../app/document/PreView.html'),
            controller: require('../../app/document/PreViewCtrl'),
            controllerAs: 'docCtrl',
            resolve: {
                docId: function () {
                    return null;
                }
            },
            size: 'lg'
        }).result.then(function (data) {
            if (data.result == 200) {
                console.log(data);
            }
        });
    };


    _this.getRegisterNumber = function (appForm) {
        if (appForm.registerPrefix) {
            if (appForm.registerNumber) {
                if (appForm.registerSufix) {
                    return appForm.registerPrefix + appForm.registerNumber + ' /' + appForm.registerSufix;
                }
            }
        }
    };

    _this.appTypeList = [
        {
            name: 'Ариза', value: 'APPLICATION'
        },
        {
            name: 'Таклиф', value: 'OFFER'
        },
        {
            name: 'Шикоят',
            value: 'COMPLAINT'
        }
    ];

    _this.contactTypeList = [{name: 'Якка тартибда', value: 'INDIVIDUAL'}, {name: 'Жамоа', value: 'COMMUNITY'}];

    _this.docTypeList = [{name: 'Хат', value: 'LETTER'}, {name: 'Факс', value: 'FAX'}, {
        name: 'Шошилинчнома',
        value: 'EMERGENCY_LETTER'
    }];

    _this.receiveTypeList = [{name: 'Почта', value: 'POST'}, {
        name: 'Қабул',
        value: 'PERSONALLY'
    }, {name: 'Эл. почта', value: 'EMAIL'}];

    _this.statusType = [{name: 'NEW', value: 'NEW'},
        {name: 'CONFIRM', value: 'CONFIRM'},
        {name: 'RESOLUTION', value: 'RESOLUTION'},
        {name: 'SEND_TO_ORGANIZATION', value: 'SEND_TO_ORGANIZATION'},
        {name: 'SEND_TO_EXPEDITION', value: 'SEND_TO_EXPEDITION'},
        {name: 'SEND_TO_CONTROL', value: 'SEND_TO_CONTROL'},
        {name: 'RECEIVE_FROM_ORGANIZATION', value: 'RECEIVE_FROM_ORGANIZATION'}
    ];
    _this.controlTypeList = [{name: 'NEW', value: 'NEW'},
        {name: 'CONFIRM', value: 'CONFIRM'},
        {name: 'RESOLUTION', value: 'RESOLUTION'},
        {name: 'SEND_TO_ORGANIZATION', value: 'SEND_TO_ORGANIZATION'},
        {name: 'SEND_TO_EXPEDITION', value: 'SEND_TO_EXPEDITION'},
        {name: 'SEND_TO_CONTROL', value: 'SEND_TO_CONTROL'},
        {name: 'RECEIVE_FROM_ORGANIZATION', value: 'RECEIVE_FROM_ORGANIZATION'}
    ];

    _this.app_shape = 1;

    _this.controlTypes = [{name: 'Control', value: 'CONTROL'}, {name: 'Uncontrolled', value: 'UN_CONTROL'}];

    _this.unControlTypeList = [{name: 'Чора кўрилди', value: 'CHORA_KORILDI'}, {
        name: 'Тушунтирилди',
        value: 'TUSHUNTIRILDI'
    }, {name: 'Рад этилди', value: 'RAD_ETILDI'}, {name: 'Маълумот учун қабул қилинди', value: 'MALUMOT_UCHUN'}];

    _this.appTypeParse = function (appType) {
        var newTemp = $filter("filter")(_this.appTypeList, {value: appType})[0];
        return newTemp.name;
    };

    _this.contactTypeParse = function (contactType) {
        var newTemp = $filter("filter")(_this.contactTypeList, {value: contactType})[0];
        return newTemp.name;
    };

    _this.receiveTypeParse = function (receiveType) {
        var newTemp = $filter("filter")(_this.receiveTypeList, {value: receiveType})[0];
        return newTemp.name;
    };

    _this.unControlTypeParse = function (unControlType) {
        var newTemp = $filter("filter")(_this.unControlTypeList, {value: unControlType})[0];
        return newTemp.name;
    };

    _this.encrypt = function (enc) {
        //return btoa(enc);
    };

    _this.decrypt = function (dec) {
        //return atob(dec);
    };

};
