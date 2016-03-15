package gitluck.com.githubentry.response;

/**
 * Created by xiao on 3/14/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Milestone {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("labels_url")
    @Expose
    private String labelsUrl;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("creator")
    @Expose
    private Creator creator;
    @SerializedName("open_issues")
    @Expose
    private Integer openIssues;
    @SerializedName("closed_issues")
    @Expose
    private Integer closedIssues;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("closed_at")
    @Expose
    private String closedAt;
    @SerializedName("due_on")
    @Expose
    private String dueOn;

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
     * The labelsUrl
     */
    public String getLabelsUrl() {
        return labelsUrl;
    }

    /**
     *
     * @param labelsUrl
     * The labels_url
     */
    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     *
     * @param number
     * The number
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     *
     * @return
     * The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     * The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The creator
     */
    public Creator getCreator() {
        return creator;
    }

    /**
     *
     * @param creator
     * The creator
     */
    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    /**
     *
     * @return
     * The openIssues
     */
    public Integer getOpenIssues() {
        return openIssues;
    }

    /**
     *
     * @param openIssues
     * The open_issues
     */
    public void setOpenIssues(Integer openIssues) {
        this.openIssues = openIssues;
    }

    /**
     *
     * @return
     * The closedIssues
     */
    public Integer getClosedIssues() {
        return closedIssues;
    }

    /**
     *
     * @param closedIssues
     * The closed_issues
     */
    public void setClosedIssues(Integer closedIssues) {
        this.closedIssues = closedIssues;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     * @return
     * The closedAt
     */
    public String getClosedAt() {
        return closedAt;
    }

    /**
     *
     * @param closedAt
     * The closed_at
     */
    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    /**
     *
     * @return
     * The dueOn
     */
    public String getDueOn() {
        return dueOn;
    }

    /**
     *
     * @param dueOn
     * The due_on
     */
    public void setDueOn(String dueOn) {
        this.dueOn = dueOn;
    }

}