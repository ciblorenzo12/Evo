package com.evopackage.evo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Event implements Serializable {
    private String _key, _name, _date, _location, _category, _uri, _description, _creator, _password;
    Boolean _priva;
    private List<String> _people;
    public Event() {}

    public Event(String key, String name, String date, String location, String category, String creator, String uri, String description, Boolean priva, String password) {
        _key = key;
        _name = name;
        _date = date;
        _location = location;
        _category = category;
        _creator = creator;
        _uri = uri;
        _description = description;
        _priva = priva;
       _people = new ArrayList<>();
       _people.add(_creator);

       _password = password;
    }

    public String GetKey() { return _key; }

    public String GetName() { return _name; }

    public String GetDate() { return _date; }

    public String GetLocation() { return _location; }

    public String GetCategory() { return _category; }

    public String GetCreator() { return _creator; }

    public int getPeopleCount() { return _people.size();}

    public void addPeople(String people) { _people.add(people);}

    public boolean isPeopleOnEvent(String people){
        boolean isCheck = false;
        for(int i = 0; i < getPeopleCount(); i++)
        {
            if(_people.get(i) == people)
            {
                isCheck = true;
            }
        }

        return isCheck;
    }

    public String GetUri() { return _uri; }

    public String GetPassword() { return _password;}

    public String GetDescription() { return _description; }

    public Boolean isEventPrivate() { return _priva;}

    public void SetEventPrivate(boolean prive){ _priva = prive;}

    // compares 2 string dates
    // greater than > indicates more recent
    // returns 1 if a > b
    // returns -1 if a < b
    // returns 0 if a = b
    public static int compareDates(String a, String b) {
            if (a == null || b == null)
                return 0;

            String[] numsA = a.split("/");
            String[] numsB = b.split("/");

            // if year is more recent
            if (Integer.parseInt(numsA[2]) > Integer.parseInt(numsB[2]))
                return 1;
            else if (Integer.parseInt(numsA[2]) < Integer.parseInt(numsB[2]))
                return -1;
            else {
                // if month is more recent
                if (Integer.parseInt(numsA[0]) > Integer.parseInt(numsB[0]))
                    return 1;
                else if (Integer.parseInt(numsA[0]) < Integer.parseInt(numsB[0]))
                    return -1;
                else {
                    // if day is more recent
                    if (Integer.parseInt(numsA[1]) > Integer.parseInt(numsB[1]))
                        return 1;
                    else if (Integer.parseInt(numsA[1]) < Integer.parseInt(numsB[1]))
                        return -1;
                    else
                        return 0;
                }
            }
        }

    // returns the list of events sort by their date (oldest to newest by default)
    public static ArrayList<Event> sortEventsByDate(ArrayList<Event> a, boolean reversed) {
        int n = a.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (eventGreater(a.get(j), a.get(j + 1)) ^ reversed) {
                    Event temp = a.get(j);
                    a.set(j, a.get(j + 1));
                    a.set(j + 1, temp);
                }
        return a;
    }

    // checks if event's date is today or in the future
    public static boolean isFutureEvent(Event e) {
        Calendar c = Calendar.getInstance();
        String currentDate = (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.YEAR);
        if (compareDates(e.GetDate(), currentDate) >= 0)
            return true;
        return false;
    }

    private static boolean eventGreater(Event a, Event b) {
        if (compareDates(a.GetDate(), b.GetDate()) > 0)
            return true;
        return false;
    }

}
