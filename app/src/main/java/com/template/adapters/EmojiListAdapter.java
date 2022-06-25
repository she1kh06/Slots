package com.template.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.template.Emoji;
import com.template.R;

import java.util.ArrayList;

public class EmojiListAdapter extends ArrayAdapter<Emoji> {
    public EmojiListAdapter(@NonNull Context context, ArrayList<Emoji> emojis) {
        super(context, 0, emojis);
    }

    private void setEmojiTo(int generatedNumber, ImageView imageViewNumber) {
        int result = getImageByNumber(generatedNumber);
        imageViewNumber.setImageResource(result);
    }

    public int getImageByNumber(int generatedNumber) {
        int result = 1;
        switch (generatedNumber) {
            case 1:
                result = R.drawable.bomb;
                break;
            case 2:
                result = R.drawable.dollar;
                break;
            case 3:
                result = R.drawable.brilliant;
                break;
            case 4:
                result = R.drawable.pear;
                break;
            case 5:
                result = R.drawable.cherry;
                break;
            case 6:
                result = R.drawable.pepe;
                break;
            case 7:
                result = R.drawable.real_coin;
                break;
            case 8:
                result = R.drawable.lemon;
                break;
            case 9:
                result = R.drawable.strawberry;
                break;
            case 10:
                result = R.drawable.tarvuz;
                break;
            case 11:
                result = R.drawable.bocket;
                break;

            case 12:
                result = R.drawable.rich;
                break;
        }
        return result;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.emoji_item, parent, false);
        }
        Emoji currentEmoji = getItem(position);
        ImageView imageViewFirstEmoji = convertView.findViewById(R.id.imageView_first_emoji);
        setEmojiTo(currentEmoji.getFirst_emojiId(), imageViewFirstEmoji);
        ImageView imageViewSecondEmoji = convertView.findViewById(R.id.imageView_second_emoji);
        setEmojiTo(currentEmoji.getSecond_emojiId(), imageViewSecondEmoji);
        ImageView imageViewThirdEmoji = convertView.findViewById(R.id.imageView_third_emoji);
        setEmojiTo(currentEmoji.getThird_emojiId(), imageViewThirdEmoji);
        return convertView;
    }
}