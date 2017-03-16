$(document).ready(function () {
    load();
});

function load() {
    form_init();
}


function form_init() {
    webix.ready(function () {
        var layout = webix.ui({
            id: "mainlayot",
            container: "mainContainer",
            cols: [
                {
                    view: "list",
                    width: 250,
                    minHeight: 400,
                    template: "#title#",
                    select: true,
                    scroll: false,
                    data: [
                        {id: 1, title: "Текущая игра"},
                        {id: 2, title: "Архив"},
                        {id: 3, title: "Фотогалерея"}
                    ],
                    on: {
                        onItemClick: function (val) {
                            $$("body").removeView("content");
                            switch (val) {
                                case "1":
                                    getLastGame();
                                    break;
                                case "2":
                                    createArchiveGameTable();
                                    break;
                            }
                        }
                    }
                }, {
                    width: 10
                }, {
                    id: "body",
                    rows: [{id: "content"}]
                }, {
                    width: 10
                }
            ]
        });

    });
}

function createSlidesView(gameId) {
    $$("mainlayot").hide();

    var layout = webix.ui({
        id: "slideslayot",
        container: "sldesContainer",
        cols: [
            {
                rows: [
                    {
                        cols: [
                            {},
                            {
                                height: 50,
                                width: 200,
                                css: "appBtn",
                                template: "<button style='width: 100%' class='btn btn-success' onclick='addSlide(\"" + gameId + "\")'>Добавить слайд</button>"
                            }
                        ]
                    },
                    {
                        id: "slidesTable",
                        view: "datatable",
                        css: "slidesTable",
                        select: 'row',
                        hover: "slidesTableHover",
                        autoheight: false,
                        height: 505,
                        url: '/arma/wr/admin/getSlidesList?gameId=' + gameId,
                        rowHeight: 30,
                        columns: [
                            {
                                id: "slidesTable.code",
                                template: "#code#",
                                header: "Код",
                                width: 90
                            },
                            {
                                id: "slidesTable.titleRus",
                                template: "#titleRus#",
                                header: "Заголовок",
                                fillspace: 1
                            }
                        ],
                        on: {
                            onAfterLoad: function () {
                                this.hideOverlay();

                                if (!this.count())
                                    this.showOverlay("<span class='nodatafound'>Нет данных</span>");
                            },
                            onItemClick: function (id, e, trg) {
                                selected = this.getSelectedItem();
                                $$("slidesTableEdit").enable();
                            },
                            onItemDblClick: function (id, e, trg) {
                                editaddGameWin();
                            }
                        },
                        pager: "slidesTablePager"
                    }, {id: "slidesTablePager", view: "pager", size: 15}
                ]
            }, {
                width: 15
            }
        ]
    });
}


function getLastGame() {
    get_ajax('/arma/wr/admin/getLastGameName', 'GET', null, function (gson) {
        if (gson && gson.result) {
            if (gson.message)
                createLastGameForm(gson.message.id, gson.message.name)
            else
                createLastGameForm(null, null)
        } else {
            notifyMessage('Ошибка! ', gson.message, notifyType.danger);
        }
    });
}


function createLastGameForm(gameId, gameName) {
    $$("body").addView({
        id: "content",
        rows: [
            {
                view: "label",
                hidden: isNullOrEmpty(gameId),
                label: gameName
            },
            {
                view: "label",
                hidden: !isNullOrEmpty(gameId),
                label: "Текущая игра не найдена"
            }, {
                height: 25
            }, {
                height: 50,
                template: "<button style='width: 300px' class='btn btn-danger' onclick='addGameWin()'>Добавить новую игру</button>"
            }, {
                height: 50,
                hidden: isNullOrEmpty(gameId),
                template: "<button style='width: 300px' class='btn btn-success' onclick='createSlidesView(\"" + gameId + "\")'>Слайды</button>"
            }, {
                height: 50,
                hidden: isNullOrEmpty(gameId),
                template: "<button style='width: 300px' class='btn btn-warning'>Редактировать</button>"
            }, {
                hidden: isNullOrEmpty(gameId),
                height: 50,
                template: "<button style='width: 300px' class='btn btn-primary'>Команды</button>"
            }
        ]

    })
}


function addGameWin() {
    if (!$$('addGameWin')) {
        webix.ui({
            view: "window",
            id: "addGameWin",
            modal: true,
            position: "center",
            width: 650,
            head: {
                cols: [
                    {width: 10},
                    {view: "label", label: "Добавить новую игру"},
                    {
                        borderless: true,
                        view: "toolbar",
                        paddingY: 2,
                        height: 40,
                        cols: [
                            {
                                view: "icon", icon: "floppy-o", css: "buttonIcon", click: function () {
                                addGameWinSubmit(this, $$("addGameForm"));
                            }
                            },
                            {
                                view: "icon", icon: "fa fa-times", css: "buttonIcon", click: function () {
                                this.getTopParentView().close();
                                window.onscroll = null;
                            }
                            }
                        ]
                    }
                ]
            },
            body: {
                view: "form",
                id: "addGameForm",
                width: 850,
                elements: [
                    {view: "text", name: "id", hidden: true},
                    {view: "text", name: "name", label: "Название игры"},
                    {
                        view: "datepicker",
                        name: "dateGame",
                        label: "Дата игры",
                        value: new Date(),
                        stringResult: true,
                        format: "%d.%m.%Y"
                    }

                ], elementsConfig: {labelAlign: "right", labelWidth: 140},
            }
        }).hide();
    }
    $$('addGameWin').show(false, false);
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
}

