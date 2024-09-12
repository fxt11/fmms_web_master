package com.example.fmms.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.fmms.component.NonStaticResourceHttpRequestHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/getVideo")
@AllArgsConstructor
@Api(tags = "赛事直播")
public class FileRestController {

    @Autowired
    private NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

    @GetMapping("/video")
    @ApiOperation(value = "视频播放")
    public void videoPreview(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //sourcePath 是获取编译后 resources 文件夹的绝对地址，获得的原始 sourcePath 以/开头，所以要用 substring(1) 去掉第一个字符/
        //realPath 即视频所在的完整地址
        String sourcePath = this.getClass().getClassLoader().getResource("").getPath().substring(1);
        String realPath = sourcePath +"static/video/1.mp4";
        Path filePath = Paths.get(realPath);
        if (Files.exists(filePath)) {
            // 利用 Files.probeContentType 获取文件类型
            String mimeType = Files.probeContentType(filePath);
            if (!StringUtils.isEmpty(mimeType)) {
                // 设置 response
                response.setContentType(mimeType);
            }
            request.setAttribute(nonStaticResourceHttpRequestHandler.filepath, filePath);
            // 利用 ResourceHttpRequestHandler.handlerRequest() 实现返回视频流
            nonStaticResourceHttpRequestHandler.handleRequest(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }
}
