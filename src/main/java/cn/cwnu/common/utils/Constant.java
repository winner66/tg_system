package cn.cwnu.common.utils;

/**
 * 常量
 *
 * @author zhugl
 */
public class Constant {
    /**
     * 超级管理员ID
     */
    public static final int SUPER_ADMIN = 1;
    public static final String SYSTEMMANAGER = "系统管理";
    public static final Long MENUE_VIEW = Long.valueOf(23);

    //默认密码
    public static final String INIT_PASSWORD = "000000";
    //随机盐长度
    public static final int SALT_LENGTH = 35;

    //管理身份类型 ： 超级管理员  普通后台管理员   普通用户
    public static final int COMPANY_IDENTITY_ID = 2;
    public static final int USER_IDENTITY_ID = 3;

    /**
     * 文件上传类型
     */
    public final static String LOW_XLS = "xls";
    public static final String LOW_XLSX = "xlsx";
    public final static String UP_XLS = "XLS";
    public static final String UP_XLSX = "XLSX";

    public static String NOT_EXCEL = "文件不是Excel";
    public static String TYPE_ERROR = "文件扩展名错误";
    public static String READ_EXCEPTION = "文件读取错误";
    public static String EMPTY_EXCEL = "数据为空，请填写数据";

    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 管理身份类型 0:超级管理员  1：普通后台管理员 2：普通用户
     */
    public static String adminType(int code) {
        String name = "";
        switch (code) {
            case 0:
                name = "超级管理员";
                break;
            case 1:
                name = "后台管理员";
                break;
            case 2:
                name = "普通用户";
                break;
            default:
                break;
        }
        return name;
    }
}
