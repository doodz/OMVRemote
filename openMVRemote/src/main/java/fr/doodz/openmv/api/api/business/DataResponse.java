package fr.doodz.openmv.api.api.business;

/**
 * Created by doods on 18/05/14.
 */
public class DataResponse<T> implements Runnable, Cloneable {
    public T value;
    public int cacheType;

    public void run() {
        // do nothing if not overloaded
    }

    /**
     * Executed before downloading large files. Overload and return false to
     * skip downloading, for instance when a list with covers is scrolling.
     *
     * @return
     */
    public boolean postCache() {
        return true;
    }
}
