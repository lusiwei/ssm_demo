package com.lusiwei.controller;

import com.google.common.collect.Lists;
import com.lusiwei.pojo.ExcelPojo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class FileController {
    @PostMapping("upload")
    public String upload(@RequestParam("myFile") MultipartFile multipartFile, String name) throws IOException {
        log.info("获得前台传入的普通文本为：{}", name);
        multipartFile.transferTo(new File("D://" + multipartFile.getOriginalFilename()));
        return "文件上传成功";
    }

    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("D://a.xlsx"));
        //设置响应头和客户端保存名称
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=demo.xlsx");
        //读入byte数组中，使用available获取字节长度
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        //包装一下写出去
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        bytes = null;
    }

    @PostMapping("excelInsert")
    public String excelInsert(@RequestParam("excel") MultipartFile file) throws IOException {
        //获得excel
        Workbook book = null;
        try {
            book = new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            book = new HSSFWorkbook(file.getInputStream());
        }
        Sheet sheet = book.getSheet("班级统计");
        if (sheet != null) {
            //获得总行数
            int lastRowNum = sheet.getLastRowNum();
            log.info("该excel的总行数为：{}", lastRowNum);
            for (Row cells: sheet) {
                //获得每行的列数
                short lastCellNum = cells.getLastCellNum();
                log.info("该excel的总列数为：{}", lastCellNum);
                for (int i = 1; i <= lastCellNum; i++) {
                    cells.getCell(i - 1).setCellType(Cell.CELL_TYPE_STRING);
                    log.info("第{}列数据为:{}", i, cells.getCell(i - 1).getStringCellValue());
                }
            }
        }
        return "导入excel成功";
    }

    @GetMapping("exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        //从数据库查询数据
        List<ExcelPojo> list = Lists.newArrayList();
        list.add(new ExcelPojo("张三", 10, new Date()));
        list.add(new ExcelPojo("李四", 10, new Date()));
        list.add(new ExcelPojo("王五", 10, new Date()));
        Workbook book = null;
        try {
            book = new XSSFWorkbook();//2007以上版本
        } catch (Exception ex) {
            book = new HSSFWorkbook();//2003以下版本
        }
        Sheet sheet = book.createSheet("excel导出测试");//创建页sheet
        //创建标题行
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("年龄");
        row.createCell(2).setCellValue("生日");
        //获得需要插入的数据
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Row row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(list.get(i).getName());//因为知道是三行 可以直接写
            row1.createCell(1).setCellValue(list.get(i).getAge());
            row1.createCell(2).setCellValue(list.get(i).getBirthday());
        }
        //设置响应头和客户端保存文件名
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=excel.xlsx");
        OutputStream outputStream = response.getOutputStream();
        book.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }


}
