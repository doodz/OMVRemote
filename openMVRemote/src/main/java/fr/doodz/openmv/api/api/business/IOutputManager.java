package fr.doodz.openmv.api.api.business;

import android.content.Context;

import fr.doodz.openmv.api.api.object.Output;

/**
 * Created by doods on 12/10/2014.
 */
public interface IOutputManager {

    void getOutput(final DataResponse<Output> response, final Context context, final String fileName, final int pos);
    void getOutput(final DataResponse<Output> response, final Context context, final Output output);
}
