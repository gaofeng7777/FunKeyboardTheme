package org.woaker.funkeyboardtheme;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

class ThemeAdapterHolder extends RecyclerView.ViewHolder{

    public ImageView holder_iv;

    public ThemeAdapterHolder(View itemView) {
        super(itemView);
        holder_iv = itemView.findViewById(R.id.theme_adapter_viewholder_iv);
    }
}
