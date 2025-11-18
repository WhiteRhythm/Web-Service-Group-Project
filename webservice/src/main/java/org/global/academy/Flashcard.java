package org.global.academy;

public class Flashcard {
    
    // --- Variables ---
    private String front;
    private String back;
    private boolean learned;
    
    
    public Flashcard(String front, String back) {
        this.front = front;
        this.back = back;
        this.learned = false; // default: not learned yet
    }


    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public boolean isLearned() {
        return learned;
    }

    public void setLearned(boolean learned) {
        this.learned = learned;
    }

    @Override
    public String toString() {
        return "Flashcard{" +
                "front='" + front + '\'' +
                ", back='" + back + '\'' +
                ", learned=" + learned +
                '}';
    }   

    
}
