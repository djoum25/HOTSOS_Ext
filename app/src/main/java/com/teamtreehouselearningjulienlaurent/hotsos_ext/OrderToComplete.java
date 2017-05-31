package com.teamtreehouselearningjulienlaurent.hotsos_ext;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by djoum on 5/30/17.
 */

public class OrderToComplete {
    private String roomNumber;
    private String timeReceive;
    private String timeComplete;
    private String dispatcherName;
    private String comments;
    private ArrayList<String> itemList;

    public OrderToComplete() {
    }

    public OrderToComplete(String roomNumber, String timeReceive, String dispatcherName) {
        this.roomNumber = roomNumber;
        this.timeReceive = timeReceive;
        this.dispatcherName = dispatcherName;
    }

    public OrderToComplete(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public OrderToComplete(String roomNumber, String timeReceive,
                           String timeComplete, String dispatcherName, String comments) {
        this.roomNumber = roomNumber;
        this.timeReceive = timeReceive;
        this.timeComplete = timeComplete;
        this.dispatcherName = dispatcherName;
        this.comments = comments;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getTimeReceive() {
        return timeReceive;
    }

    public void setTimeReceive(String timeReceive) {
        this.timeReceive = timeReceive;
    }

    public String getTimeComplete() {
        return timeComplete;
    }

    public void setTimeComplete(String timeComplete) {
        this.timeComplete = timeComplete;
    }

    public String getDispatcherName() {
        return dispatcherName;
    }

    public void setDispatcherName(String dispatcherName) {
        this.dispatcherName = dispatcherName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ArrayList<String> getItemList() {
        return itemList;
    }

    private void setItemList(ArrayList<String> itemList) {
        this.itemList = itemList;
    }

    public void convertItemsToList(String item) {
        ArrayList<String> myList = new ArrayList<>(Arrays.asList(item.split(",")));
        this.setItemList(myList);
    }


}
