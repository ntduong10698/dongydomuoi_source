package com.bksoftwarevn.adminthuocdongy.cartservice.ultils;

import com.bksoftwarevn.adminthuocdongy.cartservice.entities.json.TrangThai;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.Bill;
import com.bksoftwarevn.adminthuocdongy.cartservice.entities.key.Cart;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {

    public static Object getCellValue(Cell cell) {
        if (cell != null) {
            CellType cellType = cell.getCellTypeEnum();
            switch (cellType) {
                case BOOLEAN:
                    return cell.getBooleanCellValue();
                case NUMERIC:
                    return (int) cell.getNumericCellValue();
                case STRING:
                    return cell.getStringCellValue();
                default:
                    return null;
            }
        } else return "";
    }

    public static Iterator<Row> getRows(String url, int sheetIndex) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(url));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        return sheet.iterator();
    }

    public static Iterator<Row> getRows(File file, int sheetIndex) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        return sheet.iterator();
    }

    public static XSSFWorkbook createListBillExcel(List<Bill> billList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Bill Information");
        XSSFFont font = workbook.createFont();
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        //Write Title
        {
            Row row = sheet.createRow(0);
            //STT
            Cell cellSTT = row.createCell(0, CellType.STRING);
            cellSTT.setCellValue("STT");
            cellSTT.setCellStyle(style);
            //Mã hoa don
            Cell cellMHD = row.createCell(1, CellType.STRING);
            cellMHD.setCellValue("Code");
            cellMHD.setCellStyle(style);
            //Thoi gian tao
            Cell cellTGT = row.createCell(2, CellType.STRING);
            cellTGT.setCellValue("Created Time");
            cellTGT.setCellStyle(style);
            //Ten khach hang
            Cell cellTKH = row.createCell(3, CellType.STRING);
            cellTKH.setCellValue("Customer name");
            cellTKH.setCellStyle(style);
            //ten to chuc
            Cell cellCN = row.createCell(4, CellType.STRING);
            cellCN.setCellValue("Co name");
            cellCN.setCellStyle(style);
            //So dien thoai khach hang
            Cell cellSDT = row.createCell(5, CellType.STRING);
            cellSDT.setCellValue("Customer phone");
            cellSDT.setCellStyle(style);
            //Dia chi
            Cell cellADD = row.createCell(6, CellType.STRING);
            cellADD.setCellValue("Customer address");
            cellADD.setCellStyle(style);
            //Trang Thai
            Cell cellTT = row.createCell(7, CellType.STRING);
            cellTT.setCellValue("Status");
            cellTT.setCellStyle(style);
            //Note bill
            Cell cellNB = row.createCell(8, CellType.STRING);
            cellNB.setCellValue("Note bill");
            cellNB.setCellStyle(style);
            //note cskh
            Cell cellNC = row.createCell(9, CellType.STRING);
            cellNC.setCellValue("Note cskh");
            cellNC.setCellStyle(style);

        }
        //Write data
        int rowNum = 1;
        for (Bill bill : billList) {
            Row row = sheet.createRow(rowNum++);
            //STT
            Cell cellSTT = row.createCell(0, CellType.STRING);
            cellSTT.setCellValue(rowNum - 1);
            //Mã hoa don
            Cell cellMHD = row.createCell(1, CellType.STRING);
            cellMHD.setCellValue(bill.getCode());
            //Thoi gian tao

            Cell cellTGT = row.createCell(2, CellType.STRING);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String createdTime= sdf.format(bill.getCreatedTime());
            cellTGT.setCellValue(createdTime);

            //Ten khach hang
            Cell cellTKH = row.createCell(3, CellType.STRING);
            cellTKH.setCellValue(bill.getCustomerName());
            //ten to chuc
            Cell cellCN = row.createCell(4, CellType.STRING);
            cellCN.setCellValue(bill.getCoName());
            //So dien thoai khach hang
            Cell cellSDT = row.createCell(5, CellType.STRING);
            cellSDT.setCellValue(bill.getCustomerPhone());
            //Dia chi
            Cell cellADD = row.createCell(6, CellType.STRING);
            cellADD.setCellValue(bill.getCustomerAddress());
            //Trang Thai
            Cell cellTT = row.createCell(7, CellType.STRING);
            cellTT.setCellValue(bill.getStatus().getNameStatus());
            //Note bill
            Cell cellNB = row.createCell(8, CellType.STRING);
            cellNB.setCellValue(bill.getNoteBill());
            //Note cskh
            Cell cellNC = row.createCell(9, CellType.STRING);
            cellNC.setCellValue(bill.getCustomerNote());

        }
        return workbook;
    }

    public static XSSFWorkbook createListCartExcel(List<Cart> cartList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Cart Information");
        XSSFFont font = workbook.createFont();
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        //Write Title
        {
            Row row = sheet.createRow(0);
            //STT
            Cell cellSTT = row.createCell(0, CellType.STRING);
            cellSTT.setCellValue("STT");
            cellSTT.setCellStyle(style);
            //Thoi gian tao
            Cell cellTGT = row.createCell(1, CellType.STRING);
            cellTGT.setCellValue("Created Time");
            cellTGT.setCellStyle(style);
            //Ten khach hang
            Cell cellTKH = row.createCell(2, CellType.STRING);
            cellTKH.setCellValue("Customer name");
            cellTKH.setCellStyle(style);
            //So dien thoai khach hang
            Cell cellSDT = row.createCell(3, CellType.STRING);
            cellSDT.setCellValue("Customer phone");
            cellSDT.setCellStyle(style);
            //email
            Cell cellCE = row.createCell(4, CellType.STRING);
            cellCE.setCellValue("Customer email");
            cellCE.setCellStyle(style);
            //Dia chi
            Cell cellAD = row.createCell(5, CellType.STRING);
            cellAD.setCellValue("Customer address");
            cellAD.setCellStyle(style);
            //Trang Thai
            Cell cellTT = row.createCell(6, CellType.STRING);
            cellTT.setCellValue("Status");
            cellTT.setCellStyle(style);


        }
        //Write data
        int rowNum = 1;
        for (Cart cart : cartList) {
            Row row = sheet.createRow(rowNum++);
            //STT
            Cell cellSTT = row.createCell(0, CellType.STRING);
            cellSTT.setCellValue(rowNum - 1);
            //Thoi gian tao
            Cell cellTGT = row.createCell(1, CellType.STRING);
            cellTGT.setCellValue(cart.getCreatedTime());
            //Ten khach hang
            Cell cellTKH = row.createCell(2, CellType.STRING);
            cellTKH.setCellValue(cart.getCustomerName());
            //So dien thoai khach hang
            Cell cellSDT = row.createCell(3, CellType.STRING);
            cellSDT.setCellValue(cart.getCustomerPhone());
            //email
            Cell cellEmail = row.createCell(4, CellType.STRING);
            cellEmail.setCellValue(cart.getCustomerEmail());
            //Dia chi
            Cell celladd = row.createCell(5, CellType.STRING);
            celladd.setCellValue(cart.getCustomerAddress());
            //Trang Thai
            Cell cellTT = row.createCell(6, CellType.STRING);
            cellTT.setCellValue(TrangThai.trangThai.get(cart.getStatus()));

        }
        return workbook;
    }
}
