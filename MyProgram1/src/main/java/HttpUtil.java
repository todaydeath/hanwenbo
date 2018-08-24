import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * create by hwx533443
 */
public class HttpUtil {
    private static CloseableHttpClient httpClient = null;
    static {
        httpClient = HttpClientBuilder.create().build();
    }

    public static CloseableHttpClient getHttpClient(){
        return httpClient;
    }
}
