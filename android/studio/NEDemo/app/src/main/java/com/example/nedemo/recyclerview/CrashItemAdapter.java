package com.example.nedemo.recyclerview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import com.example.nedemo.R;

import java.util.List;

public class CrashItemAdapter extends BaseRecyclerViewAdapter<CrashItem> {

    private OnDeleteClickLister mDeleteClickListener;

    public CrashItemAdapter(Context context, List<CrashItem> data) {
        super(context, data, R.layout.item_crash);
    }

    @Override
    protected void onBindData(RecyclerViewHolder holder, CrashItem bean, int position) {
        View view = holder.getView(R.id.tv_delete);
        view.setTag(position);
        if (!view.hasOnClickListeners()) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDeleteClickListener != null) {
                        mDeleteClickListener.onDeleteClick(v, (Integer) v.getTag());
                    }
                }
            });
        }
        ((TextView) holder.getView(R.id.crash_type)).setText(bean.getCrashType());
        ((TextView) holder.getView(R.id.crash_desc)).setText(bean.getCrashDesc());

    }

    public void setOnDeleteClickListener(OnDeleteClickLister listener) {
        this.mDeleteClickListener = listener;
    }

    public interface OnDeleteClickLister {
        void onDeleteClick(View view, int position);
    }
}
