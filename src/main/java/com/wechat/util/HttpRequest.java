package com.wechat.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * Created by rongyaowen
 * on 2018/10/22.
 */
public class HttpRequest {
    private static final String PROXY_HOST_NAME = "";
    private static final int PROXY_PORT = 0;
    private static final String PROXY_SCHEME = "http";
    // 需要代理请改为true
    private static final boolean OPEN_PROXY = false;

    /**
     * get请求
     *
     * @param url
     * @param ignoreHttps 是否忽略https证书 true 忽略
     * @return
     */
    public static String get(String url, Map<String, String> header, boolean ignoreHttps) {
        StringBuffer logSb = new StringBuffer();
        logSb.append("[GET]-").append("url:[" + url + "]-").append("header:[");
        // 创建httpGet请求，设置httpGet请求的头部信息
        HttpGet httpGet = new HttpGet(url);
        // 设置头信息
        if (null != header && !header.isEmpty()) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpGet.addHeader(entry.getKey(), entry.getValue());
                logSb.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
            }
        }
        logSb.append("]-");
        logSb.append(ignoreHttps == true ? "[忽略https证书]-" : "[不忽略https证书]-");
        logSb.append(OPEN_PROXY == true ? ("[开启代理" + PROXY_SCHEME + "://" + PROXY_HOST_NAME + ":" + PROXY_PORT + "]-") : "[未开启代理]");
        LogUtil.info(logSb);
        String resStr = response(httpGet, ignoreHttps);
        LogUtil.info("响应报文：[" + resStr + "]");
        return resStr;
    }

    /**
     * post请求
     *
     * @param url
     * @param param       请求体参数
     * @param header      请求头
     * @param cotentType  请求数据类型
     * @param ignoreHttps 是否忽略https证书 true 忽略
     * @return
     */
    public static String post(String url, String param, Map<String, String> header, String cotentType, boolean ignoreHttps) {
        StringBuffer logSb = new StringBuffer();
        logSb.append("[GET]-").append("url:[" + url + "]-").append("param:[" + param + "]-").append("header:[");

        HttpPost httpPost = new HttpPost(url);
        // 设置请求数据
        StringEntity stringEntity = new StringEntity(param, Charset.forName("UTF-8"));
        stringEntity.setContentEncoding("UTF-8");
        // 发送Json格式的数据请求
        stringEntity.setContentType(cotentType);
        httpPost.setEntity(stringEntity);
        // 设置头信息
        if (null != header && !header.isEmpty()) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
                logSb.append(entry.getKey() + ":" + entry.getValue());
            }
        }
        logSb.append("]-");
        logSb.append("cotentType" + "[" + cotentType + "]-");
        logSb.append(ignoreHttps == true ? "[忽略https证书]-" : "[不忽略https证书]-");
        logSb.append(OPEN_PROXY == true ? ("[开启代理" + PROXY_SCHEME + "://" + PROXY_HOST_NAME + ":" + PROXY_PORT + "]-") : "[未开启代理]");
        LogUtil.info(logSb);
        String resStr = response(httpPost, ignoreHttps);
        LogUtil.info("响应报文：[" + resStr + "]");
        return resStr;
    }

    /**
     * post请求获取流对象
     *
     * @param url
     * @param param
     * @param header
     * @param cotentType
     * @param ignoreHttps
     * @return
     */
    public static InputStream postAsStream(String url, String param, Map<String, String> header, String cotentType, boolean ignoreHttps) {
        StringBuffer logSb = new StringBuffer();
        logSb.append("[GET]-").append("url:[" + url + "]-").append("param:[" + param + "]-").append("header:[");

        HttpPost httpPost = new HttpPost(url);
        // 设置请求数据
        StringEntity stringEntity = new StringEntity(param, Charset.forName("UTF-8"));
        stringEntity.setContentEncoding("UTF-8");
        // 发送Json格式的数据请求
        stringEntity.setContentType(cotentType);
        httpPost.setEntity(stringEntity);
        // 设置头信息
        if (null != header && !header.isEmpty()) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
                logSb.append(entry.getKey() + ":" + entry.getValue());
            }
        }
        logSb.append("]-");
        logSb.append("cotentType" + "[" + cotentType + "]-");
        logSb.append(ignoreHttps == true ? "[忽略https证书]-" : "[不忽略https证书]-");
        logSb.append(OPEN_PROXY == true ? ("[开启代理" + PROXY_SCHEME + "://" + PROXY_HOST_NAME + ":" + PROXY_PORT + "]-") : "[未开启代理]");
        LogUtil.info(logSb);
        return responseAsStream(httpPost, ignoreHttps);
    }

    /**
     * 获取请求数据
     *
     * @param httpRequestBase
     * @param ignoreHttps
     * @return
     */
    private static InputStream responseAsStream(HttpRequestBase httpRequestBase, boolean ignoreHttps) {
        CloseableHttpClient closeableHttpClient;
        if (ignoreHttps) {
            closeableHttpClient = getIgnoreHttpsClient();
        } else {
            closeableHttpClient = getClient();
        }
        CloseableHttpResponse closeableHttpResponse = null;
        String res = null;
        InputStream inputStream = null;
        try {
            closeableHttpResponse = closeableHttpClient.execute(httpRequestBase);
            if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = closeableHttpResponse.getEntity();
                inputStream = entity.getContent();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != closeableHttpResponse) {
                    closeableHttpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return inputStream;
    }

    /**
     * 获取请求数据
     *
     * @param httpRequestBase
     * @param ignoreHttps
     * @return
     */
    private static String response(HttpRequestBase httpRequestBase, boolean ignoreHttps) {
        CloseableHttpClient closeableHttpClient;
        if (ignoreHttps) {
            closeableHttpClient = getIgnoreHttpsClient();
        } else {
            closeableHttpClient = getClient();
        }
        CloseableHttpResponse closeableHttpResponse = null;
        String res = null;
        try {
            closeableHttpResponse = closeableHttpClient.execute(httpRequestBase);
            if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = closeableHttpResponse.getEntity();
                res = EntityUtils.toString(entity, "utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != closeableHttpResponse) {
                    closeableHttpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    /**
     * @return
     */
    private static RequestConfig getRequestConfig() {
        // 依次是代理地址，代理端口号，协议类型
        HttpHost proxy = new HttpHost(PROXY_HOST_NAME, PROXY_PORT, PROXY_SCHEME);
        if (OPEN_PROXY) {
            return RequestConfig.custom().setProxy(proxy).setConnectionRequestTimeout(20000).setConnectTimeout(20000).setSocketTimeout(20000).build();
        } else {
            return RequestConfig.custom().setConnectionRequestTimeout(20000).setConnectTimeout(20000).setSocketTimeout(20000).build();
        }
    }

    /**
     * 获取忽略https的client
     *
     * @return
     * @throws Exception
     */
    private static CloseableHttpClient getIgnoreHttpsClient() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContexts.custom().useTLS().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType) {
                    //信任所有
                    return true;
                }
            }).build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new AllowAllHostnameVerifier());
        Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory>create().register("https", sslConnectionSocketFactory).build();
        //设置连接池，配置过期时间为20s。
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(r);
        cm.setMaxTotal(500);
        cm.setDefaultMaxPerRoute(350);
        SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(true).setTcpNoDelay(true).setSoTimeout(20000).build();
        cm.setDefaultSocketConfig(socketConfig);
        RequestConfig requestConfig = getRequestConfig();
        //创建httpClient
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().setConnectionManager(cm).setDefaultRequestConfig(requestConfig).build();
        return closeableHttpClient;
    }


    /**
     * 获取httpclient
     *
     * @return
     */
    private static CloseableHttpClient getClient() {
        //设置连接池，配置过期时间为20s。
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(500);
        cm.setDefaultMaxPerRoute(350);
        SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(true).setTcpNoDelay(true).setSoTimeout(20000).build();
        cm.setDefaultSocketConfig(socketConfig);
        RequestConfig requestConfig = getRequestConfig();
        //创建httpClient
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().setConnectionManager(cm).setDefaultRequestConfig(requestConfig).build();
        return closeableHttpClient;
    }
}
