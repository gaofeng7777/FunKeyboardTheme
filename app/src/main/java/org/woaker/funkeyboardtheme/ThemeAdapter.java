package org.woaker.funkeyboardtheme;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapterHolder> {

    public ArrayList<Integer> integers = ThemeType.getThemeList();
    private Context context;
    private ThemeItemOnClickListener themeItemOnClickListener;

    public void setThemeItemOnClickListener(ThemeItemOnClickListener themeItemOnClickListener) {
        this.themeItemOnClickListener = themeItemOnClickListener;
    }

    public ThemeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ThemeAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThemeAdapterHolder(LayoutInflater.from(context).inflate(R.layout.theme_viewholder, parent, false));
    }

    @Override
    public void onBindViewHolder(ThemeAdapterHolder holder, final int position) {

        holder.holder_iv.setImageResource(integers.get(position));

        if (themeItemOnClickListener != null) {
            holder.holder_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    themeItemOnClickListener.itemOnClick(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return integers.size();
    }

}
