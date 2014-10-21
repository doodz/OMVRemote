package fr.doodz.openmv.UI.business;

import android.content.Context;

import fr.doodz.openmv.api.object.Output;
import fr.doodz.openmv.api.object.business.DataResponse;
import fr.doodz.openmv.api.object.business.INotifiableManager;
import fr.doodz.openmv.api.object.business.IOutputManager;

/**
 * Created by doods on 12/10/2014.
 */
public class OutputManager extends AbstractManager implements INotifiableManager,IOutputManager {

    public void getOutput(final DataResponse<Output> response, final Context context, final String fileName, final int pos) {
        mHandler.post(new Command<Output>(response, this) {
            @Override
            public void doRun() throws Exception {
                response.value = Output(context).getOutput(OutputManager.this, fileName, pos);
            }
        });
    }

    public void getOutput(final DataResponse<Output> response, final Context context, final Output output) {
        mHandler.post(new Command<Output>(response, this) {
            @Override
            public void doRun() throws Exception {
                response.value = Output(context).getOutput(OutputManager.this, output.Filename, output.Pos);
            }
        });
    }
}