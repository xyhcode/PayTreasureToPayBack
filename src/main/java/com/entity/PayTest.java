package com.entity;

/**
 * @author 羡羡
 */
public class PayTest {
    /**
     * id
     */
    public int id;
    /**
     * 金额
     */
    public String amt;
    /**
     *订单号
     */
    public String ordernumber;
    /**
     *交易号 流水号
     */
    public String transactionno;
    /**
     *交易时间
     */
    public String paytime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getTransactionno() {
        return transactionno;
    }

    public void setTransactionno(String transactionno) {
        this.transactionno = transactionno;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public PayTest() {
    }

    public PayTest(String amt, String ordernumber, String transactionno, String paytime) {
        this.amt = amt;
        this.ordernumber = ordernumber;
        this.transactionno = transactionno;
        this.paytime = paytime;
    }

    public PayTest(int id, String amt, String ordernumber, String transactionno, String paytime) {
        this.id = id;
        this.amt = amt;
        this.ordernumber = ordernumber;
        this.transactionno = transactionno;
        this.paytime = paytime;
    }

    @Override
    public String toString() {
        return "PayTest{" +
                "id=" + id +
                ", amt='" + amt + '\'' +
                ", ordernumber='" + ordernumber + '\'' +
                ", transactionno='" + transactionno + '\'' +
                ", paytime='" + paytime + '\'' +
                '}';
    }
}
