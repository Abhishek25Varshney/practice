package reddy;

public class Question {
    private String id;
    private Difficulty difficulty;
    private Tag tag;

    public Question(String id, Difficulty difficulty, Tag tag) {
        this.id = id;
        this.difficulty = difficulty;
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Tag getTag() {
        return tag;
    }
}
