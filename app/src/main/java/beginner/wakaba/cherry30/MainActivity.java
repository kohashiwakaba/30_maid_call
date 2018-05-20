package beginner.wakaba.cherry30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] maids = {"체리","비스티","유리","하나"};
    Spinner sm;
    EditText em,eq,en;
    Button bc;
    Button bt;
    View.OnClickListener cl;
    View.OnClickListener cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = findViewById(R.id.sel_maid);

        em = findViewById(R.id.t_menu);
        eq = findViewById(R.id.t_qty);
        en = findViewById(R.id.t_new);

        bc = findViewById(R.id.call);
        bt = findViewById(R.id.cont);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,maids);
        sm.setAdapter(adapter);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent();
                f.setAction("cherry.call");
                f.setPackage("beginner.wakaba.cherry31");
                f.putExtra("maid",sm.getSelectedItem().toString());
                //브로드캐스트 리시버는 기본적으로 딜레이가 존재하는데 이 문장은 우선 순위를 부여하여 딜레이를 없애준다.
                f.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);

                String fm = em.getText().toString();
                if (!fm.isEmpty()){
                    f.putExtra("menu",fm);
                }else{
                    f.putExtra("menu","카푸치노");
                }

                String fq = eq.getText().toString();
                if (!fq.isEmpty()){
                    f.putExtra("quantity",fq);
                }else{
                    f.putExtra("quantity","1");
                }

                sendBroadcast(f);
            }
        };

        cc = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newmaid = en.getText().toString();
                if (!newmaid.isEmpty()){
                    Intent c = new Intent();
                    c.setAction("cherry.contract");
                    c.setPackage("beginner.wakaba.cherry31");
                    c.putExtra("maid_cont",newmaid);
                    c.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
                    sendBroadcast(c);
                }else{
                    Toast.makeText(getApplicationContext(), "신청하실 메이드 이름을 입력해 주십시오.",Toast.LENGTH_LONG).show();
                }

            }
        };

        bc.setOnClickListener(cl);
        bt.setOnClickListener(cc);

    }
}
