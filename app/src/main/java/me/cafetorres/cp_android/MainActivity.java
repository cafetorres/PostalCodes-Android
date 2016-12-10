package me.cafetorres.cp_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.cafetorres.cp_android.api.PostalCodeClient;
import me.cafetorres.cp_android.api.PostalCodeService;
import me.cafetorres.cp_android.entities.PostalCodes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    PostalCodeClient postalCodeClient=new PostalCodeClient();
    PostalCodeService postalCodeService;

    @Bind(R.id.button)
    Button button;
    @Bind(R.id.txtCP)
    EditText txtCP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        postalCodeService=postalCodeClient.getPostalCodeService();


        Call<PostalCodes> call=postalCodeService.ListPostalcodes("MX","8","cafetorres","36545");
        call.enqueue(new Callback<PostalCodes>() {
            @Override
            public void onResponse(Call<PostalCodes> call, Response<PostalCodes> response) {
                PostalCodes postalCodeResponse=response.body();

                for(int i=0;i<postalCodeResponse.getPostalCodes().size();i++){
                    Log.v("NAME",postalCodeResponse.getPostalCodes().get(i).getPlacename());
                    Log.v("POSTALCODE",postalCodeResponse.getPostalCodes().get(i).getPostalcode());

                }
            }

            @Override
            public void onFailure(Call<PostalCodes> call, Throwable t) {
                Log.e("Error","Something went wrong: " + t.getMessage());
            }
        });
    }
}
