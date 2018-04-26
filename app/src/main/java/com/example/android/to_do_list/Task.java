    package com.example.android.to_do_list;

    import android.widget.Button;


    public class Task {

        private String name;
        private String time;

        public Task(){

        }
        public Task(String name, String time, Button btn){
            this.name = name;
            this.time = time;

        }
        public String getName() {
            return name;
        }


        public String getTime() {
            return time;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
