<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 25-Aug-20
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resources/js/ajax/user/profile.js"></script>
<main class="app-content">

    <div class="row" style="text-align: left">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <div class="card">
                <div class="card-body">
                    <div class="row" style="text-align: center">
                        <div class="col-md-12">
                            <h4>Tài khoản của bạn</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <form>
                                <div class="form-group row">
                                    <label id="label-full-name" class="col-4 col-form-label">Họ và
                                        tên<span style="color: red"></span></label>
                                    <div class="col-8">
                                        <input id="input-name"  placeholder="Họ và tên"
                                               class="form-control here" required="required" type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label  class="col-4 col-form-label">Email</label>
                                    <div class="col-8">
                                        <input id="input-email" name="email" placeholder="Email" class="form-control here"
                                               disabled="disabled" type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label  class="col-4 col-form-label">Địa chỉ<span
                                            style="color: red"></span></label>
                                    <div class="col-8">
                                        <input id="input-address" name="address" placeholder="Địa chỉ"
                                               class="form-control here" type="text">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-4 col-form-label">Số điện thoại<span
                                            style="color: red"></span></label>
                                    <div class="col-8">
                                        <input id="input-phone" name="phone" placeholder="Số điện thoại"
                                               class="form-control here" required="required" type="number">
                                    </div>
                                </div>


                            </form>
                        </div>
                    </div>

                    <div class="row" style="text-align: center">
                        <div class="col-md-12">
                            <button id="submit" type="button" class="btn btn-primary">Cập nhật</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="col-sm-2"></div>
    </div>
</main>
