package com.atguigu.vod.test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.junit.Test;

public class TestOne {

    @Test
    public void testVod() throws ClientException {
        String regionId = "cn-shanghai";
        String keyId = "xxx";
        String secret = "xxx";
        DefaultProfile profile = DefaultProfile.getProfile(regionId,keyId,secret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("3089136dd00d4c14a8c261c3653acbe7");
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);
        String title = response.getVideoMeta().getTitle();
        System.out.println(title);
        String playAuth = response.getPlayAuth();
        System.out.println(playAuth);

    }
}
