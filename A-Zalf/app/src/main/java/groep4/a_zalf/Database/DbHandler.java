package groep4.a_zalf.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import groep4.a_zalf.Collection.Diagnose;
import groep4.a_zalf.Collection.Patient;

/**
 * Created by brunodelsing on 12/17/15.
 */
public class DbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ziekenhuis.db";
    private static final String TABLE_PATIENTEN = "patienten";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PATIENTNR = "patientNr";
    public static final String COLUMN_NAAM = "naam";
    public static final String COLUMN_GEBOORTEDATUM = "geboorteDatum";
    public static final String COLUMN_WACHTWOORD = "wachtwoord";

    private static final String TABLE_DIAGNOSE = "diagnose";

    public static final String COLUMN_DIAGNOSE = "diagnose";
    public static final String COLUMN_PRESCRIPTIE = "prescriptie";
    public static final String COLUMN_INNAME = "inname";


    public DbHandler(Context context, String name,
                     SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PATIENTEN_TABLE = "CREATE TABLE " +
                TABLE_PATIENTEN + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_PATIENTNR + " INTEGER,"
                + COLUMN_NAAM + " TEXT,"
                + COLUMN_GEBOORTEDATUM + " TEXT,"
                + COLUMN_WACHTWOORD + " TEXT" + ")";
        db.execSQL(CREATE_PATIENTEN_TABLE);

        String CREATE_DIAGNOSE_TABLE = "CREATE TABLE " +
                TABLE_DIAGNOSE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_PATIENTNR + " INTEGER,"
                + COLUMN_DIAGNOSE + " TEXT,"
                + COLUMN_PRESCRIPTIE + " TEXT,"
                + COLUMN_INNAME + " TEXT" + ")";
        db.execSQL(CREATE_DIAGNOSE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTEN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIAGNOSE);
        onCreate(db);
    }

    public void add(Patient patient) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENTNR, patient.getPatientNr());
        values.put(COLUMN_NAAM, patient.getNaam());
        values.put(COLUMN_GEBOORTEDATUM, patient.getGeboorteDatum().toString());
        values.put(COLUMN_WACHTWOORD, patient.getWachtwoord());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PATIENTEN, null, values);
        db.close();
    }

    public void addDiagnose(String diagnose, String prescriptie, String inname, int patientNr) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENTNR, patientNr);
        values.put(COLUMN_DIAGNOSE, diagnose);
        values.put(COLUMN_PRESCRIPTIE, prescriptie);
        values.put(COLUMN_INNAME, inname);

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DIAGNOSE, null, null);

        db.insert(TABLE_DIAGNOSE, null, values);
        db.close();
    }

    public boolean isEmpty(String TABLE_NAME) {
        String query = "SELECT count(*) FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        return cursor.getInt(0) > 0;
    }

    public Diagnose findDiagnoseBy(String patientNr) {
        Diagnose diagnose;
        String query = "Select * FROM " + TABLE_DIAGNOSE + " WHERE " + COLUMN_PATIENTNR + " = " + patientNr;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        try {
            if (cursor.moveToFirst()) {
                diagnose =  new Diagnose(cursor.getString(2), cursor.getString(3), cursor.getString(4));
                cursor.close();
                return diagnose;
            } else {
                return null;
            }
        } finally {
            db.close();
        }
    }

    public boolean findPatientBy(String patientNr, String wachtwoord) {
        String query = "Select * FROM " + TABLE_PATIENTEN + " WHERE " + COLUMN_PATIENTNR + " = " + patientNr +  " AND " + COLUMN_WACHTWOORD + " = " + wachtwoord;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        try {
            if (cursor.moveToFirst()) {
                cursor.close();
                return true;
            } else {
                return false;
            }
        } finally {
            db.close();
        }
    }

    public Patient findPatientBy(String patientNr) {
        Patient patient = null;
        String query = "Select * FROM " + TABLE_PATIENTEN + " WHERE " + COLUMN_PATIENTNR + " = " + patientNr;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            patient = new Patient(cursor.getString(2), cursor.getString(4), cursor.getInt(1));
            cursor.close();
        }

        db.close();
        return patient;
    }
}
