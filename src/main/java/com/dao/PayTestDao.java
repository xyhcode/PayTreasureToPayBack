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
     * 添加订单
     * @param pay
     * @return
     * @throws SQLException
     */
    public int inpayconfig(PayTest pay) throws SQLException;
}
