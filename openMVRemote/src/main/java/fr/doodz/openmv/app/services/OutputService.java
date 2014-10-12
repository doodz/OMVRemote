package fr.doodz.openmv.app.services;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;

import fr.doodz.openmv.UI.business.ManagerFactory;
import fr.doodz.openmv.UI.business.presentation.INotifiableController;
import fr.doodz.openmv.api.object.Output;
import fr.doodz.openmv.api.object.business.DataResponse;
import fr.doodz.openmv.api.object.business.IOutputManager;
import fr.doodz.openmv.app.controllers.AbstractController;

/**
 * Created by doods on 12/10/2014.
 */
public class OutputService extends AbstractController implements INotifiableController {

    private IOutputManager mOutputManager;
    private Activity activity;
    private AlertDialog.Builder builder;
    public OutputService(Activity activity, Handler handler) {
        super.onCreate(activity, handler);
        mOutputManager = ManagerFactory.getOutputManager(this);
        this.activity = activity;
    }

    private String mfileName;
    private int pos = 0;
    public void getOutput(String fileName) {
        DataResponse<Output> handler = new DataResponse<Output>() {
            public void run() {
                Output var = value;
                if(var != null){


                    if(var.Running && OutputService.this.pos == 0)
                    {
                        OutputService.this.pos = var.Pos;
                        OutputService.this.showOutput(var);

                    }
                    else if (var.Running)
                    {

                    }
                    else
                    {

                        //done
                    }

                }
            }
        };

        this.mOutputManager.getOutput(handler, mActivity.getApplicationContext(), fileName, pos);
    }
    private void showOutput(Output output) {
        this.builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Output");
        builder.setMessage(output.Output);
        showDialog(builder);
    }
}
