package pr.zx.starcommunity.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.junit.Test;
import org.springframework.stereotype.Component;
import pr.zx.starcommunity.dto.AccessTokenDTO;
import pr.zx.starcommunity.dto.GithubUserDTO;

import java.io.IOException;
@Component
public class GithubUserProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) throws IOException {
        MediaType mediaType= MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string=response.body().string();
            System.out.println(string);
            return string;
        }
    }
    public GithubUserDTO getGithubUser(String string)
    {
    String accessToken=string.split("&")[0].split("=")[1];
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();

        try (Response response = client.newCall(request).execute()){
            String s1=response.body().string();
//            System.out.println(response.body().string());//response.body().string()当下的流只能使用一次,就像流水这秒流的就会过去
            System.out.println(s1);
            GithubUserDTO githubUserDTO= JSON.parseObject(s1, GithubUserDTO.class);
            return  githubUserDTO;
            //return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;


    }
}
