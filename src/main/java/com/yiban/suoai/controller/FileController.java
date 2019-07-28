package com.yiban.suoai.controller;

import com.yiban.suoai.pojo.Cyinfor;
import com.yiban.suoai.pojo.Image;
import com.yiban.suoai.service.CyinforService;
import com.yiban.suoai.service.ImageService;
import com.yiban.suoai.util.FileHelper;
import com.yiban.suoai.util.MapHelper;
import com.yiban.suoai.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("file")
@Api("文件操作")
public class FileController {

    private static Logger logger = Logger.getLogger(FileController.class);// 添加日志

    @Autowired
    ImageService imageService;
    @Autowired
    CyinforService cyinforService;

   /* @ApiOperation(value = "表白图片上传", notes = "表白图片上传")
    @RequestMapping(value ="cyImageUpload" , method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> cyImageUpload(@RequestHeader("token") @ApiParam(value = "权限校验") String token,
                                             @RequestParam("image")  @ApiParam(value = "图片") MultipartFile[] uploadFiles,
                                             @RequestParam(value = "cyid")  @ApiParam(value = "表白id  ") int cyid
    ) throws IOException {
        Map map= MapHelper.success();
        int hasImage=uploadFiles.length;//图片的数量
        Cyinfor cyinfor = cyinforService.get(cyid);
        cyinfor.setHasImage(hasImage);
        cyinforService.update(cyinfor);
        for(MultipartFile file : uploadFiles){
            String uuid= UUIDUtil.getUUID();//使用uuid作为图片的名称
            String path= FileHelper.FileSave(file,uuid,FileHelper.cyinfor);
            //保存路径
            Image image=new Image(path,cyid);
            imageService.add(image);
            //压缩图片
            //FileHelper.compressPicture(file,uuid);
        }

        return map;
    }*/




    @ApiOperation(value = "表白图片上传", notes = "表白图片上传")
    @RequestMapping(value ="cyImageUpload" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> cyImageUpload(@RequestHeader("token") @ApiParam(value = "权限校验") String token,
                                             @RequestParam(value = "cyid")  @ApiParam(value = "表白id  ") int cyid,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        //System.out.println("进入get方法！");

        Cyinfor cyinfor = cyinforService.get(cyid);
        cyinfor.setHasImage(1);
        cyinforService.update(cyinfor);
        //获取从前台传过来得图片
        MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
        MultipartFile multipartFile =  req.getFile("file");
        //获取图片的文件类型
        String uuid= UUIDUtil.getUUID();//使用uuid作为图片的名称
        String path=FileHelper.FileSave3(multipartFile,uuid,FileHelper.cyinfor);
        Image image=new Image(path,cyid);
        imageService.add(image);
        //压缩图片
        FileHelper.compressPicture(multipartFile,uuid,FileHelper.cyinfor);

        return MapHelper.success();

    }


}
