package com.summer.adbconn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;

public class MainActivity extends AppCompatActivity {

    //    private final static String command0="su";
    private final static String command1 = "stop adbd";
    private final static String command = "setprop service.adb.tcp.port ";
    private final static String command3 = "setprop service.adb.tcp.port -1";
    private final static String command2 = "start adbd";
    private TextView et_ip;
    private EditText et_port;
    private Button btn_connect;
    private Button btn_reset;
    private Button btn_disconnect;

    /**
     * 应用程序运行命令获取 Root权限，设备必须已破解(获得ROOT权限)
     *
     * @param command 命令：String apkRoot="chmod 777 "+getPackageCodePath(); RootCommand(apkRoot);
     * @return 应用程序是/否获取Root权限
     */
    public static boolean RootCommand(String command) {
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            Log.d("*** DEBUG ***", "ROOT REE" + e.getMessage());
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
        Log.d("*** DEBUG ***", "Root SUC ");
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_ip = (TextView) findViewById(R.id.ET_IP);
        btn_disconnect= (Button) findViewById(R.id.btn_disconnect);
        et_ip.setText(NetWorkUtils.getLocalIpAddress(this));
        btn_reset = (Button) findViewById(R.id.btn_reset);
        et_port = (EditText) findViewById(R.id.ET_port);
        btn_disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RootCommand(command3);
                RootCommand(command1);
                btn_connect.setClickable(true);
                et_port.setEnabled(true);
                Toast.makeText(getApplicationContext(), "adb Wifi调试已断开连接！", Toast.LENGTH_SHORT).show();

            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_connect.setClickable(true);
                et_port.setEnabled(true);
                et_port.setText("");
            }
        });
        btn_connect = (Button) findViewById(R.id.btn_connect);
        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(et_port.getText().toString()))
                    Toast.makeText(getApplicationContext(), "请指定连接的端口！", Toast.LENGTH_SHORT).show();
                else {
                    RootCommand(command + Integer.parseInt(et_port.getText().toString()));
                    RootCommand(command1);
                    RootCommand(command2);
                    Toast.makeText(getApplicationContext(), "adb Wifi调试设置成功！", Toast.LENGTH_SHORT).show();
                    btn_connect.setClickable(false);
                    et_port.setEnabled(false);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
