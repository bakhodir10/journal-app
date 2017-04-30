"use strict";
module.exports = function () {

    return {
        restrict: "A",
        require: "ngModel",
        scope: {
            ngModel: '='
        },
        link: function (scope, element) {
            var _this = this;

            var tree_glyph_options = {
                map: {
                    checkbox: "fa fa-square-o fa-fw",
                    checkboxSelected: "fa fa-check-square fa-fw",
                    checkboxUnknown: "fa fa-check-square fa-fw fa-muted",
                    error: "fa fa-exclamation-triangle fa-fw",
                    expanderClosed: "fa fa-caret-right fa-fw",
                    expanderLazy: "fa fa-angle-right fa-fw",
                    expanderOpen: "fa fa-caret-down fa-fw",
                    doc: "fa fa-file-o fa-fw",
                    noExpander: "",
                    docOpen: "fa fa-file fa-fw",
                    loading: "fa fa-refresh fa-spin fa-fw",
                    folder: "fa fa-folder fa-fw",
                    folderOpen: "fa fa-folder-open fa-fw"
                }
            };

            var extensions = [ "glyph" ];

            $(element).fancytree({
                checkbox: true,
                selectMode: 3,
                glyph: tree_glyph_options,
                extensions: extensions,
                source: [
                    {title: "Ҳукумат", folder: true, children: [
                        {title: "Алишер Навоий номидаги Ўзбекистон Миллий кутубхонаси"},
                        {title: "Тошкент вилояти, Чиноз туман ҳокимлиги"}
                    ]},
                    {title: "Вазирликлар", folder: true, children: [
                        {title: "Ўзбекистон Республикаси Иқтисодиёт вазирлиги"},
                        {title: "Қорақалпоғистон Республикаси Вазирлар Кенгаши"}
                    ]}
                ],
                activate: function(event, data){
                    
                }
            });            
            //$(element).APFancyTree();
        }
    };

};