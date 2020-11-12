package com.edureka.openweathermap.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

//POJO CLASS
public class WeatherData {

    public main main;
    public coord coord;
    public sys sys;
    public wind wind;
    @SerializedName("weather")
    @Expose
    public ArrayList<weather> weather1;
    public  String   dt;
    public String name;


    public String getdt(){return dt;}
    public String getname(){return name;}

    public class main{
        public  String   temp;
        public  String   humidity;
        public  String   temp_min;
        public  String   temp_max;

        public String gettemp(){
            return temp;
        }
        public String gethumidity(){
            return humidity;
        }
        public String gettemp_min(){
            return temp_min;
        }
        public String gettemp_max(){
            return temp_max;
        }
    }

    public class coord {
        @SerializedName("lat") // <- this will be the JSON key name
        @Expose
        public  String   lat ;
        @SerializedName("lon") // <- this will be the JSON key name
        @Expose
        public  String   lon;

        public String getLat(){
            return lat;
        }
        public String getLon(){
            return lon;
        }
    }

    public class sys {
        public  String  country;

        public String getstring(){
            return country;
        }

    }

    public class wind {
        public  String   speed;
        public  String   deg;

        public String getspeed(){
            return speed;
        }
        public String getdeg(){
            return deg;
        }
    }

    public class weather {
        public int id;
        public String main;
        public String description;
        public String icon;

        public String getDescription() {
            return description;
        }
    }

}
