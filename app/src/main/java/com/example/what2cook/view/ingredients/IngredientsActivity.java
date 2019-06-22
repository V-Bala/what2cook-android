package com.example.what2cook.view.ingredients;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.what2cook.R;
import com.example.what2cook.view.recipes.RecipesListActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class IngredientsActivity extends AppCompatActivity {
    private final static String TAG = IngredientsActivity.class.getSimpleName();
    private final static boolean DEBUG = true;
    private final Context CONTEXT = this;

    Button meatsButton;
    Button vegsButton;
    Button grainsButton;
    Button spicesButton;
    Button submitIngredientsButton;
    List selectedItems;


    private Set<String> MASTER_INGREDIENTS_SET;

    private enum INGREDIENT_TYPE {
        MEAT, VEGETABLE, GRAIN, SPICE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // SET LAYOUT
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        // INITIALIZE BUTTONS
        meatsButton = findViewById(R.id.meatsButton);
        vegsButton = findViewById(R.id.vegsButton);
        grainsButton = findViewById(R.id.grainsButton);
        spicesButton = findViewById(R.id.spicesButton);
        submitIngredientsButton = findViewById(R.id.submitIngredientsButton);
        MASTER_INGREDIENTS_SET = new TreeSet<>();

        // ADD EVENT HANDLERS
        meatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomDialog(v, INGREDIENT_TYPE.MEAT.name());
            }
        });

        vegsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomDialog(v, INGREDIENT_TYPE.VEGETABLE.name());
            }
        });

        grainsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomDialog(v, INGREDIENT_TYPE.GRAIN.name());
            }
        });

        spicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomDialog(v, INGREDIENT_TYPE.SPICE.name());
            }
        });

        submitIngredientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecipesOverviewActivity(v);
            }
        });

    }

    /**
     * Starts the {@link RecipesListActivity}
     * Called when the user taps the submit button
     *
     *
     * @param view
     */
    public void startRecipesOverviewActivity(View view) {
        Intent intent = new Intent(this, RecipesListActivity.class);
        ArrayList<String> ingredientsList = new ArrayList<>();
        ingredientsList.addAll(MASTER_INGREDIENTS_SET);
        intent.putStringArrayListExtra("INGREDIENTS_LIST", ingredientsList);

        startActivity(intent);
        finish();
    }

    /**
     *
     * @param view
     */
    public void openCustomDialog(View view, String type) {
        final LayoutInflater inflater = LayoutInflater.from(CONTEXT);
        Log.d(TAG, "OPEN DIALOG");
        final View customDialogView;
        final AlertDialog dialog;
        final String[] multiChoiceItems;
        selectedItems = new ArrayList();  // Where we track the selected

        switch (type) {
            case "MEAT":
                customDialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
                multiChoiceItems = getResources().getStringArray(R.array.dialog_meats_array);
                dialog = new AlertDialog.Builder(CONTEXT)
                        .setTitle("Select " + type)
                        .setView(customDialogView)
                        .setMultiChoiceItems(multiChoiceItems, null,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        if (isChecked) {
                                            // If the user checked the item, add it to the selected items
                                            selectedItems.add(which);
                                            MASTER_INGREDIENTS_SET.add(multiChoiceItems[which]);
                                        } else if (selectedItems.contains(which)) {
                                            // Else, if the item is already in the array, remove it
                                            selectedItems.remove(Integer.valueOf(which));
                                        }
                                    }
                                })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                                Log.d(TAG, "CLOSE DIALOG");
                            }
                        })
                        .create();
                dialog.show();
                break;

            case "VEGETABLE":
                customDialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
                multiChoiceItems = getResources().getStringArray(R.array.dialog_vegs_array);
                dialog = new AlertDialog.Builder(CONTEXT)
                        .setTitle("Select " + type)
                        .setView(customDialogView)
                        .setMultiChoiceItems(multiChoiceItems, null,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        if (isChecked) {
                                            // If the user checked the item, add it to the selected items
                                            selectedItems.add(which);
                                            MASTER_INGREDIENTS_SET.add(multiChoiceItems[which]);
                                        } else if (selectedItems.contains(which)) {
                                            // Else, if the item is already in the array, remove it
                                            selectedItems.remove(Integer.valueOf(which));
                                        }
                                    }
                                })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                                Log.d(TAG, "CLOSE DIALOG");
                            }
                        })
                        .create();
                dialog.show();
                break;

            case "GRAIN":
                customDialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
                multiChoiceItems = getResources().getStringArray(R.array.dialog_grains_array);
                dialog = new AlertDialog.Builder(CONTEXT)
                        .setTitle("Select " + type)
                        .setView(customDialogView)
                        .setMultiChoiceItems(multiChoiceItems, null,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        if (isChecked) {
                                            // If the user checked the item, add it to the selected items
                                            selectedItems.add(which);
                                            MASTER_INGREDIENTS_SET.add(multiChoiceItems[which]);
                                        } else if (selectedItems.contains(which)) {
                                            // Else, if the item is already in the array, remove it
                                            selectedItems.remove(Integer.valueOf(which));
                                        }
                                    }
                                })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                                Log.d(TAG, "CLOSE DIALOG");
                            }
                        })
                        .create();
                dialog.show();
                break;

            case "SPICE":
                customDialogView = inflater.inflate(R.layout.custom_alert_dialog, null);
                multiChoiceItems = getResources().getStringArray(R.array.dialog_spices_array);
                dialog = new AlertDialog.Builder(CONTEXT)
                        .setTitle("Select " + type)
                        .setView(customDialogView)
                        .setMultiChoiceItems(multiChoiceItems, null,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        if (isChecked) {
                                            // If the user checked the item, add it to the selected items
                                            selectedItems.add(which);
                                            MASTER_INGREDIENTS_SET.add(multiChoiceItems[which]);
                                        } else if (selectedItems.contains(which)) {
                                            // Else, if the item is already in the array, remove it
                                            selectedItems.remove(Integer.valueOf(which));
                                        }
                                    }
                                })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                                Log.d(TAG, "CLOSE DIALOG");
                            }
                        })
                        .create();
                dialog.show();
                break;
        }
    }

}
