package com.example.abdel.gplanetselectiontask.Contest;

import com.example.abdel.gplanetselectiontask.Database.ApplicationRepo;
import com.example.abdel.gplanetselectiontask.LogicUtils;
import com.example.abdel.gplanetselectiontask.Readers.Interval;
import com.example.abdel.gplanetselectiontask.Readers.Reader;
import com.example.abdel.gplanetselectiontask.Readings.Reading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ContestantPresenter implements ContestantContract.Presenter {

    private final ContestantContract.View mContestantView;
    private final ApplicationRepo mApplicationRepo;

    public ContestantPresenter(ContestantContract.View mContestantView, ApplicationRepo mApplicationRepo) {
        this.mContestantView = mContestantView;
        this.mApplicationRepo = mApplicationRepo;
        this.mContestantView.setPresenter(this);
    }

    @Override
    public void start() {
        mContestantView.setLoadingBar();
        mApplicationRepo.invokeRetrieveReadersLists();
        mApplicationRepo.invokeRetrieveReadingLists();
    }

    @Override
    public void onRetrievesListComplete(List<Reader> readerList, List<Reading> readingList) {
        mContestantView.showLoadingSuccessful();
        if (readerList.size() == 0 || readingList.size() == 0)
        {
            mContestantView.showEmptyList();
            return;
        }

        //Retrieving contest is done by sorting the reading by there from page
        //then for each distinct id we check if there is overlapping intervals or not
        //merge them and finally subtract each end from its start and increment number of pages for each id
        //finally retrieve names by id

        List<Contestant> contestantList = new ArrayList<>();

        Collections.sort(readingList);
        //Key : readerId, value : stack of sorted intervals
        Map<Integer,Stack<Interval>> intervalsMap = new HashMap<>();

        //loop over each reading
        for (int i=0;i<readingList.size();i++)
        {
            int currentReaderId = readingList.get(i).getReaderId();
            Interval currentInterval = new Interval(readingList.get(i).getPageFrom(),readingList.get(i).getPageTo());
            if (!intervalsMap.containsKey(currentReaderId))
            {
                intervalsMap.put(currentReaderId,new Stack<Interval>());
            }
            Stack<Interval> currentStack = intervalsMap.get(currentReaderId);

            //check for overlapping
            if (currentStack.empty())
                currentStack.push(currentInterval);
            else
            {
                if (LogicUtils.isOverlapped(currentStack.peek(),currentInterval))
                    currentStack.push(LogicUtils.mergeIntervals(currentStack.pop(),currentInterval));
                else
                    currentStack.push(currentInterval);
            }
        }

        //Iterate Throw the map
        for(Map.Entry<Integer,Stack<Interval>> entry : intervalsMap.entrySet())
        {
            contestantList.add(
                    new Contestant(
                            LogicUtils.getName(readerList,entry.getKey())
                            ,LogicUtils.calculateNumberOfPages(entry.getValue())
                    ));
        }


        Collections.sort(contestantList);
        mContestantView.showList(contestantList);
    }

    @Override
    public String buildContestantClickString(String name, String numberOfPages) {
        return "Reader :" + name + " has read " + numberOfPages + " pages.";
    }
}
