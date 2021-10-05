# PayTreasureToPayBack

#### 介绍
支付宝 电脑网站付款 与 退款

#### 软件架构
Java layui JavaScript JQuery mysql

#### 界面截图
![输入图片说明](https://images.gitee.com/uploads/images/2021/1005/102122_43149234_7956133.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/1005/102142_46902b1a_7956133.png "屏幕截图.png")

#### 数据库设计
数据库名：paymoney
数据库表名：paytest
![输入图片说明](https://images.gitee.com/uploads/images/2021/1005/102421_c2b93cf5_7956133.png "屏幕截图.png")

#### 使用说明
1. 创建数据库 且 字段名相同 当然可以改变 改变后代码也需要更改 已开发需求而定
2. 更改数据库配置文件 druid.properties 修改对应的url、账号、密码、驱动（此项目驱动较低 若使用8.0以上数据库 请更改驱动）
3. 更改支付宝沙箱配置类 AlipayConfig.java 修改对应的支付宝沙箱信息  更改信息参考（[支付宝开放平台](https://open.alipay.com/)）