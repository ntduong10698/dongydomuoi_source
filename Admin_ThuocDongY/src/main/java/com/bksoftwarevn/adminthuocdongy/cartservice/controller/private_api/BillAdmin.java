package com.bksoftwarevn.adminthuocdongy.cartservice.controller.private_api;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.json.JsonResult;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.Bill;
import com.bksoftwarevn.adminthuocdongy.cartservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/v1/admin/bill")
public class BillAdmin {

    @Autowired
    private BillService billService;

    @Autowired
    private BillDetailService billDetailService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private InvoiceTemplateService invoiceTemplateService;

    @Autowired
    private SourceOrderService sourceOrderService;

   // @Value("${spring.upload.folder-upload}")
    private String UPLOAD_DIRECTORY;

   // @Value("${spring.upload.link-prefix}")
    private String URL_UPLOAD_FILE;



//    @PutMapping("/{id}/status")
//    public ResponseEntity<JsonResult> setStatus(@PathVariable("id") int id,
//                                                @RequestParam("status-id") int statusId) {
//        try {
//            return statusService.findById(statusId)
//                    .map(status -> {
//                        try {
//                            return billService.findById(id)
//                                    .map(bill -> {
//                                        bill.setStatus(status);
//                                        try {
//                                            return billService.save(bill)
//                                                    .map(JsonResult::updated)
//                                                    .orElse(JsonResult.badRequest("data is invalid"));
//                                        } catch (Exception ex) {
//                                            return JsonResult.error(ex);
//                                        }
//                                    }).orElse(JsonResult.badRequest("data is invalid"));
//                        } catch (Exception ex) {
//                            return JsonResult.error(ex);
//                        }
//                    }).orElse(JsonResult.badRequest("data is invalid"));
//        } catch (Exception ex) {
//            return JsonResult.error(ex);
//        }
//    }
//
//    @PutMapping("/{id}/source")
//    public ResponseEntity<JsonResult> setSource(@PathVariable("id") int id,
//                                                @RequestParam("source-id") int sourceId) {
//        try {
//            return sourceOrderService.findById(sourceId)
//                    .map(source -> {
//                        try {
//                            return billService.findById(id)
//                                    .map(bill -> {
//                                        bill.setSourceOrder(source);
//                                        try {
//                                            return billService.save(bill)
//                                                    .map(JsonResult::updated)
//                                                    .orElse(JsonResult.badRequest("data is invalid"));
//                                        } catch (Exception ex) {
//                                            return JsonResult.error(ex);
//                                        }
//                                    }).orElse(JsonResult.badRequest(""));
//                        } catch (Exception ex) {
//                            return JsonResult.error(ex);
//                        }
//                    }).orElse(JsonResult.badRequest(""));
//        } catch (Exception ex) {
//            return JsonResult.error(ex);
//        }
//    }
//
//    @PutMapping("/{id}/invoice-template")
//    public ResponseEntity<JsonResult> setInvoiceForm(@PathVariable("id") int id,
//                                                     @RequestParam("template-id") int templateId) {
//        try {
//            return invoiceTemplateService.findById(templateId)
//                    .map(template -> {
//                        try {
//                            return billService.findById(id)
//                                    .map(bill -> {
//                                        bill.setInvoiceTemplate(template);
//                                        try {
//                                            return billService.save(bill)
//                                                    .map(JsonResult::updated)
//                                                    .orElse(JsonResult.badRequest("data is invalid"));
//                                        } catch (Exception ex) {
//                                            return JsonResult.error(ex);
//                                        }
//                                    }).orElse(JsonResult.badRequest(""));
//                        } catch (Exception ex) {
//                            return JsonResult.error(ex);
//                        }
//                    }).orElse(JsonResult.badRequest(""));
//        } catch (Exception ex) {
//            return JsonResult.error(ex);
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult> deleted(@PathVariable("id") int id) throws Exception {
        boolean bol = billService.delete(id);
        try {
            if (bol)
                return JsonResult.deleted();
            return JsonResult.badRequest("Delete fail");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PostMapping
    public ResponseEntity<JsonResult> upload(@RequestParam("status-id") int statusId,
                                             @RequestParam("source-id") int sourceId,
                                             @RequestParam("template-id") int templateId,
                                             @RequestBody Bill data) {
        try {
            return statusService.findById(statusId)
                    .map(status -> {
                        data.setStatus(status);
                        data.setDeleted(false);
                        try {
                            return sourceOrderService.findById(sourceId)
                                    .map(sourceOder -> {
                                        try {
                                            data.setSourceOrder(sourceOder);
                                            data.setDeleted(false);
                                            data.setInvoiceTemplate(invoiceTemplateService.findById(templateId).orElse(null));
                                            data.setDeleted(false);
                                            data.setCreatedTime(new Date());
                                            return billService.save(data)
                                                    .map(JsonResult::uploaded)
                                                    .orElse(JsonResult.badRequest("data is invalid"));
                                        } catch (Exception e) {
                                            return JsonResult.error(e);
                                        }
                                    }).orElse(JsonResult.badRequest("data is invalid"));
                        } catch (Exception ex) {
                            return JsonResult.error(ex);
                        }
                    }).orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception ex) {
            return JsonResult.error(ex);
        }
    }

    @PutMapping
    public ResponseEntity<JsonResult> put(@RequestBody Bill data) {
        try {
            return billService.save(data)
                    .map(JsonResult::updated)
                    .orElse(JsonResult.badRequest("data is invalid"));
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @GetMapping("/{id}/check")
    public ResponseEntity<JsonResult> updateCheck(@PathVariable("id") int id) {
        try {
            billService.check(id);
            return JsonResult.success("checked");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

    @PutMapping("/check")
    public ResponseEntity<JsonResult> updateChecks(@RequestParam("ids") int[] ids) {
        try {
            billService.checkAll(ids);
            return JsonResult.success("checked");
        } catch (Exception e) {
            return JsonResult.error(e);
        }
    }

//    @GetMapping("/excel")
//    public ResponseEntity<JsonResult> getExcel(@RequestParam("list-bill") List<Bill> list) {
//        XSSFWorkbook workbook = ExcelUtils.createListBillExcel(list);
//        try {
//            String fileName = "Listbill_" + LocalDateTime.now().getNano() + ".xlsx";
//            File file = new File(UPLOAD_DIRECTORY + fileName);
//            file.getParentFile().mkdirs();
//            FileOutputStream outFile;
//            outFile = new FileOutputStream(file);
//            workbook.write(outFile);
//            outFile.close();
//            return JsonResult.uploaded(URL_UPLOAD_FILE + fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return JsonResult.badRequest("Create Excel fail");
//    }


}
