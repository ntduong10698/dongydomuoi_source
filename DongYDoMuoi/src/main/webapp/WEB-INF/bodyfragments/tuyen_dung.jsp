<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!-- ============= Custom Css ============= -->

<link rel="stylesheet" href="css/tuyen_dung.css">


<%--phan chung cho danh muc--%>
<script src="ajax/object/article.js"></script>
<script src="ajax/pages/page_article.js"></script>
<%--<script src="ajax/pages/page_article_details.js"></script>--%>
<script src="ajax/pages/page_tuyen_dung.js"></script>
<%--<script src="js/lienhe.js"></script>--%>

<div class="col-12 col-md-8 col-lg-9 " >
    <div id="list-tin-tuc">
        <!-- ===hidden tintuc element=== -->
        <a href="#" class="tinTuc__element article__element row d-none  mt-3 mt-md-5" id="tinTuc__element">
            <div class="col-12 col-md-5">
                <div class="tinTuc__element--img article__element--img">
                    <img src="https://thietbidienpanasonic.com/wp-content/uploads/2018/08/bep-dien-tu-panasonic-chat-luong-300x163.jpg"
                         >
                    <div
                            class="tinTuc__element--time article__element d-flex flex-column justify-content-center align-items-center">
                        <div class="tinTuc__element--date article__element--date">28</div>
                        <div class="tinTuc__element--month article__element--date">Th8</div>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-7">
                <div class="tinTuc__element--info">
                    <h4 class="tinTuc__element--name article__element--name text-center text-md-left pt-3 pt-md-0">Đại lý cung
                        cấp bếp điện
                        từ Panasonic tp.HCM</h4>
                    <p class="tinTuc__element--description article__element--description">Hiện nay, với nhu cầu lắp đặt, sử dụng bếp
                        điện từ Panasonic ngày càng
                        nhiều, đặc biệt ở các thành phố lớn như Hà Nội, tp.HCM. Dòng sản phẩm bếp điện
                        từ Panasonic có nhiều ưu điểm nổi bật; chất lượng tốt, thiết kế tinh tế, độ bền
                        cao, giá thành phải chăng..</p>
<%--                    <p class="tinTuc__element--commentCount  text-uppercase">4 comments</p>--%>
                    <div class="tinTuc__element--showMoreBtn article__element--showMoreBtn d-flex d-md-none d-lg-flex">
                        <button class="btn-showMore">Chi Tiết</button>
                    </div>
                </div>
            </div>
        </a>
        <!-- ===end hidden tintuc element=== -->
    </div>
    <div class="tinTuc__btnShowMoreNews row justify-content-center mt-3 mt-md-5">
        <div class="col-12 text-center" >
            <button class="btn btn-primary d-none" id="btn-showMoreNews">Xem Thêm</button>
        </div>
    </div>




</div>



