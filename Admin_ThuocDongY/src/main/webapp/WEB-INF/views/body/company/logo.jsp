<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="resources/js/ajax/company/logo.js"></script>
<main class="app-content">
    <div class="card">
        <div class="card-header" style="display: flex; justify-content: space-around">
            <h5 class="colg-lg-4">Chỉnh sửa logo</h5>
            </div>
        </div>
        <div class="col-lg-12 card-body" style="background: white">
            <div style="display: contents">
                <div class="form-group col-lg-12" style="text-align: center">
                    <label class="col-form-label">Tải lên logo (định dạng png)<span
                            style="color: red"></span></label>
                    <div class="input-group" style="display: none">
                        <div class="custom-file">
                            <form method="post" enctype="multipart/form-data"
                                  id="form-upload-image">
                                <input type="file" accept="image/png" class="custom-file-input"
                                       id="choose-file">
                            </form>
                        </div>
                    </div>
                    <div  class="row"
                         style="justify-content: center; margin-top: 5%">
                        <img id="img-preview" src="resources/image/camera2.png"
                             style="width: 200px" alt="Hình ảnh"
                             onclick="$('#choose-file').trigger('click')">
                    </div>
                </div>

                <div class="row" style=" padding-top: 10px; justify-content: center">
                    <div class="col-lg-3" style=" display: flex; justify-content: center">
                        <button id="submit" type="button" class="btn btn-success">&nbsp;Lưu lại</button>
                    </div>
                </div>
            </div>
        </div>
</main>

<style>
    #img-preview:hover {
        cursor: pointer;
    }
</style>

