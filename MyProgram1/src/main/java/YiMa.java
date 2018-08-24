import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * create by hwx533443
 */
public class YiMa {
    private final static String itemid = "21872";  //项目编号，目前默认为LAEX的项目编号
    private final static String userName = "13832370650";  //易码用户名
    private final static String password = "a9410121114";  //易码密码
    //易码登陆的URL
    private final static String loginUrl = "http://api.fxhyd.cn/UserInterface.aspx?action=login&username="+userName+"&password="+password;

    //通过用户名和密码进行登陆，获取token
    public static String getToken() throws IOException {
        CloseableHttpClient httpClient = HttpUtil.getHttpClient();
        HttpGet httpGet = new HttpGet(loginUrl);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        String str = EntityUtils.toString(httpResponse.getEntity());
        //获取token
        String token = null;
        if(str.startsWith("success")){
            token = str.substring(8);
        }else{
            System.out.println("getToken failed");
        }
        return token;
    }

    //通过token获取手机号码
    public  static String getMobileNumber(String token) throws IOException {
        CloseableHttpClient httpClient = HttpUtil.getHttpClient();
        String getMobileNumberUrl = "http://api.fxhyd.cn/UserInterface.aspx?action=getmobile&token="+token+"&itemid="+itemid;
        HttpResponse httpResponseGetNumber = httpClient.execute(new HttpGet(getMobileNumberUrl));
        String getNumber = EntityUtils.toString(httpResponseGetNumber.getEntity());
        if(getNumber.startsWith("success")){
            getNumber = getNumber.substring(8);
        }else{
            System.out.println("getMobileNumber failed");
        }
        return getNumber;
    }

    //通过手机号码和token获取校验码
    public static String getValidCode(String mobileNumber,String token) throws IOException {
        CloseableHttpClient httpClient = HttpUtil.getHttpClient();
        long time1 = System.currentTimeMillis();
        String validMsg = null;
        while(true){
            //获取短信,同时释放手机号
            String getMessageUrl = "http://api.fxhyd.cn/UserInterface.aspx?action=getsms&token="+token+"&itemid=21872&mobile="+mobileNumber+"&release=1";
            HttpResponse getMessageResponse = httpClient.execute(new HttpGet(getMessageUrl));
            validMsg = EntityUtils.toString(getMessageResponse.getEntity(),"UTF-8");
            if(validMsg.startsWith("success")){
                break;
            }
            long time2 = System.currentTimeMillis();
            if((time2 - time1) > 1000*60){
                System.out.println("getValidCode time out");
                return null;
            }
        }
        //截取出校验码
        String validCode = validMsg.substring(validMsg.length()-4,validMsg.length());
        return validCode;
    }

    //释放手机号
    public static boolean relasePhoneNumber(String mobileNumber,String token) throws IOException {
        String relasePhoneNumberUrl = "http://api.fxhyd.cn/UserInterface.aspx?action=release&token="+token+"&itemid="+itemid+"&mobile="+mobileNumber;
        CloseableHttpClient httpClient = HttpUtil.getHttpClient();
        HttpResponse httpResponse = httpClient.execute(new HttpGet(relasePhoneNumberUrl));
        int status = httpResponse.getStatusLine().getStatusCode();
        if(status == 200){
            System.out.println("relase phoneNumber success");
            return true;
        }
        System.out.print("relase phoneNumber failed");
        return false;
    }
}
