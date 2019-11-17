package scnxq.com.appbase.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

public abstract class NiexqBaseRcAdpater<T> extends RecyclerView.Adapter<NiexqRcViewHolder> {
    private List<T> mItems = new ArrayList<>(0);

    public void addItem(T t) {
        mItems.add(t);
    }

    public List<T> getmItems() {
        return mItems;
    }

    public void clearItems() {
        mItems.clear();
    }

    public void addItems(List<T> items) {
        mItems.addAll(items);
    }

    public T getItem(int position) {
        return mItems.get(position);
    }

    @NonNull
    @Override
    public abstract NiexqRcViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(@NonNull NiexqRcViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
