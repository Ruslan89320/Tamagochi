package com.tamagochi.game;

public class GameData {
    private int Hunger,Thrist,Expunge, Sleep, Happines, Age ,Date;
    private float musicVolume;

    public void setAll(Logic logic){
        setHunger(logic.getHunger());
        setHappines(logic.getHappiness());
        setSleep(logic.getSleep());
        setExpunge(logic.getExpunge());
        setThist(logic.getThrist());
        setAge(logic.getAge());
    }

    public float getmusicVolume(){return musicVolume;}
    public void setmusicVolume(float musicvolume){this.musicVolume = musicvolume;}

    public int getDate(){return Date;}
    public void setDate(int date){this.Date = date;}

    public int getHunger() { return Hunger; }
    public void setHunger(int hunger) { this.Hunger = hunger; }


    public int getThist() {return Thrist; }
    public void setThist(int thrist) { this.Thrist = thrist; }


    public int getExpunge() { return Expunge; }
    public void setExpunge(int expunge) { this.Expunge = expunge; }


    public int getSleep() { return Sleep; }
    public void setSleep(int sleep) { this.Sleep = sleep; }


    public int getHappines() { return Happines; }
    public void setHappines(int happines) { this.Happines = happines; }


    public int getAge() { return Age; }
    public void setAge(int age) { this.Age = age; }

}
