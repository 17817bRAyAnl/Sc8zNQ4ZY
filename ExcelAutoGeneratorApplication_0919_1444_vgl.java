// 代码生成时间: 2025-09-19 14:44:37
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class ExcelAutoGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelAutoGeneratorApplication.class, args);
    }

    @GetMapping("/generate")
    public ResponseEntity<byte[]> generateExcel(@RequestParam(name = "data", required = false) String data, HttpServletResponse response) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Generated Data");
            int rowNum = 0;
            int cellNum;

            // Create header row
            Row headerRow = sheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Column 1");
            headerRow.createCell(1).setCellValue("Column 2");

            // Create data rows
            if (data != null && !data.isEmpty()) {
                String[] dataArray = data.split(",");
                for (String entry : dataArray) {
                    Row dataRow = sheet.createRow(rowNum++);
                    dataRow.createCell(0).setCellValue(entry);
                    dataRow.createCell(1).setCellValue("Value");
                }
            }

            // Create a header style
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Apply the header style to the header cells
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                Cell cell = headerRow.getCell(i);
                cell.setCellStyle(headerStyle);
            }

            // Write the output to the response.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "generated-data.xlsx");

            byte[] bytes = new byte[0];
            try (OutputStream os = response.getOutputStream()) {
                workbook.write(os);
                bytes = os.toByteArray();
            }

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(bytes);

        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Error generating Excel file.");
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }
}
