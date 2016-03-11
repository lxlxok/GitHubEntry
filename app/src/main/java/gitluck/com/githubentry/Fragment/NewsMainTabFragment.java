package gitluck.com.githubentry.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import gitluck.com.githubentry.Activity.MemberActivity;
import gitluck.com.githubentry.Adapter.AboutAdapter;
import gitluck.com.githubentry.Bean.ItemAbout;
import gitluck.com.githubentry.R;

/**
 * Created by Administrator on 2/27/2016.
 */
public class NewsMainTabFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("TAG","显示聊天");
        View view =  inflater.inflate(R.layout.tab01, container, false);
        ListView lv = (ListView) view.findViewById(R.id.id_lv_chat1);


        lv.setAdapter(new AboutAdapter(getContext(), getDatas(), lv));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TAG", String.valueOf(position));
                //Toast.makeText(getContext(), position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MemberActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private List<ItemAbout> getDatas() {
        List<ItemAbout> mDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDatas.add(new ItemAbout(
                    "https://avatars.githubusercontent.com/u/23469?v=3",
                    "Title" + i,
                    "Content" + i
            ));
        }
        return mDatas;
    }

//    public void searchUser() {
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        // set your desired log level
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                .addInterceptor(logging)
//                .build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.github.com/")	//We are using Foursquare API to get data
//                .addConverterFactory(GsonConverterFactory.create())	//parse Gson string
//                .client(httpClient)	//add logging
//                .build();
//
//        GetUserService service = retrofit.create(GetUserService.class);
//
//        Call<User> queryResponseCall = service.listFollowers("china", "indexed", "desc");
//
//        //Call retrofit asynchronously
//        queryResponseCall.enqueue(new Callback<User>() {
//            TextView tv_error = (TextView) findViewById(R.id.error);
//            TextView tv_location = (TextView) findViewById(R.id.city);
//            TextView tv_elevation = (TextView) findViewById(R.id.elevation);
//            TextView tv_temperatrue = (TextView) findViewById(R.id.temperature);
//            TextView tv_humidity = (TextView) findViewById(R.id.humidity);
//            TextView tv_wind = (TextView) findViewById(R.id.wind_speed);
//            TextView tv_weather = (TextView) findViewById(R.id.weather);
//            @Override
//            public void onResponse(Response<User> response) {
//                if (response == null) {
//                    tv_error.setText("Error: No response.");
//                    tv_location.setText("No valid input");
//                    tv_elevation.setText("No valid input");
//                    tv_temperatrue.setText("No valid input");
//                    tv_humidity.setText("No valid input");
//                    tv_wind.setText("No valid input");
//                    tv_weather.setText("No valid input");
//                } else if (response.code() == 500) {
//                    tv_error.setText("Error: Server error occurs.");
//                    tv_location.setText("No valid input");
//                    tv_elevation.setText("No valid input");
//                    tv_temperatrue.setText("No valid input");
//                    tv_humidity.setText("No valid input");
//                    tv_wind.setText("No valid input");
//                    tv_weather.setText("No valid input");
//                } else if (response.body().response.result.equals("error")) {
//                    tv_error.setText("Error: Application error occurs.");
//                    tv_location.setText("No valid input");
//                    tv_elevation.setText("No valid input");
//                    tv_temperatrue.setText("No valid input");
//                    tv_humidity.setText("No valid input");
//                    tv_wind.setText("No valid input");
//                    tv_weather.setText("No valid input");
//                } else {
//                    tv_error.setText("Valid input");
//                    tv_location.setText("City: " + response.body().response.conditions.observationLocation.city);
//                    tv_elevation.setText("Elevation: " + response.body().response.conditions.observationLocation.elevation);
//                    tv_temperatrue.setText("Temperature: " + response.body().response.conditions.tempF);
//                    tv_humidity.setText("Relative Humidity: " + response.body().response.conditions.relativeHumidity);
//                    tv_wind.setText("Wind Speed: " + response.body().response.conditions.windMph);
//                    tv_weather.setText("Weather: " + response.body().response.conditions.weather);
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                // Log error here since request failed
//            }
//        });
//    }
//
//    public interface GetUserService {
//        @GET("default/get_weather")
//        Call<User> listFollowers(@Query("q") String q,
//                                 @Query("srot") String sort,
//                                 @Query("order") String order);
//    }
}
