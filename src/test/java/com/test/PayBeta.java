package com.test;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.dao.impl.PayTestDaoimpl;
import com.dbutils.AliPool;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class PayBeta {

    PayTestDaoimpl pa=new PayTestDaoimpl();

    @Test
    public void test1() throws SQLException {
        List lis=pa.seall();
        System.out.println(lis);
    }


    @Test
    public  void test2(){
        Connection conn= AliPool.getConn();
        System.out.println(conn);
    }

    @Test
    public void test3() throws UnsupportedEncodingException {
        String cff="10000";
        String total_amount = new String(cff.getBytes("ISO-8859-1"),"UTF-8");
        int amu=Integer.parseInt(total_amount);
        System.out.println(amu);
    }

    @Test
    public void test4(){
        /*String dateStr1 = "2021-09-5 10:33:23";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2021-09-10 11:33:23";
        Date date2 = DateUtil.parse(dateStr2);

        //相差一个月，31天
        long betweenDay = DateUtil.between(date1, date2, DateUnit.DAY);
        System.out.println(betweenDay);*/


        //数据库去支付的时间
        String dateStr1 = "2021-9-05 11:05:12";
        Date date1 = DateUtil.parse(dateStr1);

        //获取当前时间
        String nowtime=DateUtil.now();
        Date date2 = DateUtil.parse(nowtime);

        //相差多少天
        long betweenDay = DateUtil.between(date1, date2, DateUnit.DAY);
        System.out.println(betweenDay);
        //得到用户的航班的状态是否起飞 如果是起飞无法退票  || 如果是当前时间大于飞机到达时间无法退票 飞机起飞前1小时无法退票

    }
}
