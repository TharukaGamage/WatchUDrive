package com.example.watchudrive.ViewHolders;

import android.view.View;
import android.widget.TextView;

import com.example.watchudrive.BaseViewHolder;
import com.example.watchudrive.R;

public class UploadItemViewHolder extends BaseViewHolder {

    TextView tvLoadingItems;

    public UploadItemViewHolder(View itemView) {
        super(itemView);

        tvLoadingItems = (TextView)itemView.findViewById(R.id.tvLoadingItems);
    }

    @Override
    protected void clear() {

    }
}
