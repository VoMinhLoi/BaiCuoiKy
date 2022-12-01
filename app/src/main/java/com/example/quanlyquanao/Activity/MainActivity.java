package com.example.quanlyquanao.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.quanlyquanao.Class.Product;
import com.example.quanlyquanao.Fragment.CartFragment;
import com.example.quanlyquanao.Fragment.HistoryFragment;
import com.example.quanlyquanao.Fragment.ProductFragment;
import com.example.quanlyquanao.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AHBottomNavigation ahBotNavHome;

    private List<Product> listCartProduct;

    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDataBotNavHome();
    }
// khởi tạo các item
    private void initItem() {
        ahBotNavHome = findViewById(R.id.ahbotnav_home);
        if(listCartProduct == null){
            listCartProduct = new ArrayList<>();
        }
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contet_frame, new ProductFragment());

        fragmentTransaction.commit();
    }

    private void setDataBotNavHome() {

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_product, R.drawable.ic_baseline_home_24, R.color.teal_200);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_cart, R.drawable.ic_baseline_add_shopping_cart_24, R.color.gray);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_history, R.drawable.ic_baseline_history_24, R.color.yellow);

        // Add items
        ahBotNavHome.addItem(item1);
        ahBotNavHome.addItem(item2);
        ahBotNavHome.addItem(item3);

        ahBotNavHome.setColored(false);

        // Set màu nav
        ahBotNavHome.setDefaultBackgroundColor(getResources().getColor(R.color.white));

        // Khi click vào các icon trên nav
        ahBotNavHome.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                switch (position){
                    case 0:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.contet_frame, new ProductFragment());
                        fragmentTransaction.commit();
                        break;

                    case 1:
                        break;
                    case 2:
                        break;
                }
                return true;
            }
        });
      }
    }