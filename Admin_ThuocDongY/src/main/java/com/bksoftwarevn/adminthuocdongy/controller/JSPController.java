package com.bksoftwarevn.adminthuocdongy.controller;


import com.bksoftwarevn.adminthuocdongy.entities.JsonResult;
import com.bksoftwarevn.adminthuocdongy.entities.UserJson;
import com.bksoftwarevn.adminthuocdongy.userservice.service.CheckService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class JSPController {

    @Autowired
    private CheckService checkService;

    @GetMapping(value = {"/forget"})
    public String forgetPage() {
        return "forget";
    }

    @GetMapping(value = {"/", "/login"})
    public String loginPage(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "logout", defaultValue = "false") boolean logout) {
        //response.setHeader("Set-Cookie", "HttpOnly;Secure;SameSite=Strict");
        Cookie[] cookies = request.getCookies();
        try {
            if (logout) {
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("Authorization")) {
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                    }
                }
                return "login";
            }
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("Authorization")) {
                        UserJson userJson = checkService.check(cookie.getValue());
                        if (userJson != null && userJson.isAdmin() && userJson.getComId() == 3) {
                            return "don-hang";
                        }
                    }
                }
            }
        } catch (Exception ex) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Authorization")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
            return "login";
        }
        return "login";
    }

    @GetMapping(value = {"/admin-home"})
    public String indexPage() {
        return "home";
    }

    @GetMapping("/admin-san-pham")
    public String sanPham() {
        return "san-pham";
    }

    @GetMapping("/admin-thuong-hieu")
    public String thuongHieu() {
        return "thuong-hieu";
    }

    @GetMapping("/admin-thuoc-tinh")
    public String thuocTinh() {
        return "thuoc-tinh";
    }

    @GetMapping("/admin-giang-vien")
    public String giangVien() {
        return "giang-vien";
    }

    @GetMapping("/admin-san-pham-hoc-vien")
    public String sanPhamHocVien() {
        return "san-pham-hoc-vien";
    }

    @GetMapping("/admin-khoa-hoc")
    public String khoaHoc() {
        return "khoa-hoc";
    }

    @GetMapping("/admin-danh-muc")
    public String danhMuc() {
        return "danh-muc";
    }

    @GetMapping("/admin-loai-san-pham")
    public String loaiSanPham() {
        return "loai-san-pham";
    }

    @GetMapping("/admin-danh-muc-bai-viet")
    public String danhMucBaiViet(){return "danh-muc-bai-viet";}

    @GetMapping("/admin-bai-viet")
    public String baiViet() {
        return "bai-viet";
    }

    @GetMapping("/admin-lien-he")
    public String lienHe() {
        return "lien-he";
    }

    @GetMapping("/admin-lich-kham")
    public String lichKham() {
        return "lich-kham";
    }

    @GetMapping("/van-chuyen")
    public String vanChuyen() {
        return "van-chuyen";
    }

    @GetMapping("/admin-chuong-trinh-khuyen-mai")
    public String chuongTrinhKhuyenMai() {
        return "chuong-trinh-khuyen-mai";
    }

    @GetMapping("/admin-coupon")
    public String coupon() {
        return "coupon";
    }

    @GetMapping("/admin-popup")
    public String combo() {
        return "popup";
    }

    @GetMapping("/admin-don-hang")
    public String donHang() {
        return "don-hang";
    }

    @GetMapping("/admin-doanh-nghiep")
    public String doanhNghiep() {
        return "doanh-nghiep";
    }

    @GetMapping("/admin-khach-hang")
    public String khachHang() {
        return "khach-hang";
    }

    @GetMapping("/admin-tai-lieu")
    public String taiLieu() {
        return "tai-lieu";
    }

    @GetMapping("/admin-logo")
    public String logo() {
        return "logo";
    }

    @GetMapping("/admin-robot")
    public String robot() {
        return "robot";
    }

    @GetMapping("/admin-giao-dien")
    public String giaoDien() {
        return "giao-dien";
    }

    @GetMapping("/admin-tai-khoan")
    public String taiKhoan() {
        return "tai-khoan";
    }

    @GetMapping("/admin-doi-mat-khau")
    public String doiMatKhau() {
        return "doi-mat-khau";
    }

    @GetMapping("/admin-bac-si")
    public String bacSi() {
        return "bac-si";
    }
}
