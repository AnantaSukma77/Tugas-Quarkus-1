//package id.kawahedukasi.service;
//
//import com.lowagie.text.Row;
//import id.kawahedukasi.model.Item;
//import id.kawahedukasi.model.dto.UploadItemRequest;
//import io.quarkus.narayana.jta.runtime.TransactionConfiguration;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.transaction.Transactional;
//import javax.ws.rs.core.Response;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//@ApplicationScoped
//public class UploadService {
//    public Response upload(UploadItemRequest request) throws IOException {
//        List<Item> itemList1 = new ArrayList<>();
//
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(request.file);
//        XSSFWorkbook workbook = new XSSFWorkbook(byteArrayInputStream);
//        XSSFSheet sheet = workbook.getSheetAt(0);
//
//        //remove header
//        sheet.removeRow(sheet.getRow(0));
//        sheet.removeRow(sheet.getRow(1));
//
//        //looping setiap baris
//        Iterator<Row> rowIterator = sheet.rowIterator();
//        while (rowIterator.hasNext()){
//            Item item = new Item();
//            Row row = rowIterator.next();
//            Object nama = row.getCell(0);
//
////            item.setName(name);
////            item.setCount(count);
////            item.setPrice(price);
////            item.setType(type);
////            item.setDescription(description);
//            item.setName((String) nama).getStringCellValue();
//            item.setCount(); = Double.valueOf(row.getCell(1).getNumericCellValue()).intValue();
//            item.setPrice(); = row.getCell(2).getStringCellValue();
//            item.setPrice(); = LocalDate.parse(row.getCell(3).getStringCellValue(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//            item.setDescription(); = row.getCell(4).getStringCellValue();
//            itemList1.add(item);
//        }
//
//        persistListPeserta(pesertaList);
//
//        return Response.ok().build();
//    }
//
//    public Response download() throws IOException {
//        List<Peserta> pesertaList = Peserta.listAll();
//
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("list-peserta");
//
//        //set header
//        int rownum = 0;
//        Row row = sheet.createRow(rownum++);
//        row.createCell(0).setCellValue("id");
//        row.createCell(1).setCellValue("name");
//        row.createCell(2).setCellValue("batch");
//        row.createCell(3).setCellValue("role");
//        row.createCell(4).setCellValue("dob");
//        row.createCell(5).setCellValue("pob");
//        row.createCell(6).setCellValue("created_at");
//        row.createCell(7).setCellValue("updated_at");
//
//        for(Peserta peserta : pesertaList){
//            row = sheet.createRow(rownum++);
//            row.createCell(0).setCellValue(peserta.id);
//            row.createCell(1).setCellValue(peserta.name);
//            row.createCell(2).setCellValue(peserta.batch);
//            row.createCell(3).setCellValue(peserta.role);
//            row.createCell(4).setCellValue(peserta.dob.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//            row.createCell(5).setCellValue(peserta.pob);
//            row.createCell(6).setCellValue(peserta.createdAt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));
//            row.createCell(7).setCellValue(peserta.updatedAt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));
//        }
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        workbook.write(outputStream);
//
////        Content-Disposition: attachment; filename="name_of_excel_file.xls"
//        return Response.ok()
//                .type("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
//                .header("Content-Disposition", "attachment; filename=\"peserta_list_excel.xlsx\"")
//                .entity(outputStream.toByteArray()).build();
//    }
//
//
//    @Transactional
//    @TransactionConfiguration(timeout = 30)
//    public void persistListPeserta(List<Peserta> pesertaList){
//        Peserta.persist(pesertaList);
//    }
//}
