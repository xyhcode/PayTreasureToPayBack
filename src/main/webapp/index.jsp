<%--
  Created by IntelliJ IDEA.
  User: 羡羡
  Date: 2021/10/4
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>优惠券购买</title>
    <link rel="stylesheet" href="/PayTreasureToPayBack/layui/css/layui.css">
    <script src="/PayTreasureToPayBack/layui/layui.js"></script>
    <script src="/PayTreasureToPayBack/JS/jquery-1.10.2.js"></script>
    <style>
        .coupon {
            width: 300px;
            height: 100px;
            line-height: 100px;
            margin: 50px auto;
            text-align: center;
            position: relative;
            background: radial-gradient(circle at right bottom, transparent 10px, #ffffff 0) top right /50% 51px no-repeat,
            radial-gradient(circle at left bottom, transparent 10px, #ffffff 0) top left / 50% 51px no-repeat,
            radial-gradient(circle at right top, transparent 10px, #ffffff 0) bottom right / 50% 51px no-repeat,
            radial-gradient(circle at left top, transparent 10px, #ffffff 0) bottom left / 50% 51px no-repeat;
            filter: drop-shadow(2px 2px 2px rgba(0, 0, 0, .2));
        }

        .coupon span {
            display: inline-block;
            vertical-align: middle;
            margin-right: 10px;
            color: red;
            font-size: 50px;
            font-weight: 400;
        }
    </style>
    <script>
        $(function () {
            $.get("/PayTreasureToPayBack/ConfigServlet/seall", function (res) {
                var crin = JSON.parse(res);
                var th = "";
                for (var i = 0; i < crin.length; i++) {
                    var obj = crin[i];
                    th += "<p class='coupon'><span>" + obj.amt + "</span>优惠券 <button type='button' class='layui-btn layui-btn-danger' onclick='fc(" + obj.amt + "," + obj.ordernumber + "," + obj.transactionno + ")'>退票</button> </p>";
                }
                $("hr").after(th);
            })
        })
    </script>
</head>
<body>
<form name=alipayment action=alipay.trade.page.pay.jsp method=post
      target="_blank">
    <input id="WIDout_trade_no" name="WIDout_trade_no" type="hidden" />
    <input id="WIDsubject" name="WIDsubject" value="航空机票付款" type="hidden"/>
    <input id="WIDtotal_amount" name="WIDtotal_amount" type="hidden"/>
    <input id="WIDbody" name="WIDbody" value="南方航空G98T21机票付款" type="hidden"/>
    <p class="coupon">
        <span id="sp">10</span>优惠券
        <button type="submit" class="layui-btn layui-btn-warm">购买</button>
    </p>
</form>
<hr>
</body>
<script>
    function GetDateNow() {
        var vNow = new Date();
        var sNow = "";
        sNow += String(vNow.getFullYear());
        sNow += String(vNow.getMonth() + 1);
        sNow += String(vNow.getDate());
        sNow += String(vNow.getHours());
        sNow += String(vNow.getMinutes());
        sNow += String(vNow.getSeconds());
        sNow += String(vNow.getMilliseconds());
        document.getElementById("WIDout_trade_no").value =  sNow;
        var nj=$("#sp").text();
        document.getElementById("WIDtotal_amount").value = nj;
    }
    GetDateNow();
</script>
</html>
