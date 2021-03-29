package com.atguigu.vod.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

public class VodClientInitUtils {
    /**
     * 初始化DefaultAcsClient
     * @param regionId 区域
     * @param keyId 秘钥ID
     * @param keySecret 秘钥
     * @return
     */
    public static DefaultAcsClient initClient(String regionId,String keyId,String keySecret){
        DefaultProfile profile = DefaultProfile.getProfile(regionId,keyId,keySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    /**
     * 初始化DefaultAcsClient
     * @param keyId 秘钥ID
     * @param keySecret 秘钥
     * @return
     */
    public static DefaultAcsClient initClient(String keyId,String keySecret){
        return initClient("cn-shanghai",keyId,keySecret);
    }
}
