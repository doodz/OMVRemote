package fr.doodz.openmv.app.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import fr.doodz.openmv.api.api.object.Host;
import fr.doodz.openmv.app.R;
import fr.doodz.openmv.utils.HostFactory;

/**
 * Created by doods on 24/05/14.
 */
public class HostPreference extends DialogPreference {

    public static final String ID_PREFIX = "settings_host_";
    private Host mHost;
    private Context mContext;

    private EditText mNameView, mHostView, mPortView, mUserView, mPassView,
            mMacAddrView, mWolPortView;
    private CheckBox mWifiOnlyView;

    public HostPreference(Context context) {
        this(context, null);
    }

    public HostPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        setDialogLayoutResource(R.layout.preference_host);
        setDialogTitle(R.string.add_host);
        setDialogIcon(R.drawable.ic_add2);
    }

    public void setHost(Host host) {
        mHost = host;
        setTitle(host.name);
        setSummary(host.getSummary());
        setDialogTitle(host.name);
        setDialogIcon(null);
    }

    public void create(PreferenceManager preferenceManager) {
        onAttachedToHierarchy(preferenceManager);
        showDialog(null);
    }

    @Override
    protected View onCreateView(final ViewGroup parent) {
        final ViewGroup view = (ViewGroup) super.onCreateView(parent);
        if (mHost != null) {
            ImageView btn = new ImageView(getContext());
            btn.setImageResource(R.drawable.abc_ic_clear_normal);
            btn.setClickable(true);
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    String msg = getContext().getString(R.string.dialog_delete_host);
                    msg = String.format(msg, mHost.name);
                    builder.setMessage(msg);
                    builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            HostFactory.deleteHost(getContext(), mHost);
                            ((PreferenceActivity) view.getContext()).getPreferenceScreen().removePreference(HostPreference.this);
                        }
                    });
                    builder.setNegativeButton(R.string.dialog_ko, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.create().show();
                }
            });
            view.addView(btn);
        }
        return view;
    }

    @Override
    protected View onCreateDialogView() {
        final ViewGroup parent = (ViewGroup) super.onCreateDialogView();
        mNameView = (EditText) parent.findViewById(R.id.pref_name);
        mHostView = (EditText) parent.findViewById(R.id.pref_host);
        mPortView = (EditText) parent.findViewById(R.id.pref_port);
        mUserView = (EditText) parent.findViewById(R.id.pref_user);
        mPassView = (EditText) parent.findViewById(R.id.pref_pass);
        mMacAddrView = (EditText) parent.findViewById(R.id.pref_mac_addr);
        mWifiOnlyView = (CheckBox) parent.findViewById(R.id.pref_wifi_only);
        mWolPortView = (EditText) parent.findViewById(R.id.pref_wol_port);
        return parent;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        if (mHost != null) {
            mNameView.setText(mHost.name);
            mHostView.setText(mHost.addr);
            mPortView.setText(String.valueOf(mHost.port));
            mUserView.setText(mHost.user);
            mPassView.setText(mHost.pass);
            mMacAddrView.setText(mHost.mac_addr);
            mWifiOnlyView.setChecked(mHost.wifi_only);
            mWolPortView.setText(String.valueOf(mHost.wol_port));

        } else {
            //set defaults:
            mPortView.setText("" + Host.DEFAULT_HTTP_PORT);
            mWolPortView.setText("" + Host.DEFAULT_WOL_PORT);
        }
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        if (positiveResult) {

            final Host host = new Host();
            host.name = mNameView.getText().toString();
            host.addr = mHostView.getText().toString().trim();
            try {
                host.port = Integer.parseInt(mPortView.getText().toString());
            } catch (NumberFormatException e) {
                host.port = Host.DEFAULT_HTTP_PORT;
            }
            host.user = mUserView.getText().toString();
            host.pass = mPassView.getText().toString();

            host.mac_addr = mMacAddrView.getText().toString();
            host.wifi_only = mWifiOnlyView.isChecked();
            try {
                host.wol_port = Integer.parseInt(mWolPortView.getText().toString());
            } catch (NumberFormatException e) {
                host.wol_port = Host.DEFAULT_WOL_PORT;
            }
            host.wol_wait = Host.DEFAULT_WOL_WAIT;

            if (mHost == null) {
                HostFactory.addHost(getContext(), host);
            } else {
                host.id = mHost.id;
                HostFactory.updateHost(getContext(), host);
            }
            if (callChangeListener(host)) {
                notifyChanged();
            }
            setHost(host);

            if (HostFactory.host == null) {
                HostFactory.saveHost(mContext, host);
            }

        }
    }
}
