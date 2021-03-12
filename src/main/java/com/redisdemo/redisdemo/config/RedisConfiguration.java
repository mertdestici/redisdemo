package com.redisdemo.redisdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;


@EnableCaching
@Configuration
public class RedisConfiguration {

    @Autowired
    CacheProperties cacheProperties;

    private String KEY_SEPERATOR = "#";

    @Bean
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getSimpleName());
                sb.append(KEY_SEPERATOR);
                sb.append(method.getName());
                sb.append(KEY_SEPERATOR);
                for(Object param : objects){
                    sb.append(objects.toString());
                    sb.append(objects.toString());
                }
                String str = sb.toString();
                return str.substring(0, str.length() - 1);
            }
        };
    }
}
