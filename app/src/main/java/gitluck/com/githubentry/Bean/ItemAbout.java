package gitluck.com.githubentry.Bean;

/**
 * Created by Administrator on 2/28/2016.
 */
public class ItemAbout {
    public String imgurl;
    public String username;
    public String realname;
    public String email;
    public String location;
    public String company;
    public int followerNum;
    public int followingNum;
    public int reposNum;


    public ItemAbout(String imgurl, String username, String realname, String email, String location, String company, int followerNum, int followingNum, int reposNum) {
        this.imgurl = imgurl;
        this.username = username;
        this.realname = realname;
        this.email = email;
        this.location = location;
        this.company = company;
        this.followerNum = followerNum;
        this.followingNum = followingNum;
        this.reposNum = reposNum;
    }

    public ItemAbout(String imgurl, String username, String email) {
        this.imgurl = imgurl;
        this.username = username;
        this.email = email;
    }
}
