package com.template.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.template.R;
import com.template.activities.GameInfo;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.SlotsViewHolder> {

    private List<GameInfo> historyList;

    public HistoryAdapter() {
        historyList = new ArrayList<>();
    }

    public void setHistoryList(List<GameInfo> historyList) {
        this.historyList = historyList;
        notifyDataSetChanged();
    }

    public void addInfo(GameInfo info) {
        historyList.add(info);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SlotsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View result = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item , parent ,false);
        return new SlotsViewHolder(result);
    }

    @Override
    public void onBindViewHolder(@NonNull SlotsViewHolder holder, int position) {
        GameInfo gameInfo = historyList.get(position);
        if (gameInfo.isWin()) {
            holder.tv_earnedCoins.setText(String.format("Earned coins: %s",convertToString(gameInfo.getCoin_amount())));

        } else {
            holder.tv_earnedCoins.setText(String.format("Lost coins: %s",convertToString(gameInfo.getCoin_amount())));
        }
        holder.tv_gameTurn.setText(String.format("Game order: %s ",convertToString(gameInfo.getTern())));
        holder.imv_FirstEmoji.setImageResource(gameInfo.getEmoji_1());
        holder.imv_SecondEmoji.setImageResource(gameInfo.getEmoji_2());
        holder.imv_ThirdEmoji.setImageResource(gameInfo.getEmoji_3());
    }

    private String convertToString(int integer) {
        return String.valueOf(integer);
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    static class SlotsViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_gameTurn;
        private final TextView tv_earnedCoins;
        private final ImageView imv_FirstEmoji;
        private final ImageView imv_SecondEmoji;
        private final ImageView imv_ThirdEmoji;

        public SlotsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_gameTurn =  itemView.findViewById(R.id.textViewGameTern);
            tv_earnedCoins =  itemView.findViewById(R.id.textViewEarnedCoins);
            imv_FirstEmoji =  itemView.findViewById(R.id.imageViewFirstEmoji);
            imv_SecondEmoji =  itemView.findViewById(R.id.imageViewSecondEmoji);
            imv_ThirdEmoji =  itemView.findViewById(R.id.imageViewThirdEmoji);
        }
    }
}
