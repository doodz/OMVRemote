package fr.doodz.openmv.jsonrpc.client;

import com.fasterxml.jackson.databind.JsonNode;

import fr.doodz.openmv.api.object.Host;
import fr.doodz.openmv.api.object.Output;
import fr.doodz.openmv.api.object.business.INotifiableManager;
import fr.doodz.openmv.api.object.data.IOutputClient;
import fr.doodz.openmv.jsonrpc.Connection;

/**
 * Created by doods on 12/10/2014.
 */
public class OutputClient  extends Client implements IOutputClient {

    public static final String TAG = "OutputClient";

    /**
     * Class constructor needs reference to HTTP client connection
     *
     * @param connection
     */
    public OutputClient(Connection connection) {
        super(connection);
    }

    /**
     * Updates host info on the connection.
     *
     * @param host
     */
    public void setHost(Host host) {
        mConnection.setHost(host);
    }


    public Output getOutput(INotifiableManager manager,String fileName,int pos)
    {
        JsonNode result = mConnection.getJson(manager, "getOutput", "Exec",
                obj().p("filename", fileName).p("pos", pos));
        result = result.get("data");
        final JsonNode jsonRep = result.get("response");
        Output output = null;
        if (jsonRep != null) {
            output = new Output(getString2(jsonRep, "Filename"), getInt(jsonRep, "Pos"), getString2(jsonRep, "Output"), getBool(jsonRep, "Running"));
        }
        return output;
    }
}