function addGameWinSubmit(me, form) {
    if (form.validate()) {
        var data = form.getValues();

        me.disable();
        var json = JSON.stringify(data, null, 1);
        get_ajax('/arma/wr/admin/addGame', 'POST', json, function (gson) {
            me.enable();
            if (!gson || !gson.result) {
                notifyMessage('Ошибка! ', gson.message, notifyType.danger);
                return;
            }
            $$("body").removeView("content");
            createLastGameForm(gson.message, data.name)
            $$("addGameWin").hide();
            notifyMessage('Информация! ', 'Игра добавлена!', notifyType.info);
        });
    }
}

function createArchiveGameTable() {
    $$("body").addView({
        id: "content",
        rows: [
            {
                id: "archiveTable",
                view: "datatable",
                css: "archiveTable",
                select: 'row',
                hover: "archiveTableHover",
                autoheight: false,
                height: 505,
                url: '/arma/wr/admin/getArchiveGameList',
                rowHeight: 30,
                columns: [
                    {
                        id: "name",
                        header: "Название игры",
                        fillspace: 1
                    },
                    {
                        id: "dateGame",
                        header: "Дата игры",
                        width: 150
                    }
                ],
                on: {
                    onAfterLoad: function () {
                        this.hideOverlay();

                        if (!this.count())
                            this.showOverlay("<span class='nodatafound'>Нет данных</span>");
                    },
                    onItemClick: function (id, e, trg) {

                    },
                    onItemDblClick: function (id, e, trg) {
                    }
                },
                pager: "archiveTablePager"
            }, {id: "archiveTablePager", view: "pager", size: 15}
        ]

    })
}


function addSlideWinResize() {

    var cont_h = $(window).height();
    var cont_w = $(window).width();
}

function addSlide(gameId) {
    if (!$$('addSlideWin')) {
        webix.ui({
            view: "window",
            id: "addSlideWin",
            modal: true,
            position: "center",
            width: 650,
            head: {
                cols: [
                    {width: 10},
                    {view: "label", label: "Добавить новый слайд"},
                    {
                        borderless: true,
                        view: "toolbar",
                        paddingY: 2,
                        height: 40,
                        cols: [
                            {
                                view: "icon", icon: "floppy-o", css: "buttonIcon", click: function () {
                                addSlideWinSubmit(this, $$("addSlideForm"));
                            }
                            },
                            {
                                view: "icon", icon: "fa fa-times", css: "buttonIcon", click: function () {
                                this.getTopParentView().close();
                                window.onscroll = null;
                            }
                            }
                        ]
                    }
                ]
            },
            body: {
                view: "form",
                id: "addSlideForm",
                elements: [
                    {view: "text", name: "id", hidden: true},
                    {

                        view: "scrollview",
                        scroll: 'x',
                        body: {
                            rows: [
                                {height: 20},
                                {
                                    cols: getSlidesTemplateButtons()
                                },
                                {height: 20}
                            ]
                        }
                    }
                ], elementsConfig: {labelAlign: "right", labelWidth: 140},
            }
        }).hide();
    }
    $$('addSlideWin').show(false, false);
    var y = window.scrollY;
    window.onscroll = function () {
        window.scrollTo(0, y);
    };
}

function getSlidesTemplateArr() {
    return [
        {id: "onlyText", center: "fa-file-text-o"},
        {id: "onlyImg", center: "fa-picture-o"},
        {id: "textSound", left: "fa-file-text-o", right: "fa-volume-up"},
        {id: "imgSound", left: "fa-picture-o", right: "fa-volume-up"},
        {id: "imgVideo", left: "fa-picture-o", right: "fa fa-video-camera"},
        {id: "textVideo", left: "fa-file-text-o", right: "fa fa-video-camera"}
    ];
}

function getSlidesTemplateButtons() {
    var arr = getSlidesTemplateArr();

    var data = [];
    for (var i in arr) {
        var template = "";
        if (arr[i].center) {
            template = "<div onclick='selectTemplate(\"" + arr[i].id + "\")' class='slides-template '> <span class='fa center " + arr[i].center + "'></span></div>";
        } else {
            template = "<div onclick='selectTemplate(\"" + arr[i].id + "\")' class='slides-template '> <span class='fa " + arr[i].left + "'></span><span class='fa " + arr[i].right + "'></span></div>";
        }
        data.push({
            width: 120,
            height: 70,
            id: "selectTemplate" + arr[i].id,
            view: 'button',
            template: template
        })
    }

    return data
}

function selectTemplate(id) {
    var arr = getSlidesTemplateArr();
    for (var i in arr) {
        if (arr[i].id != id) {
            var inp = $$("selectTemplate" + arr[i].id);
            if (inp) {
                webix.html.removeCss(inp.$view, "selected");
            }
        }

    }

    $$("selectTemplate" + id).define("css", "selected");
    console.log(id);
}