package com.example.recyclecardview.Activities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.recyclecardview.Models.Movie;
import com.example.recyclecardview.Adapters.MyAD;
import com.example.recyclecardview.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.movies = this.myMovies();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //El layout a renderizar
        layoutManager = new LinearLayoutManager(MainActivity.this);
        //layoutManager = new GridLayoutManager(MainActivity.this, 2);
        //layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        myAdapter = new MyAD(this.movies, R.layout.activity_recycler_view, new MyAD.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {
//                Toast.makeText(MainActivity.this,names.get(position)+": "+position,Toast.LENGTH_SHORT).show();
                deleteMovies(position);
            }
        });
        //Se utiliza solo cuando los elementos en el layout tienen el mismo tamaño
        recyclerView.setHasFixedSize(true);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
    }

    private List<Movie> myMovies(){
        return new ArrayList<Movie>(){{
            add(new Movie("AVENGERS",R.drawable.avengers));
            add(new Movie("CONSTANTINE",R.drawable.contantine));
            add(new Movie("PACIFIC RIM",R.drawable.pacific));
            add(new Movie("REAL STEEL",R.drawable.realsteel));
        }};
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_item,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_menu:
                this.addMovies(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void addMovies(int position){
        this.movies.add(new Movie("New Movie",R.drawable.contantine));
        this.myAdapter.notifyItemInserted(position);
        this.layoutManager.scrollToPosition(position);
    }

    private void deleteMovies(int position) {
        this.movies.remove(position);
        this.myAdapter.notifyItemRemoved(position);
    }
}
