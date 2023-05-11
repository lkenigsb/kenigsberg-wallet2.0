package com.mintedtech.wallet_20.classes;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mintedtech.wallet_20.R;

//Java mirror of rv_item.xml
class CardItemViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    ImageView imageView;
    TextView tv_card_name;
    TextView tv_daysLeft;

    Button button;

    public CardItemViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.cardView);
        imageView = itemView.findViewById(R.id.imageView);
        tv_card_name = itemView.findViewById(R.id.tv_cardName);
        tv_daysLeft = itemView.findViewById(R.id.tv_daysLeft);
        button = itemView.findViewById(R.id.button);
    }
}
