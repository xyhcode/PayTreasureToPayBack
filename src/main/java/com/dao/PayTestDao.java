package com.dao;

import com.entity.PayTest;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 羡羡
 */
public interface PayTestDao {
    /**
     * 查询所有的订单
     * @return
     * @throws SQLException
     */
    public List seall()throws SQLException;;

    /**
     * 付款成功的话添加订单
     * @param pay 实体类
     * @return
     * @throws SQLException
     */
    public int inpayconfig(PayTest pay) throws SQLException;

    /**
     * 如果是退款成功则上传这条记录
     * @param ordernumber
     * @return
     * @throws SQLException
     */
    public int depayconfig(String ordernumber) throws SQLException;
}
