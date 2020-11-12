package com.edureka.openweathermap;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.edureka.openweathermap.model.WeatherData;
import com.edureka.openweathermap.rest.DataGenerator;
import com.edureka.openweathermap.rest.WeatherClient;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.edureka.openweathermap.R.color.dark_white;
import static com.edureka.openweathermap.R.color.white;

public class MainActivity extends AppCompatActivity {

    //Declare Elements in Layout
    TextView responseText ;
    Button searchBtn ;
    ProgressBar progressBar ;
    EditText editText ;
    //Declare GitClient Interface
    WeatherClient weatherClient;
    String key="key";
    WeatherData weatherData;

    //OnClick routine

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //CAST elements
        searchBtn     = (Button)         findViewById(R.id.main_btn_lookup);
        responseText  = (TextView)       findViewById(R.id.main_text_response);
        editText      = (EditText)       findViewById(R.id.main_edit_CityName);
        progressBar   = (ProgressBar)    findViewById(R.id.main_progress);


        //Set progress bar visibility to invisible
        progressBar.setVisibility(View.INVISIBLE);

        //implementation of the WeatherClient interface.
        weatherClient  = DataGenerator.getClient().create(WeatherClient.class);

        //OnClick routine
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SET ProgressBar to Visible
                progressBar.setVisibility(View.VISIBLE);

                //Get username from EditText View
                String user =editText.getText().toString();

                //Create Call
                final Call<WeatherData> call = weatherClient.getdata(user,key);

                //Call Enqueue
                call.enqueue(new Callback<WeatherData>() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {

                        WeatherData weathermodel = response.body();

                        if(weathermodel !=null){
                            //Set textView
                            responseText.setText(getString(R.string.main_response_text,
                                    weathermodel.coord.getLat(),
                                    weathermodel.coord.getLon(),
                                    weathermodel.sys.getstring(),
                                    weathermodel.weather1.get(0).getDescription(),
                                    weathermodel.main.gettemp(),
                                    weathermodel.main.gethumidity(),
                                    weathermodel.main.gettemp_min(),
                                    weathermodel.main.gettemp_max(),
                                    weathermodel.wind.getspeed(),
                                    weathermodel.wind.getdeg(),
                                    weathermodel.getdt(),
                                    weathermodel.getname()
                                    )
                            );

                            responseText.setTextSize(16);
                            responseText.setTextColor(Color.BLUE);

                            //Hide progressbar after Complete
                            progressBar.setVisibility(View.INVISIBLE);

                        }
                        else {
                            responseText.setText("");
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.main_error_text),
                                    Toast.LENGTH_SHORT).show();

                            //Hide progressbar after Complete
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                    @Override
                    public void onFailure(@NotNull Call<WeatherData> call, Throwable t) {

                        //   Display Error message When the request fails
                        responseText.setText("Error Loading "+t.fillInStackTrace().toString()); //Error needs to be handled properly
                        responseText.setTextColor(Color.RED);
                        responseText.setTextSize(20);
                        Log.d("tag1",t.fillInStackTrace().toString());

                        //Hide progressbar after Complete
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                });
            }
        });


    }
}