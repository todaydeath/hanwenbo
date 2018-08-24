import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * create by hwx533443
 */
public class Project {
    //给项目发送指令，让项目发送验证码给手机
    public static void projectSendMsg(String phoneNumber) throws IOException {
        CloseableHttpClient httpClient = HttpUtil.getHttpClient();
        String sendMsgUrl = "https://www.laex.pro/v1/sendsms/register";
        HttpPost sendMsgPost = new HttpPost(sendMsgUrl);
        sendMsgPost.addHeader("content-type","application/json");
        String param = "{\"mobile\":\""+phoneNumber+"\",\"areaCode\":\"0086\"}";
        StringEntity stringEntity = new StringEntity(param);

        sendMsgPost.setEntity(stringEntity);
        HttpResponse httpResponse1 = httpClient.execute(sendMsgPost);
        if(httpResponse1.getStatusLine().getStatusCode() != 200){
            System.out.println("Project projectSendMsg failed");
        }
    }

    public static void register(String mobileNumber , String vaildCode,String inviteCode) throws IOException {
        String url = "https://www.laex.pro/v1/user/register";
        String password = System.currentTimeMillis()+"h";
        System.out.println("password ="+password);
        String param = "{\"countryId\":37,\"areaCode\":\"0086\",\"phoneNumber\":\""+mobileNumber+"\",\"email\"" +
                ":\"\",\"verifyCode\":\""+vaildCode+"\",\"password\":\""+password+"\",\"inviteCode\":\""+inviteCode+"\"}";
        CloseableHttpClient httpClient = HttpUtil.getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("content-type","application/json");

        StringEntity stringEntity = new StringEntity(param);
        httpPost.setEntity(stringEntity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        if(httpResponse.getStatusLine().getStatusCode() != 200){
            System.out.println("Project projectSendMsg failed");
        }else{
            System.out.println("Project projectSendMsg success");
        }
    }
}
