package com.example.listview;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.listview.databinding.SampleRowBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * SampleAdapter
 * Created by pdc-k-kamiya on 2017/12/26.
 */
public class SampleAdapter extends BaseAdapter {

    private List<SampleModel> mModels;

    /**
     * コンストラクタ
     * @param mModels List {@link SampleModel}
     */
    public SampleAdapter(List<SampleModel> mModels) {
        this.mModels = mModels;
    }

    /**
     * 設定必須
     * Listに表示するデータの個数を設定する
     * @return int
     */
    @Override
    public int getCount() {
        return mModels.size();
    }

    /**
     * 任意
     * @param position int
     * @return {@link SampleModel}
     */
    @Override
    public SampleModel getItem(int position) {
        return mModels.get(position);
    }

    /**
     * 任意
     * @param position int
     * @return int
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * リストの中に表示するViewを設定する
     * @param position int
     * @param convertView View
     * @param parent ViewGroup
     * @return View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SampleRowBinding binding;
        if(convertView == null){
            // sample_row.xmlからViewを作成
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.sample_row, parent, false);
            convertView = binding.getRoot();
            // BindingClassをConvertViewのTagに追加
            convertView.setTag(binding);
        } else{
            // ConvertViewのtagから取り出す
            binding = (SampleRowBinding) convertView.getTag();
        }

        // Model情報を取得する
        SampleModel model = getItem(position);

        // sample.xmlのtextと紐付ける
        binding.setModel(model);
        // 通信して画像を読み込み処理
        Picasso.with(parent.getContext()).load(model.getImageUrl()).into(binding.image);

        return convertView;
    }
}
