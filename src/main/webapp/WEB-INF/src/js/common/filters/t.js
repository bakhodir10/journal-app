var ru = require('raw!./translations_ru.properties');
var uz = require('raw!./translations_uz.properties');

var bundles = {ru: ru, uz: uz};
var translations = {ru: {}, uz: {}};

for (var key in bundles) {
    if (bundles.hasOwnProperty(key)) {
        bundles[key].split('\n').forEach(function (item) {
            if (item) {
                var arr = item.split('=');
                translations[key][arr[0]] = arr[1];
            }
        });
    }
}

/*@ngInject*/
module.exports = function () {
    return function (key) {
        return translations['ru'][key] || '***' + key + '***';
    };
};
