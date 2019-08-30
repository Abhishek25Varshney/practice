package reddy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tags {
    private Map<Difficulty, ArrayList<Question>> questions;
    private List<Question> easy;
    private List<Question> medium;
    private List<Question> difficult;
    public Tags() {
        questions = new HashMap<>();
        easy = new ArrayList<>();
        medium = new ArrayList<>();
        difficult = new ArrayList<>();
    }

    public Map<Difficulty, ArrayList<Question>> getQuestions() {
        return questions;
    }
}
