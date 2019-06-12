package com.example.salestax;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import  android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public String itemType;
    public Button buttonAddItems , buttonCosts;

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private ElegantNumberButton buttonQuantity;
    private EditText description ,price;
    private TextView output;
    private Spinner goodsSpinner;
    private ArrayList<Goods> allProductInfo;
    private Calculations calculations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allProductInfo = new ArrayList<>();
        calculations = new Calculations(allProductInfo);

        radioGroup = findViewById(R.id.radioGroup);
        goodsSpinner = findViewById(R.id.spnGoods);
        description = findViewById(R.id.edtDescr);
        price = findViewById(R.id.edtPrices);
        output = findViewById(R.id.txtOutput);
        buttonQuantity = findViewById(R.id.btnQty);
        buttonAddItems = findViewById(R.id.btnAddItems);
        buttonCosts = findViewById(R.id.btnCalculateCosts);

        buttonAddItems.setOnClickListener(this);
        buttonCosts.setOnClickListener(this);
        buttonQuantity.setOnClickListener(this);

        output.setMovementMethod(new ScrollingMovementMethod());
        initializeSpinner();

    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnAddItems:
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                if(!description.getText().toString().isEmpty() && !price.getText().toString().isEmpty())
                {
                    calculations.populateItems(itemType, description.getText().toString(), Double.parseDouble(price.getText().toString()), Integer.parseInt(buttonQuantity.getNumber()), radioButton.getText().toString());
                    Toast.makeText(this, "Item " + description.getText().toString() + " added to the Basket ", Toast.LENGTH_LONG).show();
                    description.setText("");
                    price.setText("");
                }else {
                    Toast.makeText(this, "Please make sure you Enter product description and price  " , Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btnCalculateCosts:
                DecimalFormat df = new DecimalFormat("#.00");
                for(int i = 0 ; i < allProductInfo.size() ; i++)
                {
                    String status = "";

                    if( allProductInfo.get(i).getImportStatus().equalsIgnoreCase("yes")){
                        status = "Imported";
                    }
                    double percentage = calculations.calculatePercentage(allProductInfo.get(i).getType(), allProductInfo.get(i).getImportStatus(), allProductInfo.get(i).getPrice());
                    String finalPrice = df.format(percentage);
                    String display = allProductInfo.get(i).getQuantity() + "       " +  status +  "      " + allProductInfo.get(i).getDesc() +  "   : " + finalPrice ;
                    output.append(display);
                    output.append("\n");
                }
                String salesOutputFormat = df.format(calculations.calculateSales());
                String salesOutput = "Sales Taxes :" + salesOutputFormat;
                String totalOutputFormat = df.format(calculations.calculateTotal());
                String totalOutput = "Total      : " + totalOutputFormat;
                output.append(salesOutput);
                output.append("\n");
                output.append(totalOutput);
                break;
        }
    }

    public void checkButton(View view)
    {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Product Import Status : " + radioButton.getText(), Toast.LENGTH_LONG).show();
    }

    public  void initializeSpinner()
    {
        goodsSpinner.setDropDownVerticalOffset(90);

        ArrayList<String> allGoodsCategory;
        allGoodsCategory= new ArrayList<>();
        allGoodsCategory.add("Others");
        allGoodsCategory.add("Books");
        allGoodsCategory.add("Food");
        allGoodsCategory.add("Medical");

        final CustomSpinnerAdapter  customSpinnerAdapter = new CustomSpinnerAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item , allGoodsCategory);
        customSpinnerAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);


        goodsSpinner.setAdapter(customSpinnerAdapter);
        goodsSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

            @Override
            public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){
                 itemType = parent.getItemAtPosition(position).toString();
                if(position > 0){
                    Toast.makeText(parent.getContext(), "Shelf of  : " + itemType + " Products " , Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private class CustomSpinnerAdapter  extends ArrayAdapter<String> implements SpinnerAdapter
    {

        private  int res;
        private Context context;
        private List<String> objects;

        private CustomSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<String> objects){
            super(context, resource, objects);
            this.res = resource;
            this.context = context;
            this.objects = objects;
        }

        @Override
        public int getCount()
        {
            return  objects.size();
        }

        @Override
        public String getItem(int posistion)
        {
            return  objects.get(posistion);
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public  View getDropDownView(int position ,View convertView ,@NonNull ViewGroup parent)
        {

            TextView txt = (TextView)View.inflate(context,android.R.layout.select_dialog_singlechoice,null );
            txt.setTextSize(15);
            txt.setWidth(200);
            txt.setHeight(18);
            txt.setTypeface(Typeface.DEFAULT_BOLD);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setBackground(getDrawable(R.drawable.spinner_item_divider));
            txt.setText(objects.get(position));
            txt.setTextColor(Color.parseColor("#000000"));

            return  txt;
        }

        @NonNull
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(MainActivity.this);
            txt.setGravity(Gravity.CENTER);
            txt.setTextSize(ViewGroup.LayoutParams.WRAP_CONTENT);
            txt.setPadding(20,20,20,20);
            txt.setCompoundDrawablesWithIntrinsicBounds(0 , 0 , R.drawable.ic_arrowdown ,0);
            txt.setText(objects.get(position));
            txt.setTypeface(Typeface.DEFAULT_BOLD);
            txt.setHint("Choose Destination");
            txt.setHintTextColor(Color.parseColor("#FF0023"));
            txt.setTextColor(Color.parseColor("#F30404"));

            return  txt;
        }

    }

}
