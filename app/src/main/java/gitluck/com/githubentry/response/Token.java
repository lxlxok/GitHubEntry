package gitluck.com.githubentry.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiao on 3/6/16.
 */
public class Token {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("scopes")
    @Expose
    private List<String> scopes = new ArrayList<String>();
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("token_last_eight")
    @Expose
    private String tokenLastEight;
    @SerializedName("hashed_token")
    @Expose
    private String hashedToken;
    @SerializedName("app")
    @Expose
    private App app;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("note_url")
    @Expose
    private String noteUrl;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("fingerprint")
    @Expose
    private String fingerprint;

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
     * The scopes
     */
    public List<String> getScopes() {
        return scopes;
    }

    /**
     *
     * @param scopes
     * The scopes
     */
    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    /**
     *
     * @return
     * The token
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token
     * The token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     *
     * @return
     * The tokenLastEight
     */
    public String getTokenLastEight() {
        return tokenLastEight;
    }

    /**
     *
     * @param tokenLastEight
     * The token_last_eight
     */
    public void setTokenLastEight(String tokenLastEight) {
        this.tokenLastEight = tokenLastEight;
    }

    /**
     *
     * @return
     * The hashedToken
     */
    public String getHashedToken() {
        return hashedToken;
    }

    /**
     *
     * @param hashedToken
     * The hashed_token
     */
    public void setHashedToken(String hashedToken) {
        this.hashedToken = hashedToken;
    }

    /**
     *
     * @return
     * The app
     */
    public App getApp() {
        return app;
    }

    /**
     *
     * @param app
     * The app
     */
    public void setApp(App app) {
        this.app = app;
    }

    /**
     *
     * @return
     * The note
     */
    public String getNote() {
        return note;
    }

    /**
     *
     * @param note
     * The note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     *
     * @return
     * The noteUrl
     */
    public String getNoteUrl() {
        return noteUrl;
    }

    /**
     *
     * @param noteUrl
     * The note_url
     */
    public void setNoteUrl(String noteUrl) {
        this.noteUrl = noteUrl;
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
     * The fingerprint
     */
    public String getFingerprint() {
        return fingerprint;
    }

    /**
     *
     * @param fingerprint
     * The fingerprint
     */
    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }


}




