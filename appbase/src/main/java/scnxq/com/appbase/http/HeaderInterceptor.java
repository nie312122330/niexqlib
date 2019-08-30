package scnxq.com.appbase.http;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    private DynamicHeaderService dynamicHeaderService = null;

    public HeaderInterceptor(DynamicHeaderService dynamicHeaderService) {
        this.dynamicHeaderService = dynamicHeaderService;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        Set<Map.Entry<String, String>> entries = dynamicHeaderService.dynamicHeaders().entrySet();
        for (Map.Entry<String, String> en : entries) {
            requestBuilder.addHeader(en.getKey(), en.getValue());
        }
        return chain.proceed(requestBuilder.build());
    }
}
