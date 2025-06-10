let listObject = [];
let editor;
let textSearch;

$(function () {
    editor = initQuill('#input-content');
    $('#choose-file').on('change', function () {
        previewImage(this, '#img-preview');
    });

    checkAliasEvent();

    loadCategories();

    pageObject(1, 15);

    addEvent();

    searchEvent();
});

// bổ sung trường category
async function loadList(page = 1, size = 15, category = 0) {
    let data;
    let uri = `v1/public/newses/filter?category=${category}&company=${COMPANY_ID}&page=${page}&size=${size}`;
    textSearch = $('#text-search').val() !== null ? $('#text-search').val() : "";

    if ($('#text-search').val() !== null && $('#text-search').val().length !== 0) {
        let namePara = `&name=${textSearch}`;
        uri = uri.concat(namePara);
    }
    data = await callGetAjax(NEWS_SERVICE, uri)
        .then(rs => {
            listObject = rs.data.content;
            return rs.data;
        }).catch(ex => {
            error("Tải dữ liệu lỗi")
        });

    return data;
}

function loadCategories(isFullCate = false) {
    let url = `v1/public/categories/company/${COMPANY_ID}`;
    let templateDanhMucOption = `<option value="0">Danh mục</option>`;
    let templateDanhMucOption1 = ``;

    callGetAjax(NEWS_SERVICE, url).then(rs => {
        if (rs.success) {
            rs = rs.data;

            templateDanhMucOption += rs.map(rs1 => `<option value="${rs1.id}">${rs1.name}</option>`).join('');
            if (isFullCate) {
                templateDanhMucOption1 += rs.map(rs1 => `<option value="${rs1.id}">${rs1.name}</option>`).join('');
            } else {
                templateDanhMucOption1 += rs.map(rs1 => {
                    if (rs1.id < 3 || rs1.id > 5) {
                        return `<option value="${rs1.id}">${rs1.name}</option>`;
                    }
                    return '';
                }).join('');
            }


            $('#select-category').html(templateDanhMucOption);

            $('#input-danh-muc').html(templateDanhMucOption1);

            $('#input-danh-muc').val('1');

        }
    }).catch(err => {
        error(`Hiển thị danh mục thất bại`);
    });

}

//hien thi len table + phan trang
function pageObject(page = 1, size = 15) {
    let container = $('#page');
    container.html('');
    let idCategory = parseInt($('#select-category').children('option:selected').val());
    loadList(page, size, idCategory).then(rs => {
        container.pagination({
            dataSource: 'https://api.flickr.com/services/feeds/photos_public.gne?tags=cat&tagmode=any&format=json&jsoncallback=?',
            locator: 'items',
            totalNumber: rs.totalPages,
            pageSize: 1,
            showPageNumbers: true,
            showPrevious: true,
            showNext: true,
            showGoInput: true,
            showGoButton: true,
            showNavigator: true,
            formatNavigator: `<span style="color: #000000"> đi đến trang`,
            showFirstOnEllipsisShow: true,
            showLastOnEllipsisShow: true,
            callback: function (response, pagination) {
                currentPage = pagination.pageNumber;
                loadList(pagination.pageNumber, size, idCategory).then(rs1 => {
                    fillTable(listObject);
                })
            }
        })
    }).catch(e => {
        error("Tải dữ liệu lỗi");
    });
}

//view date text vn

