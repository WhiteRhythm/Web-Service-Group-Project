package org.global.academy;

public class BurmeseConsonantFlashcard extends Flashcard {

    private String description;

    public BurmeseConsonantFlashcard(String front, String back, String description) {
        // Pass the Burmese character (front) and definition (back) to the parent
        super(front, back);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
