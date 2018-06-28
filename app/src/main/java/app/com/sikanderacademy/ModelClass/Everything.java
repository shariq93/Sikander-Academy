
package app.com.sikanderacademy.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


@SuppressWarnings("unused")
public class Everything {

    @Expose
    private List<Chapter> chapter;
    @Expose
    private List<Lesson> lesson;
    @SerializedName("sub_topic")
    private List<SubTopic> subTopic;
    @Expose
    private List<Subject> subject;
    @Expose
    private List<Topic> topic;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Expose
    private List<Book> books;

    public List<Dictionary> getDictionary() {
        return dictionary;
    }

    public void setDictionary(List<Dictionary> dictionary) {
        this.dictionary = dictionary;
    }

    @Expose
    private List<Dictionary> dictionary;

    public List<Chapter> getChapter() {
        return chapter;
    }

    public void setChapter(List<Chapter> chapter) {
        this.chapter = chapter;
    }

    public List<Lesson> getLesson() {
        return lesson;
    }

    public void setLesson(List<Lesson> lesson) {
        this.lesson = lesson;
    }

    public List<SubTopic> getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(List<SubTopic> subTopic) {
        this.subTopic = subTopic;
    }

    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }

    public List<Topic> getTopic() {
        return topic;
    }

    public void setTopic(List<Topic> topic) {
        this.topic = topic;
    }

}
