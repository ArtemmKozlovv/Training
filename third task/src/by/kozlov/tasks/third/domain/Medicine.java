package by.kozlov.tasks.third.domain;

public class Medicine {
    private String identity;
    private String name;
    private String pharm;
    private Group group;
    private Long versions;
    // Analogs
    private String analogFirst;
    private String analogSecond;
    // Certificate
    private Long number;
    private String date;
    // Package
    private Long numberOfTablets;
    private Long cost;

    private String dosage;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPharm() {
        return pharm;
    }

    public void setPharm(String pharm) {
        this.pharm = pharm;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getAnalogFirst() {
        return analogFirst;
    }

    public void setAnalogFirst(String analogFirst) {
        this.analogFirst = analogFirst;
    }

    public String getAnalogSecond() {
        return analogSecond;
    }

    public void setAnalogSecond(String analogSecond) {
        this.analogSecond = analogSecond;
    }

    public Long getVersions() {
        return versions;
    }

    public void setVersions(Long versions) {
        this.versions = versions;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getNumberOfTablets() {
        return numberOfTablets;
    }

    public void setNumberOfTablets(Long numberOfTablets) {
        this.numberOfTablets = numberOfTablets;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return "Medicine " + identity + " : \n" +
                "\tname : " + name + '\n'+
                "\tpharm : " + pharm + '\n'+
                "\tgroup :" + group + '\n'+
                "\tanalogFirst :" + analogFirst + '\n'+
                "\tanalogSecond :" + analogSecond + '\n'+
                "\tversions :" + versions + '\n'+
                "\tnumber :" + number + '\n'+
                "\tdate :" + date + '\n'+
                "\tnumberOfTablets :" + numberOfTablets + '\n'+
                "\tcost :" + cost + '\n'+
                "\tdosage :" + dosage + '\n';
    }
}

