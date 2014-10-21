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
    private  Output var;
    private String mfileName;
    private int pos = 0;

    // TODO : mettre un sleep
    public void getOutput(String fileName) {
        DataResponse<Output> handler = new DataResponse<Output>() {
            public void run() {
                OutputService.this.var = value;
                if(OutputService.this.var != null){


                    if(value.Running && OutputService.this.pos == 0)
                    {

                        OutputService.this.showOutput(value);
                        OutputService.this.getOutput(value.Filename);
                    }
                    else if (value.Running)
                    {
                        OutputService.this.showOutput(value);
                        OutputService.this.getOutput(value.Filename);
                    }
                    else
                    {
                        OutputService.this.pos = 0;

                        //done
                    }
                    OutputService.this.pos = value.Pos;
                }
            }
        };
        this.mfileName = fileName;
        if(fileName != "")
            this.mOutputManager.getOutput(handler, mActivity.getApplicationContext(), fileName, pos);
    }
    private void showOutput(Output output) {

        OutputService.this.msg = OutputService.this.msg.concat(output.Output);
        if(!mDialogShowing) {
            this.builder = new AlertDialog.Builder(mActivity);
            builder.setTitle("Output");
            builder.setMessage(msg);
            this.showDialog2(builder);
        }
        else {

            OutputService.this.alert.setMessage(msg);
        }
    }

    private String msg = "";
    private AlertDialog alert;
    private Boolean mDialogShowing = false;

    protected void showDialog2(final AlertDialog.Builder builder) {
        builder.setCancelable(true);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                mDialogShowing = false;
            }
        });

        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                OutputService.this.alert = builder.create();
                try {
                    if (!mDialogShowing) {
                        OutputService.this.alert.show();
                        mDialogShowing = true;
                    }
                    else
                        OutputService.this.alert.setMessage( OutputService.this.var.Output);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
