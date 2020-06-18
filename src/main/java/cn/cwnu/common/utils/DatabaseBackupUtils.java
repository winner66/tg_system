package cn.cwnu.common.utils;

import java.io.*;
import java.util.Date;

/**
 * 数据库备份、还原工具类
 *
 * @author zhugl
 */
public class DatabaseBackupUtils {
    //MySQL数据库所在服务器地址IP
    private static String hostIP;
    //MySQL数据库所在服务器地址PORT
    private static String hostPort;
    //数据库导出文件文件名
    private static String fileName;
    //要导出的数据库名
    private static String databaseName;

    /**
     * Java代码实现MySQL数据库导出
     *
     * @param hostIP          MySQL数据库所在服务器地址IP
     * @param userName        进入数据库所需要的用户名
     * @param password        进入数据库所需要的密码
     * @param savePath        数据库导出文件保存路径
     * @param fileName        数据库导出文件文件名
     * @param databaseName 要导出的数据库名
     * @return 返回true表示导出成功，否则返回false。
     */
    public static boolean exportDatabaseTool(String hostIP, String hostPort, String userName, String password, String savePath, String fileName, String databaseName)  {
        File saveFile = new File(savePath);
        // 如果目录不存在 
        if (!saveFile.exists()) {
            // 创建文件夹 
            saveFile.mkdirs();
        }
        if (!savePath.endsWith(File.separator)) {
            savePath = savePath + File.separator;
        }
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            //String cmd = "mysqldump -h127.0.0.1 -uroot -P3308 -p123456 archives";     
            String cmd = "mysqldump -h " + hostIP + " -u " + userName + " -P " + hostPort + " -p " + password + " " + databaseName;
            Process process = Runtime.getRuntime().exec(cmd);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");

            bufferedReader = new BufferedReader(inputStreamReader);
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line);
            }
            printWriter.flush();
            //0 表示线程正常终止。 
            if (process.waitFor() == 0) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Java实现MySQL数据库导入*
     *
     * @param hostIP                MySQL数据库所在服务器地址IP
     * @param userName            数据库用户名
     * @param password            进入数据库所需要的密码
     * @param importFilePath 数据库文件路径
     * @param sqlFileName      数据库文件名
     * @param databaseName   要导入的数据库名
     * @return 返回true表示导入成功，否则返回false。
     * @author GaoHuanjie
     */
    public static boolean importDatabase(String hostIP, String hostPort, String userName, String password, String importFilePath, String sqlFileName, String databaseName) {
        File saveFile = new File(importFilePath);
        // 如果目录不存在
        if (!saveFile.exists()) {
            // 创建文件夹
            saveFile.mkdirs();
        }
        if (!importFilePath.endsWith(File.separator)) {
            importFilePath = importFilePath + File.separator;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mysql").append(" -h").append(hostIP);
        stringBuilder.append(" -u").append(userName).append(" -P").append(hostPort).append(" -p").append(password);
        stringBuilder.append(" ").append(databaseName);
        stringBuilder.append(" <").append(importFilePath).append(sqlFileName);
        try {
            //必须要有“cmd /c ”   
            Process process = Runtime.getRuntime().exec("cmd /c " + stringBuilder.toString());
            // 0 表示线程正常终止。 
            if (process.waitFor() == 0) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取配置 - 取出相关连接信息
     *
     * @param url
     * @return
     */
    public static String[] getParams(String url) {
        //jdbc:mysql://localhost:3306/jitu_system?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8
        String[] params = new String[4];
        if (null == url) {
            return null;
        }
        //截取://以后部分
        url = url.split("://")[1];
        hostIP = url.substring(0, url.indexOf(":"));
        hostPort = url.substring(url.indexOf(":")+1, url.indexOf("/"));
        databaseName = url.substring(url.indexOf("/")+1, url.indexOf("?"));
        fileName = databaseName + "_" + DateUtils.yearMonthDay(new Date()) + ".sql";

        params[0] = hostIP;
        params[1] = hostPort;
        params[2] = databaseName;
        params[3] = fileName;

        return params;
    }
}
