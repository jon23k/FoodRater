package com.example.jony.foodrater;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by jony on 6/20/16.
 */
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private HashMap<String, Integer > sDefaultNamesAndIds = new HashMap<String, Integer>();
    private ArrayList<String> mNames = new ArrayList<String>();
    private Random mRandom = new Random();
    private HashMap<String, Float> namesAndRatings = new HashMap<>();
    private RecyclerView mRecyclerView;

    public FoodAdapter(RecyclerView r) {
        mRecyclerView = r;
        namesAndRatings.put("banana", 0.0f);
        namesAndRatings.put("homemade bread", 0.0f);
        namesAndRatings.put("chicken", 0.0f);
        namesAndRatings.put("chocolate", 0.0f);
        namesAndRatings.put("ice cream", 0.0f);
        namesAndRatings.put("lima beans", 0.0f);
        namesAndRatings.put("steak", 0.0f);
        namesAndRatings.put("broccoli", 0.0f);
        sDefaultNamesAndIds.put("broccoli", R.drawable.broccoli);
        sDefaultNamesAndIds.put("homemade bread", R.drawable.bread);
        sDefaultNamesAndIds.put("chicken", R.drawable.chicken);
        sDefaultNamesAndIds.put("chocolate", R.drawable.chocolate);
        sDefaultNamesAndIds.put("ice cream", R.drawable.icecream);
        sDefaultNamesAndIds.put("lima beans", R.drawable.limabeans);
        sDefaultNamesAndIds.put("steak", R.drawable.steak);
        sDefaultNamesAndIds.put("banana", R.drawable.banana);
        sDefaultNamesAndIds.put("broccoli", R.drawable.broccoli);
        sDefaultNamesAndIds.put("homemade bread", R.drawable.bread);
        sDefaultNamesAndIds.put("chicken", R.drawable.chicken);
        sDefaultNamesAndIds.put("chocolate", R.drawable.chocolate);
        sDefaultNamesAndIds.put("ice cream", R.drawable.icecream);
        sDefaultNamesAndIds.put("lima beans", R.drawable.limabeans);
        sDefaultNamesAndIds.put("steak", R.drawable.steak);
        for (int i = 0; i < 5; i++) {
            mNames.add(getRandomFoodName());
        }
    }

    private String getRandomFoodName() {
        ArrayList<String> keysAsArray = new ArrayList<String>(sDefaultNamesAndIds.keySet());
        return keysAsArray.get(mRandom.nextInt(keysAsArray.size()));
    }

    @Override
    public int getItemCount() {
        return sDefaultNamesAndIds.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_food, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("","POSITION IS: "+ position);
        String name = mNames.get(position);
        holder.foodNameView.setText(name);
        holder.foodImageView.setImageResource(sDefaultNamesAndIds.get(name));
        holder.foodRatingBar.setRating(namesAndRatings.get(name));
    }

    public void addName()
    {
        mNames.add(0, getRandomFoodName());
        notifyItemInserted(0);
        mRecyclerView.getLayoutManager().scrollToPosition(0);
    }

    public void removeName(int position){
        Log.d("","POSITION IS: "+ position);
        mNames.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView foodNameView;
        private ImageView foodImageView;
        private RatingBar foodRatingBar;
        public ViewHolder(View view){
            super(view);
            view.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v){
                    removeName(getAdapterPosition());
                    return false;
                }
            });
            foodNameView = (TextView)view.findViewById(R.id.textView);
            foodImageView =  (ImageView)view.findViewById(R.id.imageView);
            foodRatingBar = (RatingBar)view.findViewById(R.id.ratingBar);
            foodRatingBar.setOnRatingBarChangeListener(
                    new RatingBar.OnRatingBarChangeListener() {
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                            namesAndRatings.put(foodNameView.getText().toString(), rating);
                        }
                    }
            );

        }
    }

}
