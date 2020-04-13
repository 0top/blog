package top.zerotop.blog.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by:zerotop  date:2019/7/17
 */
@Api(value = "文件下载接口",description = "文件下载接口")
@RestController
@RequestMapping(value = "/api/v1/download", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class FileController {
    @ApiOperation(value = "文件下载", notes = "文件下载")
    @GetMapping(value = "/download")
    public void insertAdmin(HttpServletResponse res) {
        File file = new File("D:\\img.jpg");
        System.out.println(file.getAbsolutePath());
        try {
//            res.setContentType("application/octet-stream");
            res.setContentType("image/jpg");
            res.setHeader("Content-Disposition", "attachment;fileName=3.jpg;encoding=UTF-8");
            StreamUtils.copy(new FileInputStream(file), res.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
