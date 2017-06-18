package toandoan.framgia.com.rxjavaretrofit.data.source.remote.api.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by framgia on 18/04/2017.
 */

public class GitHub {
    @SerializedName("login")
    private String mLogin;
    @SerializedName("blog")
    private String mBlog;
    @SerializedName("public_repos")
    private int mPublicRepos;

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    public String getBlog() {
        return mBlog;
    }

    public void setBlog(String blog) {
        mBlog = blog;
    }

    public int getPublicRepos() {
        return mPublicRepos;
    }

    public void setPublicRepos(int publicRepos) {
        mPublicRepos = publicRepos;
    }

    @Override
    public String toString() {
        return "GitHub{"
                + "mLogin='"
                + mLogin
                + '\''
                + ", mBlog='"
                + mBlog
                + '\''
                + ", mPublicRepos="
                + mPublicRepos
                + '}';
    }
}
