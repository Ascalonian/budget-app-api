package com.majicode.budgetapp;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;


public class RFC3339DateFormat {

  // Same as ISO8601DateFormat but serializing milliseconds.
  public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
    DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    toAppendTo.append(df2.format(date));
    
    return toAppendTo;
  }


}