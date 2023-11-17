package com.example.xmljsonparsing;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<String> parseData(String data) {
        List<String> currencyRates = new ArrayList<>();

        try {
            String currencyName = "";
            String rate  = "";
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(getInputStream(data), "UTF-8");

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG && parser.getName().equals("targetCurrency")) {
                    // Extract required information and format the currency rates
                    currencyName = parser.nextText();
                }
                if (eventType == XmlPullParser.START_TAG && parser.getName().equals("exchangeRate")) {
                    // Extract required information and format the currency rates
                    rate = parser.nextText();
                    currencyRates.add(currencyName + " - " + rate);
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        return currencyRates;
    }

    private InputStream getInputStream(String data) {
        // Convert the String data to InputStream
        return new ByteArrayInputStream(data.getBytes());

    }
}
