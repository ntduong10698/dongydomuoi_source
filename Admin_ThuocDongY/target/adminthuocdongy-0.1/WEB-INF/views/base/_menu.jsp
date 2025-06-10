<%--
  Created by IntelliJ IDEA.
  User: hieuv
  Date: 07-Dec-19
  Time: 9:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="resources/js/menu.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/menu.css">
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
    <ul class="app-menu">
        <li class="treeview single" id="c1"><a class="app-menu__item" href="admin-don-hang">
            <i class="app-menu__icon fas fa-shopping-cart"></i><span class="app-menu__label">Danh sách đơn hàng<span
                id="new-cart" style="color: yellow"></span></span></a>
        </li>

        <li class="treeview single" id="c10"><a class="app-menu__item" href="admin-lien-he">
            <i class="app-menu__icon fas fa-user user-menu"></i><span class="app-menu__label">Yêu cầu liên hệ<span
                id="new-contact" style="color: yellow"></span></span></a>
        </li>

        <li class="treeview single" id="c20"><a class="app-menu__item" href="admin-lich-kham">
            <i class="app-menu__icon fas fa-calendar appointment-menu"></i>
            <span class="app-menu__label">Lịch khám được đặt<span
                    id="new-appointment" style="color: yellow"></span></span>
        </a>
        </li>


        <li class="treeview" id="c2"><a class="app-menu__item" href="#" data-toggle="treeview"><i
                class="app-menu__icon fas fa-sitemap"></i><span class="app-menu__label">Quản lý sản phẩm</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li>
                    <a class="treeview-item" href="admin-san-pham"><i
                            class="app-menu__icon "></i>Danh sách sản phẩm</a>
                </li>
                <li>
                    <a class="treeview-item" href="admin-loai-san-pham"><i
                            class="app-menu__icon "></i>Loại sản phẩm</a>
                </li>
                <li>
                    <a class="treeview-item" href="admin-danh-muc"><i
                            class="app-menu__icon "></i>Danh mục sản phẩm</a>
                </li>
                <li>
                    <a class="treeview-item" href="admin-thuoc-tinh"><i
                            class="app-menu__icon "></i> Thông số sản phẩm</a>
                </li>
                <li>
                    <a class="treeview-item" href="admin-thuong-hieu"><i
                            class="app-menu__icon "></i> Danh sách thương hiệu</a>
                </li>
            </ul>
        </li>

        <li class="treeview" id="c5"><a class="app-menu__item" href="#" data-toggle="treeview"><i
                class="app-menu__icon fas fa-dollar"></i><span class="app-menu__label">Quản lý marketing</span><i
                class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li>
                    <a class="treeview-item" href="admin-chuong-trinh-khuyen-mai"><i
                            class="app-menu__icon"></i> Chương trình khuyến mãi</a>
                </li>
                <li>
                    <a class="treeview-item" href="admin-coupon"><i
                            class="app-menu__icon"></i> Mã coupon</a>
                </li>
                <li>
                    <a class="treeview-item" href="admin-popup"><i
                            class="app-menu__icon"></i> Popup trang chủ</a>
                </li>
                <li>
                    <a class="treeview-item" href="admin-robot"><i
                            class="app-menu__icon"></i> Robot.txt</a>
                </li>
            </ul>
        </li>

        <li class="treeview" id="c7"><a class="app-menu__item" href="#" data-toggle="treeview">
            <i class="app-menu__icon fas fa-pen-alt"></i><span class="app-menu__label">Quản lý bài viết</span>
            <i class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li>
                    <a class="treeview-item" href="admin-bai-viet"><i
                            class="app-menu__icon"></i> Danh sách bài viết</a>
                </li>
                <li>
                    <a class="treeview-item" href="admin-danh-muc-bai-viet"><i
                            class="app-menu__icon"></i> Danh mục bài viết</a>
                </li>

            </ul>
        </li>

        <li class="treeview single" id="c12"><a class="app-menu__item" href="admin-bac-si">
            <i class="app-menu__icon fas fa-plus-circle"></i><span class="app-menu__label">Đội ngũ bác sĩ</span></a>
        </li>

        <li class="treeview single" id="c15"><a class="app-menu__item" href="admin-khach-hang">
            <i class="app-menu__icon fas fa-check"></i><span class="app-menu__label">Khách hàng đánh giá</span></a>
        </li>

        <li class="treeview single" id="c18"><a class="app-menu__item" href="admin-tai-lieu">
            <i class="app-menu__icon fas fa-file-pdf-o"></i><span class="app-menu__label">Tài liệu chuyên khoa</span></a>
        </li>

        <li class="treeview single" id="c8"><a class="app-menu__item" href="admin-doanh-nghiep">
            <i class="app-menu__icon fas fa-building"></i><span class="app-menu__label">Quản lý thông tin</span></a>
        </li>

        <li class="treeview" id="c9"><a class="app-menu__item" href="#" data-toggle="treeview">
            <i class="app-menu__icon fas fa-image"></i><span class="app-menu__label">Quản lý giao diện</span>
            <i class="treeview-indicator fa fa-angle-right"></i></a>
            <ul class="treeview-menu">
                <li>
                    <a class="treeview-item" href="admin-logo"><i
                            class="app-menu__icon"></i> Thay đổi logo</a>
                </li>
                <li>
                    <a class="treeview-item" href="admin-giao-dien"><i
                            class="app-menu__icon"></i> Nội dung website</a>
                </li>
            </ul>
        </li>


    </ul>
</aside>
