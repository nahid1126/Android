package com.example.quizapp.model;

public class Questions {

    private int qusID;
    private boolean ansTrue;

    public Questions(int qusID, boolean andTrue) {
        this.qusID = qusID;
        this.ansTrue = andTrue;
    }

    public int getQusID() {
        return qusID;
    }

    public void setQusID(int qusID) {
        this.qusID = qusID;
    }

    public boolean isAnsTrue() {
        return ansTrue;
    }

    public void setAnsTrue(boolean ansTrue) {
        this.ansTrue = ansTrue;
    }

}
