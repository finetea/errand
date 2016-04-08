package com.swchung.softchain;

import android.os.Bundle;
import android.util.Log;

/**
 * Created by icete on 2016-04-09.
 */
public class SCMsgFactory {
    public static final String SVC_TAG = "SC_SERVICE";

    private static SCMsgFactory ourInstance = new SCMsgFactory();

    public static SCMsgFactory getInstance() {
        return ourInstance;
    }

    private SCMsgFactory() {
    }

    public ISCRequestMsg processInnerMessage(Bundle bundle) {
        ISCRequestMsg requestMsg = null;
        String msgId = bundle.getString("MSG_ID");
        Log.i(SVC_TAG, "msgId : " + msgId);

        if (msgId.equals(EInnerCmdType.E_INNER_CMD_TYPE_CREATE_TASK)) {
            requestMsg = new SCCreateTaskRequestMsg();
        }

        return requestMsg;
    }
}
