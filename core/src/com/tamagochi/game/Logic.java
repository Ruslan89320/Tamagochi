package com.tamagochi.game;

public class Logic {
    public static final int AGE_HATCH = 12800;
    public static final int AGE_MATURE = 79600;
    public static final int AGE_DEATHFROMNATURALCAUSES = 819200;

    public static final int HUNGER_CANEAT = 4500;
    public static final int HUNGER_NEEDSTOEAT = 2000;
    public static final int HUNGER_SICKFROMNOTEATING = 1000;
    public static final int HUNGER_DEADFROMNOTEATING = 0;

    public static final int WASTE_EXPUNGE = 256;

    private int Age=0;
    private int Hunger=5000;
    private int Expunge=5000;
    private int Thirst=5000;
    private int Sleep=5000;
    private int Happiness=5000;

    public Logic(){}

    public void Cycle() {
        setExpunge(getExpunge()-1);
        setHunger(getHunger()-1);
        setThrist(getThrist()-1);
        setSleep(getSleep()-1);
        Age+=1;
        if(getExpunge()>= WASTE_EXPUNGE) setHappiness(getHappiness()-8);
    }

    public void doSleep(int sleep){
        setSleep(getSleep()+ sleep);
        if (getSleep()>5000){
            setSleep(5000);
        }
    }
    public void doEat(int eat){
        setHunger(getHunger()+ eat);
        if (getHunger()>5000){
            setHunger(5000);
        }
    }
    public void doDrink(int drink){
        setThrist(getThrist()+ drink);
        if (getThrist()>5000){
            setThrist(5000);
        }
    }
    public void doWaste(){
        setExpunge(getExpunge()+ 4000);
        if (getExpunge()>5000){
            setExpunge(5000);
        }
    }
    public void doHappiness(int happiness){
        setHappiness(getHappiness()+ happiness);
        if (getHappiness()>5000){
            setHappiness(5000);
        }
    }

    public void setHunger(int hunger){
        this.Hunger=hunger;
    }
    public void setExpunge(int expunge){
        this.Expunge=expunge;
    }
    public void setThrist(int thirst){
        this.Thirst=thirst;
    }
    public void setSleep(int sleep){
        this.Sleep=sleep;
    }
    public void setHappiness(int happiness){
        this.Happiness=happiness;
    }
    public int getHunger(){
        return this.Hunger;
    }
    public int getExpunge(){
        return this.Expunge;
    }
    public int getThrist(){
        return this.Thirst;
    }
    public int getSleep(){
        return this.Sleep;
    }
    public int getHappiness(){
        return this.Happiness;
    }
}
