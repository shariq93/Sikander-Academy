
package app.com.sikanderacademy.ModelClass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;


@SuppressWarnings("unused")
public class Chapter extends SugarRecord{

    @SerializedName("chapter_id")
    private String chapterId;
    @SerializedName("subject_id")
    private String subjectId;
    @Expose
    private String title;

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
