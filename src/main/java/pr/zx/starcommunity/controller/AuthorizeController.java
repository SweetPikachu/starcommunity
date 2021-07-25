package pr.zx.starcommunity.controller;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pr.zx.starcommunity.dto.AccessTokenDTO;
import pr.zx.starcommunity.dto.GithubUserDTO;
import pr.zx.starcommunity.provider.GithubUserProvider;

import java.io.IOException;

@Controller
public class AuthorizeController {

    @Autowired
   private GithubUserProvider githubUserProvider;
        @GetMapping("/callback")
        public String callback(@RequestParam(name="code") String code,
                               @RequestParam(name="state", required=false) String state
                               )
        {
            AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
            accessTokenDTO.setClient_id("36ce2b893d89fdb39007");
            accessTokenDTO.setClient_secret("b8a285f6dc43fc08dbc843cf33f134482b55e09e");
            accessTokenDTO.setRedirect_uri("http://localhost:8881/callback");
            accessTokenDTO.setCode(code);
            accessTokenDTO.setState(state);
            try {
                String accessToken=githubUserProvider.getAccessToken(accessTokenDTO);
                GithubUserDTO githubUserDTO=githubUserProvider.getGithubUser(accessToken);
                System.out.println(githubUserDTO.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return  "index";
        }


        }



