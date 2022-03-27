package com.example.servayapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SurveyModel extends RealmObject implements Parcelable {

    @PrimaryKey
    private int id;
    private String surveyHomeDist;
    private String surveyName, surveyEducation, surveyDOB, surveyArea, surveyKids, surveyHouse,
            currentTime,imagePath;

    public SurveyModel() {
    }

    public SurveyModel(int id, String surveyHomeDist, String surveyName, String surveyEducation,
                       String surveyDOB, String surveyArea, String surveyKids, String surveyHouse, String currentTime, String imagePath) {
        this.id = id;
        this.surveyHomeDist = surveyHomeDist;
        this.surveyName = surveyName;
        this.surveyEducation = surveyEducation;
        this.surveyDOB = surveyDOB;
        this.surveyArea = surveyArea;
        this.surveyKids = surveyKids;
        this.surveyHouse = surveyHouse;
        this.currentTime = currentTime;
        this.imagePath = imagePath;
    }

    protected SurveyModel(Parcel in) {
        id = in.readInt();
        surveyHomeDist = in.readString();
        surveyName = in.readString();
        surveyEducation = in.readString();
        surveyDOB = in.readString();
        surveyArea = in.readString();
        surveyKids = in.readString();
        surveyHouse = in.readString();
        currentTime = in.readString();
        imagePath = in.readString();
    }

    public static final Creator<SurveyModel> CREATOR = new Creator<SurveyModel>() {
        @Override
        public SurveyModel createFromParcel(Parcel in) {
            return new SurveyModel(in);
        }

        @Override
        public SurveyModel[] newArray(int size) {
            return new SurveyModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurveyHomeDist() {
        return surveyHomeDist;
    }

    public void setSurveyHomeDist(String surveyHomeDist) {
        this.surveyHomeDist = surveyHomeDist;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public String getSurveyEducation() {
        return surveyEducation;
    }

    public void setSurveyEducation(String surveyEducation) {
        this.surveyEducation = surveyEducation;
    }

    public String getSurveyDOB() {
        return surveyDOB;
    }

    public void setSurveyDOB(String surveyDOB) {
        this.surveyDOB = surveyDOB;
    }

    public String getSurveyArea() {
        return surveyArea;
    }

    public void setSurveyArea(String surveyArea) {
        this.surveyArea = surveyArea;
    }

    public String getSurveyKids() {
        return surveyKids;
    }

    public void setSurveyKids(String surveyKids) {
        this.surveyKids = surveyKids;
    }

    public String getSurveyHouse() {
        return surveyHouse;
    }

    public void setSurveyHouse(String surveyHouse) {
        this.surveyHouse = surveyHouse;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(surveyHomeDist);
        dest.writeString(surveyName);
        dest.writeString(surveyEducation);
        dest.writeString(surveyDOB);
        dest.writeString(surveyArea);
        dest.writeString(surveyKids);
        dest.writeString(surveyHouse);
        dest.writeString(currentTime);
        dest.writeString(imagePath);
    }

    @Override
    public String toString() {
        return "SurveyModel{" +
                "id=" + id +
                ", surveyHomeDist='" + surveyHomeDist + '\'' +
                ", surveyName='" + surveyName + '\'' +
                ", surveyEducation='" + surveyEducation + '\'' +
                ", surveyDOB='" + surveyDOB + '\'' +
                ", surveyArea='" + surveyArea + '\'' +
                ", surveyKids='" + surveyKids + '\'' +
                ", surveyHouse='" + surveyHouse + '\'' +
                ", currentTime='" + currentTime + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
