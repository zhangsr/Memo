package me.zsr.memo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @description:
 * @author: Match
 * @date: 12/11/2017
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    private static final int HEIGHT_MIN = 200;
    private static final int HEIGHT_MAX = 600;
    private List<Memo> items;
    private MemoItemObserver memoItemObserver;

    public ItemAdapter(List<Memo> items, MemoItemObserver observer) {
        this.items = items;
        memoItemObserver = observer;
    }

    public void setData(List<Memo> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        String content = items.get(position).getContent();
        View view = holder.itemView;
        TextView textView = (TextView) view.findViewById(R.id.info_text);
        textView.setText(content);
        textView.setHeight(getCardHeight(content));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memoItemObserver.onItemClick(position);
            }
        });
    }

    // TODO: 22/11/2017 OPTMIZE
    private int getCardHeight(String content) {
        int height = content.length() / 5 * 60;
        if (height < HEIGHT_MIN) {
            height = HEIGHT_MIN;
        }
        if (height > HEIGHT_MAX) {
            height = HEIGHT_MAX;
        }
        return height;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
