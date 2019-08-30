package scnxq.com.appbase.http;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import scnxq.com.appbase.http.FastJsonConverterFactory;

/**
 * Created by nextsmile on 2016/7/31.
 */
public class HttpUtils {
    private static Map<String, Retrofit> retrofitMap = new ConcurrentHashMap<>();

    private static OkHttpClient createHttpCLient(Interceptor... appInterceptors) {
        OkHttpClient.Builder clitentBulider = new OkHttpClient.Builder();
        clitentBulider.connectTimeout(10, TimeUnit.SECONDS);
        clitentBulider.writeTimeout(60, TimeUnit.SECONDS);
        clitentBulider.readTimeout(60, TimeUnit.SECONDS);
        if (null != appInterceptors) {
            for (Interceptor interceptor : appInterceptors) {
                clitentBulider.addInterceptor(interceptor);
            }
        }
        return clitentBulider.build();
    }

    /**
     * 支持多个BASEURL
     */
    public static void initRetrofit(String baseUrl, DynamicHeaderService dynamicHeaderService) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(FastJsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.client(createHttpCLient(new HeaderInterceptor(dynamicHeaderService), httpLoggingInterceptor));
        retrofitMap.put(baseUrl, builder.build());
    }


    public static <T> T getInterface(String baseUrl, Class<T> cls) {
        if (retrofitMap.containsKey(baseUrl)) {
            throw new RuntimeException("Please call initRetrofit when you use it");
        }
        //检查返回值
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            Class<?> returnType = method.getReturnType();
            if (!(returnType.equals(Observable.class))) {
                throw new RuntimeException("InterFace returnType must  be Observable");
            }
        }
        return retrofitMap.get(baseUrl).create(cls);
    }

}
