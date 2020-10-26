package org.electricuniverse.lab_31;

public interface FragmentTracker
{
    public void fragmentVisible(String s);
    public void goNext();
    public void goBack();
    public void saveNameAndLastName(String name, String lname);
    public void saveCityAndZip(String city, String zip);
    public void saveLanguage(String language);
    public void finished();

}