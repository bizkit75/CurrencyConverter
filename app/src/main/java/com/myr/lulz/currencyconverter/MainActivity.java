package com.myr.lulz.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static android.widget.ListView.*;

public class MainActivity extends AppCompatActivity {

    Integer NumberOfItem = 0;

    final String[] list = new String[6];
    final ArrayList<XMLClass> listXMLclass = new ArrayList<XMLClass>();
    XMLClass XMLclassItem;

    String[] values;
    Double[] listconverted ;
    Double number =1.00;

    String[] arraySpinner;
    Double result = 1.00;
    ListView listview;
    ArrayAdapter adapterlistview;

    public void Start()
    {
        arraySpinner = new String[NumberOfItem];
        listconverted = new Double[NumberOfItem];

        System.out.println("taille listXMLClass" + listXMLclass.size());

        for (int i = 0; i  < NumberOfItem; i++) {

            arraySpinner[i] = listXMLclass.get(i).getName();
            listconverted[i] = listXMLclass.get(i).getValue();


            list[i] = String.valueOf( 1 + " - " +  listXMLclass.get(i).getName().toString());
        }
        listview.setAdapter(adapterlistview);
    }

    public void Calculate(Double number, Integer Currency) {

        System.out.println("id de currency" + Currency);

        System.out.println("taille listXMLClass" + listXMLclass.size());


        for (int i = 0; i  < NumberOfItem; i++) {

            arraySpinner[i] = listXMLclass.get(i).getName();
            listconverted[i] = listXMLclass.get(i).getValue();


            result = (number * listXMLclass.get(i).getValue()) / listXMLclass.get(Currency).getValue();


            list[i] = String.valueOf(result + " - " +  listXMLclass.get(i).getName().toString());
            }

            listview.setAdapter(adapterlistview);
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         listview = (ListView) findViewById(R.id.listView);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final EditText numberbox = (EditText) findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);

        adapterlistview = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);

        Parse();
        Start();
        Calculate(1.00, 0);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);




            button.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (numberbox.getText().toString().length() == 0)
                        return;

                    number = Double.parseDouble(numberbox.getText().toString());
                    System.out.println("number value =" + number);
                    Calculate(number, spinner.getSelectedItemPosition());

                }

            });


        }
    public void Parse()
    {
        listXMLclass.clear();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream is = getResources().getAssets().open("currencies_data.xml");

            Document doc = builder.parse(is);
            Element element = doc.getDocumentElement();

            NodeList nodes = element.getChildNodes();
            NodeList itemsList = doc.getElementsByTagName("currency");

            System.out.println("Taille de itemlist: " + itemsList.getLength());
            NumberOfItem = itemsList.getLength();


            XMLclassItem = null;
            for (int i = 0; i  < itemsList.getLength(); i++) {

                Node itemNode = itemsList.item(i);
                Element item = (Element) itemNode;

                Node idNode = item.getElementsByTagName("id").item(0);
                String id = idNode.getFirstChild().getNodeValue();
                System.out.println("id: " + id);

                Node nameNode = item.getElementsByTagName("name").item(0);
                String name = nameNode.getFirstChild().getNodeValue();
                System.out.println("name: " + name);

                Node valueNode = item.getElementsByTagName("value").item(0);
                String value = valueNode.getFirstChild().getNodeValue();
                System.out.println("value: " + value);

                XMLclassItem = new XMLClass(Integer.valueOf(id),name,Double.parseDouble(value));

                listXMLclass.add(XMLclassItem);


            }
        } catch (Exception  e){
            e.printStackTrace();
        }
    }




}


