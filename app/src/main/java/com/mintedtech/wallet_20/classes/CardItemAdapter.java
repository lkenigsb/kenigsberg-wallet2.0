package com.mintedtech.wallet_20.classes;

import static androidx.core.content.ContextCompat.startActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.mintedtech.wallet_20.R;

import java.util.Date;

public class CardItemAdapter extends RecyclerView.Adapter<CardItemViewHolder> {

    private final int[] cardImages;
    private final String[] cardNames;

    private final String[] url;

    private final Context mContext;

    public CardItemAdapter(Context context, int[] cardImages, String[] cardNames, String[] url) {
        this.cardImages = cardImages;
        this.cardNames = cardNames;
        this.url = url;
        this.mContext = context;
    }

    @NonNull
    @Override
    public CardItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Make one "tile"; this will be called once per number of visible tiles on screen
        View itemView = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.rv_item, parent, false);

        return new CardItemViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CardItemViewHolder holder, int position) {
        // Insert into this tile the image and name of the car we are up to
        holder.imageView.setImageResource(cardImages[position]);
        holder.tv_card_name.setText(cardNames[position]);
        holder.itemView.setOnClickListener(v -> cardClickHandler(v, holder));
        holder.button.setOnClickListener(v -> showDatePicker(holder));
    }

    private void showDatePicker(@NonNull CardItemViewHolder holder) {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTheme(R.style.ThemeOverlay_App_MaterialCalendar);
        builder.setTitleText("Upcoming Due Date?");
        MaterialDatePicker<Long> datePicker = builder.build();
        datePicker.addOnPositiveButtonClickListener(selection -> showDaysLeft(selection, holder));

        FragmentManager fm = ((FragmentActivity) mContext).getSupportFragmentManager();
        datePicker.show(fm, "datePicker");
    }

    private void showDaysLeft(Long selection, @NonNull CardItemViewHolder holder) {
        if (selection != 0) {
            Date dateReturned = new Date(selection);
            Date today = new Date();

            double differenceInMilliseconds = dateReturned.getTime() - today.getTime();
            double daysDifference = differenceInMilliseconds / (1000 * 60 * 60 * 24);

            int wholeDaysLeft = (int) Math.ceil(daysDifference);
            wholeDaysLeft = wholeDaysLeft == -0 ? 0 : wholeDaysLeft;

            holder.tv_daysLeft.setText(wholeDaysLeft + " Day(s) Left");
        }
    }

    private void cardClickHandler(View v, @NonNull CardItemViewHolder holder) {
        int currentPosition = holder.getAdapterPosition();

        //TODO: Add calculation using mDataSet[position] to produce the next billing statement due date

        String msg = "Clicked on #" + +(currentPosition + 1) + ": " + cardNames[currentPosition] + "\n For login info click OK";

        Context context = v.getContext();


        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("American Express");

        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url[currentPosition]));
                        startActivity(context, i, null);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull CardItemViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        animateCard(holder.cardView);
    }

    private void animateCard(CardView view) {
        int centerX = 0, centerY = 0, startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator circularRevealAnimationOfCard = ViewAnimationUtils.createCircularReveal
                (view, centerX, centerY, startRadius, endRadius);
        circularRevealAnimationOfCard.start();
    }

    @Override
    public int getItemCount() {
        return cardImages.length;
    }
}







/*
package com.mintedtech.wallet_20.classes;

import static androidx.core.content.ContextCompat.startActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mintedtech.wallet_20.R;

import java.time.LocalDate;
import java.time.Period;


public class CardItemAdapter extends RecyclerView.Adapter<CardItemViewHolder> implements TextWatcher {

    private final int[] cardImages;
    private final String[] cardNames;

    private final String[] url;

    private String[] mDataset;

    private int position;

    public CardItemAdapter(int[] cardImages, String[] cardNames, String[] url, String[] myDataSet) {
        this.cardImages = cardImages;
        this.cardNames = cardNames;
        this.url = url;
        this.mDataset = myDataSet;
    }

    @NonNull
    @Override
    public CardItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Make one "tile"; this will be called once per number of visible tiles on screen
        View itemView = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.rv_item, parent, false);


        return new CardItemViewHolder(itemView);

        */
/*
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_edittext, parent, false);
        // pass MyCustomEditTextListener to viewholder in onCreateViewHolder
        // so that we don't have to do this expensive allocation in onBindViewHolder
        ViewHolder vh = new ViewHolder(v, new MyCustomEditTextListener());

        return vh;*//*

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CardItemViewHolder holder, int position) {
        // Insert into this tile the image and name of the car we are up to
        holder.imageView.setImageResource(cardImages[position]);
        holder.textView.setText(cardNames[position]);
        holder.itemView.setOnClickListener(v -> clickHandler(v, holder));
        updatePosition(holder.getAdapterPosition());
        holder.editText.setText(mDataset[holder.getAdapterPosition()]);

        //Validate date AKA what's in editText


        if (isNumeric()) {
            //then set the edit text with the amount of days remaining
            holder.editText.setText(getAmountDaysLeft());
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getAmountDaysLeft()
    {
        LocalDate date = LocalDate.now();

        Integer month = date.getMonthValue() + 1;
        LocalDate start = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
        LocalDate end = LocalDate.of(date.getYear(), month, Integer.parseInt(mDataset[position]));
        Period period = Period.between(start, end);
        return period.getDays();
    }


    public boolean isNumeric() {
        try {
            Double.parseDouble(mDataset[position]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void clickHandler(View v, @NonNull CardItemViewHolder holder) {
        int currentPosition = holder.getAdapterPosition();

        //TODO: Add calculation using mDataSet[position] to produce the next billing statement due date

        String msg = "Clicked on #" + +(currentPosition + 1) + ": " + cardNames[currentPosition] + "\n For login info click OK";

        Context context = v.getContext();


        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("American Express");

        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url[currentPosition]));
                        startActivity(context, i, null);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull CardItemViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        animateCard(holder.cardView);
    }

    private void animateCard(CardView view) {
        int centerX = 0, centerY = 0, startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator circularRevealAnimationOfCard = ViewAnimationUtils.createCircularReveal
                (view, centerX, centerY, startRadius, endRadius);
        circularRevealAnimationOfCard.start();
    }

    public void updatePosition(int position) {
        this.position = position;
    }

    @Override
    public int getItemCount() {
        return cardImages.length;
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        mDataset[position] = charSequence.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void afterTextChanged(Editable editable) {
        //Validate date AKA what's in editText
*/
/*

        if (isNumeric()) {
            //then set the edit text with the amount of days remaining
            LocalDate date = LocalDate.now();

            Integer month = date.getMonthValue() + 1;
            LocalDate start = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
            LocalDate end = LocalDate.of(date.getYear(), month, Integer.parseInt(mDataset[position]));
            Period period = Period.between(start, end);
            int days = period.getDays();

            editable.clear();
            editable.append(days + "days remaining");*//*

 */
/*
            holder.editText.setText(days + "days remaining for nect statement balance");*//*
 */
/*

        }

*//*

    }


}

*/