//tao template
function fillTable(list) {
    let content = '';
    list.forEach((object) => {
        console.log(object)
        content += ` <tr class="my-item" style="">
                                <td>${object.id}</td>
                                <td style="text-align: start;max-width: 350px">${object.name ? object.name : ''}</td>       
                                <td>${object.category ? object.category.name : ''}</td> 
                                <td>${object.tag ? object.tag.split('|').join(', ') : ''}</td>                                                
                                <td>${viewDateTextVn(object.creatTime, false)}</td>           
                                <td>${object.view}</td>                                
                                <td style="max-width: 6%">
                                    <div class="dropdown">
                                        <button id="menu-drop-1" type="button" class="btn btn-action dropdown-toggle" style="height: 30px"
                                                data-toggle="dropdown" type-btn="btn-dropdown" category-id="${object.category.id}">
                                            <i class="fas fa-cog"></i>
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="menu-drop-1" style="">
                                            <li>
                                                <button class="drop-1 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" index="${object.id}"  data-toggle="modal"
                                                        data-target="#myModal">
                                                        <i class="fas fa-pen"></i>&nbsp;&nbsp;Chỉnh sửa
                                                </button>
                                            </li>
                                             ${object.type === 2 ?
            `<li>
                                                <button class="drop-3 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" index="${object.id}" data-toggle="modal"
                                                        data-target="#files-modal">
                                                        <i class="fas fa-list"></i>&nbsp;&nbsp;Danh sách tài liệu
                                                </button>
                                            </li>
                                            <li>
                                                <button class="drop-4 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" index="${object.id}" data-toggle="modal"
                                                        data-target="#video-modal">
                                                        <i class="fas fa-file-video-o"></i>&nbsp;&nbsp;Video công trình
                                                </button>
                                            </li>` : ''}   
                                             <li>
                                                <button class="drop-2 btn btn-action font-weight-normal col-lg-12"
                                                        style="text-align: left" delete-id="${object.id}" category-id="${object.category.id}" data-toggle="modal"
                                                        data-target="#delete-modal" category-id="object.category.id">
                                                    <i class="fas fa-trash-alt"></i>&nbsp;&nbsp;Xóa
                                                </button>
                                            </li>                                                                                          
                                            
                                        </ul>
                                    </div>
                                </td>
                                
                                
                            </tr>`;
    });
    $('#row-ajax').html(content);
    changeEvent();
    deleteEvent();
    viewFileEvent();
    uploadVideoEvent();
}

function checkInput(checkFile = true) {
    let count = 4;

    if ($('#input-name').val().length === 0) {
        info("Vui lòng điền tên bài viết");
        count--;
    }

    if ($('#input-alias').val().length === 0) {
        info("Vui lòng điền đường dẫn thay thế");
        count--;
    }

    if ($('#input-danh-muc').val() === null) {
        info("Vui lòng chọn danh mục");
        count--;
    }

    if (checkFile && $('#choose-file').get(0).files.length === 0) {
        info("Vui lòng tải lên ảnh");
        count--;
    }

    return count === 4;
}

function addEvent() {

    $('#btn-add').off('click').on('click', function () {
        loadCategories();
        //clear content
        $('#input-name').val('');
        $('#input-author').val('');
        $('#input-tags').val('');
        $('#input-danh-muc').attr('disabled', false);
        $('#input-preview').val('');
        $('#choose-file').val('');
        $('#input-alias').val('');
        $('#img-preview').attr('src', 'resources/image/camera2.png');
        editor.root.innerHTML = '';

        $('#input-danh-muc').change(function () {

        });

        $('#submit').off('click').on('click', function () {

            let newsType = 4;

            if ($('#input-danh-muc').val() == 16) {
                newsType = 1;
            } else if ($('#input-danh-muc').val() == 14) {
                newsType = 2;
            } else if ($('#input-danh-muc').val() == 13) {
                newsType = 3;
            }

            if (checkInput(true)) {
                if ($('#input-alias').val().length > 0)
                    checkAlias($('#input-alias').val())
                        .then(rs => {
                            if (!rs) {
                                let formData = new FormData();
                                formData.append("files", $('#choose-file').get(0).files[0]);
                                callUploadFile(formData).then(rs => {
                                    let object = {
                                        author: $('#input-author').val(),
                                        content: autoAddAltImg(editor.root.innerHTML),
                                        name: $('#input-name').val(),
                                        alias: $('#input-alias').val(),
                                        preview: $('#input-preview').val(),
                                        tag: $('#input-tags').val(),
                                        type: newsType,
                                        image: rs.data[0].uri
                                    };
                                    callPostAjax(NEWS_SERVICE, `v1/admin/news?category-id=${parseInt($('#input-danh-muc').val())}`, object)
                                        .then((saved) => {
                                            //create alias
                                            let danhMucAlias = REMEDY_ALIAS;
                                            let chiTiet = REMEDY_ALIAS_PATH;

                                            if (newsType === 1) {
                                                danhMucAlias = RESEARCH_ALIAS;
                                                chiTiet = RESEARCH_ALIAS_PATH;
                                            } else if (newsType === 2) {
                                                danhMucAlias = RECRUITMENT_ALIAS;
                                                chiTiet = RECRUITMENT_ALIAS_PATH;
                                            } else if (newsType === 3) {
                                                danhMucAlias = INTRODUCTION_ALIAS;
                                                chiTiet = INTRODUCTION_ALIAS_PATH;
                                            }

                                            callPostAjax(IS_SERVICE, 'v1/admin/url-alias?danh-muc-alias-id=' + danhMucAlias, {
                                                alias: $('#input-alias').val(),
                                                url: `${chiTiet}?id=` + saved.data.id
                                            }).then().catch(ex => {
                                                error("Tạo alias lỗi!")
                                            });
                                            success("Tạo bài viết thành công");
                                            $('#close-add').trigger('click');
                                            pageObject(1, 15);
                                        }).catch(ex => error("Tạo mới bài viết thất bại"))
                                }).catch(ex => error("Tải hình ảnh thất bại"))
                            } else error("Đường dẫn đã được sử dụng")
                        });
            }
        })
    });

}

