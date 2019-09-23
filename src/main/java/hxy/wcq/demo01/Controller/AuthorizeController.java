package hxy.wcq.demo01.Controller;

import hxy.wcq.demo01.dto.AccessTokenDTO;
import hxy.wcq.demo01.dto.GithubUser;
import hxy.wcq.demo01.provide.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("2983af3dc005997fefc5");
        accessTokenDTO.setClient_secret("1b79a7c1b20ee1962c0035a3f95e7a12fcf3bd60");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.print(user.getName());
        return "index";
    }
}
