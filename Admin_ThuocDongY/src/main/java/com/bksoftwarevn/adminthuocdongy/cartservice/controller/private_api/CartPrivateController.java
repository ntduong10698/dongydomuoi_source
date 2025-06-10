package com.bksoftwarevn.adminthuocdongy.cartservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.json.PageJson;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.Cart;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.CartService;
import com.bksoftwarevn.adminthuocdongy.cartservice.ultils.ExcelUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin/cart")
public class CartPrivateController {

    @Autowired
    private CartService cartService;

   // @Value("${spring.upload.folder-upload}")
    private String UPLOAD_DIRECTORY;

   // @Value("${spring.upload.link-prefix}")
    private String URL_UPLOAD_FILE;

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult> findById(@PathVariable(name = "id") int id){
        try {
            return cartService.findById(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("id is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<JsonResult> findByCusId(@PathVariable(name = "id") int id){
        try {
            return cartService.findByCustomer(id)
                    .map(JsonResult::success)
                    .orElse(JsonResult.badRequest("customer is not exist"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<JsonResult> setStatus(@PathVariable("id") int id,
                                                @RequestParam("status") int status) {
        if (cartService.setStatus(id, status))
            return JsonResult.updated("change status success");
        else return JsonResult.badRequest("change status fail");
    }

    @GetMapping("/filter")
    public ResponseEntity<JsonResult> adminFilter(@RequestParam(name = "start-date" ) String startDate,
                                                  @RequestParam(name = "end-date") String endDate,
                                                  @RequestParam(name = "cus-id", defaultValue = "0" ) int cusId,
                                                  @RequestParam(name = "status", defaultValue = "0") int status,
                                                  @RequestParam(name = "dateAsc", defaultValue = "true") boolean dateAsc,
                                                  @RequestParam(name = "page", defaultValue = "1") int page,
                                                  @RequestParam(name = "size", defaultValue = "20") int size) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            Date sDate= formatter.parse(startDate);
            Date eDate= formatter.parse(endDate);
            PageRequest request = PageRequest.of(page - 1, size);
            if (startDate==null && endDate == null && cusId ==0 && status ==0 ) {
                return JsonResult.success(new PageJson<Cart>(cartService.findAll(request)));
            }
            return JsonResult.success(new PageJson<Cart>(cartService.filter(sDate,  eDate,  status,  cusId ,  request ,  dateAsc)));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> deleted(@PathVariable("id")int id) throws Exception {
        boolean bol=cartService.delete(id);
        try {
            if(bol)
                return JsonResult.deleted();
            return JsonResult.badRequest("Delete fail");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestBody Cart data) {
        try {
            data.setDeleted(false);
            data.setCreatedTime(new Date());
            return cartService.save(data)
                    .map(JsonResult::uploaded)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> update(@RequestBody Cart data){
        try {
            return cartService.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/excel")
    public ResponseEntity<JsonResult> getExcel(@RequestParam("list-cart") List<Cart> list) {
        XSSFWorkbook workbook = ExcelUtils.createListCartExcel(list);
        try {
            String fileName = "Listcart_" + LocalDateTime.now().getNano() + ".xlsx";
            File file = new File(UPLOAD_DIRECTORY + fileName);
            file.getParentFile().mkdirs();
            FileOutputStream outFile;
            outFile = new FileOutputStream(file);
            workbook.write(outFile);
            outFile.close();
            return JsonResult.uploaded(URL_UPLOAD_FILE + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonResult.badRequest("Create Excel fail");
    }

}
