package com.dao.impl;

import com.dao.PayTestDao;
import com.dbutils.AliPool;
import com.entity.PayTest;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 羡羡
 */
public class PayTestDaoimpl implements PayTestDao {
    QueryRunner runner = new QueryRunner();

    /**
     * 查询所有
     * @return
     * @throws SQLException
     */
    @Override
    public List seall() throws SQLException {
        String se="select * from paytest";
        Connection conn = AliPool.getConn();
        List lis= runner.query(conn,se,new BeanListHandler<PayTest>(PayTest.class));
        conn.close();
        return lis;
    }

    /**
     * 添加订单Beta
     * @param pay 实体类
     * @return
     * @throws SQLException
     */
    @Override
    public int inpayconfig(PayTest pay) throws SQLException {
        String se="insert into paytest(amt,ordernumber,transactionno,paytime) values (?,?,?,?)";
        Connection conn=AliPool.getConn();
        Object[] obj=new Object[4];
        obj[0]=pay.amt;
        obj[1]=pay.ordernumber;
        obj[2]=pay.transactionno;
        obj[3]=pay.paytime;
        int inau=runner.update(conn,se,obj);
        conn.close();
        return inau;
    }

    @Override
    public int depayconfig(String ordernumber) throws SQLException {
        String se="delete from paytest where ordernumber=?";
        Connection conn=AliPool.getConn();
        int desu=runner.update(conn,se,ordernumber);
        return desu;
    }
}
