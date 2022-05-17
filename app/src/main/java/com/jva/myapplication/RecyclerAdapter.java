package com.jva.myapplication;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {


    ///Definim lista noastra de mancare ce o sa fie afisata
    List<String> foodList;

    ///Definim lista noastra de mancare static
    List<String> foodListAll;

    /// Constructor si primim datele ca parametrii
    public RecyclerAdapter(List<String> foodList) {
        this.foodList = foodList;
        this.foodListAll = new ArrayList<>(foodList);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Facem inflate la interfata pentru item definita in row_item
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Setam text-ul din fiecare item sa fie egal cu foodList[position]
        holder.textView.setText((foodList.get(position)));
    }

    @Override
    public int getItemCount() {
        /// Cate elemente trebuie sa afisate = cate elemente sunt vector
        return foodList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ///Lista cu rezultate de search
            List<String> filteredList = new ArrayList<>();

            /// Daca nu avem search, afisam lista completa
            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(foodListAll);
            } else {
                for (String food : foodListAll) {
                    // Convertim text search si textele din lista la caractere lowercase si le comparam
                    if (food.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        /// Daca contine searchtext, il adaugam la lista de rezultate
                        filteredList.add(food);
                    }

                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            foodList.clear();
            foodList.addAll((Collection<? extends String>) filterResults.values);

            notifyDataSetChanged();

        }
    };

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView, rowCountTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.rowImageView);
            textView = itemView.findViewById(R.id.rowTitle);
            ///Cand dam click pe un item
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            //Afisam elementul curent intr un toast
            Toast.makeText(view.getContext(), foodList.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
    }

}