function changeEvent() {
    $('.drop-1').off('click').on('click', function () {
        loadCategories(true);
        let id = $(this).attr('index');
        callGetAjax(NEWS_SERVICE, `v1/public/newses/${id}`)
            .then(rs => {
                let payload = rs.data;
                $('#input-name').val(payload.name);
                $('#input-danh-muc').val(`${payload.category.id}`);
                $('#input-danh-muc').attr('disabled', true);
                $('#input-tags').val(payload.tag);
                $('#input-alias').val(payload.alias ? payload.alias : '');
                $('#input-preview').val(payload.preview ? payload.preview : '');
                $('#img-preview').attr('src', viewSourceFile(payload.image));
                editor.root.innerHTML = payload.content;
                $('#input-author').val(payload.author ? payload.author : '');

                $('#submit').off('click').on('click', function () {

                        if (!checkInput(false))
                            return;
                        payload.author = $('#input-author').val();
                        payload.content = autoAddAltImg(editor.root.innerHTML);
                        payload.name = $('#input-name').val();
                        payload.preview = $('#input-preview').val();
                        payload.tag = $('#input-tags').val();

                        let newsType = 4;

                        if ($('#input-danh-muc').val() == 16) {
                            newsType = 1;
                        } else if ($('#input-danh-muc').val() == 14) {
                            newsType = 2;
                        } else if ($('#input-danh-muc').val() == 13) {
                            newsType = 3;
                        }

                        if (payload.alias && payload.alias !== $('#input-alias').val()) {
                            checkAlias($('#input-alias').val())
                                .then(rs => {
                                    if (!rs) {
                                        callPutAjax(IS_SERVICE, 'v1/admin/url-alias', {
                                            alias: payload.alias,
                                            companyId: COMPANY_ID,
                                            newAlias: $('#input-alias').val()
                                        }).then().catch(ex => error("Thay đổi alias lỗi"));
                                        payload.alias = $('#input-alias').val();
                                        putNews();
                                    } else {
                                        error("Đường dẫn đã được sử dụng");
                                    }
                                }).catch(ex => {
                                console.log(ex)
                                error("Có lỗi xảy ra")
                            })

                        } else if (!payload.alias && $('#input-alias').val().length > 0) {
                            //create alias
                            checkAlias($('#input-alias').val())
                                .then(rs => {
                                    if (!rs) {
                                        //create alias
                                        let danhMucAlias = REMEDY_ALIAS;
                                        let chiTiet = REMEDY_ALIAS_PATH;

                                        if (newsType === 1) {
                                            danhMucAlias = RESEARCH_ALIAS;
                                            chiTiet = RESEARCH_ALIAS_PATH;
                                        } else if (newsType === 2) {
                                            danhMucAlias = RECRUITMENT_ALIAS;
                                            chiTiet = RECRUITMENT_ALIAS_PATH;
                                        } else if (newsType === 3) {
                                            danhMucAlias = INTRODUCTION_ALIAS;
                                            chiTiet = INTRODUCTION_ALIAS_PATH;
                                        }
                                        callPostAjax(IS_SERVICE, 'v1/admin/url-alias?danh-muc-alias-id=' + danhMucAlias, {
                                            alias: $('#input-alias').val(),
                                            url: `${chiTiet}?id=` + payload.id
                                        }).then(rs => {
                                            payload.alias = $('#input-alias').val();
                                            putNews();
                                        }).catch(ex => {
                                            error("Tạo alias lỗi!")
                                        });

                                    } else {
                                        error("Đường dẫn đã được sử dụng");
                                    }
                                }).catch(ex => {
                                console.log(ex)
                                error("Có lỗi xảy ra")
                            })

                        } else putNews();

                        function putNews() {
                            if ($('#choose-file').get(0).files.length > 0) {
                                let formData = new FormData();
                                formData.append("files", $('#choose-file').get(0).files[0]);
                                callUploadFile(formData).then(rs => {
                                    payload.image = rs.data[0].uri;
                                    ajaxUpdateProduct(payload);
                                }).catch(ex => error("Tải hình ảnh thất bại"))
                            } else ajaxUpdateProduct(payload);

                        }
                    }
                )
            }).catch()
    });
}

