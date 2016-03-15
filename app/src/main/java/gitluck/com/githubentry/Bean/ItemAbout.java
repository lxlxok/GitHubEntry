package gitluck.com.githubentry.Bean;

/**
 * Created by Administrator on 2/28/2016.
 */
public class ItemAbout {
    public String imgurl;
    public String username;
    public String email;
    public String location;
    public String company;
    public String followerNum;
    public String followingNum;
    public String reposNum;


    public ItemAbout(String imgurl, String username, String email, String location, String company, int followerNum, int followingNum, int reposNum) {
        this.imgurl = imgurl;
        this.username = username;
        this.email = email;
        this.location = location;
        this.company = company;
        this.followerNum = String.valueOf(followerNum);
        this.followingNum = String.valueOf(followingNum);
        this.reposNum = String.valueOf(reposNum);
    }

}
