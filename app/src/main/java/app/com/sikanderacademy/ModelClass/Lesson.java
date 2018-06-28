
package app.com.sikanderacademy.ModelClass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;


@SuppressWarnings("unused")
public class Lesson extends SugarRecord {

    @Expose
    private String description;
    @SerializedName("lesson_id")
    private String lessonId;
    @SerializedName("sub_topic_id")
    private String subTopicId;
    @Expose
    private String title;
    @SerializedName("video_url")
    private String videoUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

}
