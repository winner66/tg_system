package cn.cwnu.modules.business.controller;

import cn.cwnu.common.utils.R;
import cn.cwnu.modules.business.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 文件上传
 *
 * @author zhugl
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private FileStorageService fileService;

    /**
     * 保存上传的xxx
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/uploadFile")
    public R uploadMultipleLicense(HttpServletRequest request) {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        //接收前端传过来的字段
//        String id = params.getParameter("companyId");
//        String name = params.getParameter("companyName");
//        String flag = params.getParameter("flag");

        //多文件取值
        for (int i = 0; i < files.size(); ++i) {
            MultipartFile file = files.get(i);
            if (!file.isEmpty()) {
                //文件保存到文件夹和数据库操作分开
                String[] strFile = fileService.storeFile(file).split(",");

                //文件名、文件地址、标记
                //strFile[0], strFile[1], flag
            } else {
                return R.error("文件上传失败");
            }
        }
        return R.ok();
    }


    /**
     * 保存上传的xxx
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/uploadxxxxx")
    public R uploadMultipleTrace(HttpServletRequest request) {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        //接收前端传过来的字段
//        String id = params.getParameter("companyId");
//        String flag = params.getParameter("flag");
//        String number = params.getParameter("number");

        //多文件取值
        for (int i = 0; i < files.size(); ++i) {
            MultipartFile file = files.get(i);
            if (!file.isEmpty()) {
                //文件保存到文件夹和数据库操作分开
                String[] strFile = fileService.storeFile(file).split(",");


            } else {
                return R.error("图片上传失败");
            }
        }
        return R.ok();
    }

}
