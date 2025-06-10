<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="d-flex align-items-center">
    <div class="container ">
        <div class="row">
            <div class="col-12 col-md-8 d-flex align-items-center">
                <a href="trang-chu" class="logo-area">
                    <div class="logo-a__img">
                        <img src="https://cdn.bksoftwarevn.com/resources/micro-upload/dong-y/logo/logo-dong-y.png" alt="Đông Y Đỗ Mười">
                    </div>
                    <div class="logo-a__text">
                        <span class="text-name-company"></span>
                        <p><i class="text-solgan"></i></p>
                    </div>
                </a>
            </div>
            <div class="col-12 col-md-4 d-none d-md-block">
                <div class="header-contact">
                    <a href="" class="email link-email-company" target="_blank">
                        <img src="icon/message-squared-w.png" alt="Mail">
                    </a>
                    <a href="" class="facebook link-facebook-company" target="_blank">
                        <img src="icon/facebook.png" alt="Facebook">
                    </a>
                    <a href="" class="youtube link-youtube-company" target="_blank">
                        <img src="icon/youtube-squared.png" alt="Youtube">
                    </a>
                    <a href="" class="zalo link-zalo-company" target="_blank">
                        <img src="icon/zalo-w.png" alt="Zalo">
                    </a>
                </div>
            </div>
        </div>
    </div>
</header>
<nav>
    <div class="container">
        <div class="row">
            <div class="col-12 ">
                <div class="nav-wrap d-flex justify-content-between align-items-center">
                    <div class="shadow"><i class="fas fa-times"></i></div>
                    <i class="fas fa-bars d-lg-none bars-open"></i>
                    <ul class="nav" id="nav-main">
                        <li><a href="trang-chu" class="item-logo d-lg-none">
                            <div class="item-logo__img">
                                <img src="https://cdn.bksoftwarevn.com/resources/micro-upload/dong-y/logo/logo-dong-y.png" alt="Đông Y Đỗ Mười">
                            </div>
                            <div class="item-logo__text">
                                <span class="text-name-company"></span>
                                <i class="text-solgan"></i>
                            </div>
                        </a>
                        </li>
                        <li class="active" data-active="trang-chu"><a href="trang-chu">Trang chủ</a></li>
                        <li data-active="gioi-thieu"><a href="gioi-thieu">Giới thiệu</a></li>
                        <li data-active="bai-thuoc"><a href="bai-thuoc">Bài thuốc</a></li>
                        <li data-active="san-pham"><a href="san-pham">Sản phẩm</a></li>
                        <li data-active="nghien-cuu"><a href="nghien-cuu">Nghiên cứu</a></li>
                        <li data-active="tai-lieu-y-khoa"><a href="tai-lieu-y-khoa">Tài liệu y khoa</a></li>
                        <li data-active="tuyen-dung"><a href="tuyen-dung">Tuyển dụng</a></li>
                        <li data-active="lien-he"><a href="lien-he">Liên hệ</a></li>
                    </ul>
                    <div class="search-icon" title="Tìm kiếm">
                        <i class="fas fa-search search-click"></i>
                        <div class="input-search">
                            <select id="cars">
                                <option value="danh-muc?id=0" selected="selected">Tất cả</option>
                            </select>
                            <input type="text" placeholder="Tìm kiếm sản phẩm" id="text-search">
                            <i class="fas fa-search" onclick="searchProduct()"></i>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</nav>