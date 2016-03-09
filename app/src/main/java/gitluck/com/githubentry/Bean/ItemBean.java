package gitluck.com.githubentry.Bean;

/**
 * Created by Administrator on 2/28/2016.
 */
public class ItemBean {
    public int itemImageResid;
    public String itemTitle;
    public String itemContent;

    public ItemBean(int itemImageResid, String itemTitle, String itemContent) {
        this.itemImageResid = itemImageResid;
        this.itemTitle = itemTitle;
        this.itemContent = itemContent;
    }
}
