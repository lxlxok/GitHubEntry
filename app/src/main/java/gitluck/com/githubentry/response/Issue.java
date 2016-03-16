package gitluck.com.githubentry.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiao on 3/14/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Issue {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("repository_url")
    @Expose
    private String repositoryUrl;
    @SerializedName("labels_url")
    @Expose
    private String labelsUrl;
    @SerializedName("comments_url")
    @Expose
    private String commentsUrl;
    @SerializedName("events_url")
    @Expose
    private String eventsUrl;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("user")
    @Expose
    private IssueUser user;
    @SerializedName("labels")
    @Expose
    private List<Label> labels = new ArrayList<Label>();
    @SerializedName("assignee")
    @Expose
    private Assignee assignee;
    @SerializedName("milestone")
    @Expose
    private Milestone milestone;
    @SerializedName("locked")
    @Expose
    private Boolean locked;
    @SerializedName("comments")
    @Expose
    private Integer comments;
    @SerializedName("pull_request")
    @Expose
    private PullRequest pullRequest;
    @SerializedName("closed_at")
    @Expose
    private Object closedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

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
     * The repositoryUrl
     */
    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    /**
     *
     * @param repositoryUrl
     * The repository_url
     */
    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
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
     * The commentsUrl
     */
    public String getCommentsUrl() {
        return commentsUrl;
    }

    /**
     *
     * @param commentsUrl
     * The comments_url
     */
    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    /**
     *
     * @return
     * The eventsUrl
     */
    public String getEventsUrl() {
        return eventsUrl;
    }

    /**
     *
     * @param eventsUrl
     * The events_url
     */
    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
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
     * The body
     */
    public String getBody() {
        return body;
    }

    /**
     *
     * @param body
     * The body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     *
     * @return
     * The user
     */
    public IssueUser getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(IssueUser user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The labels
     */
    public List<Label> getLabels() {
        return labels;
    }

    /**
     *
     * @param labels
     * The labels
     */
    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    /**
     *
     * @return
     * The assignee
     */
    public Assignee getAssignee() {
        return assignee;
    }

    /**
     *
     * @param assignee
     * The assignee
     */
    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

    /**
     *
     * @return
     * The milestone
     */
    public Milestone getMilestone() {
        return milestone;
    }

    /**
     *
     * @param milestone
     * The milestone
     */
    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    /**
     *
     * @return
     * The locked
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     *
     * @param locked
     * The locked
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    /**
     *
     * @return
     * The comments
     */
    public Integer getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     * The comments
     */
    public void setComments(Integer comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     * The pullRequest
     */
    public PullRequest getPullRequest() {
        return pullRequest;
    }

    /**
     *
     * @param pullRequest
     * The pull_request
     */
    public void setPullRequest(PullRequest pullRequest) {
        this.pullRequest = pullRequest;
    }

    /**
     *
     * @return
     * The closedAt
     */
    public Object getClosedAt() {
        return closedAt;
    }

    /**
     *
     * @param closedAt
     * The closed_at
     */
    public void setClosedAt(Object closedAt) {
        this.closedAt = closedAt;
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

}
