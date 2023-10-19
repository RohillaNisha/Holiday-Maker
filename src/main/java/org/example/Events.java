package org.example;

import java.util.ArrayList;

public class Events {
        private Database db;
        private ArrayList<Event> list;

    public Events() {
        this.db = new Database();
       // this.list = db.listOfAllEvents();
       this.list= db.listOfAllEvents();
    }

        public String getEventName(){
        return "eventName";
    }

        public double getEventPrice(){
        return 10;
    }
        public void createEvent(String eventName, double eventPrice) {
        db.createNewEvent(eventName, eventPrice);
        //this.list = db.listOfAllEvents();
            this.list=db.listOfAllEvents();
    }

        public void printAllEvents() {
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(this.list.get(i).toString());
        }
    }

    @Override
    public String toString() {
        return "Events{" +
                "db=" + db +
                ", list=" + list +
                '}';
    }
}

