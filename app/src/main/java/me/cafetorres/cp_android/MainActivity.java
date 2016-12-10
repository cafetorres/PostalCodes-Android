package me.cafetorres.cp_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.cafetorres.cp_android.api.PostalCodeClient;
import me.cafetorres.cp_android.api.PostalCodeService;
import me.cafetorres.cp_android.entities.PostalCodes;
import me.cafetorres.cp_android.fragments.HistoryListFragment;
import me.cafetorres.cp_android.fragments.HistoryListFragmentListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    PostalCodeClient postalCodeClient=new PostalCodeClient();
    PostalCodeService postalCodeService;

    @Bind(R.id.btn_cp)
    Button btn_cp;
    @Bind(R.id.txtCP)
    EditText txtCP;

    private HistoryListFragmentListener fragmentListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        postalCodeService=postalCodeClient.getPostalCodeService();
        HistoryListFragment fragment=(HistoryListFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentList);

        fragment.setRetainInstance(true);
        fragmentListener=(HistoryListFragmentListener)fragment;
        fragmentListener.clearList();
    }

    @OnClick(R.id.btn_cp)
    public void handleFetchClick(){
        fragmentListener.clearList();
        String txtpc= txtCP.getText().toString().trim();
        if(!txtpc.isEmpty()){
        Call<PostalCodes> call=postalCodeService.ListPostalcodes("MX","8","cafetorres",txtpc);
        call.enqueue(new Callback<PostalCodes>() {
            @Override
            public void onResponse(Call<PostalCodes> call, Response<PostalCodes> response) {
                PostalCodes postalCodeResponse=response.body();

                for(int i=0;i<postalCodeResponse.getPostalCodes().size();i++){
                    fragmentListener.addToList(postalCodeResponse.getPostalCodes().get(i));

                }
            }

            @Override
            public void onFailure(Call<PostalCodes> call, Throwable t) {
                Log.e("Error","Something went wrong: " + t.getMessage());
            }
        });

    }}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_about:
                //Toast.makeText(this, "Elaborado por: CARLOS FERNANDO TORRES LUNA", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, about_activity.class );
                startActivity(i);
                return true;
        }
        return false;
    }
}
