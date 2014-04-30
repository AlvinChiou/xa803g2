package com.order.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
G	Era designator	AD
y	Year	1996; 96
M	Month in year	July; Jul; 07
w	Week in year	27
W	Week in month	2
D	Day in year	189
d	Day in month	10
F	Day of week in month	2
E	Day in week	Tuesday; Tue
a	Am/pm marker	PM
H	Hour in day (0-23)	0
k	Hour in day (1-24)	24
K	Hour in am/pm (0-11)	0
h	Hour in am/pm (1-12)	12
m	Minute in hour	30
s	Second in minute	55
S	Millisecond	978
z	Time zone	Pacific Standard Time; PST; GMT-08:00
Z	Time zone	-0800
*/
public class GetTimer {
	String DATE_FORMAT_NOW;
	public GetTimer(String DATE_FORMAT_NOW){
		this.DATE_FORMAT_NOW = DATE_FORMAT_NOW; //"yyyy-MM-dd HH:mm:ss ";
	}		
	public String GetToDay() {
		Calendar tmpCal = Calendar.getInstance();
		SimpleDateFormat tmpSDF = new SimpleDateFormat(DATE_FORMAT_NOW);
		String toDay= tmpSDF.format(tmpCal.getTime()).toString();
		return toDay;
	}
}
