package com.yulong.common.utils.http;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

/**
 * created by 赵玉龙
 * on 2020/5/25-11:21
 */
public class HttpClientUtils {
    //打印在主⽇志⽂件
    // private ﬁnal static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    // 如果发起的http请求返回结果为null,暂定返回空字符串
    private final static String DEFAULT_RETURN = "";
    private static CloseableHttpClient httpClient;
    private static Charset UTF_8 = Charset.forName("UTF-8");
    private final static String UTF_8_STRING = "UTF-8";
    private static RequestConfig requestConﬁg;

    /**
     * ⾃定义http连接池
     **/
    static {
        PoolingHttpClientConnectionManager poolConn = new PoolingHttpClientConnectionManager();
        poolConn.setMaxTotal(30);
        poolConn.setDefaultMaxPerRoute(5);
        requestConﬁg = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(9000).build();
        httpClient = HttpClients.custom().setDefaultRequestConfig(requestConﬁg).setConnectionManager(poolConn).build();
    }

    /**
     * 删除操作
     * * @param url
     * * @param params
     * * @return
     */
    public static String httpRequestDelete(String url, Map<String, String> params) {
        HttpDelete httpDelete = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if (params.size() > 0) {
                ArrayList<NameValuePair> pairs = convertParamsToNameValuesP(params);
                builder.setParameters(pairs);
            }
            httpDelete = new HttpDelete(builder.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return getResult(httpDelete);
    }

    /**
     * 有参GET请求
     * * @param url
     * * @param params
     * * @return
     * * @throws Exception
     */
    public static String httpRequestGet(String url, Map<String, String> params) {
        HttpGet httpGet = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            ArrayList<NameValuePair> pairs = convertParamsToNameValuesP(params);
            if (pairs.size() > 0) {
                builder.setParameters(pairs);
            }
            httpGet = new HttpGet(builder.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return getResult(httpGet);
    }

    /**
     * ⽆参GET请求
     * * @param url
     * * @return
     * * @throws Exception
     */
    public static String httpRequestGetNoParams(String url) {
        HttpGet httpGet = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            httpGet = new HttpGet(builder.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return getResult(httpGet);
    }

    /**
     * 表单POST请求
     * * @param url
     * * @param params
     * * @return
     */
    public static String httpRequestPost(String url, Map<String, String> params) {
        HttpPost httpPost = null;
        try {
            ArrayList<NameValuePair> pairs = convertParamsToNameValuesP(params);
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, UTF_8);
            httpPost = new HttpPost(url);
            httpPost.setEntity(entity);
            httpPost.setConfig(requestConﬁg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getResult(httpPost);
    }

    /**
     * JSON实体请求
     * * @param url
     * * @param encodJson
     * * @return
     * * @throws Exception
     */
    public static String httpRequestPostWithBody(String url, String encodJson) {
        URIBuilder builder = new URIBuilder();
        builder.setPath(url);
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(builder.build());
            httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
            httpPost.setEntity(new StringEntity(encodJson, UTF_8_STRING));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return getResult(httpPost);
    }

    /**
     * 将URL和表单中的参数转换为Name-Value对象放⼊List中
     * * @param params
     * * @return
     */
    private static ArrayList<NameValuePair> convertParamsToNameValuesP(Map<String, String> params) {
        ArrayList<NameValuePair> list = new ArrayList<>();
        for (Map.Entry<String, String> param : params.entrySet()) {
            list.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }
        return list;
    }

    /**
     * 实际请求发送
     * * @param request
     * * @return
     * * @throws Exception
     */
    private static String getResult(HttpRequestBase request) {
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                response.close();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            //如果返回结果为null，则返回空字符串
            return DEFAULT_RETURN;
        }
    }
