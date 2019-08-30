package reddy;
public enum Tag {
    Tag1("Tag1"),
    Tag2("Tag2"),
    Tag3("Tag3"),
    Tag4("Tag4"),
    Tag5("Tag5"),
    Tag6("Tag6");

    private String value;

    public String getValue() {
        return value;
    }

    private Tag(String value) {
        this.value = value;
    }

}
