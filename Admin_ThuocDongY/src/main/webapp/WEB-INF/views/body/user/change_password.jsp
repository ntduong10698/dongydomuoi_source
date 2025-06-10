<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 25-Aug-20
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/user/change_password.js"></script>

<main class="app-content">
    <div class="app-title">
        <div>
            <h1><i class="fa fa-th-list"></i> Thay đổi mật khẩu</h1>
        </div>

    </div>

    <div class="row" style="text-align: left">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <div class="card">
                <div class="card-body">

                    <div class="row">
                        <div class="col-md-12">
                            <form>
                                <div class="form-group row">
                                    <label  class="col-4 col-form-label">Mật khẩu hiện tại</label>
                                    <div class="col-8">
                                        <input id="old-pass" name="newpass"
                                               class="form-control here" type="password">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-4 col-form-label">Mật khẩu mới</label>
                                    <div class="col-8">
                                        <input id="new-pass" name="newpass" placeholder="Tối thiểu 6 ký tự"
                                               class="form-control here" type="password">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label  id="label-password" class="col-4 col-form-label">Xác nhận mật khẩu mới</label>
                                    <div class="col-8">
                                        <input id="confirm-pass" name="newpass" placeholder="Tối thiểu 6 ký tự"
                                               class="form-control here" type="password">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="row" style="text-align: center">
                        <div class="col-md-12" style="display: flex; justify-content: center; width: 100%">
                            <button id="submit" type="button" style="display: inline-block" class="btn btn-primary">Lưu lại</button>
                            <button id="mayBack" style="margin-left: 20px; display: inline-block" type="button" class="btn btn-primary">Quay lại</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="col-sm-2"></div>
    </div>
</main>
