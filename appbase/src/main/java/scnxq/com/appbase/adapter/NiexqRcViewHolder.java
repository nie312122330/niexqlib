package scnxq.com.appbase.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;


public class NiexqRcViewHolder extends RecyclerView.ViewHolder {

    public NiexqRcViewHolder(View itemView) {
        super(itemView);
    }

    public <T extends View> T getView(int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) itemView.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<>();
            itemView.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = itemView.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
