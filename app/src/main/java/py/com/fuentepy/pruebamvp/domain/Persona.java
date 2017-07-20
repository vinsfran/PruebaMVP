package py.com.fuentepy.pruebamvp.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vinsfran on 20/07/17.
 */

public class Persona implements Parcelable {

    public static final String ID_KEY = "id";
    public static final String EH_FAVORITO_KEY = "eh-favorito";

    private int id;

    public String gender;

    public String mail;

    public String phone;

    public String cell;

    public Persona() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.gender);
        dest.writeString(this.mail);
        dest.writeString(this.phone);
        dest.writeString(this.cell);
    }

    protected Persona(Parcel in) {
        this.id = in.readInt();
        this.gender = in.readString();
        this.mail = in.readString();
        this.phone = in.readString();
        this.cell = in.readString();
    }

    public static final Creator<Persona> CREATOR = new Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel source) {
            return new Persona(source);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };
}
