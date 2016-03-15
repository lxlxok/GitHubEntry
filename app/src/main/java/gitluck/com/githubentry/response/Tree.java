package gitluck.com.githubentry.response;

/**
 * Created by xiao on 3/15/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tree {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("sha")
    @Expose
    private String sha;

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The sha
     */
    public String getSha() {
        return sha;
    }

    /**
     *
     * @param sha
     * The sha
     */
    public void setSha(String sha) {
        this.sha = sha;
    }

}