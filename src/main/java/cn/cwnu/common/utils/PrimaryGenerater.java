package cn.cwnu.common.utils;

import java.util.Date;

/**
 * 编码生成工具类  规则："yyyyMMdd" + 2为增码 +
 *
 * @author zhugl
 */
public class PrimaryGenerater {
    private static int sequence = 0;

    private static int length = 2;

    /**
     * "yyyyMMdd" + 2为增码 + 企业id
     *
     * @return
     */
    public static synchronized String getLocalTrmSeqNum() {
        sequence = sequence >= 999999 ? 1 : sequence + 1;
        String datetime = DateUtils.yearMonthDay(new Date());
        String s = Integer.toString(sequence);
        return datetime + addLeftZero(s, length);
    }

    /**
     * 左填0
     *
     * @param s
     * @param length
     * @return
     */
    public static String addLeftZero(String s, int length) {
        // StringBuilder sb=new StringBuilder();
        int old = s.length();
        if (length > old) {
            char[] c = new char[length];
            char[] x = s.toCharArray();
            if (x.length > length) {
                throw new IllegalArgumentException(
                        "Numeric value is larger than intended length: " + s
                                + " LEN " + length);
            }
            int lim = c.length - x.length;
            for (int i = 0; i < lim; i++) {
                c[i] = '0';
            }
            System.arraycopy(x, 0, c, lim, x.length);
            return new String(c);
        }
        return s.substring(0, length);
    }
}
