package uk.ac.cam.echo2016.multinarrative;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.BaseBundle;

/**
 * Represents a {@code Node} on the {@code MultiNarrative} graph structure. Each node has an {@code ArrayList} of
 * {@code Narrative} references leaving the {@code Node}.
 * 
 * @author tr393
 * @author rjm232
 * @version 1.0
 *
 */
public abstract class Node implements Serializable, Cloneable { // TODO Documentation
    private static final long serialVersionUID = 1;
    private final String id;
    private BaseBundle properties;
    private ArrayList<Route> entryRoutes;
    private ArrayList<Route> exitRoutes;

    public Node(String id) {
        this.id = id;
        this.exitRoutes = new ArrayList<Route>();
        this.entryRoutes = new ArrayList<Route>();
}

    @Override
    public Node clone() {
        try {
            Node clone = (Node) super.clone();
            clone.properties = BaseBundle.deepcopy(this.properties);

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method is implemented in derived classes ChoiceNode and SyncNode, to allow this class to make new objects of
     * those derived types in the copyToGraph method.
     * 
     * @param id
     * @return
     */
    protected abstract Node create(String id);

    public abstract BaseBundle startRoute(Route option);

    public abstract GameChoice onEntry(Route played, NarrativeInstance instance);

    public String getId() {
        return id;
    }

    public void createProperties() {
        if (properties == null)
            properties = new BaseBundle(); // TODO Initialize with default starting size?
    }

    public BaseBundle getProperties() {
        return properties;
    }

    public void setProperties(BaseBundle b) {
        properties = b;
    }

    public ArrayList<Route> getEntering() {
        return entryRoutes;
    }

    public void setEntering(ArrayList<Route> entryRoutes) {
        this.entryRoutes = entryRoutes;
    }

    public ArrayList<Route> getExiting() {
        return exitRoutes;
    }

    public void setExiting(ArrayList<Route> exitRoutes) {
        this.exitRoutes = exitRoutes;
    }
}
