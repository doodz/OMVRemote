package fr.doodz.openmv.app.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import fr.doodz.openmv.UI.business.Command;
import fr.doodz.openmv.api.api.object.Host;
import fr.doodz.openmv.api.api.business.INotifiableManager;
import fr.doodz.openmv.app.Activity.HostSettingsActivity;
import fr.doodz.openmv.app.Activity.SettingsActivity;
import fr.doodz.openmv.httpapi.NoNetworkException;
import fr.doodz.openmv.httpapi.NoSettingsException;
import fr.doodz.openmv.httpapi.WrongDataFormatException;
import fr.doodz.openmv.utils.HostFactory;
import fr.doodz.openmv.utils.WifiHelper;

/**
 * Created by doods on 18/05/14.
 */
public abstract class AbstractController {

    public static final int MAX_WAIT_FOR_WIFI = 20;
    public static final String TAG = "AbstractController";

    protected Activity mActivity;
    protected Handler mHandler;
    protected boolean mPaused = true;
    private boolean mDialogShowing = false;
    private Thread mWaitForWifi;

    public void onCreate(Activity activity, Handler handler) {
        mActivity = activity;
        mHandler = handler;
        HostFactory.readHost(activity.getApplicationContext());
        //ClientFactory.resetClient(HostFactory.host);
    }

