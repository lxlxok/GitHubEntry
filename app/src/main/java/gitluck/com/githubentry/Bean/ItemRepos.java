package gitluck.com.githubentry.Bean;

/**
 * Created by xiao on 3/10/16.
 */
public class ItemRepos {

    public int itemImageResid;
    public String itemTitle;
    public String itemlanguage;
    public String starNum;
    public String watchNum;


    public ItemRepos(int itemImageResid, String itemTitle, String itemlanguage, int starNum, int watchNum) {
        this.itemImageResid = itemImageResid;
        this.itemTitle = itemTitle;
        this.itemlanguage = itemlanguage;
        this.starNum = String.valueOf(starNum);
        this.watchNum = String.valueOf(watchNum);
    }







}
