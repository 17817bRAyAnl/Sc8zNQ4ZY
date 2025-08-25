// 代码生成时间: 2025-08-26 05:43:48
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
# 增强安全性

@RestController
@RequestMapping("/api/excel")
public class ExcelGeneratorService {

    @GetMapping("/download")
    public ResponseEntity<Workbook> downloadExcelFile(HttpServletResponse response) throws IOException {
# 优化算法效率
        try {
            String fileName = "excel_generator_" + new Date().getTime() + ".xlsx";
            Workbook workbook = new XSSFWorkbook();
            // 这里可以添加实际的Excel内容生成逻辑
            // 例如：workbook.createSheet("Sheet 1");

            // 设置Content-Disposition，用于下载文件
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" +
                    URLEncoder.encode(fileName, "UTF-8") + "");
            
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // 异常处理
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while generating the Excel file.", e);
        }
    }
}
