package gitluck.com.githubentry.Bean;

/**
 * Created by xiao on 3/15/16.
 */
public class ItemContent {
    public int itemImageResid;

    public ItemContent(int itemImageResid, String itemTitle) {
        this.itemImageResid = itemImageResid;
        this.itemTitle = itemTitle;
    }

    public String itemTitle;
}
