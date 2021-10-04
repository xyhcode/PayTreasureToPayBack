package com.test;

import com.dao.impl.PayTestDaoimpl;
import com.dbutils.AliPool;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
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
}
