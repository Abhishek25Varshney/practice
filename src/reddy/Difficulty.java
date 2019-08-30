package reddy;
enum Difficulty {
    EASY("EASY"),
    MEDIUM("MEDIUM"),
    HARD("HARD");

    private String value;

    public String getValue() {
        return this.value;
    }

    private Difficulty(String value) {
        this.value = value;
    }

}