// handle time view and post

function formatTimeChangeEvent(stringData) {
    let now = new Date(stringData);
    let day = ("0" + now.getDate()).slice(-2);
    let month = ("0" + (now.getMonth() + 1)).slice(-2);
    let today = now.getFullYear() + "-" + (month) + "-" + (day);

    let hoursData = now.getUTCHours() < 10 ? '0' + now.getUTCHours() : now.getUTCHours();
    let minutesData = now.getUTCMinutes() < 10 ? '0' + now.getUTCMinutes() : now.getUTCMinutes();
    let timeData = `${hoursData}:${minutesData}`;
    return {
        today: today,
        time: timeData
    }
}

//thời gian đẩy lên db sẽ lệch 7 tiếng

function viewStartTime(ngay, gio) {
    let timeData = `${ngay}T${gio}:00.000Z`;
    let timeDate = new Date(timeData);
    return `${ngay}T${timeDate.getUTCHours() < 10 ? '0' + timeDate.getUTCHours() : timeDate.getUTCHours()}:${timeDate.getUTCMinutes() < 10 ? '0' + timeDate.getUTCMinutes() : timeDate.getUTCMinutes()}`;

}

function fomartStartTime(ngay, gio) {
    let timeData = `${ngay}T${gio}:00.000Z`;
    let timeDate = new Date(timeData);
    return timeDate.toISOString();
}

function viewDateTextVn(text, isStartTime) {
    if (text) {
        let date = new Date(text);
        let hours = date.getUTCHours();
        let minutes = date.getUTCMinutes();

        if (isStartTime) {
            return `${date.getUTCDay() != 0 ? `Thứ ${date.getUTCDay() + 1}` : "Chủ nhật"}, ${date.getUTCDate()}/${date.getUTCMonth() + 1}/${date.getFullYear()} - ${hours}:${minutes}`;
        } else {
            return `${date.getUTCDay() != 0 ? `Thứ ${date.getUTCDay() + 1}` : "Chủ nhật"}, ${date.getUTCDate()}/${date.getUTCMonth() + 1}/${date.getUTCFullYear()}`;
        }
    } else {
        return '';
    }

};

// end handle time view and post

function viewFileEvent() {
    $('.drop-3').off('click').on('click', function () {
        let id = $(this).attr("index");

        function loadFiles() {
            callGetAjax(NEWS_SERVICE, `v1/public/attachments/news/${id}`)
                .then(rs => {
                    let listFiles = rs.data;
                    $('#row-file').html(listFiles.map((file, index) => `<tr>
                                        <td>${index + 1}</td>
                                        <td><a href="${viewSourceFile(file.url)}">${file.url.split('/')[2]}</a></td>
                                        <td><button index="${file.id}" class="btn btn-danger btn-delete-file">Xóa</button></td>
                                    </tr>`));
                    $('.btn-delete-file').off('click').on('click', function () {
                        callDeleteAjax(PRODUCT_SERVICE, 'v1/admin/files?ids=' + $(this).attr("index"))
                            .then(rs => {
                                success("Đã xóa file");
                                loadFiles();
                            }).catch(ex => {
                            error("Xóa file thất bại");
                        })
                    })
                }).catch(ex => error("Tải dữ liệu tài liệu thất bại"))
        }

        loadFiles();

        $('#choose-file-doc').off('change').on('change', function () {
            let form = new FormData($(this).parents('form')[0]);
            callUploadFile(form)
                .then(rs => {
                    let files = rs.data.map(file => {
                        return {
                            url: file.uri,
                            name: '',
                            news: null
                        }
                    });
                    callPostAjax(NEWS_SERVICE, 'v1/admin/attachment?news-id=' + id, files)
                        .then(rs => {
                            loadFiles();
                            success("Tải lên tài liệu thành công")
                        }).catch(ex => {
                        error("Tải lên tài liệu thất bại");
                        console.log(ex);
                    })
                }).catch(ex => {
                console.log(ex);
                error("Tải tài liệu thất bại")
            })
        })
    })
}

