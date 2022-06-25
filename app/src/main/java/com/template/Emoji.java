package com.template;

public class Emoji {
    private int first_emojiId;
    private int second_emojiId;
    private int third_emojiId;

    public Emoji(int first_emojiId, int second_emojiId, int third_emojiId) {
        this.first_emojiId = first_emojiId;
        this.second_emojiId = second_emojiId;
        this.third_emojiId = third_emojiId;
    }

    public int getFirst_emojiId() {
        return first_emojiId;
    }

    public void setFirst_emojiId(int first_emojiId) {
        this.first_emojiId = first_emojiId;
    }

    public int getSecond_emojiId() {
        return second_emojiId;
    }

    public void setSecond_emojiId(int second_emojiId) {
        this.second_emojiId = second_emojiId;
    }

    public int getThird_emojiId() {
        return third_emojiId;
    }

    public void setThird_emojiId(int third_emojiId) {
        this.third_emojiId = third_emojiId;
    }
}
