package com.bb.hotel.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bb.hotel.R;
import com.bb.hotel.adapter.NameAdaptor;
import com.bb.hotel.model.Name;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int count = 0;
    private TextView mainTextView;
    private String mainKey = "my.count.key";
    private SharedPreferences sharedPreferences;

    private NameAdaptor nameAdaptor;

    private EditText guestNameEditText;
    private ListView guestListView;
    private Button addGuestButton;

    private int guestCount = 0;
    private String guestKeyPrefix = "GUEST_";//GuestCount 0


    private List guestList = new ArrayList<String>();
    private List nameList = new ArrayList<Name>();

    private final String GUEST_COUNT_KEY = "guest.count.key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("com.bb.hotel", Context.MODE_PRIVATE);

        guestNameEditText = findViewById(R.id.editText);
        guestListView = findViewById(R.id.guest_list);
        addGuestButton = findViewById(R.id.add_new_guest);

        guestCount = sharedPreferences.getInt(GUEST_COUNT_KEY, 0);

        addGuestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String guestName = guestNameEditText.getText().toString().trim();
                guestCount++;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(guestKeyPrefix + guestCount, guestName);
                editor.putInt(GUEST_COUNT_KEY, guestCount);
                editor.apply();

                readGuests();
                guestNameEditText.setText("");
            }
        });

        readGuests();

    }

    private void readGuests() {
        guestCount = sharedPreferences.getInt(GUEST_COUNT_KEY, 0);
        guestList.clear();//To avoid adding the same values

        for (int i = 0; i < guestCount; i++) {
            String name = sharedPreferences.getString(guestKeyPrefix+(i + 1), "unknown");
            guestList.add(name);

            nameList.add(new Name("Mr.", name));

            //Log.d("TAG_X", name);
        }

        updateGuestList();
    }

    private void updateGuestList(){
        nameAdaptor = new NameAdaptor(nameList);
        guestListView.setAdapter(nameAdaptor);
    }
}
