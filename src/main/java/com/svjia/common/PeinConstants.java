package com.svjia.common;


/**
 * @Description: 查询参数常量
 * @Auther: chenjw
 * @Date: 2018/8/9 18:58
 */
public interface PeinConstants {

    /**
     * 参数值
     */
    String YEAR = "year";

    String QUARTER = "quarter";

    String MONTH = "month";

    String WEEK = "week";

    String DAY = "day";

    String RING_RATE = "ringRate";

    String YEAR_RATE = "yearRate";

    /**
     * 不同维度年限间隔,web只显示12个数据,
     */
    int YEAR_INTERVAL_YEAR = 5;

    int QUARTER_INTERVAL_YEAR = 4;

    int MONTH_INTERVAL_YEAR = 1;

    int WEEK_INTERVAL_YEAR = 1;

    int DAY_INTERVAL_YEAR = 0;

    /**
     * 时间维度end
     */
    int QUARTER_MAX = 4;

    int MONTH_MAX = 12;

    int WEEK_MAX = 54;

    int DAY_MAX = 31;

}
