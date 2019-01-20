package com.example.abdel.gplanetselectiontask.Contest;

import android.support.annotation.NonNull;

class Contestant implements Comparable<Contestant> {

    private String contestantName;
    private int numberOfPages;

    public Contestant(String name, int numberOfPages) {
        this.contestantName = name;
        this.numberOfPages = numberOfPages;
    }

    public String getName() {
        return contestantName;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    @Override
    public int compareTo(@NonNull Contestant contestant) {
        return - numberOfPages + contestant.numberOfPages;
    }
}
