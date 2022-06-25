package com.template.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.template.Coins;
import com.template.Emoji;
import com.template.R;
import com.template.adapters.EmojiListAdapter;
import com.template.database.MoneyDatabase;
import com.template.database.SlotsDatabase;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private ListView listViewFirstRow;
    private ListView listViewSecondRow;
    private ListView listViewThirdRow;
    private TextView textViewCoinsamount;
    private boolean isBetted;
    private boolean win;
    int firstMainEmoji;
    int secondMainEmoji;
    int thirdMainEmoji;

    int reward;
    int result;

    private SharedPreferences preferences;

    private MoneyDatabase database;
    private SlotsDatabase slotsDatabase;
    int allCoins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        database = MoneyDatabase.getInstance(this);
        slotsDatabase = SlotsDatabase.getInstance(this);
        allCoins = database.getDao().getCoins().getCoinCount();
        listViewFirstRow = findViewById(R.id.list_view_first_row);
        listViewSecondRow = findViewById(R.id.list_view_second_row);
        listViewThirdRow = findViewById(R.id.list_view_third_row);
        textViewCoinsamount = findViewById(R.id.textViewCurrentCoins);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getInt("tern", -1) == -1) {
            int tern = 0;
            preferences.edit().putInt("tern", tern).apply();
        }
        isBetted = false;
        textViewCoinsamount.setText(String.format(getString(R.string.coins), database.getDao().getCoins().getCoinCount()));
        generateEmojies();
    }

    private void generateEmojies() {
        ArrayList<Emoji> first_row_array = new ArrayList<>();
        firstMainEmoji = getEmojiId();
        first_row_array.add(new Emoji(getEmojiId(), firstMainEmoji, getEmojiId()));

        ArrayList<Emoji> second_row_array = new ArrayList<>();
        secondMainEmoji = getEmojiId();
        second_row_array.add(new Emoji(getEmojiId(), secondMainEmoji, getEmojiId()));

        ArrayList<Emoji> third_row_array = new ArrayList<>();
        thirdMainEmoji = getEmojiId();
        third_row_array.add(new Emoji(getEmojiId(), thirdMainEmoji, getEmojiId()));

        EmojiListAdapter first_row_adapter = new EmojiListAdapter(this, first_row_array);
        listViewFirstRow.setAdapter(first_row_adapter);
        EmojiListAdapter second_row_adapter = new EmojiListAdapter(this, second_row_array);
        listViewSecondRow.setAdapter(second_row_adapter);
        EmojiListAdapter third_row_adapter = new EmojiListAdapter(this, third_row_array);
        listViewThirdRow.setAdapter(third_row_adapter);
    }

    private int getEmojiId() {
        int minimum = 1;
        int maximum = 12;
        return (int) (Math.random() * (maximum - minimum + 1) + minimum);
    }

    private void getMainEmojies(int firstEmojiId, int secondEmojiId, int thirdEmojiId) {
        GameInfo info = new GameInfo(reward, win, preferences.getInt("tern", 1), getImageResByNumber(firstEmojiId), getImageResByNumber(secondEmojiId), getImageResByNumber(thirdEmojiId));
        slotsDatabase.getDao().setGameInfo(info);
    }

    private int getImageResByNumber(int generatedNumber) {
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

    public void onClickSpinCasino(View view) {
        if (isBetted) {
            CountDownTimer countDownTimer = new CountDownTimer(3000, 100) {
                @Override
                public void onTick(long l) {
                    generateEmojies();
                }

                @Override
                public void onFinish() {
                    preferences.edit().putInt("tern", preferences.getInt("tern", 1) + 1).apply();
                    boolean isWinner = isWinner();

                    if (isWinner) {
                        showAlertDialog(true, 5, 20);
                        win = true;
                    } else {
                        showAlertDialog(false, 10, 20);
                        win = false;
                    }
                    isBetted = false;
                    getMainEmojies(firstMainEmoji, secondMainEmoji, thirdMainEmoji);
                }
            };
            countDownTimer.start();
        } else {
            Toast.makeText(GameActivity.this, "You have to bet some coins for playing in slots", Toast.LENGTH_SHORT).show();
        }
    }

    private int generateCoin(int minimum, int maximum) {
        return (int) (Math.random() * (maximum - minimum + 1) + minimum);
    }

    private boolean isWinner() {
        result = (int) (Math.random() * (50 - 10 + 1) + 10);
        if (result <= 25) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int itemId = item.getItemId();
        if (itemId == R.id.back) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public void onClickBetMoney(View view) {
        if (!isBetted) {
            if (allCoins < 5) {
                showAlertDialog(true, 20, 30);
            } else {
                allCoins = allCoins - 5;
                textViewCoinsamount.setText(getString(R.string.earned_coins_s, String.valueOf(allCoins)));
                isBetted = true;
            }
        } else {
            Toast.makeText(this, "You have already bet 5 coins", Toast.LENGTH_SHORT).show();
        }
    }

    private void showAlertDialog(boolean claim, int max, int min) {
        if (claim) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            reward = generateCoin(min, max);
            allCoins = allCoins + reward;
            alertDialog.setMessage(String.format("Your prize is %s coins", reward));
            alertDialog.setTitle("Congratulations");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Claim",
                    (dialogInterface, i) -> {
                        textViewCoinsamount.setText(getString(R.string.earned_coins_s, String.valueOf(allCoins)));
                        Coins currentCoin = database.getDao().getCoins();
                        currentCoin.setCoinCount(allCoins);
                        database.getDao().updateCoins(currentCoin);
                        generateEmojies();
                    });
            alertDialog.show();
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            reward = generateCoin(min, max);
            allCoins = allCoins - reward;
            alertDialog.setMessage(String.format("You losed %s coins", reward));
            alertDialog.setTitle("We are sorry");
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Pay",
                    (dialogInterface, i) -> {
                        textViewCoinsamount.setText(getString(R.string.earned_coins_s, String.valueOf(allCoins)));
                        Coins currentCoin = database.getDao().getCoins();
                        currentCoin.setCoinCount(allCoins);
                        database.getDao().updateCoins(currentCoin);
                        generateEmojies();
                        if (allCoins < 5) {
                            doIfNoMoney();
                        }
                    });
            alertDialog.show();
        }
    }

    private void doIfNoMoney() {
        CountDownTimer countDownTimer = new CountDownTimer(500, 500) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                showAlertDialog(true, 50, 30);
            }
        };
        countDownTimer.start();
    }
}