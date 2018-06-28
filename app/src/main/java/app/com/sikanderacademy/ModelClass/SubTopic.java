
package app.com.sikanderacademy.ModelClass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;


@SuppressWarnings("unused")
public class SubTopic extends SugarRecord {

    @SerializedName("sub_topic_id")
    private String subTopicId;
    @Expose
    private String title;
    @SerializedName("topic_id")
    private String topicId;

    public String getSubTopicId() {
        return subTopicId;
    }

    public void setSubTopicId(String subTopicId) {
        this.subTopicId = subTopicId;
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
