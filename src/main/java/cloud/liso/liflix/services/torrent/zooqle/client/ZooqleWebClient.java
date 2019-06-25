package cloud.liso.liflix.services.torrent.zooqle.client;

import cloud.liso.liflix.exceptions.ZoogleWebClientErrorException;
import cloud.liso.liflix.services.httpClient.WebClient;
import cloud.liso.liflix.services.httpClient.WebPage;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ZooqleWebClient implements WebClient {

    @Override
    public WebPage fetchWebPage(String url) {
        int timeout = 5;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();

        WebPage webpage = null;
        Header contentType = new BasicHeader(
                HttpHeaders.CONTENT_TYPE, "text/html");
        Header agent = new BasicHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        List<Header> headers = Arrays.asList(contentType, agent);

        try (CloseableHttpClient client = HttpClientBuilder.create()
                .setDefaultHeaders(headers)
                .setDefaultRequestConfig(config)
                .build()) {

            CloseableHttpResponse response = client.execute(new HttpGet(url));
            HttpEntity entity = response.getEntity();
            webpage = new WebPage(EntityUtils.toString(entity));
        } catch (IOException e) {
            throw new ZoogleWebClientErrorException(e.getMessage());
        }

        return webpage;

    }
}
