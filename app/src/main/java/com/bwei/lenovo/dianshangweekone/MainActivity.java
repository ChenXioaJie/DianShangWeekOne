package com.bwei.lenovo.dianshangweekone;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.bwei.lenovo.dianshangweekone.home.adapter.MyAdapter;
import com.bwei.lenovo.dianshangweekone.home.bean.MyBean;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public String pathss = "http://www.zhaoapi.cn/home/getHome";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MyAsync().execute();

    }

    class MyAsync extends AsyncTask<Integer,Integer,String>{

        String messes = "";
        private GridView grid;

        @Override
        protected String doInBackground(Integer... integers) {

            try {
                URL url = new URL(pathss);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5*1024);
                connection.setReadTimeout(5*1024);

                if (connection.getResponseCode() == 200){
                    InputStream inputStream = connection.getInputStream();

                    byte[] bytes = new byte[1024 * 512];
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                    int len = 0;
                    while ((len=inputStream.read(bytes))>-1){
                        outputStream.write(bytes,0,len);
                    }
                    messes = outputStream.toString();
                    inputStream.close();
                    connection.disconnect();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return messes;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            grid = findViewById(R.id.gridView1);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s!=null){

                Gson gson = new Gson();
                MyBean myBean = gson.fromJson(s, MyBean.class);
                List<MyBean.DataBean.BannerBean> banner;
                banner = myBean.getData().getBanner();

                if (banner!=null){
                    MyAdapter adapter = new MyAdapter(MainActivity.this, banner);
                    grid.setAdapter(adapter);

                }


            }

        }
    }

}
