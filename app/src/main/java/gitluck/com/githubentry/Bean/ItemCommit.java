package gitluck.com.githubentry.Bean;

/**
 * Created by xiao on 3/15/16.
 */
public class ItemCommit {
    public int itemImageResid;
    public String itemTitle;
    public String itemUser;
    public String itemUpdatedTime;



    public ItemCommit(int itemImageResid, String itemTitle, String itemUser, String itemUpdatedTime) {
        this.itemImageResid = itemImageResid;
        this.itemTitle = itemTitle;
        this.itemUser = itemUser;
        this.itemUpdatedTime = itemUpdatedTime;
    }
}
