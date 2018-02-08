package cn.hjgx.Utils;

import java.util.Date;
import java.util.Random;

/**
 * Created by alvin on 2018/2/6.
 */
public class OrderNoUtil {

    /**
     * 生成单号
     *
     * @param prefix
     * @return
     */
    public static String generateOrderNo(String prefix) {

        String date = DateTimeUtils.date2string(new Date(), DateTimeUtils.FORMAT_DATETIME_BACKEND);

        String random = "";
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            random += r.nextInt(9);
        }

        return prefix + "-" + date + "-" + random;

    }

}
