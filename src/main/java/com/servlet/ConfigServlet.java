package com.servlet;

import cn.hutool.core.date.DateUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.config.AlipayConfig;
import com.dao.impl.PayTestDaoimpl;
import com.entity.PayTest;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author 羡羡
 */
@WebServlet("/ConfigServlet/*")
public class ConfigServlet extends BaseServlet {
    PayTestDaoimpl pay=new PayTestDaoimpl();

    /**
     * 查询所有的订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    protected void seall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //查询出所有的订单
        List lis=pay.seall();
        //转换成json
        JSONArray js= JSONArray.fromObject(lis);
        String json=js.toString();
        //发送到前台
        response.getWriter().write(json);
    }

    /**
     * 支付成功   {@link AlipayConfig}配置中支付成功时调用的servlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws AlipayApiException
     */
    protected void PaySuccessful(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, AlipayApiException {
        /* *
         * 功能：支付宝服务器同步通知页面
         * 日期：2017-03-30
         * 说明：
         * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
         * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。


         *************************页面功能说明*************************
         * 该页面仅做页面展示，业务逻辑处理请勿在该页面执行
         */

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            //输出 订单号 交易号  付款金额
            System.out.println("商户订单号："+out_trade_no+" "+"交易宝账号："+trade_no+" "+"付款金额："+total_amount);
            //得到当前时间
            String now = DateUtil.now();
            //执行添加数据 把订单信息放到数据库中
            PayTest paenti=new PayTest(total_amount,out_trade_no,trade_no,now);
            int cf=pay.inpayconfig(paenti);
            if (cf>0){
                //添加成功跳转回首页
                response.sendRedirect("/PayTreasureToPayBack/index.jsp");
            }else{
                //添加失败
                System.out.println("添加失败！");
            }
        }else {
            System.out.println("验签失败");
        }
    }

    /**
     * 退款
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws AlipayApiException
     */
    protected void ARefund (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(request.getParameter("WIDTRout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("WIDTRtrade_no").getBytes("ISO-8859-1"),"UTF-8");
        //请二选一设置
        //需要退款的金额，该金额不能大于订单金额，必填
        String refund_amount = new String(request.getParameter("WIDTRrefund_amount").getBytes("ISO-8859-1"),"UTF-8");
        //退款的原因说明
        String refund_reason = new String(request.getParameter("WIDTRrefund_reason").getBytes("ISO-8859-1"),"UTF-8");
        //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
        String out_request_no = new String(request.getParameter("WIDTRout_request_no").getBytes("ISO-8859-1"),"UTF-8");

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"trade_no\":\""+ trade_no +"\","
                + "\"refund_amount\":\""+ refund_amount +"\","
                + "\"refund_reason\":\""+ refund_reason +"\","
                + "\"out_request_no\":\""+ out_request_no +"\"}");
        //执行退款
        AlipayTradeRefundResponse arefundresponse = alipayClient.execute(alipayRequest);
        //判断是否成功
        if(arefundresponse.isSuccess()){
            PayTestDaoimpl pa=new PayTestDaoimpl();
            //执行删除
            int cf=pa.depayconfig(out_trade_no);
            //删除成功
            if(cf>0){
                //成功调回主页
                response.sendRedirect("/PayTreasureToPayBack/index.jsp");
            }else{
                System.out.println("数据库删除退款失败！");
            }
        }else{
            System.out.println("退款失败！");
        }
    }
}
