package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000117643486";
	
	// 商户私钥，您的PKCS8格式RSA2私钥                                         
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDLAY2C2riXXzSaxHZhpq1DcratMyePkOit1H4nNGr7n6dYVggxUfZcdVaXUNEfR8P+G8ZYE4u8qW8QYDQrEVYaEczXibJpy7odhAie0frAk1ijlro5caSm9B1STI/IMH2DXTLwQzQUWMp+S1AncBrNcsCESF/vtBheVjiiDHrtCFJ/AQNs4rfxS23/2jGgDc2r5wYbBjookkWQcjC2LaOUObleB5AHBUiFQ1hlYUuvCglqqnL0BtBIPAsrhs19gz4hjpFVD98/Ogh/MNKLPXbFGT+8HPKu+KJkaH2qOrQAT7x2e23wmODVEh9Dgg3vOAmvenTf/QyTCnierlVexWfAgMBAAECggEAAn7w81BOTVZYDKzq3we3o+BKTtUH2671nH/+eEuLlcjo8derVJxMXK/Uzm5d3EzBLY2dl48EjdIiIy3f5JiSjTcWu7x74nRi6Rtt/Sn5WVhm3ow04J+Woq2CTukAosDdz++7nDDlLIWP7HmISeV2htO0NCrdR0/7KQ+lCFJtGL4CjtQjEm8TradLWTuftwf6MZmW5x1p1fSGswPnKVPaGGk4g0WaxHVeBTiQm7fZDlYz1TibBLIb4PF2/EQibDsOnVADv2Hk0OBGqzp2ivzhLrUKtNxl99HgIVoacyqLu+9vUraiOztFlw22spxQxmdnlCtxFtsdVUtxufeWygEQoQKBgQDiaA2vKuTvkCAJ7Z0/UU8CLQqKsJHEP+PY9SEjYYNFwYVxCQbbKpgZtJ2cvqqTH8jRM6ry3ryy4DhHMNTIVPvYXS7yXxBPhC6N/1FRJKsu0zHSbwxYY45Y2lA30121zzV5ElQi5Scwa9/bkbN5Vpc46u0teaAZfZR+AomjS/RT0QKBgQCUUUMjSxCwGd0vLcAyWy7nHPyf8INL9hCgQxUdBL52sxF7GPrM0o//AIqROEb2iXUMv2gozTq0vJqp3DWQZEQkhKEsg5fchC5RNEw8rYd9nu83Og6jQxQXfsIsfqZgobX1eHl8bbRIK4LY1jS0gXGdd40kKwadpmSGBDa6+IRebwKBgB4AA7AuUt+bjWNDZQ66y2lSZo8yDl6eyKY69QUnKqnXlsL/mzT3U+g0Ep5udVPBZm6yFfjQothg9ZQZWFijSivFTS84gYTH0xNlvXSvEy/FNFjZ9hzQNFB5vR9oloY8yq0cTKHDE4pLAQKrL8joPGmCV6Mq9pfqF/8KfwpwShPBAoGBAI1DaMKwuFBCEhzhYnGTz3Roc2SSWzt6PhOmUPG2lwHPRBMOk7GJy4SBK3ltNImUGlvt4nMaJZQHHvgxuQKxvQVbHXecToPFkafeO85/KNFJE2ZycYamWPiXzrE6X5Uh8IDAIlWU12T1rEaCnuhdca0dNN0B7ecxmATc9pPWJ50ZAoGAVIxDHhjktIF5sGlDuBqaa5mdW9W+zwN1xUUyNCFAfsWk0t4z17UxoX/b08CtTDiicUrXa7HPpQSahmZuxNBxJ0pOCifxLwQQSnh4K6DckVsfoko7dGkC2f86TPytc95Mno7FTG5LPuVBEs6VaH7yZqiJrJ3ZRb5gc8mtT0WIiaw=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7OXhypJ1BhuWz/ndkcoROaqy7fDaXXfBXnlGfeStyC9kPnrTH19fkkmduK24r+ysU2CO7lStoV4xMcIVbecJ2gLFbUuEcHYXbwNVLsNTabgb+021SencVm17xLFvwKQwEViObAbWoWjxA09RBb4egShun3QNaI42oUsMUSPuX4T8KviXIJys4Co6Pk8OSRqH9R2eEGoMojFA7FCmAEvkAKLe+aO5oFDRWLuezxE21yxSBlv21coIMdQPDP5p9XylaCHivAx58fupEHCrctveGo7RbHQsrmmVdfb0SNXSRYDzRHn3WU+ZfaXWa6Ct5KiykPoeJNdjSEGYvltkKY2VhwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://127.0.0.1:8080/mi/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://127.0.0.1:8080/mi/payment";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

