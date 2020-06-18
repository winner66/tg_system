package cn.cwnu.common.utils;

import cn.cwnu.common.exception.RRException;
import cn.cwnu.modules.sys.entity.ClientUserEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * excel解析读取工具类
 *
 * @author zgl
 */
public class ReadExcelUtils {
    private int rows = 0;

    /**
     * 解析excel
     *
     * @param myFile
     * @return
     */
    public Sheet readExcel(MultipartFile myFile) {
        //1.  使用HSSFWorkbook 打开或者创建 “Excel对象”
        //2.  用HSSFWorkbook返回对象或者创建sheet对象
        //3.  用sheet返回行对象，用行对象得到Cell对象
        //4.  对Cell对象进行读写
        Workbook workbook = null;
        //  获取文件名
        String fileName = myFile.getOriginalFilename();
        if (fileName.endsWith(Constant.LOW_XLS) || fileName.endsWith(Constant.UP_XLS)) {
            try {
                workbook = WorkbookFactory.create(myFile.getInputStream());
                //  2003版本
                //workbook = new HSSFWorkbook(myFile.getInputStream());
            } catch (InvalidFormatException e) {
                throw new RRException(Constant.TYPE_ERROR);
            } catch (IOException e) {
                throw new RRException(Constant.READ_EXCEPTION);
            } catch (Exception e) {
                throw new RRException("文件读取错误，请上传指定模板文件");
            }
        } else if (fileName.endsWith(Constant.LOW_XLSX) || fileName.endsWith(Constant.UP_XLSX)) {
            try {
                workbook = WorkbookFactory.create(myFile.getInputStream());
                //  2007版本
                //workbook = new XSSFWorkbook(myFile.getInputStream());
            } catch (IOException e) {
                throw new RRException(Constant.TYPE_ERROR);
            } catch (InvalidFormatException e) {
                throw new RRException(Constant.READ_EXCEPTION);
            } catch (Exception e) {
                throw new RRException("文件读取错误，请上传指定模板文件");
            }
        } else {
            // 文件不是Excel文件
            throw new RRException(Constant.NOT_EXCEL);
        }
        //示意访问sheet
        Sheet sheet = workbook.getSheetAt(0);
        //Sheet sheet = workbook.getSheet("sheet");
        rows = sheet.getLastRowNum();
        if (rows == 0) {
            // 数据为空 请填写数据
            throw new RRException(Constant.EMPTY_EXCEL);
        }
        return sheet;
    }

    /**
     * 获取cell数据
     *
     * @param cell 数据
     * @param i    是否取整标记（0：取整  1：不取整）
     * @return
     */
    public String getCellValue(Cell cell, int i) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    value = cell.getNumericCellValue() + " ";
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        if (date != null) {
                            //  日期格式化
                            value = DateUtils.format(date);
                        } else {
                            value = "";
                        }
                    } else {
                        if (i == 0) {
                            //  解析cell时候 数字类型默认是double类型的 但是想要获取整数类型 需要格式化
                            value = new DecimalFormat("0").format(cell.getNumericCellValue());
                        } else {
                            value = cell.getNumericCellValue() + " ";
                        }
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING: //  字符串
                    value = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:   //  Boolean类型
                    value = cell.getBooleanCellValue() + "";
                    break;
                case HSSFCell.CELL_TYPE_BLANK:   // 空值
                    value = "";
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 错误类型
                    value = "非法字符";
                    break;
                default:
                    value = "未知类型";
                    break;
            }
        }
        return value.trim();
    }

    /**
     * 字符串截取，{"code":3321311,"name": "资产公司"}
     *
     * @param str 字符串："(3321311) 资产公司"
     * @return json对象
     */
    public JSONObject getCodeAndName(String str) {
        if (null != str) {
            String code = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
            String name = str.substring(str.indexOf(")") + 1).trim();
            String t = "{\"code\":" + code + ",\"name\":" + "\" " + name + "\"" + "}";
            return JSON.parseObject(t);
        }
        return null;
    }

    /**
     * ////////////////////////////////////////////////
     *
     * ////////////////////////////////////////////////
     */

    /**
     * 返回人员信息列表
     *
     * @param myFile
     * @return
     */
    public List<ClientUserEntity> getUserInfoLists(MultipartFile myFile) {
        List<ClientUserEntity> infoEntitys = new ArrayList<>();
        //获取每行数据
        Sheet sheet = readExcel(myFile);
        // 取出excel数据
        for (int i = 1; i <= rows + 1; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                ClientUserEntity infoEntity = new ClientUserEntity();
                //用户名
//                String username = getCellValue(row.getCell(0), 0);
//                infoEntity.set(username);
//                //账号
//                String account = getCellValue(row.getCell(1), 0);
//                infoEntity.set(account);
//                //手机号
//                String phone = getCellValue(row.getCell(2), 0);
//                infoEntity.set(phone);
//                //所在分组
//                String group = getCellValue(row.getCell(3), 0);
//                infoEntity.set(group);
//                //单位名称
//                String dept = getCellValue(row.getCell(4), 0);
//                infoEntity.set(dept);
//                //人员等级
//                String level = getCellValue(row.getCell(5), 0);
//                infoEntity.set(level);
//                //性别
//                String sex = getCellValue(row.getCell(6), 0);
//                infoEntity.set(sex);
//                //状态  0：禁用   1：正常
//                String status = getCellValue(row.getCell(7), 0);
//                infoEntity.set(status);
//                //邮箱
//                String email = getCellValue(row.getCell(8), 0);
//                infoEntity.set(email);
//                //开户银行
//                String bank = getCellValue(row.getCell(9), 0);
//                infoEntity.set(bank);
//                //银行账号
//                String bankNum = getCellValue(row.getCell(10), 0);
//                infoEntity.set(bankNum);
//                //详细地址
//                String address = getCellValue(row.getCell(11), 0);
//                infoEntity.set(address);
//                //备注
//                String remarks = getCellValue(row.getCell(12), 0);
//                infoEntity.set(remarks);
                // 数据添加到list
                infoEntitys.add(infoEntity);
            }
        }
        return infoEntitys;
    }

}
