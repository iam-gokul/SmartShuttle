package com.vit.app.smartshuttle.modals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("regNo")
        @Expose
        private String regNo;
        @SerializedName("balance")
        @Expose
        private String balance;
        @SerializedName("userType")
        @Expose
        private String userType;



        public String getUserName() {
            return userName;
        }



        public String getRegNo() {
            return regNo;
        }

        public String getBalance() {
            return balance;
        }


        public String getUserType() {
            return userType;
        }

    }


