package com.example.recyclerviewpractice.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class StudentModel extends RealmObject implements Parcelable{
    @PrimaryKey
    private int studentId;
    private String studentName, studentMail, studentPhone, studentDept, studentDateOfBirth,divisonadd;

    public StudentModel() {
    }

    public StudentModel(int studentId, String studentName, String studentMail, String studentPhone, String studentDept, String studentDateOfBirth, String divisonadd) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentMail = studentMail;
        this.studentPhone = studentPhone;
        this.studentDept = studentDept;
        this.studentDateOfBirth = studentDateOfBirth;
        this.divisonadd = divisonadd;
    }

    protected StudentModel(Parcel in) {
        studentId = in.readInt();
        studentName = in.readString();
        studentMail = in.readString();
        studentPhone = in.readString();
        studentDept = in.readString();
        studentDateOfBirth = in.readString();
        divisonadd = in.readString();
    }

    public static final Creator<StudentModel> CREATOR = new Creator<StudentModel>() {
        @Override
        public StudentModel createFromParcel(Parcel in) {
            return new StudentModel(in);
        }

        @Override
        public StudentModel[] newArray(int size) {
            return new StudentModel[size];
        }
    };

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMail() {
        return studentMail;
    }

    public void setStudentMail(String studentMail) {
        this.studentMail = studentMail;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getStudentDept() {
        return studentDept;
    }

    public void setStudentDept(String studentDept) {
        this.studentDept = studentDept;
    }

    public String getStudentDateOfBirth() {
        return studentDateOfBirth;
    }

    public void setStudentDateOfBirth(String studentDateOfBirth) {
        this.studentDateOfBirth = studentDateOfBirth;
    }

    public String getDivisonadd() {
        return divisonadd;
    }

    public void setDivisonadd(String divisonadd) {
        this.divisonadd = divisonadd;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentMail='" + studentMail + '\'' +
                ", studentPhone='" + studentPhone + '\'' +
                ", studentDept='" + studentDept + '\'' +
                ", studentDateOfBirth='" + studentDateOfBirth + '\'' +
                ", divisonadd='" + divisonadd + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(studentId);
        dest.writeString(studentName);
        dest.writeString(studentMail);
        dest.writeString(studentPhone);
        dest.writeString(studentDept);
        dest.writeString(studentDateOfBirth);
        dest.writeString(divisonadd);
    }
}
