<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!-- ============= Custom Css ============= -->

<link rel="stylesheet" href="css/tuyen_dung.css">
<%--phan chung cho danh muc--%>
<script src="ajax/pages/page_tai_lieu.js"></script>

<div class="col-12 col-md-8 col-lg-9 ">
    <div id="list-tin-tuc">
        <table class="table table-striped table-bordered tlyk mt-5" >
            <thead class="tb__color-main">
            <tr class="text-center">
                <th scope="col">Stt</th>
                <th scope="col">Tên tài liệu</th>
                <th scope="col">Tải về</th>
            </tr>
            </thead>
            <tbody id="list-doc">
            <tr class="d-none" id="doc-element">
                <th scope="row" class="text-center index">1</th>
                <td class="tlyk-name text-left name"><a href="" target="_blank"><img src="icon/folder_page.png">Nghiên cứu
                    ứng dụng xương nhân tạo, máu tuỷ xương tự thân điều trị khớp giả xương dài chi dưới</a></td>
                <td class="tlyk-btdl link text-center"><a download class="text-center" href=""><img src="https://img.icons8.com/color/48/000000/pdf.png">Tải
                    về</a></td>
            </tr>
            </tbody>
        </table>


    </div>
</div>


