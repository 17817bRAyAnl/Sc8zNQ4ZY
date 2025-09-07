// 代码生成时间: 2025-09-08 00:56:10
package com.example.imageresizebatch.controller;

import com.example.imageresizebatch.service.ImageResizeService;
import com.example.imageresizebatch.exception.ImageResizeException;
# 优化算法效率
import com.example.imageresizebatch.model.ImageResizeRequest;
import org.springframework.beans.factory.annotation.Autowired;
# 增强安全性
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
# 扩展功能模块
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
# NOTE: 重要实现细节
@RequestMapping("/api/images")
public class ImageResizeBatchController {

    @Autowired
    private ImageResizeService imageResizeService;

    @PostMapping("/resize")
    public ResponseEntity<?> resizeImages(@RequestParam("files") List<MultipartFile> files, @RequestParam("width") int width, @RequestParam("height") int height) {
# NOTE: 重要实现细节
        try {
            List<String> resizedImagePaths = imageResizeService.resizeImages(files, width, height);
            return ResponseEntity.ok(resizedImagePaths);
        } catch (ImageResizeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
# 优化算法效率
        }
    }
}
