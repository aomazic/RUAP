package com.ruap.backend.model;


public class Input {
    private int recency;
    private int frequency;
    private int monetary;
    private int time;
    private int class_;
    public int getFrequency() {
        return frequency;
    }
    public int getMonetary() {
        return monetary;
    }
    public int getTime() {
        return time;
    }
    public int getRecency() {
        return recency;
    }
    public int getClass_() {
        return class_;
    }
    @Override
    public String toString() {
        return "Input [recency=" + recency + ", frequency=" + frequency + ", monetary=" + monetary + ", time=" + time
                + ", Class=" + class_ + "]";
    }
}
