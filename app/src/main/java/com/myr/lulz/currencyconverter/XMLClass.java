package com.myr.lulz.currencyconverter;

import android.os.Bundle;
import android.util.Xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by lulz on 28/10/2015.
 */
public class XMLClass extends MainActivity{

    private Integer id;
    private String name;
    private Double value;

    XMLClass(int id, String  name, Double value){
        this.id = id;

        this.name = name;

        this.value = value;

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }



}





