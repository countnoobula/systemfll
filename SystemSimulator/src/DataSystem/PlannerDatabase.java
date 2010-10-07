package DataSystem;

import java.io.Serializable;

import Planner.PlannerNoteData;

import java.util.ArrayList;

/**
 * The database for all the planner objects
 */
public class PlannerDatabase implements Serializable{

    //stores the sticky notes
    ArrayList<PlannerNoteData> panels;

    public PlannerDatabase() {
        this.panels = new ArrayList<PlannerNoteData>(0);
    }


    public void clearNotes() {
        panels.clear();
    }

    public PlannerNoteData getPlannerPanel(int i) {
        return panels.get(i);
    }

    public int getAmountOfPlannerNotes() {
        return panels.size();
    }

    public void addPlannerNote(PlannerNoteData p) {
        panels.add(p);
    }
}
