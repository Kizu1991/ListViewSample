package com.example.listview;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.listview.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "sample.json";

    private JSONArray mJsonArray;
    private ActivityMainBinding mBinding;
    private SampleAdapter mAdapter;
    private List<SampleModel> mModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // BindingClassを作成
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Assetsディレクトリからsample.jsonデータを取得
        String json = getSampleJson(FILE_NAME);
        // JsonArrayを作成
        try {
            mJsonArray = new JSONArray(json);
        } catch (JSONException e) {
            // エラー処理
            Log.e("Exception", "JSONException", e);
        }

        // ListViewに表示するようのデータ格納配列を生成
        mModels = new ArrayList<>();
        // Adapterクラスにデータを受け渡す
        mAdapter = new SampleAdapter(mModels);
        // ListViewとAdapterを紐付ける
        mBinding.listview.setAdapter(mAdapter);
        // 増やすボタン押下時
        mBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // リスト表示用配列にデータを追加
                mModels.addAll(createModels(mJsonArray));
                // データを追加した後、Adapterを更新
                // 更新するとリストの数が増える
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * JSONArrayからModelを作成するメソッド
     * @return List {@link SampleModel}
     */
    public List<SampleModel> createModels(JSONArray jsonArray){
        List<SampleModel> models = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i ++){
            JSONObject object = jsonArray.optJSONObject(i);
            SampleModel model = new SampleModel();
            model.setImageUrl(object.optString("image_url"));
            model.setName(object.optString("name"));
            model.setDescription(object.optString("description"));
            models.add(model);
        }
        return models;
    }

    /**
     * Assetsディレクトリからファイルを読み込みStringにして返却するメソッド
     * @param fileName String
     * @return String
     */
    public String getSampleJson(String fileName){
        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        BufferedReader br = null;

        try {
            try {
                // assetsフォルダ内の sample.txt をオープンする
                is = this.getAssets().open(fileName);
                br = new BufferedReader(new InputStreamReader(is));

                // １行ずつ読み込み
                String str;
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
            } finally {
                if (is != null) is.close();
                if (br != null) br.close();
            }
        } catch (Exception e){
            // エラー発生時の処理
            e.printStackTrace();
            Log.e("Exception", "Exception", e);
        }

        return sb.toString();
    }
}
