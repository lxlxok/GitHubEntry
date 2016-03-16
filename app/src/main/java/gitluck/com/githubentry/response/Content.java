package gitluck.com.githubentry.response;

/**
 * Created by xiao on 3/15/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("sha")
    @Expose
    private String sha;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("git_url")
    @Expose
    private String gitUrl;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("download_url")
    @Expose
    private String downloadUrl;
    @SerializedName("_links")
    @Expose
    private Links links;

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The size
     */
    public Integer getSize() {
        return size;
    }

    /**
     *
     * @param size
     * The size
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The path
     */
    public String getPath() {
        return path;
    }

    /**
     *
     * @param path
     * The path
     */
    public void setPath(String path) {
        this.path = path;
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
     * The gitUrl
     */
    public String getGitUrl() {
        return gitUrl;
    }

    /**
     *
     * @param gitUrl
     * The git_url
     */
    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    /**
     *
     * @return
     * The htmlUrl
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     *
     * @param htmlUrl
     * The html_url
     */
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    /**
     *
     * @return
     * The downloadUrl
     */
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     *
     * @param downloadUrl
     * The download_url
     */
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    /**
     *
     * @return
     * The Links
     */
    public Links getLinks() {
        return links;
    }

    /**
     *
     * @param Links
     * The _links
     */
    public void setLinks(Links links) {
        this.links = links;
    }

}