function uploadVideoEvent() {
    $('.drop-4').off('click').on('click', function () {
        let id = $(this).attr("index");
        callGetAjax(NEWS_SERVICE, 'v1/public/newses/' + id)
            .then(rs => {
                let data = rs.data;

                $('#enable-video').prop('checked', data.enableVideo);
                if (data.video) {
                    if (data.video.startsWith("http")) {
                        $('#input-youtube').val(data.video)
                        $('#div-video').html(`<iframe style="max-width: 100%" src="${data.video}"></iframe>`)

                    } else {
                        $('#div-video').html(`<video id="lesson-video-preview" style="max-width: 100%" controls="controls" preload="metadata">
                                        <source src="${viewSourceFile(data.video)}" type="video/mp4">
                                       </video>`)
                    }
                } else $('#div-video').html('<h3>Chưa có video</h3>');

                $('#input-youtube').off('change').on('change', function () {
                    $('#div-video').html(`<iframe style="max-width: 100%" src="${$(this).val()}"></iframe>`)
                })

                $('#choose-file-video').off('change').on('change', function () {
                    //upload video
                    $('#img-choose-video').attr('src', 'resources/image/uploading.gif')
                    $('#div-video').html('');
                    let form = new FormData;
                    form.append("files", $('#choose-file-video').get(0).files[0]);
                    callUploadFile(form)
                        .then(rs => {
                            $('#img-choose-video').attr('src', 'resources/image/camera2.png')
                            data.video = rs.data[0].uri
                            let video = `<video id="lesson-video-preview" style="max-width: 100%" controls="controls" preload="metadata">
                                        <source src="${viewSourceFile(rs.data[0].uri)}" type="video/mp4">
                                       </video>`
                            $('#div-video').html(video);
                            $('#input-youtube').val('');
                        }).catch(ex => {
                        console.log(ex)
                        error("Tải video thất bại")
                    })
                })
                $('#submit-video').off('click').on('click', function () {
                    data.enableVideo = $('#enable-video').is(":checked");
                    if (!data.video && !checkInputVideo())
                        return;
                    if ($('#input-youtube').val().length > 0)
                        data.video = $('#input-youtube').val();
                    callPutAjax(NEWS_SERVICE, 'v1/admin/news', data)
                        .then(rs => {
                            success("Cập nhật thành công")
                            $('#video-modal').modal('hide');
                        }).catch(e => error("Cập nhật thất bại"))
                })
            })
    })
}

function checkInputVideo(checkFile) {
    let count = 1;
    if ($('#input-youtube').val().length === 0) {
        if (checkFile && $('#choose-file-video').get(0).files.length === 0) {
            info("Vui lòng tải lên video hoặc điền link Youtube")
            count--;
        }
    }
    return count === 1;
}

function ajaxUpdateProduct(payload) {
    callPutAjax(NEWS_SERVICE, 'v1/admin/news', payload)
        .then(saved => {
            success("Chỉnh sửa bài viết thành công");
            $('#close-add').trigger('click');
            pageObject();
        }).catch(ex => {
        error("Chỉnh sửa bài viết thất bại")
    });
}

function deleteEvent() {
    $('button[type-btn="btn-dropdown"]').off('click').click(function () {
        let idCate = parseInt($(this).attr('category-id'));
        if (idCate > 2 && idCate < 6) {
            $('.drop-2').off('click').hide();
        } else {
            $('.drop-2').off('click').show().on('click', function () {
                let id = $(this).attr("delete-id");
                $('#submit-delete').off('click').on('click', function () {
                    callDeleteAjax(NEWS_SERVICE, `v1/admin/news/${id}`)
                        .then(rs => {
                            pageObject();
                            $('#close-delete').trigger('click');
                            success("Xóa bài viết thành công");
                        }).catch(ex => {
                        error("Xóa bài viết thất bại")
                    });
                });
                $('.drop-2').off('click').on('click', function () {
                    let id = $(this).attr("delete-id");
                    let news = listObject.filter(p => p.id == id)[0];
                    $('#submit-delete').off('click').on('click', function () {
                        callDeleteAjax(NEWS_SERVICE, `v1/admin/news/${id}`)
                            .then(rs => {
                                pageObject();
                                $('#close-delete').trigger('click');
                                success("Xóa bài viết thành công");
                                if (news.alias)
                                    callDeleteAjax(IS_SERVICE, `v1/admin/url-alias/alias?alias=${news.alias}&company-id=${COMPANY_ID}`)
                                        .then(rs => {
                                        }).catch(e => {
                                    })
                            }).catch(ex => {
                            error("Xóa bài viết thất bại")
                        });
                    })
                });

            })
        }
    })
}

function searchEvent() {
    $('#btn-search').on('click', function () {
        pageObject();

    });
}








