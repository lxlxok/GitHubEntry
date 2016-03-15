package gitluck.com.githubentry.Bean;

/**
 * Created by xiao on 3/14/16.
 */
public class ItemIssue {
    public int itemImageResid;

    public ItemIssue(int itemImageResid, String itemTitle, String itemUser, String itemUpdatedTime) {
        this.itemImageResid = itemImageResid;
        this.itemTitle = itemTitle;
        this.itemUser = itemUser;
        this.itemUpdatedTime = itemUpdatedTime;
    }

    public String itemTitle;
    public String itemUser;
    public String itemUpdatedTime;


}
