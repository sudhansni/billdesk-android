package com.billdesk.app.billdesk.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.billdesk.app.billdesk.models.Category;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final List<Category> categories;
    private Context context;


    public CategoryAdapter(Context context, List<Category> categories) {
        this.categories = categories;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_messages, parent, false);
//        return new ViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Category message = categories.get(position);
//        holder.name.setText(message.getModifiedBy());
//        holder.time.setText(UiUtil.getRelativeTimeForMessages(context, message.getDateTime()));
//        holder.message.setText(message.getMessageText());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView name, time, message;

        ViewHolder(View view) {
            super(view);
//            name = (AppCompatTextView) view.findViewById(R.id.textview_name);
//            time = (AppCompatTextView) view.findViewById(R.id.textview_time);
//            message = (AppCompatTextView) view.findViewById(R.id.textview_message);

        }
    }
}
