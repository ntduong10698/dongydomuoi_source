<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="resources/js/ajax/company/robot.js"></script>
<main class="app-content">
    <div class="card">
        <div class="card-header" style="display: flex; justify-content: space-around">
            <h5 class="colg-lg-4">Chỉnh sửa robot.txt</h5>
        </div>
    </div>
    <div class="col-lg-12 card-body" style="background: white">
        <div style="display: contents">
            <div class="form-group col-lg-12" style="text-align: center">
               <textarea class="form-control" rows="10" id="input-robot">

               </textarea>
            </div>

            <div class="row" style=" padding-top: 10px; justify-content: center">
                <div class="col-lg-4" style=" display: flex; justify-content: space-around">
                    <button id="submit" type="button" class="btn btn-success">&nbsp;Lưu lại</button>
                    <button id="update-sitemap" class="btn btn-primary">Cập nhật sitemap</button>
                </div>
            </div>
        </div>
    </div>
</main>


