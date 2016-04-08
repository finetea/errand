package com.swchung.softchain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by icete on 2016-04-05.
 */
public class CreateTaskActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, MainService.class);
        Bundle bundle = new Bundle();
        bundle.putString("MSG_ID", EInnerCmdType.E_INNER_CMD_TYPE_CREATE_TASK.toString());
        intent.putExtras(bundle);
        startService(intent);
    }
}
