package com.etop.baseProject.commons.util;

import java.math.BigDecimal;

/**
 * 工具类
 * Created by jessy on 2015/3/5.
 */
public class MyUtils {
    /**
     * 判断字符串不为null或空字符串
     * 建议使用StringUtils.isEmpty(String str)
     * @param str
     * @return
     */
    @Deprecated
    public static boolean notEmpty(String str) {

        if (str != null && str.trim().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将数组里的元素用指定字符连接起来 来自commons-lang StringUtils Revision 448015
     *
     * @param array
     * @param separator
     * @return
     */
    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        int arraySize = array.length;
        int bufSize = (arraySize == 0 ? 0 : ((array[0] == null ? 16 : array[0].toString().length()) + 1) * arraySize);
        StringBuilder buf = new StringBuilder(bufSize);

        for (int i = 0; i < arraySize; i++) {
            if (i > 0) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    /**
     * 根据精度截断浮点数
     *
     * @param value	需要截断的浮点数
     * @param scale 精度
     * @return
     */
    public static double round(double value, int scale) {
        BigDecimal db = new BigDecimal(value);
        db = db.setScale(2, BigDecimal.ROUND_HALF_UP);
        return db.doubleValue();
    }

}