    public void onWrongConnectionState(int state, final INotifiableManager manager, final Command<?> source) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        switch (state) {
            case WifiHelper.WIFI_STATE_DISABLED:
                builder.setTitle("Wifi disabled");
                builder.setMessage("This host is Wifi only. Should I activate Wifi?");
                builder.setNeutralButton("Activate Wifi", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog pd = new ProgressDialog(mActivity);
                        pd.setCancelable(true);
                        pd.setTitle("Activating Wifi");
                        pd.setMessage("Please wait while Wifi is activated.");
                        pd.show();
                        (new Thread() {
                            public void run() {
                                final WifiHelper helper = WifiHelper.getInstance(mActivity);
                                helper.enableWifi(true);
                                int wait = 0;
                                while (wait <= MAX_WAIT_FOR_WIFI * 1000 && helper.getWifiState() != WifiHelper.WIFI_STATE_ENABLED) {
                                    try {
                                        sleep(500);
                                        wait += 500;
                                    } catch (InterruptedException e) {
                                    }
                                }
                                manager.retryAll();
                                pd.cancel();
                                mDialogShowing = false;

                            }
                        }).start();
                    }
                });
                showDialog(builder);
                break;
            case WifiHelper.WIFI_STATE_ENABLED:
                final Host host = HostFactory.host;
                final WifiHelper helper = WifiHelper.getInstance(mActivity);
                final String msg;
                if (host != null && host.access_point != null && !host.access_point.equals("")) {
                    helper.connect(host);
                    msg = "Connecting to " + host.access_point + ". Please wait";
                } else {
                    msg = "Waiting for Wifi to connect to your LAN.";
                }
                final ProgressDialog pd = new ProgressDialog(mActivity);
                pd.setCancelable(true);
                pd.setTitle("Connecting");
                pd.setMessage(msg);
                mWaitForWifi = new Thread() {
                    public void run() {
                        mDialogShowing = true;
                        pd.show();
                        (new Thread() {
                            public void run() {
                                int wait = 0;
                                while (wait <= MAX_WAIT_FOR_WIFI * 1000 && helper.getWifiState() != WifiHelper.WIFI_STATE_CONNECTED) {
                                    try {
                                        sleep(500);
                                        wait += 500;
                                    } catch (InterruptedException e) {
                                    }
                                }
                                pd.cancel();
                                mDialogShowing = false;
                            }
                        }).start();
                        pd.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            public void onDismiss(DialogInterface dialog) {
                                if (helper.getWifiState() != WifiHelper.WIFI_STATE_CONNECTED) {
                                    builder.setTitle("Wifi doesn't seem to connect");
                                    builder.setMessage("You can open the Wifi settings or wait " + MAX_WAIT_FOR_WIFI + " seconds");
                                    builder.setNeutralButton("Wifi Settings", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                            mDialogShowing = false;
                                            mActivity.startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));
                                        }
                                    });
                                    builder.setCancelable(true);
                                    builder.setNegativeButton("Wait", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            mDialogShowing = false;
                                            mActivity.runOnUiThread(mWaitForWifi); //had to make the Thread a field because of this line
                                        }
                                    });

                                    mActivity.runOnUiThread(new Runnable() {
                                        public void run() {
                                            final AlertDialog alert = builder.create();
                                            try {
                                                if (!mDialogShowing) {
                                                    alert.show();
                                                    mDialogShowing = true;
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            }

                        });
                    }
                };
                mActivity.runOnUiThread(mWaitForWifi);
        }

    }

    public void onError(Exception exception) {
        if (mActivity == null) {
            return;
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        try {
            throw exception;
        } catch (NoSettingsException e) {
            builder.setTitle("No hosts detected");
            builder.setMessage(e.getMessage());
            builder.setNeutralButton("Settings", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    final Intent intent = new Intent(mActivity, HostSettingsActivity.class);
//					intent.putExtra(SettingsActivity.JUMP_TO, SettingsActivity.JUMP_TO_INSTANCES);
                    mActivity.startActivity(intent);
                    mDialogShowing = false;
                }
            });
        } catch (NoNetworkException e) {
            builder.setTitle("No Network");
            builder.setMessage(e.getMessage());
            builder.setCancelable(true);
            builder.setNeutralButton("Settings", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    mActivity.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                    mDialogShowing = false;
                }
            });
        } catch (WrongDataFormatException e) {
            builder.setTitle("Internal Error");
            builder.setMessage("Wrong data from HTTP API; expected '" + e.getExpected() + "', got '" + e.getReceived() + "'.");
        } catch (SocketTimeoutException e) {
            builder.setTitle("Socket Timeout");
            builder.setMessage("Make sure OMV webserver is enabled and OMV is running.");
            builder.setNeutralButton("Settings", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    mActivity.startActivity(new Intent(mActivity, SettingsActivity.class));
                    mDialogShowing = false;
                }
            });
        } catch (ConnectException e) {
            builder.setTitle("Connection Refused");
            builder.setMessage("Make sure OMV webserver is enabled and OMV is running.");
            builder.setNeutralButton("Settings", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    mActivity.startActivity(new Intent(mActivity, HostSettingsActivity.class));
                    mDialogShowing = false;
                }
            });
        } catch (IOException e) {
            if (e.getMessage() != null && e.getMessage().startsWith("Network unreachable")) {
                builder.setTitle("No ic_network");
                builder.setMessage("OMV Remote needs local ic_network access. Please make sure that your wireless ic_network is activated. You can click on the Settings button below to directly access your ic_network settings.");
                builder.setNeutralButton("Settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mActivity.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                        mDialogShowing = false;
                    }
                });
            } else {
                builder.setTitle("I/O Exception (" + e.getClass().getCanonicalName() + ")");
                if (e.getMessage() != null) {
                    builder.setMessage(e.getMessage().toString());
                }
                Log.e(TAG, e.getStackTrace().toString());
            }
        } catch (HttpException e) {
            if (e.getMessage().startsWith("401")) {
                builder.setTitle("HTTP 401: Unauthorized");
                builder.setMessage("The supplied username and/or password is incorrect. Please check your settings.");
                builder.setNeutralButton("Settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mActivity.startActivity(new Intent(mActivity, SettingsActivity.class));
                        mDialogShowing = false;
                    }
                });
            }
        } catch (Exception e) {
            final String name = e.getClass().getName();
            builder.setTitle(name.substring(name.lastIndexOf(".") + 1));
            builder.setMessage(e.getMessage());
        } finally {

            exception.printStackTrace();
        }
        showDialog(builder);
    }

    protected void showDialog(final AlertDialog.Builder builder) {
        builder.setCancelable(true);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                mDialogShowing = false;
//				ConnectionManager.resetClient();
            }
        });

        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                final AlertDialog alert = builder.create();
                try {
                    if (!mDialogShowing) {
                        alert.show();
                        mDialogShowing = true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void resetDialogShowing() {
        this.mDialogShowing = false;
    }

    protected void showDialog(int id) {
        mActivity.showDialog(id);
    }

    protected void dismissDialog(int id) {
        mActivity.dismissDialog(id);
    }

    public void onMessage(final String message) {
        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                Toast toast = Toast.makeText(mActivity, message, Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    public void runOnUI(Runnable action) {
        if (mHandler != null) {
            //Log.i(TAG, "### running on UI at " + mActivity.getClass().getSimpleName());
            mHandler.post(action);
            //mActivity.runOnUiThread(action);
        }
    }

    public void onActivityPause() {
//		mActivity = null;
        mPaused = true;
    }

    public void onActivityResume(Activity activity) {
        mActivity = activity;
        mPaused = false;
    }
}