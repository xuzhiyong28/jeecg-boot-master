package org.jeecg;

import cn.hutool.json.JSONObject;
import org.apache.poi.ss.util.ImageUtils;
import org.jeecg.common.util.security.SecurityTools;
import org.jeecg.common.util.security.entity.*;
import org.jeecg.modules.demo.test.util.ImageSyUtils;
import org.junit.Test;

import java.io.*;

public class SecurityToolsTest {
    @Test
    public void Test(){
        MyKeyPair mkeyPair = SecurityTools.generateKeyPair();

        JSONObject msg = new JSONObject();
        msg.put("name", "党政辉");
        msg.put("age", 50);
        JSONObject identity = new JSONObject();
        identity.put("type", "01");
        identity.put("no", "210882165896524512");
        msg.put("identity", identity);

        // 签名加密部分
        SecuritySignReq signReq = new SecuritySignReq();
        // data为要加密的报文字符串
        signReq.setData(msg.toString());
        // 为rsa私钥
        signReq.setPrikey(mkeyPair.getPriKey());
        // 调用签名方法
        SecuritySignResp sign = SecurityTools.sign(signReq);
        // 打印出来加密数据
        // signData为签名数据
        // data为aes加密数据
        // asekey为ras加密过的aeskey
        System.out.println(new JSONObject(sign).toStringPretty());

        // 验签解密部分
        SecurityReq req = new SecurityReq();
        //对方传过来的数据一一对应
        req.setAesKey(sign.getAesKey());
        req.setData(sign.getData());
        req.setSignData(sign.getSignData());
        //我们的公钥
        req.setPubKey(mkeyPair.getPubKey());
        //验签方法调用
        SecurityResp securityResp = SecurityTools.valid(req);
        //解密报文data为解密报文
        //sucess 为验签成功失败标志 true代码验签成功，false代表失败
        System.out.println(new JSONObject(securityResp).toStringPretty());
    }

    @Test
    public void testImg() throws IOException {
        FileInputStream fis = new FileInputStream("D:\\1.jpg");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int n;
        while ((n = fis.read(b)) != -1)
        {
            bos.write(b, 0, n);
        }
        fis.close();
        bos.close();
        byte[] buffer = bos.toByteArray();
        byte[] newBuffer = ImageSyUtils.imgAddSy(buffer);
        BufferedOutputStream bos2 = null;
        FileOutputStream fos = null;
        File file = new File("D:\\2.jpg");
        fos = new FileOutputStream(file);
        bos2 = new BufferedOutputStream(fos);
        bos2.write(newBuffer);
    }

    @Test
    public void testFile(){
        System.out.println(ImageSyUtils.class.getClassLoader().getResource("sy.jpg").getPath());
        System.out.println(ImageSyUtils.class.getProtectionDomain().getCodeSource().getLocation().getFile());
    }


}

