import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;

public class MyTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        //获取token
        String token = YiMa.getToken();
        //通过token获取手机号码
        String mobileNumber = YiMa.getMobileNumber(token);
        System.out.println("mobileNumber="+mobileNumber);
        //给项目发送指令，让项目发送验证码给手机
        Project.projectSendMsg(mobileNumber);
        //易码获取短信验证码
        String validCode = YiMa.getValidCode(mobileNumber,token);
        System.out.println(validCode);
        //设置国籍、手机号码、验证码、登陆密码、邀请码进行注册，此处只传入手机号码和验证码，其他信息在项目类中进行处理
        Project.register(mobileNumber,validCode,"pQJsVZ7G");

    }
}
