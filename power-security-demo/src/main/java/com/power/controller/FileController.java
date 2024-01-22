package com.power.controller;

import com.power.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @author Powerveil
 * @Date 2024/1/22 10:05
 */
@RestController
@RequestMapping("/file")
public class FileController {
    private String folder = "D:\\code\\javacode\\power-security\\power-security\\power-security-demo\\src\\main\\java\\com\\power\\controller\\file";

    // 这里写到了本地文件中，实际上我们会写到阿里云这种OSS服务或自己搭建一个fastdfs服务
    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(folder, new Date().getTime() + ".txt");
        // 传上去的文件写到本地文件
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }


    /**
     * 下载文件
     *
     * @param id 文件ID(之前的时间戳)
     * @throws IOException
     */
    @GetMapping("/{id}")
    public void download(HttpServletRequest request,
                         HttpServletResponse response,
                         @PathVariable("id") String id) throws IOException {

        try (InputStream in = new FileInputStream(new File(folder, id + ".txt"));
             OutputStream out = response.getOutputStream()) {
            response.setContentType("application/x-download");
            // 这里可以设置文件名
            response.addHeader("Content-Disposition", "attachment;filename=Hello Powerveil" + id + ".txt");
            IOUtils.copy(in, out);
            out.flush();
        }

        System.out.println(id);
    }
}
