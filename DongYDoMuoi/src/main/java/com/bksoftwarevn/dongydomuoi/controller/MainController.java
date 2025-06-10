package com.bksoftwarevn.dongydomuoi.controller;

import com.bksoftwarevn.dongydomuoi.json.RestBuilder;
import com.bksoftwarevn.dongydomuoi.json.UrlAlias;
import com.bksoftwarevn.dongydomuoi.service_impl.RestService;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    @Value("${urlCDNConfig}")
    private String urlCDNConfig;

    @Value("${urlCDN}")
    private String urlCDN;

    @Value("${inforSystemService}")
    private String inforSystemService;

    @Value("${newsService}")
    private String newsService;

    @Value("${productService}")
    private String productService;

    @Value("${companyId}")
    private String companyId;

    @Autowired
    private RestService restService;

    @GetMapping(value = {"/404"})
    public String page404() {
        return "404";
    }

    @GetMapping(value = {"/", "/trang-chu"})
    public String trangChu(HttpServletRequest request) {
        try {
            JSONObject jsonObject = restService.callGetJson(RestBuilder.build()
                    .service(inforSystemService)
                    .uri("api/v1/public/companies/" + companyId));
            String valImage = jsonObject.get("logo").toString();
            String valTitle = jsonObject.get("nameCompany").toString();
            String valDescription = jsonObject.get("description").toString();
            request.setAttribute("image", viewSrcFile(valImage));
            request.setAttribute("title", valTitle);
            request.setAttribute("description", valDescription);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "trang-chu";
    }

    @GetMapping(value = {"/tuyen-dung"})
    public String tuyenDung(HttpServletRequest request) {
        request.setAttribute("title", "Tuyển dụng");
        return "tuyen-dung";
    }

    @GetMapping(value = {"/chi-tiet-tuyen-dung"})
    public String chiTietTuyenDung(HttpServletRequest request) {
        try {
            JSONObject jsonObject = restService.callGetJson(RestBuilder.build()
                    .service(newsService)
                    .uri("api/v1/public/newses/" + request.getParameter("id")));
            String valImage = jsonObject.get("image").toString();
            String valTitle = jsonObject.get("name").toString();
            String valDescription = jsonObject.get("preview").toString();
            request.setAttribute("image", viewSrcFile(valImage));
            request.setAttribute("title", valTitle);
            request.setAttribute("description", valDescription);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "chi-tiet-tuyen-dung";
    }
// gioi thieu
    @GetMapping(value = {"/gioi-thieu"})
    public String gioiThieu(HttpServletRequest request) {
        request.setAttribute("title", "Giới thiệu");
        return "gioi-thieu";
    }

    @GetMapping(value = {"/chi-tiet-gioi-thieu"})
    public String chiTietGioiThieu(HttpServletRequest request) {
        try {
            JSONObject jsonObject = restService.callGetJson(RestBuilder.build()
                    .service(newsService)
                    .uri("api/v1/public/newses/" + request.getParameter("id")));
            String valImage = jsonObject.get("image").toString();
            String valTitle = jsonObject.get("name").toString();
            String valDescription = jsonObject.get("preview").toString();
            request.setAttribute("image", viewSrcFile(valImage));
            request.setAttribute("title", valTitle);
            request.setAttribute("description", valDescription);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "chi-tiet-gioi-thieu";
    }
    //end gioi thieu
    //bai thuoc
    @GetMapping(value = {"/bai-thuoc"})
    public String baiThuoc(HttpServletRequest request) {
        request.setAttribute("title", "Bài thuốc");
        return "bai-thuoc";
    }

    @GetMapping(value = {"/bai-thuoc-danh-muc"})
    public String baiThuocDanhMuc(HttpServletRequest request) {
        request.setAttribute("title", "Bài thuốc");
        return "bai-thuoc-danh-muc";
    }

    @GetMapping(value = {"/chi-tiet-bai-thuoc"})
    public String chiTietBaiThuoc(HttpServletRequest request) {
        try {
            JSONObject jsonObject = restService.callGetJson(RestBuilder.build()
                    .service(newsService)
                    .uri("api/v1/public/newses/" + request.getParameter("id")));
            String valImage = jsonObject.get("image").toString();
            String valTitle = jsonObject.get("name").toString();
            String valDescription = jsonObject.get("preview").toString();
            request.setAttribute("image", viewSrcFile(valImage));
            request.setAttribute("title", valTitle);
            request.setAttribute("description", valDescription);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "chi-tiet-bai-thuoc";
    }
    //end bai thuoc
    //    nghien cuu
    @GetMapping(value = {"/nghien-cuu"})
    public String nghienCuu(HttpServletRequest request) {
        request.setAttribute("title", "Nghiên cứu");
        return "nghien-cuu";
    }

    @GetMapping(value = {"/chi-tiet-nghien-cuu"})
    public String chiTietNghienCuu(HttpServletRequest request) {
        try {
            JSONObject jsonObject = restService.callGetJson(RestBuilder.build()
                    .service(newsService)
                    .uri("api/v1/public/newses/" + request.getParameter("id")));
            String valImage = jsonObject.get("image").toString();
            String valTitle = jsonObject.get("name").toString();
            String valDescription = jsonObject.get("preview").toString();
            request.setAttribute("image", viewSrcFile(valImage));
            request.setAttribute("title", valTitle);
            request.setAttribute("description", valDescription);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "chi-tiet-nghien-cuu";
    }
    //end nghien cuu
    @GetMapping(value = {"/lien-he"})
    public String lienHe(HttpServletRequest request) {
        request.setAttribute("title", "Liên hệ");
        return "lien-he";
    }

    @GetMapping(value = {"/dat-lich-kham"})
    public String datLichKham(HttpServletRequest request) {
        request.setAttribute("title", "Đặt lịch khám");
        return "dat-lich-kham";
    }

    @GetMapping(value = {"/danh-muc"})
    public String danhMuc(HttpServletRequest request) {
        request.setAttribute("title", "Danh mục");
        try {
            int root = Integer.parseInt(request.getParameter("root"));
            JSONObject jsonObject = restService.callGetJson(RestBuilder.build()
                    .service(productService)
                    .uri("api/v1/public/".concat(root == 1 ? "product-types/" : "categorys/")+ request.getParameter("id")));
            if(jsonObject != null) {
                String valImage = jsonObject.get("image").toString();
                String valTitle = jsonObject.get("name").toString();
                String valDescription = jsonObject.get("description").toString();
                request.setAttribute("image", viewSrcFile(valImage));
                request.setAttribute("title", valTitle);
                request.setAttribute("description", valDescription);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "danh-muc";
    }

    @GetMapping(value = {"/san-pham"})
    public String sanPham(HttpServletRequest request) {
        request.setAttribute("title", "Sản phẩm");
        return "san-pham";
    }

    @GetMapping(value = {"/chi-tiet-san-pham"})
    public String chiTietSanPham(HttpServletRequest request) {
        request.setAttribute("title", "Sản phẩm");
        try {
            JSONObject jsonObject = restService.callGetJson(RestBuilder.build()
                    .service(productService)
                    .uri("api/v1/public/products/" + request.getParameter("id"))
                    .param("cost", "false")
                    .param("property", "false")
                    .param("file", "false")
                    .param("category", "false")
                    .param("promotion", "false")
                    .param("statistic", "false"));
            jsonObject = (JSONObject) jsonObject.get("product");
            String valImage = jsonObject.get("image").toString();
            String valTitle = jsonObject.get("name").toString();
            String valDescription = jsonObject.get("preview").toString();
            request.setAttribute("image", viewSrcFile(valImage));
            request.setAttribute("title", valTitle);
            request.setAttribute("description", valDescription);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "chi-tiet-san-pham";
    }

    @GetMapping(value = {"/tai-lieu-y-khoa"})
    public String taiLieuYKhoa(HttpServletRequest request) {
        request.setAttribute("title", "Tài liệu y khoa");
        return "tai-lieu-y-khoa";
    }

    @GetMapping(value = {"/gio-hang"})
    public String gioHang(HttpServletRequest request) {
        request.setAttribute("title", "Giỏ Hàng");
        return "gio-hang";
    }
    @GetMapping(value = {"/thanh-toan"})
    public String thanhToan(HttpServletRequest request) {
        request.setAttribute("title", "Thanh Toán");
        return "thanh-toan";
    }

    @GetMapping(value = {"/robots.txt"})
    private void robot(HttpServletResponse response) throws IOException {
        try {
            response.getWriter().println("# we use BKSoftwarevn as our ecommerce platform");
            response.getWriter().println();
            response.getWriter().println(restService.callGetFileConfig("robot.txt"));
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("404");
        }
    }

    @GetMapping(value = {"/sitemap*.xml"})
    private void sitemap(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String fileName = request.getServletPath();
            if (fileName.indexOf("sitemap_page.xml") > -1) {
                response.sendRedirect("trang-chu");
            } else {
                response.setContentType("application/xml");
                response.getWriter().println(restService.callGetFileConfig(fileName));
            }
        } catch (Exception ex) {
            response.sendRedirect("404");
        }
    }

    @GetMapping(value = {"/*"})
    public void aliasPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        String uriAlias = "404";
        ModelMapper modelMapper = new ModelMapper();
        try {
            Object o = restService.callGet(RestBuilder.build()
                    .service("infor-system-service")
                    .uri("api/v1/public/url-alias/alias/" + servletPath + "/company-id/3"));
            if (o != null) {
                List<UrlAlias> urlAliasList = Arrays.asList(modelMapper.map(o, UrlAlias[].class));
                if (urlAliasList != null && urlAliasList.size() > 0) {
                    UrlAlias urlAlias = urlAliasList.get(0);
                    uriAlias = urlAlias.getUrl();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(uriAlias);
        requestDispatcher.forward(request, response);
    }

    public String viewSrcFile(String url) {
        return url.indexOf("http") == 0 ? url : urlCDN + url;
    }

}
