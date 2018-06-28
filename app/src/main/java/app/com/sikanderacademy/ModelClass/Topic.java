
package app.com.sikanderacademy.ModelClass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;


@SuppressWarnings("unused")
public class Topic extends SugarRecord {

    @SerializedName("chapter_id")
    private String chapterId;
    @Expose
    private String title;
    @SerializedName("topic_id")
    private String topicId;

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

}
